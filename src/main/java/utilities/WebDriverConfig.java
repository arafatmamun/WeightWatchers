package utilities;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class WebDriverConfig {

	public static WebDriver myDriver = null;
	
	private static String fileSeparator = System.getProperty("file.separator"); 
	private static String currentDir = System.getProperty("user.dir");

	private static String chromeDriverPath = currentDir 
			+ fileSeparator
			+ "macChromeDriver" 
			+ fileSeparator 
			+ "chromedriver"; 
	
	public static String webURL = "file://" +currentDir + "/webPage/index.html";

	@BeforeSuite (alwaysRun = true)
	public void webDriverStart(){
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		myDriver = new ChromeDriver();

		myDriver.navigate().to(webURL);
		myDriver.manage().window().maximize();
		myDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	

	public static WebElement getElement ( String locator, String attributelocator){

		if(locator.equalsIgnoreCase("id")){
			return myDriver.findElement(By.id(attributelocator));
		}else if (locator.equalsIgnoreCase("className")){
			return myDriver.findElement(By.className(attributelocator));

		}else if(locator.equalsIgnoreCase("css")){
			return myDriver.findElement(By.cssSelector(attributelocator));
		}else if( locator.equalsIgnoreCase("Xpath")){
			return myDriver.findElement(By.xpath(attributelocator));

		}else if (locator.equalsIgnoreCase("name")){
			return myDriver.findElement(By.name(attributelocator));
		}else
			throw new NoSuchElementException("No Such Element:" + attributelocator);
	}
	
	public List<WebElement> getElements(String locator, String attributeOfLocator){
		List < WebElement > elements = null;
		if(locator.equalsIgnoreCase("id"))
		elements = myDriver.findElements(By.id(attributeOfLocator));
		else if(locator.equalsIgnoreCase("className"))
		elements = myDriver.findElements(By.className(attributeOfLocator));
		else if(locator.equalsIgnoreCase("name"))
		elements = myDriver.findElements(By.name(attributeOfLocator));
		else if(locator.equalsIgnoreCase("xpath"))
		elements = myDriver.findElements(By.xpath(attributeOfLocator));
		else if(locator.equalsIgnoreCase("css"))
		elements = myDriver.findElements(By.cssSelector(attributeOfLocator));
		else throw new NoSuchElementException(attributeOfLocator);
		return elements;
	}
	
	@AfterSuite ( alwaysRun = true)
	public void tearDown(){
		myDriver.close();
		myDriver.quit();
	}
}
