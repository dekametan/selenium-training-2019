import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    static WebDriver driver;

    @BeforeClass
    public static void startBrowser() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
