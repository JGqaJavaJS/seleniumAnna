import org.testng.annotations.Test;

public class MainPageTests extends BaseTest{

    @Test
    public void startTest() {
        System.out.println("-------------" + app.getMainPage().getTextH5ByIndex());
    }

}
