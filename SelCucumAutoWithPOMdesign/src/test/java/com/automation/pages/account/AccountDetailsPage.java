package com.automation.pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.POMBasePage;

public class AccountDetailsPage extends POMBasePage {
	@FindBy(xpath = "//*[@id='ep']/div[1]/table/tbody/tr/td[1]/h2")
	WebElement acc_details;

	public AccountDetailsPage(WebDriver driver) {
		super(driver);
	}

	public String getAccDetailsText() {
		return GetText(acc_details);
	}
}
