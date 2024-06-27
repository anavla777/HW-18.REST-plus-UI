package api.authorization;

import com.codeborne.selenide.WebDriverRunner;
import config.App;
import io.qameta.allure.Step;
import models.AuthRequestDTO;
import models.AuthResponseDTO;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.DemoqaSpec.demoqaOkResponseSpec;
import static specs.DemoqaSpec.demoqaRequestSpec;

public class AuthApi {
    @Step("Get userid and token")
    public static AuthResponseDTO authorization() {
        AuthRequestDTO authData = new AuthRequestDTO();
        authData.setUserName(App.config.userLogin());
        authData.setPassword(App.config.userPassword());

        return (given(demoqaRequestSpec)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(demoqaOkResponseSpec)
                .extract().as(AuthResponseDTO.class));
    }

    @Step("Auth in browser via cookie")
    public static void setCookiesInBrowser(AuthResponseDTO authResponse) {
        open("/images/Toolsqa.jpg");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        open("");
    }

    public static String extactValueFromCookies(String str) {
        String cookieValue = String.valueOf(WebDriverRunner.getWebDriver().manage().getCookieNamed(str));
        return cookieValue.substring(cookieValue.indexOf("=") + 1, cookieValue.indexOf(";"));
    }
}
