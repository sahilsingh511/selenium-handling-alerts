package com.selenium.automation.testng;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class HandleAlertsTest {

	WebDriver driver = null;

	@Parameters("browserName")
	@BeforeTest
	public void openBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();

		// Apply Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

	@Test(priority = 1)
	public void navigateToURL() {
		String url = "https://demo.automationtesting.in/Alerts.html";
		driver.get(url);
	}

	@Test(priority = 2)
	public void hoverOverMenu() {
		// Perform hover over 'SwitchTo' menu to toggle drop down menu
		WebElement dropdown = driver.findElement(By.className("dropdown-toggle"));
		Actions action = new Actions(driver);
		action.moveToElement(dropdown).perform();
	}

	@Test(priority = 3)
	public void selectAlertsOption() {
		// Select Alerts option from drop down menu
		List<WebElement> menu = driver.findElements(By.cssSelector(".dropdown-menu li"));
		for (WebElement item : menu) {
			if (item.getText().equals("Alerts")) {
				item.click();
				break;
			}
		}
	}

	@Test(priority = 4)
	public void handleSimpleAlert() throws InterruptedException {
		// Handling Simple Alert Box
		driver.findElement(By.xpath("//div/div[1]/ul/li[1]/a")).click();
		driver.findElement(By.id("OKTab")).click();
		Alert simpleAlert = driver.switchTo().alert();
		System.out.println(simpleAlert.getText());
		Thread.sleep(2000);
		simpleAlert.accept();
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void handleConfirmationAlert() throws InterruptedException {
		// Handling Confirmation Alert Box
		driver.findElement(By.xpath("//div/div[1]/ul/li[2]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("CancelTab")).click();
		Alert confirmAlert = driver.switchTo().alert();
		System.out.println(confirmAlert.getText());
		Thread.sleep(2000);
		confirmAlert.dismiss();
		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void handlePromptAlert() throws InterruptedException {
		// Handling Prompt Alert Box
		driver.findElement(By.xpath("//div/div[1]/ul/li[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("Textbox")).click();
		Alert promptAlert = driver.switchTo().alert();
		promptAlert.sendKeys("Sahil");
		System.out.println(promptAlert.getText());
		Thread.sleep(2000);
		promptAlert.accept();
		Thread.sleep(4000);
	}

}
