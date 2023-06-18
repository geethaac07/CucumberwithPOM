package com.automation.AllStepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import com.automation.utility.Log4JUtility;
import com.automation.utility.POMPropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.automation.pages.login.LoginPage;
import com.automation.pages.login.ForgotPasswordPage;
import com.automation.pages.login.CheckYourEmailPage;
import com.automation.pages.home.*;
import org.openqa.selenium.TakesScreenshot;

public class LoginStepdef {
	protected static Logger log;
	public static WebDriver driver;
	protected static Log4JUtility logObject = Log4JUtility.getInstance();
	LoginPage login;
	HomePage home;
	ForgotPasswordPage forgotPW;
	CheckYourEmailPage checkEmail;
	
	WebDriverWait waitObj;

	public POMPropertiesUtility propUtility = new POMPropertiesUtility();
	public Properties propertyFile = propUtility.loadFile("salesforceproperties");
	String username = propUtility.getPropertyValue("sf.valid.username");
	String password = propUtility.getPropertyValue("sf.valid.password");
	public String emailForgotPassword = propUtility.getPropertyValue("sf.forgot.password");
	public String invalidUsername = propUtility.getPropertyValue("sf.invalid.password");
	public String invalidPassword = propUtility.getPropertyValue("sf.invalid.password");

	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log = logObject.getLogger();
	}

	@Before
	public void setUpEachScenario() {

		launchBrowser("chrome");

	}

//	@After
//	public void tearDown() {
//		closeBrowser();
//	}

	@AfterStep
	public void after_each_scenario(Scenario sc) throws IOException {
		sc.log("After step execution");
		System.out.println("After each step is executed");
		if (sc.isFailed()) {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
			sc.attach(fileContent, "image/png", "screenshot");
		}
	}

	public void goToUrl(String url) {
		driver.get(url);
		log.info("Url is entered in the browser");
	}

	public void closeBrowser() {
		driver.close();
	}

	public void launchBrowser(String str) {
		switch (str) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;
		}
	}

	@Given("user opens salesforce application")
	public void user_opens_salesforce_application() {
		Properties propertyFile = propUtility.loadFile("salesforceproperties");
		String url = propUtility.getPropertyValue("salesforce.url");
		goToUrl(url);
	}

	@When("I enter valid username only")
	public void i_enter_valid_username_only() {
		login.enterUserName(username);

	}

	@When("click on Login button")
	public void click_on_login_button() {
		driver = login.clickButton();
	}

	@Then("verify error message {string}")
	public void verify_error_message(String string1) {
		string1 = login.getLoginErrorText();
	}

	@When("I enter valid username and valid password")
	public void i_enter_valid_username_and_valid_password() {

		login.enterUserName(username);
		login.enterPassword(password);
	}

	@Then("verify Getting started heading {string}")
	public void verify_getting_started_heading(String str) {
		str = home.getTextGetStarted();
	}

	@When("click remember me checkbox")
	public void click_remember_me_checkbox() {
		login.rememberMeLogin(true);
	}

	@When("click logout")
	public void click_logout() {
		waitObj = new WebDriverWait(driver, 60);
		home.clickUserProfile();
		driver = home.clickLogout();
	}

	@Then("username is entered in the text field")
	public void username_is_entered_in_the_text_field() {
		String savedUser = login.getUserNameText();

		Assert.assertEquals(username, savedUser);

		log.info("User Name is remembered and displayed in the Username field");
		log.error("User Name is remembered and displayed in the Username field");
	}

	@When("user is on {string}")
	public void user_is_on(String page) {
		if (page.equalsIgnoreCase("loginpage"))
			login = new LoginPage(driver);
		else if (page.equalsIgnoreCase("homepage"))
			home = new HomePage(driver);
		else if (page.equalsIgnoreCase("forgotpasswordpage"))
			forgotPW = new ForgotPasswordPage (driver);
		else if (page.equalsIgnoreCase("checkyouremailpage"))
			checkEmail = new CheckYourEmailPage(driver);
	}

	@When("user click on forgotpassword link")
	public void user_click_on_forgotpassword_link() {
		driver = login.clickForgotPassword();
	}

	@When("user enter emailid and click continue")
	public void user_enter_emailid_and_click_continue() {
		forgotPW.enterUserNameText(emailForgotPassword);
		driver = forgotPW.clickContinueButton();
	}

	@Then("user clicks returnToLogin")
	public void user_clicks_return_to_login() {
		checkEmail.returnToLogin();
	}

	@When("user enters invalid username and password")
	public void user_enters_invalid_username_and_password() {
		login.enterUserName(invalidUsername);
		login.enterPassword(invalidPassword);
	}

}
