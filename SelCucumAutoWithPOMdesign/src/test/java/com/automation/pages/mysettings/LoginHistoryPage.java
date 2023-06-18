package com.automation.pages.mysettings;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.POMBasePage;

public class LoginHistoryPage extends POMBasePage{
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public static Robot robot = null;
	
	@FindBy(xpath = "//*[@id='LoginHistory_font']/span")
	WebElement loginHistory;

	@FindBy(xpath = "//div[@class='pShowMore']/a")
	WebElement downloadHistory;
	
	
	public void clickLoginHistory() throws InterruptedException {
		Thread.sleep(3000);

		js.executeScript("arguments[0].click();", loginHistory);
		System.out.println("Login History is clicked");
		log.info("Login History is clicked");
		Thread.sleep(5000);
	}

	public void clickDownloadHistory() throws InterruptedException, AWTException {
		Thread.sleep(3000);
		robot = new Robot();
		robot.mouseWheel(6);
		ExplicitWaitElement(downloadHistory);
		js.executeScript("arguments[0].click();", downloadHistory);

	}
	
	public LoginHistoryPage(WebDriver driver) {
		super(driver);		
	}

}
