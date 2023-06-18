package com.automation.pages.base;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.basetest.POMBaseTest;

import com.automation.utility.POMPropertiesUtility;

public class POMBasePage {
	
	protected WebDriver driver;
	protected WebDriverWait waitObj;
	
	protected String s;
//	protected ExtentReportUtility report = ExtentReportUtility.getInstance();
	protected POMPropertiesUtility propUtility = new POMPropertiesUtility();
	protected Logger log = LogManager.getLogger(POMBaseTest.class.getName());
	
	public POMBasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void EnterText(WebElement element, String objName, String data) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			log.info("data is entered in the  objName  field");
//			report.logTestInfo("data is entered in the objName field");
		} else {
			log.error("Element is not displayed");
//			report.logTestFailed("Element is not displayed");
		}
	}

	public void RememberMeLogin(boolean rememberMe) {
		if (rememberMe == true) {
			WebElement RememberMe_Checkbox = driver.findElement(By.xpath("//*[@id='rememberUn']"));
			RememberMe_Checkbox.click();
		}
	}

	public void ClickElement(WebElement element, String objName) {
		if (element.isEnabled()) {
			element.click();
			log.info(objName + " is clicked");
//			report.logTestInfo(objName + " is clicked");
		}
	}

	public String GetText(WebElement element) {
		if (element.isDisplayed())
			s = element.getText();
		return s;
	}

	public String Get_Attribute(WebElement element) {
		s = element.getAttribute("type");
		return s;
	}

	public String GetPageTitle(WebDriver driver) {
		s = driver.getTitle();
		return s;
	}

	public void Verify_ElementDisplay(WebElement element, String data) {
		if (element.isDisplayed())
			log.info(data + " is displayed");
		else
			log.error(data + " is NOT displayed");
	}

	public void ExplicitWaitElement(WebElement element) {
		waitObj = new WebDriverWait(driver, 30);
		waitObj.until(ExpectedConditions.visibilityOf(element));
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void FluentWaitElement(WebElement element) {
		waitObj = new WebDriverWait(driver, 30);
		@SuppressWarnings("unchecked")
		Wait<WebDriver> waitObj = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
	}

	public void Alert_accept() {
		Alert alert = driver.switchTo().alert();
		// String exp_errorMsg = "Error : The password is invalid or the user does not
		// have a password.";
		String alertMessage = alert.getText();
		log.info(alertMessage);
		alert.accept();
	}

	public void Alert_dismiss() {
		Alert alert = driver.switchTo().alert();
		// String exp_errorMsg = "Error : The password is invalid or the user does not
		// have a password.";
		String alertMessage = alert.getText();
		log.info(alertMessage);
		alert.dismiss();
	}

	public void ValidLogin() {
		String username = propUtility.getPropertyValue("sf.valid.username");
		String password = propUtility.getPropertyValue("sf.valid.password");

		WebElement uName = driver.findElement(By.id("username"));
		EnterText(uName, "username", username);
		WebElement pWord = driver.findElement(By.id("password"));
		EnterText(pWord, "Password", password);
	}

	public void loginToWeb() {
		String username = propUtility.getPropertyValue("sf.valid.username");
		String password = propUtility.getPropertyValue("sf.valid.password");

		String actualTitle = "Login | Salesforce";
		String expectedTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle);

		WebElement uName = driver.findElement(By.id("username"));
		EnterText(uName, "username", username);
		WebElement pWord = driver.findElement(By.id("password"));
		EnterText(pWord, "Password", password);

		RememberMeLogin(false);
		WebElement btn_Login = driver.findElement(By.name("Login"));
		ClickElement(btn_Login, "Login");

	}

	public void Close_SwitchTo_Lightning_PopUp() // Closing the switch to lightning exp popup
	{
		WebElement noThanks_popup = driver.findElement(By.xpath("//*[@id='lexNoThanks']"));
		ExplicitWaitElement(noThanks_popup);
		ClickElement(noThanks_popup, "No Thanks");

		WebElement submit_popup = driver.findElement(By.xpath("//*[@id='lexSubmit']"));
		ExplicitWaitElement(submit_popup);
		ClickElement(submit_popup, "Send TO SalesForce");
	}
}
