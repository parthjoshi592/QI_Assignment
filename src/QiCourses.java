import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class QiCourses {
	public static WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Parth\\eclipse-workspace\\libs\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String url = "https://quantra.quantinsti.com";
		report = ExtentFactory.getInstance();
		test = report.startTest("QI Assignment");
		driver.get(url);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Application opened");
		login();
		findCourse();
		cartPageTest();
		driver.quit();
		report.endTest(test);
	}
	
	public static void login() {
		driver.findElement(By.xpath("//li[contains(@class, 'signup-link')]")).click();
		driver.findElement(By.name("email")).sendKeys("parth.joshi592@gmail.com");
		driver.findElement(By.name("password")).sendKeys("testQi123");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		test.log(LogStatus.INFO, "Login successful");
	}
	
	public static void findCourse() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@class='link' and @href='/courses']"))).perform();
		driver.findElement(By.xpath("//a[@href='/learning-track/sentiment-analysis-trading']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='course-detail__left-view']/h1")).getText());
		System.out.println(driver.findElement(By.xpath("//div[contains(@class, 'price-data-unit')]//span[@class='cd__data-unit__striked']/following-sibling::span/span")).getText());
		driver.findElement(By.xpath("//div[@class='course-detail__buttons']//span[contains(text(), 'Enroll Now')]")).click();
		test.log(LogStatus.INFO, "Clicked on Enroll Now");
	}
	
	public static void cartPageTest() {
		List<WebElement> listOfCourses = driver.findElements(By.xpath("//div[@class='cart-header']/following-sibling::div"));
		Assert.assertEquals(listOfCourses.size(), driver.findElement(By.className("cart-count")).getText());
		test.log(LogStatus.PASS, "Verified number of courses on cart page");
		
		System.out.println(driver.findElement(By.xpath("//div[text()='Base Amount']/following-sibling::div")).getText());
		System.out.println(driver.findElement(By.xpath("//div[contains(@class, 'amt-payable-wrap')]//h5/span")).getText());
		
		driver.findElement(By.xpath("//a[@href='/course/python-for-trading' and text()='View Details']")).click();
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		
		driver.findElement(By.xpath("//a[@href='/course/python-for-trading']/following-sibling::a")).click();
		System.out.println(driver.findElement(By.xpath("//div[contains(@class, 'toasted-primary')]")).getText());
		test.log(LogStatus.INFO, "Removed a course");
		
		driver.findElement(By.xpath("//span[text()='Apply Coupon']")).click();
		driver.findElement(By.xpath("//div[@class='coupon-form__unit']/input")).sendKeys("ABC");
		driver.findElement(By.xpath("//div[@class='coupon-form__button']//span[text()='Apply']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='coupon-alert-box']//span")).getText());
		driver.findElement(By.xpath("//div[contains(@class,'modal-dialog')]//button[@class='close']")).click();
		test.log(LogStatus.INFO, "Checked Apply Coupon functionality");
		
		driver.findElement(By.xpath("//div[@class='profile-pic-initials']")).click();
		driver.findElement(By.xpath("//li[contains(@class,'logout')]/a")).click();
		test.log(LogStatus.INFO, "Logged out");
	}
}
