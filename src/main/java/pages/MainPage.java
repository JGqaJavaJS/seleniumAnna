package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

    public By HEADER_MENU_BTN = By.xpath("//button[@data-testid='header-member-menu-button']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // locators
    // function for this page like - get text click...
    // validations
    // methods with logic for page testing
}
