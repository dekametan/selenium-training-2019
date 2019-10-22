import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class FirstClass extends TestBase {

    @Test
    public void testFirst(){
        driver.get("http://demo.litecart.net/admin/");
        driver.findElement(By.cssSelector("button.btn")).click();

        // Time out added to wait for left panel to be completely rendered
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int menuItems = driver.findElements(By.className("app")).size();
        System.out.println("Menu items number: " + menuItems);

        for (int i = 0; i < menuItems; i++) {
            WebElement menuItem = driver.findElements(By.className("app")).get(i);
            menuItem.click();
            menuItem = driver.findElements(By.className("app")).get(i);
            List <WebElement> subMenuItems = menuItem.findElements(By.cssSelector("li.doc"));
            if (subMenuItems.size() == 0) {
                try {
                    driver.findElement(By.cssSelector("div.panel-heading"));
                    System.out.println(String.format("Header is present for menu item #%s (It hasn't sub-menu items)", i));
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    throw new NoSuchElementException(String.format("Header is not present for menu item #%s", i));
                }
            }
            else {
                for (int j = 0; j < subMenuItems.size(); j++) {
                    menuItem = driver.findElements(By.className("app")).get(i);
                    subMenuItems = menuItem.findElements(By.cssSelector("li.doc"));
                    WebElement subItem = subMenuItems.get(j);
                    subItem.click();
                    try {
                        driver.findElement(By.cssSelector("div.panel-heading"));
                        System.out.println(String.format("Header is present for menu item #%s and sub-menu item #%s", i, j));
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        throw new NoSuchElementException(String.format("Header is not present for menu item #%s and sub-menu item #%s", i, j));
                    }
                }
            }
        }
    }
}
