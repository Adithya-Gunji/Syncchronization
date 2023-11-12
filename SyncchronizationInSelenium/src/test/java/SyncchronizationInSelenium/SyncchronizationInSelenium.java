package SyncchronizationInSelenium;

import java.util.concurrent.TimeUnit;

import org.junit.validator.PublicClassValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SyncchronizationInSelenium {
	public static WebDriver driver;
	public static void main(String[] args) {
    	WebDriverManager.edgedriver().setup();// To Avoid version control problem(Browser update)
    	driver=new EdgeDriver();
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    	//implicit wait always applied globally is applicable for all web elements
    	//Dynamic in nature //it can be changed anywhere and at any time in your code
    	driver.get("https://www.facebook.com");

    	WebElement email =driver.findElement(By.name("email"));
    	WebElement password=driver.findElement(By.name("pass"));
    	sendKeys(driver, email, 10, "adithya.gunji@gmail.com");
    	sendKeys(driver, password, 5, "adithya123@");
    	
    	WebElement forgotAccount= driver.findElement(By.xpath("//div[1]/div/div/div/div[2]/div/div[1]/form/div[3]/a"));
    	clickOn(driver, forgotAccount, 5);//2 sec utilize 3 sec ignored with this explicit wait
	}
	//Explicit wait
	//1. no explicit keyword or Method
	//2. available with webDriver wait with some expected conditions
	//3. Specific to element 
	//4. Dynamic in nature
	//5. we should never use implicit and explicit waits together: selenium WD will wait for the element first 
	//   because of implicitly wait will be applied hence, total sync wait will be increased for each element
   public static void sendKeys(WebDriver driver, WebElement element,int timeout,String value) {
	   
	   new WebDriverWait(driver, timeout).
	   until(ExpectedConditions.visibilityOf(element));
	   element.sendKeys(value);
   }
   
  public static void clickOn(WebDriver driver, WebElement element,int timeout) {
	   
	   new WebDriverWait(driver, timeout).
	   until(ExpectedConditions.elementToBeClickable(element));
	   element.click();;
   }
}
