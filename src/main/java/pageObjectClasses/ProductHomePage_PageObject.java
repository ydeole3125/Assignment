package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductHomePage_PageObject {

    WebDriver driver;

    public void addToWishList(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (int i =0;i< iframes.size();i++) {
            driver.switchTo().frame(i);
            List<WebElement> wishlist = driver.findElements(By.xpath("//a[@title = 'Add to Wish List']"));
            if (!wishlist.isEmpty())
                driver.findElement(By.xpath("//a[@title = 'Add to Wish List']")).click();
            driver.switchTo().defaultContent();
        }
    }
}
