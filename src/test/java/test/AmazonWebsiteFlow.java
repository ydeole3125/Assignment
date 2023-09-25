package test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Parameters;

import pageObjectClasses.AmazonHomePage_PageObject;
import pageObjectClasses.GoogleHomePage_PageObject;
import pageObjectClasses.ProductHomePage_PageObject;
import utility.PropertiesClass;

public class AmazonWebsiteFlow {

	static String driverPath;
	AmazonHomePage_PageObject amazonPageObject;
	GoogleHomePage_PageObject googleHomePage_PageObject;
	ProductHomePage_PageObject productHomePage_PageObject;
	String cardValidation;
	WebDriver driver;
	PropertiesClass propertiesClass = new PropertiesClass();
	Properties prop;

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) {
		
		prop = propertiesClass.loadPropertiesFile();
		 
		  if(browser.equalsIgnoreCase("chrome")) {
			   System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			   driver = new ChromeDriver();   
		  }
		  
		  else if (browser.equalsIgnoreCase("firefox")) { 
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			   driver = new FirefoxDriver();      		 
		  } 
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		
 		
	}
	
	
	@Test
	public void amazonWebsiteFlow() throws InterruptedException {

		SoftAssert softAssertion = new SoftAssert();
		googleHomePage_PageObject = PageFactory.initElements(driver, GoogleHomePage_PageObject.class);
		driver = googleHomePage_PageObject.googleSearch(driver);
		amazonPageObject = PageFactory.initElements(driver, AmazonHomePage_PageObject.class);
		driver = amazonPageObject.searchDetails(driver);
		softAssertion.assertEquals(amazonPageObject.validatePrice(),true,"Price Range Validated");
		driver = amazonPageObject.displayFiveStarRatedProduct();
		productHomePage_PageObject = PageFactory.initElements(driver, ProductHomePage_PageObject.class);
		productHomePage_PageObject.addToWishList(driver);
		softAssertion.assertAll();
	}
	
	@AfterTest
	public void afterTest()
	{
		driver.quit();
	}

}
