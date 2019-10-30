import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomeWork2 extends TestBase{
    private String targetUrl = "http://demo.litecart.net";

    @Test
    public void chromeTest(){
        driverChrome.get(targetUrl);

        WebDriverWait wait = new WebDriverWait(driverChrome, 2);
        wait.until((WebDriver d) -> d.findElement(By.cssSelector(".product-column")));
        testMain(driverChrome);
    }

    @Test
    public void fireFoxTest(){
        driverFireFox.get(targetUrl);

        WebDriverWait wait = new WebDriverWait(driverFireFox, 5);
        wait.until((WebDriver d) -> d.findElement(By.className("product-column")));
        testMain(driverFireFox);
    }

    @Test
    public void IETest(){
        driverIE.get(targetUrl);

        JavascriptExecutor js = (JavascriptExecutor) driverIE;
        js.executeScript("window.scroll(0, 800)", "");

        WebDriverWait wait = new WebDriverWait(driverChrome, 2);
//        wait.until((WebDriver d) -> d.findElement(By.className("product-column")));
        testMain(driverIE);
    }

    private void testMain(WebDriver driver){
        WebElement firstElementHomePage = driver.findElements(By.cssSelector(".product-column")).get(0);

        String regularPriceHomePageValue = firstElementHomePage.findElement(By.cssSelector(".regular-price")).getText();
        String regularPriceHomePageColor = firstElementHomePage.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String regularPriceHomePageElementTagName = firstElementHomePage.findElement(By.cssSelector(".regular-price")).getTagName();
        Assert.assertEquals("del", regularPriceHomePageElementTagName);

        String campaignPriceHomePageValue = firstElementHomePage.findElement(By.cssSelector(".campaign-price")).getText();
        String campaignPriceHomePageColor = firstElementHomePage.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignPriceHomePageElementTagName = firstElementHomePage.findElement(By.cssSelector(".campaign-price")).getTagName();
        Assert.assertEquals("strong", campaignPriceHomePageElementTagName);

        firstElementHomePage.click();

        WebElement productPageItem = driver.findElement(By.id("#box-product"));
        String regularPriceProductPageValue = productPageItem.findElement(By.cssSelector(".regular-price")).getText();
        String regularPriceProductPageColor = productPageItem.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String regularProductPageElementTagName = productPageItem.findElement(By.cssSelector(".regular-price")).getTagName();
        Assert.assertEquals("del", regularProductPageElementTagName);

        String campaignPriceProductPageValue = productPageItem.findElement(By.cssSelector(".campaign-price")).getText();
        String campaignPriceProductPageColor = productPageItem.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignProductPageElementTagName = productPageItem.findElement(By.cssSelector(".campaign-price")).getTagName();
        Assert.assertEquals("strong", campaignProductPageElementTagName);

        Assert.assertEquals(regularPriceHomePageValue, regularPriceProductPageValue);
        Assert.assertEquals(regularPriceHomePageColor, regularPriceProductPageColor);

        Assert.assertEquals(campaignPriceHomePageValue, campaignPriceProductPageValue);
        Assert.assertEquals(campaignPriceHomePageColor, campaignPriceProductPageColor);
    }
}
