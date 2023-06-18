package com.automation.pages.mysettings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;
import java.awt.Robot;
import java.util.Properties;
import com.automation.pages.base.POMBasePage;
import com.automation.utility.POMPropertiesUtility;

public class MySettingsPage extends POMBasePage {

	JavascriptExecutor js = (JavascriptExecutor) driver;
	public static Robot robot = null;

	public POMPropertiesUtility propUtility = new POMPropertiesUtility();
	public Properties dataPropFile = propUtility.loadFile("salesforcedataproperties");
	public String lastNameProp = propUtility.getPropertyValue("user.lastname");
//	String username = propUtility.getPropertyValue("sf.valid.username");

	String custAppOption = propUtility.getPropertyValue("custApp.name");
	String availTabText = propUtility.getPropertyValue("availtab.name");

	@FindBy(xpath = "//*[@id=\"PersonalInfo\"]/a")
	WebElement personalTab; //

	@FindBy(xpath = "//*[@id=\"DisplayAndLayout_font\"]")
	WebElement dispLayout;

	@FindBy(xpath = "//*[@id='EmailSetup_font']")
	WebElement emailSetup;
	@FindBy(id = "EmailSettings_font")
	WebElement myEmailSettings;
	@FindBy(id = "EmailTemplates_font")
	WebElement emailTemplate;
	@FindBy(id = "MassMails_font")
	WebElement massEmail;

	@FindBy(id = "CalendarAndReminders_font")
	WebElement calAndReminder;
	@FindBy(id = "Reminders_font")
	WebElement actReminder;

	@FindBy(id = "tsidButton")
	WebElement topRightDropdown;
	@FindBy(xpath = "//div[@id='tsid-menuItems']/a[6]")
	WebElement salesforceChatter;

	public MySettingsPage(WebDriver driver) {
		super(driver);
	}

	public void clickPersonalTab() {
		ExplicitWaitElement(personalTab);
		if (personalTab.isDisplayed())
			ClickElement(personalTab, "Personal Tab");
		else
			System.out.println("element not found");
	}

	public void clickDisplayLayout() throws InterruptedException {
		js.executeScript("arguments[0].click();", dispLayout);
		log.info("Display & Layout is clicked");
		Thread.sleep(3000);
	}

	public void clickTopRightDropdown() {
		ExplicitWaitElement(topRightDropdown);
		if (topRightDropdown.isDisplayed())
			ClickElement(topRightDropdown, "Top Right Drop Down");
	}

	public void clickSalesForceChatterMenu() throws InterruptedException {
		if (salesforceChatter.isDisplayed()) {
			Thread.sleep(3000);
			ClickElement(salesforceChatter, "Salesforce Chatter");
			Close_SwitchTo_Lightning_PopUp();
		} else
			System.out.println("SalesForce Chatter menu element not found");
	}

	public void clickEmail() {
		ExplicitWaitElement(emailSetup);
		if (emailSetup.isDisplayed())
			ClickElement(emailSetup, "Email");
		else
			log.info("Element is not found");
	}

	public void clickEmailSettings() {
		ExplicitWaitElement(myEmailSettings);
		if (myEmailSettings.isDisplayed())
			ClickElement(myEmailSettings, "My Email Settings");
		else
			log.info("Element is not found");
	}

	public void clickCalAndReminder() {
		ExplicitWaitElement(calAndReminder);
		if (calAndReminder.isDisplayed())
			ClickElement(calAndReminder, "Calender & Reminder");
		else
			log.info("calAndReminder element is not found");
	}

	public void clickActReminder() {
		if (actReminder.isDisplayed())
			ClickElement(actReminder, "Activity Reminder");
		else
			log.info("actReminder element is not found");
	}

}
