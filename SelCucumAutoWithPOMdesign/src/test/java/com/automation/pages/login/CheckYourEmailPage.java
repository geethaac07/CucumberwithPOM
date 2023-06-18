package com.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.POMBasePage;

public class CheckYourEmailPage extends POMBasePage {
	@FindBy(xpath = "//*[@id='forgotPassForm']/a")
	WebElement returnToLogin;

	public CheckYourEmailPage(WebDriver driver) {
		super(driver);
	}

	public String getCheckYourEmailTitle(WebDriver driver) {
		return GetPageTitle(driver);
	}
	
	public WebDriver returnToLogin() {
		ClickElement(returnToLogin, "Return to Login");
		return driver;
	}
}
