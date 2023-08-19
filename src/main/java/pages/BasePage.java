package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BasePage {

    Logger logger = LoggerFactory.getLogger(BasePage.class);

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<WebElement> getListWithElements(By by) {
        return driver.findElements(by);
    }

    public String getText(WebElement element) {
        return element.getText().trim().toUpperCase();
    }

}
