package com.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.POMBasePage;

public class ForgotPasswordPage extends POMBasePage {

	@FindBy(name = "un")
	WebElement userNameText;
	@FindBy(name = "continue")
	WebElement continueBtn;

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	public void enterUserNameText(String uName) {
		ExplicitWaitElement(userNameText);
		EnterText(userNameText, "User Name", uName);
	}
	public WebDriver clickContinueButton() {
		ClickElement(continueBtn, "Continue Button");
		return driver;
	}

}
