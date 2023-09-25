package pageObjectClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
public class AmazonHomePage_PageObject {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    WebElement searchDropdownBox;

    @FindBy(xpath = "//option[@value='search-alias=electronics']")
    WebElement electronicsOption;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(id = "low-price")
    WebElement lowPriceSpan;

    @FindBy(id = "high-price")
    WebElement highPriceSpan;

    @FindBy(className = "a-button-input")
    WebElement goButton;

    @FindBy(xpath = "//span[@class = 'a-price-whole']")
    List<WebElement> priceListPageOne;

    @FindBy(xpath = "//a[@aria-label = 'Go to page 2']")
    WebElement pageTwo;

    @FindBy(xpath = "//a[@aria-label = 'Go to page 1']")
    WebElement pageOne;

    public WebDriver searchDetails(WebDriver driver) throws InterruptedException {
        int counter = 0;
        int listSize;
        this.driver = driver;
        searchDropdownBox.click();
        electronicsOption.click();
        searchBox.click();
        searchBox.sendKeys("dell computers");
        searchButton.click();
        lowPriceSpan.click();
        lowPriceSpan.sendKeys("30000");
        highPriceSpan.click();
        highPriceSpan.sendKeys("50000");
        goButton.click();
        Thread.sleep(2000);
        return driver;
    }

    public boolean validatePrice() throws InterruptedException {
        int counter = 0;
        int listSize;
        StringBuilder s;

        pageTwo.click();
        Thread.sleep(2000);
        List<WebElement> priceListPageTwo = driver.findElements(By.xpath("//span[@class = 'a-price-whole']"));
        listSize = priceListPageOne.size() + priceListPageTwo.size();
        for(WebElement price : priceListPageOne) {
            s = new StringBuilder(price.getText());
            s.deleteCharAt(2);
            if (Integer.parseInt(String.valueOf(s)) > 30000 && Integer.parseInt(String.valueOf(s)) < 50000)
                counter++;
        }
        for(WebElement price : priceListPageTwo) {
            s = new StringBuilder(price.getText());
            s.deleteCharAt(2);
            if (Integer.parseInt(String.valueOf(s)) > 30000 && Integer.parseInt(String.valueOf(s)) < 50000)
                counter++;
        }

        return counter == listSize;
    }

    public WebDriver displayFiveStarRatedProduct() throws InterruptedException {
        int listSize;
        pageOne.click();
        Thread.sleep(2000);
        List<WebElement> allProducts = driver.findElements(By.xpath("//span[text()='5.0 out of 5 stars']//preceding::span[@class = 'a-size-medium a-color-base a-text-normal'][1]"));
        for(WebElement product : allProducts)
            System.out.println(product.getText());
        pageTwo.click();
        Thread.sleep(2000);
        allProducts = driver.findElements(By.xpath("//span[text()='5.0 out of 5 stars']//preceding::span[@class = 'a-size-medium a-color-base a-text-normal'][1]"));
        for(WebElement product : allProducts)
            System.out.println(product.getText());
        pageOne.click();
        Thread.sleep(2000);
        allProducts = driver.findElements(By.xpath("//span[text()='5.0 out of 5 stars']//preceding::span[@class = 'a-size-medium a-color-base a-text-normal'][1]"));
        allProducts.get(0).click();
        return driver;
    }

}
