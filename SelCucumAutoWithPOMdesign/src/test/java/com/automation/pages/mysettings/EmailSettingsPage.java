package com.automation.pages.mysettings;

import java.awt.Robot;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.POMBasePage;
import com.automation.utility.POMPropertiesUtility;

public class EmailSettingsPage extends POMBasePage{

	public POMPropertiesUtility propUtility = new POMPropertiesUtility();
	public Properties propertyFile = propUtility.loadFile("salesforceproperties");
	String username = propUtility.getPropertyValue("sf.valid.username");
	String password = propUtility.getPropertyValue("sf.valid.password");
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public static Robot robot = null;
	
	@FindBy(id = "sender_name")
	WebElement senderName;
	@FindBy(id = "sender_email")
	WebElement senderEmail;
	@FindBy(id = "auto_bcc1")
	WebElement autoBccYes;
	@FindBy(id = "auto_bcc0")
	WebElement autoBccNo;

	@FindBy(name = "save")
	WebElement saveEmailSettings;
	@FindBy(xpath = "//*[@id='meSaveCompleteMessage']/table/tbody/tr/td[2]/div")
	WebElement saveMessage;
	
	public EmailSettingsPage(WebDriver driver) {
		super(driver);		
	}
	
	public void enterSenderName() {
		if (senderName.isDisplayed())
			EnterText(senderName, "Sender Name", username);
		else
			log.info("Sender Name Element is Not displayed");
	}

	public void enterSenderEmail() {
		if (senderEmail.isDisplayed())
			EnterText(senderName, "Sender Name", password);
		else
			log.info("Sender Email lement is Not displayed");
	}

	public void autoBccYes() {
		if (!autoBccYes.isSelected()) {
			autoBccYes.click();
			log.info("Automatic Bcc type selected as YES");
		} else
			log.info("Automatic Bcc type selected as NO");
	}

	public void saveEmailSettings() {
		if (saveEmailSettings.isDisplayed())
			ClickElement(saveEmailSettings, "Save the Tab");
		else
			System.out.println("Save button is not found");
	}

	public void getSuccessMsg() {
		if (saveMessage.isDisplayed())
			GetText(saveMessage);
		else
			log.info("Success Message is Not displayed");
	}

}
