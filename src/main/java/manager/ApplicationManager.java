package manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.MainPage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    // changed for WD listener
    EventFiringWebDriver driver; // WebDriver driver;

    MainPage mainPage;

//    HelperLogin helperLogin;
//
//    HelperLogout helperLogout;
//
//    HelperMainPage helperMainPage;

    WebDriverWait wait;

    String browser;
    Properties properties;

    public ApplicationManager(String browser) {
        properties = new Properties();
        this.browser = browser;
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

//    public HelperLogin getHelperLogin() {
//        return helperLogin;
//    }
//
//    public HelperLogout getHelperLogout() {return helperLogout;}
//
//    public HelperMainPage getHelperMainPage() { return helperMainPage;}

    //  @BeforeSuite
    public void init() {

        //   with throw exception:
        //   properties.load(new FileReader(new File("src/test/resources/prod.properties")));

        String target = System.getProperty("target", "prod");
        String path = String.format("src/test/resources/%s.properties", target);

        //src/test/resources/%s.properties

        // System.setProperty("webdriver.gecko.driver","/Users/julia/Tools/geckodriver.exe");

        try (FileReader fr = new FileReader(new File(path))) {
            properties.load(fr);
        } catch (IOException e) {

        }

//        driver = new ChromeDriver();
        // driver = new EventFiringWebDriver(new ChromeDriver());

        if (browser.equals(BrowserType.CHROME)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            // changed for WD Listener
            driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions)); // without listener and logger just a WebDriver object
        } else if (browser.equals(BrowserType.FIREFOX)) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            driver = new EventFiringWebDriver(new FirefoxDriver(firefoxOptions));
        }


        driver.register(new WebDriverListener());
        mainPage = new MainPage(driver); // ObjectName someName = New ObjectName()
//        helperLogin = new HelperLogin(driver);
//        helperLogout = new HelperLogout(driver);
//        helperMainPage = new HelperMainPage(driver);
        driver.manage().window().maximize();
        driver.navigate().to(properties.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 90);

//        System.out.println("_________________________________________");
//        System.out.println(properties.getProperty("url"));
//        System.out.println(properties.getProperty("email"));
//        System.out.println(properties.getProperty("password"));
//        System.out.println("_________________________________________");

//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement element =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("elementId")));


    }

    public void tearDown(){
        driver.quit();
    }

}
