package tests;

import api.authorization.AuthApi;
import data.TestData;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.AddBookRequestDTO;
import pages.ProfilePage;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.DemoqaSpec.demoqaCreatedResponseSpec;
import static specs.DemoqaSpec.demoqaRequestSpec;

public class TestSteps {
    TestData testData = new TestData();
    ProfilePage steps = new ProfilePage();

    @Step("Add new book into list")
    public void addListOfBook() {
        AddBookRequestDTO bookData = new AddBookRequestDTO();
        String userID = AuthApi.extactValueFromCookies("userID");
        String token = AuthApi.extactValueFromCookies("token");
        bookData.setUserId(userID);
        bookData.setIsbn(testData.isbn);

        Response addBookResponse = given(demoqaRequestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(demoqaCreatedResponseSpec)
                .extract()
                .response();
        assertThat(bookData.getCollectionOfIsbns()
                .get(0).getIsbn(), equalTo(addBookResponse.path("books[0].isbn")));
    }

    @Step("Delete book from the list")
    public void deleteBookFromListBooks() {
        steps.openPage()
                .deleteBook()
                .confirmDeletion()
                .verifyDeletion();
    }
}
