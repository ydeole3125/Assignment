package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import java.util.List;

public class GoogleHomePage_PageObject {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy(name = "q")
    WebElement googleSearchSpan;

    @FindBy(className = "VuuXrf")
    List<WebElement> searchList;
    @FindBy(xpath = "//a[@jsname='UWckNb']")
    List<WebElement> searchLink;


    public WebDriver googleSearch(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        wait= new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(googleSearchSpan));
        googleSearchSpan.click();
        googleSearchSpan.sendKeys("amazon");
        googleSearchSpan.sendKeys(Keys.ENTER);
        for(WebElement list : searchList)
        {
            System.out.println(list.getText());
        }
        for(WebElement list : searchLink)
        {
            if(list.getAttribute("href").equals("https://www.amazon.in/"))
            {
                list.click();
                break;
            }
        }
        Thread.sleep(500);
        return driver;
    }
}
