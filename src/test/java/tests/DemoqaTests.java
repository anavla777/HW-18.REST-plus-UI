package tests;

import helpers.WithLogin;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Demoqa tests")
public class DemoqaTests extends TestBase {
    final TestSteps step = new TestSteps();

    @Test
    @DisplayName("Delete book from book list")
    @Owner("Ananenkov Vladislav")
    @WithLogin
    void deleteBookTest() {
        step.addListOfBook();
        step.deleteBookFromListBooks();
    }
}
