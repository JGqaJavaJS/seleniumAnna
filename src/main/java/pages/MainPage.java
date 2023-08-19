package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public By H5_TITLES = By.xpath("//h5");

    public String getTextH5ByIndex() {
        List<WebElement> h5s = getListWithElements(H5_TITLES);
        return getText(h5s.get(0));
    }

    // locators
    // function for this page like - get text click...
    // validations
    // methods with logic for page testing
}
