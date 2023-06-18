package com.automation.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.POMBasePage;

public class AccountsPage extends POMBasePage {

	@FindBy(xpath = "//input[@name='new']")
	WebElement new_account;

	@FindBy(xpath = "//*[@id='ep']/div[1]/table/tbody/tr/td[1]/h2")
	WebElement acc_details;

	public AccountsPage(WebDriver driver) {
		super(driver);
	}

	public WebDriver createNewAAccount() {
		if (new_account.isDisplayed())
			ClickElement(new_account, "New Account");
		else
			System.out.println("element not found");
		return driver;
	}

	public String accountDetails() {
		String acc_Details = GetText(acc_details);
		return acc_Details;
	}
}
