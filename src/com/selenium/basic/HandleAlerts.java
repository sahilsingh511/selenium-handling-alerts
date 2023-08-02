package com.selenium.basic;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleAlerts {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Genius\\eclipse-workspace\\MiniProject\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demo.automationtesting.in/Alerts.html");

		// Perform hover over SwitchTo menu
		WebElement dropdown = driver.findElement(By.className("dropdown-toggle"));
		Actions action = new Actions(driver);
		action.moveToElement(dropdown).perform();

		// Select Alerts option from drop down menu
		List<WebElement> menu = driver.findElements(By.cssSelector(".dropdown-menu li"));
		for (WebElement item : menu) {
			if (item.getText().equals("Alerts")) {
				item.click();
				break;
			}
		}

		// Simple Alert Box
		driver.findElement(By.xpath("//div/div[1]/ul/li[1]/a")).click();
		driver.findElement(By.id("OKTab")).click();
		Alert simpleAlert = driver.switchTo().alert();
		System.out.println(simpleAlert.getText());
		Thread.sleep(2000);
		simpleAlert.accept();
		Thread.sleep(2000);


//		Confirmation Alert Box
		driver.findElement(By.xpath("//div/div[1]/ul/li[2]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("CancelTab")).click();
		Alert confirmAlert = driver.switchTo().alert();
		System.out.println(confirmAlert.getText());
		Thread.sleep(2000);
		confirmAlert.dismiss();
		Thread.sleep(2000);

//		Prompt Alert Box
		driver.findElement(By.xpath("//div/div[1]/ul/li[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("Textbox")).click();
		Alert promptAlert = driver.switchTo().alert();
		promptAlert.sendKeys("Sahil");
		System.out.println(promptAlert.getText());
		Thread.sleep(2000);
		promptAlert.accept();
		Thread.sleep(4000);	
		
		driver.quit();
	}

}