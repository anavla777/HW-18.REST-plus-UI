package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private static final SelenideElement
            deleteButton = $("#delete-record-undefined"),
            closeModalButton = $("#closeSmallModal-ok"),
            listArea = $(".rt-noData");

    @Step("Navigate to /profile")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Delete book from the list")
    public ProfilePage deleteBook() {
        deleteButton.click();
        return this;
    }

    @Step("Confirm deletion")
    public ProfilePage confirmDeletion() {
        closeModalButton.click();
        return this;
    }

    @Step("Check that deleted books are not present in the list")
    public void verifyDeletion() {
        listArea.shouldHave(text("No rows found"));
    }
}
