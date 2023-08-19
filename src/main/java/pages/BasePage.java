package pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
