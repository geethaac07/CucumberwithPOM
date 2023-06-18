package com.automation.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.POMBasePage;

public class LoginPage extends POMBasePage {
	@FindBy(id = "username")
	WebElement userName;
	@FindBy(id = "password")
	WebElement passWord;
	@FindBy(name = "Login")
	WebElement loginBtn;
	@FindBy(xpath = "//*[@id='rememberUn']")
	WebElement rememberMe;
	@FindBy(id = "error")
	WebElement loginError;
	@FindBy(id = "forgot_password_link")
	WebElement forgotPwdLink;

	@FindBy(xpath = "//*[@id='hint_back_chooser']")
	WebElement savedUserName;

	@FindBy(xpath = "//*[@id='idcard-identity']")
	WebElement uNameValue;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterUserName(String uName) {
		EnterText(userName, "User Name", uName);
	}

	public void enterPassword(String pWord) {
		EnterText(passWord, "Password", pWord);
	}

	public void rememberMeLogin(boolean remember) {
		if (remember == true)
			ClickElement(rememberMe, "Remember Me Checkbox");
	}

	public WebDriver clickButton() {
		ClickElement(loginBtn, "Login Button");
		return driver;
	}

	public String getTitleOfThePage(WebDriver driver) {
		return GetPageTitle(driver);
	}

	public String getLoginErrorText() {
		return GetText(loginError);
	}

	public String getUserNameText() {
		ExplicitWaitElement(uNameValue);
		return GetText(uNameValue);
	}

	public boolean rememberIsSelected() {
		if (rememberMe.isSelected())
			return true;
		else
			return false;
	}

	public WebDriver clickForgotPassword() {
		if (forgotPwdLink.isDisplayed())
			ClickElement(forgotPwdLink, "Forgot Password Link");
		return driver;

	}

}
