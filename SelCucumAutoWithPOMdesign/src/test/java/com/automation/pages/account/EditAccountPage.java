package com.automation.pages.account;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.automation.pages.base.POMBasePage;
import com.automation.utility.POMPropertiesUtility;


public class EditAccountPage extends POMBasePage {
	
	public POMPropertiesUtility propUtility = new POMPropertiesUtility();
	public Properties dataPropFile = propUtility.loadFile("salesforcedataproperties");
	String accNameProp = propUtility.getPropertyValue("acc.name");
	String accTypeProp = propUtility.getPropertyValue("acc.type");
	String custPriority = propUtility.getPropertyValue("cust.priority");
	
	@FindBy(xpath = "//input[@id='acc2']")
	WebElement acc_Name;
	@FindBy(xpath = "//select[@id='acc6']")
	WebElement acc_Type;
	@FindBy(xpath = "//*[@id='00NDp00000CTEGD']")
	WebElement cust_priority;

	@FindBy(xpath = "//input[@name='save']")
	WebElement save_btn;

	@FindBy(xpath = "//*[@id='ep']/div[1]/table/tbody/tr/td[1]/h2")
	WebElement acc_details;

	public EditAccountPage(WebDriver driver) {
		super(driver);
	}

	public void setAccountName() {
		if (acc_Name.isDisplayed())
			EnterText(acc_Name, "Account Name", accNameProp);
		else
			System.out.println("Account name element is Not displayed");
	}

	public void setAccountType() {
		Select select_AccType = new Select(acc_Type);
		select_AccType.selectByVisibleText(accTypeProp);
	}

	public void setCustPriority() {
		Select select_CustPriority = new Select(cust_priority);
		select_CustPriority.selectByVisibleText(custPriority);
	}

	public WebDriver clickSaveAcc() {
		if (acc_Name.isDisplayed())
			ClickElement(save_btn, "Save Account");
		else
			System.out.println("Save button element is Not displayed");
		return driver;
	}

}
