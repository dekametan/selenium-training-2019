import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class FirstClass extends TestBase {

    @Test
    public void testFirst(){
        driver.get("http://demo.litecart.net/admin/");

        Wait<WebDriver> wait = new WebDriverWait(driver, 5).withMessage("Element was not found");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button.btn"))));
        driver.findElement(By.cssSelector("button.btn")).click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int menuItems = driver.findElements(By.className("app")).size();
        System.out.println("Menu items number: " + menuItems);

        for (int i = 0; i < menuItems; i++) {
            System.out.println("Menu item -> " + i);
            WebElement menuItem = driver.findElements(By.className("app")).get(i);
            menuItem.click();
            menuItem = driver.findElements(By.className("app")).get(i);
            List <WebElement> subItems = menuItem.findElements(By.cssSelector("li.doc"));
            System.out.println("Menu subitems number: " + subItems.size());
            System.out.println("---------------------------");
            for (int j = 0; j < subItems.size(); j++) {
                menuItem = driver.findElements(By.className("app")).get(i);
                subItems = menuItem.findElements(By.cssSelector("li.doc"));
                System.out.println("Menu subitem -> " + j);
                WebElement subItem = subItems.get(j);
                subItem.click();
                try {
                    driver.findElement(By.cssSelector("div.panel-heading"));
                    System.out.println(String.format("Header is present for %s and subitem %s", i, j));
                    System.out.println("---------");
                } catch (NoSuchElementException e) {
                    throw new NoSuchElementException(String.format("No header is present for %s and subitem %s", i, j));
                }
            }
        }
    }
}
