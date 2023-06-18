package com.automation.testscripts;

import java.util.List;
import java.util.Set;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.awt.AWTException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.automation.basetest.POMBaseTest;
import com.automation.pages.home.HomePage;
import com.automation.pages.login.CheckYourEmailPage;
import com.automation.pages.login.ForgotPasswordPage;
import com.automation.pages.login.LoginPage;
import com.automation.pages.myprofile.MyProfilePage;
import com.automation.pages.mysettings.CalenderReminderPage;
import com.automation.pages.mysettings.CustomizeTabsPage;
import com.automation.pages.mysettings.EmailSettingsPage;
import com.automation.pages.mysettings.LoginHistoryPage;
import com.automation.pages.mysettings.MySettingsPage;
import com.automation.pages.account.*;
import com.automation.utility.POMPropertiesUtility;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

public class POMAutomationScripts extends POMBaseTest {

	public POMPropertiesUtility propUtility = new POMPropertiesUtility();
	public Properties propertyFile = propUtility.loadFile("salesforceproperties");
	String username = propUtility.getPropertyValue("sf.valid.username");
	String password = propUtility.getPropertyValue("sf.valid.password");
	public String emailForgotPassword = propUtility.getPropertyValue("sf.forgot.password");
	public String invalidUsername = propUtility.getPropertyValue("sf.invalid.password");
	public String invalidPassword = propUtility.getPropertyValue("sf.invalid.password");

	@Test
	public void Login_Error_Message_TestScript_01() {
		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);
		login.enterUserName(username);
		login.clickButton();

		String actualText = "Please enter your password.";
		String expectedText = login.getLoginErrorText();

		Assert.assertEquals(actualText, expectedText);
		System.out.println("Error Message is Matched");
		log.info("Error Message is Matched");

	}

	@Test
	public void Success_login_TestScript_02() {

		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(username);
		login.enterPassword(password);
		login.clickButton();

		HomePage home = new HomePage(driver);
		String expText = home.getTextGetStarted();
		String actText = "Getting Started";

		Assert.assertEquals(actText, expText);

		log.info("Home Page Text is matched");
//		report.logTestInfo("Home Page Text is matched");

	}

	@Test
	public void Success_login_RememberMe_TestScript_03() throws InterruptedException {

		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(username);
		login.enterPassword(password);
		login.rememberMeLogin(true);
		login.clickButton();

		HomePage home = new HomePage(driver);
		String expText = home.getTextGetStarted();
		String actText = "Getting Started";

		Assert.assertEquals(actText, expText);

		log.info("Home Page Text is matched");
//		report.logTestInfo("Home Page Text is matched");

		home.clickUserProfile();
		driver = home.clickLogout();
		Thread.sleep(3000);
		String savedUser = login.getUserNameText();

		Assert.assertEquals(username, savedUser);

		log.info("User Name is remembered and displayed in the Username field");
		log.error("User Name is remembered and displayed in the Username field");

	}

	@Test(enabled = false)
	public void Forgot_Password_TestScript__4A() {

		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		driver = login.clickForgotPassword();

		ForgotPasswordPage forgotPW = new ForgotPasswordPage(driver);
		forgotPW.enterUserNameText(emailForgotPassword);
		driver = forgotPW.clickContinueButton();

		CheckYourEmailPage checkEmail = new CheckYourEmailPage(driver);
		checkEmail.returnToLogin();
	}

	@Test
	public void Forgot_Password_TestScript_4B() {
		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(invalidUsername);
		login.enterPassword(invalidPassword);
		login.rememberMeLogin(false);
		login.clickButton();

		String expError = login.getLoginErrorText();
		String actError = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";

		Assert.assertEquals(actError, expError);

		log.info("Error Message is displayed");
//		report.logTestInfo("Error Message is displayed");
	}

	@Test
	public void User_Menu_Dropdown_TestScript_05() throws InterruptedException {

		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(username);
		login.enterPassword(password);
		login.rememberMeLogin(false);
		login.clickButton();

		HomePage home = new HomePage(driver);
		Thread.sleep(4000);

		home.checkUserMenuOptions();
	}

	@Test
	public void MyProfile_UserMenu_Option_TestScript_06() throws AWTException, InterruptedException {

		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(username);
		login.enterPassword(password);
		login.rememberMeLogin(false);
		login.clickButton();

		HomePage home = new HomePage(driver);
		Thread.sleep(4000);
		home.clickUserProfile();

		driver = home.clickMyProfile();

		MyProfilePage myProfile = new MyProfilePage(driver);
		myProfile.clickEditDropDown();
		myProfile.clickEditProfile();
		Thread.sleep(3000);
		myProfile.switchToFrame();
		myProfile.enterLastName();
		myProfile.clickOnSaveButton();

		myProfile.clickPostLink();
		myProfile.clickPostFrame();
		myProfile.enterMessageToPost();

	}

	@Test
	public void MySettings_UserMenu_Option_TestScript_07() throws InterruptedException, AWTException {
		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(username);
		login.enterPassword(password);
		login.rememberMeLogin(false);
		login.clickButton();

		HomePage home = new HomePage(driver);
		Thread.sleep(4000);
		home.clickUserProfile();
		driver = home.clickMySettings();

		MySettingsPage mySettings = new MySettingsPage(driver);
		mySettings.clickPersonalTab();
		LoginHistoryPage loginHistory = new LoginHistoryPage(driver);
		loginHistory.clickLoginHistory();
		loginHistory.clickDownloadHistory();

		MySettingsPage mySettings1 = new MySettingsPage(driver);
		mySettings1.clickDisplayLayout();

		CustomizeTabsPage custTab = new CustomizeTabsPage(driver);

		custTab.clickCustTabs();
		custTab.selectSalesForceChatCustApp();
		custTab.selectAvailTabs();

		MySettingsPage mySettings2 = new MySettingsPage(driver);
//		mySettings.addToTab();
//		mySettings.clickSaveTab();
		mySettings2.clickTopRightDropdown();
		mySettings2.clickSalesForceChatterMenu();

		HomePage home1 = new HomePage(driver);
		home1.verifyReportsTab();
		home1.clickUserProfile();
		home1.clickMySettings();

		MySettingsPage mySettings3 = new MySettingsPage(driver);

		mySettings3.clickEmail();
		mySettings3.clickEmailSettings();

		EmailSettingsPage email = new EmailSettingsPage(driver);
		email.enterSenderName();
		email.enterSenderEmail();
		email.autoBccYes();
		email.saveEmailSettings();

		mySettings3.clickCalAndReminder();
		mySettings3.clickActReminder();

		CalenderReminderPage calReminder = new CalenderReminderPage(driver);

		calReminder.clickTestReminder();

		calReminder.verifySampleEvent();
	}

	@Test
	public void DevConsole_UserMenu_Option_TestScript_08() throws InterruptedException, AWTException {

		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(username);
		login.enterPassword(password);
		login.rememberMeLogin(false);
		login.clickButton();

		HomePage home = new HomePage(driver);
		Thread.sleep(4000);

		home.clickUserProfile();
		home.clickDevConsole();
		home.switchToParentWindow();
		log.info("Developer Console window is closed - TC passed");
//		report.logTestInfo("Developer Console window is closed - TC passed");
	}

	@Test
	public void Logout_TestScript_09() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(username);
		login.enterPassword(password);
		login.rememberMeLogin(false);
		login.clickButton();

		HomePage home = new HomePage(driver);
		Thread.sleep(4000);

		home.clickUserProfile();
		home.clickLogout();
		String actualTitle2 = "Login | Salesforce";
		Thread.sleep(3000);
		String expectedTitle2 = home.GetPageTitle(driver);

		Assert.assertEquals(actualTitle2, expectedTitle2);
	}

	@Test
	public void Create_Account_TestScript_10() throws AWTException, InterruptedException {
		LoginPage login = new LoginPage(driver);
		String actualLoginTitle = "Login | Salesforce";
		String expectedLoginTitle = login.getTitleOfThePage(driver);

		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);

		login.enterUserName(username);
		login.enterPassword(password);
		login.rememberMeLogin(false);
		login.clickButton();

		HomePage home = new HomePage(driver);
		driver = home.clickAccounts();
		Thread.sleep(4000);
		home.Close_SwitchTo_Lightning_PopUp();

		AccountsPage accounts = new AccountsPage(driver);
		driver = accounts.createNewAAccount();

		EditAccountPage editAccount = new EditAccountPage(driver);
		editAccount.setAccountName();
		editAccount.setAccountType();
		editAccount.setCustPriority();
		driver = editAccount.clickSaveAcc();

		AccountDetailsPage accDetails = new AccountDetailsPage(driver);
		String expHeading = accDetails.getAccDetailsText();
		String actHeading = "Account Detail";

		Assert.assertEquals(actHeading, expHeading);
		System.out.println("New account is created and the Account Details page is displayed.");
	}
	/*
	 * 
	 * 
	 * @Test public void Account_New_View_TestScript_11() throws AWTException {
	 * loginToWeb(); WebElement accounts =
	 * driver.findElement(By.xpath("//ul/li[6]/a")); ClickAction(accounts,
	 * "Accounts"); try { Thread.sleep(4000); } catch (InterruptedException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement create_newView =
	 * driver.findElement(By.xpath("//span[@class='fFooter']/a[2]"));
	 * ClickAction(create_newView, "Create New View");
	 * 
	 * String actualTitle =
	 * " Accounts: Create New View ~ Salesforce - Developer Edition"; String
	 * expectedTitle = getPageTitle(driver); Assert.assertEquals(actualTitle,
	 * expectedTitle); log.info("Create New View page is displayed");
	 * report.logTestInfo("Create New View page is displayed");
	 * 
	 * WebElement viewName = driver.findElement(By.id("fname")); enterKeys(viewName,
	 * "View Name", "View7"); WebElement view_uniqName =
	 * driver.findElement(By.id("devname")); enterKeys(view_uniqName,
	 * "View Unique Name", "");
	 * 
	 * try { Thread.sleep(5000); } catch (InterruptedException e) {
	 * e.printStackTrace(); } Robot robot = new Robot(); robot.mouseWheel(6);
	 * WebElement save_btn = driver.findElement(By.xpath(
	 * "//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[1]"));
	 * 
	 * ExplicitWaitElement(save_btn); ClickAction(save_btn, "Save");
	 * 
	 * WebElement updated_view = driver.findElement(By.name("fcf"));
	 * ExplicitWaitElement(updated_view); Select savedView = new
	 * Select(updated_view); driver.switchTo().activeElement(); WebElement
	 * view_Displayed = savedView.getFirstSelectedOption(); String updatedViewName =
	 * "View7";
	 * 
	 * log.info("The updated View name is displayed in the UI:" +
	 * updatedViewName.equals(view_Displayed.getText()));
	 * report.logTestInfo("The updated View name is displayed in the UI");
	 * 
	 * }
	 * 
	 * @Test public void Edit_View_Script_12() throws AWTException,
	 * InterruptedException {
	 * 
	 * loginToWeb();
	 * 
	 * WebElement accounts = driver.findElement(By.xpath("//ul/li[6]/a"));
	 * ClickAction(accounts, "Accounts");
	 * 
	 * Thread.sleep(4000); Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement select_view = driver.findElement(By.xpath("//*[@id='fcf']"));
	 * ExplicitWaitElement(select_view); Select selectView = new
	 * Select(select_view); // selectView.selectByIndex(3);
	 * selectView.getFirstSelectedOption();
	 * 
	 * WebElement editView = driver .findElement(By.
	 * cssSelector("#filter_element > div > span > span.fFooter > a:nth-child(1)"));
	 * // .xpath("//div[@class='topNav // primaryPalette']/div/div/a"));
	 * ExplicitWaitElement(editView); ClickAction(editView, "Edit");
	 * 
	 * String actualTitle = "Accounts: Edit View ~ Salesforce - Developer Edition";
	 * String expectedTitle = getPageTitle(driver); Assert.assertEquals(actualTitle,
	 * expectedTitle);
	 * 
	 * log.info("Accounts page Title Matched");
	 * report.logTestInfo("Accounts page title is matched.");
	 * 
	 * WebElement newView_name = driver.findElement(By.id("fname")); // edit view
	 * name enterKeys(newView_name, "New View Name", "ViewUpdated");
	 * 
	 * WebElement filter1_field = driver.findElement(By.id("fcol1"));
	 * ExplicitWaitElement(filter1_field); Select select_filter1 = new
	 * Select(filter1_field); select_filter1.selectByVisibleText("Account Name");
	 * 
	 * Thread.sleep(3000); WebElement filter2_op =
	 * driver.findElement(By.id("fop1")); ExplicitWaitElement(filter2_op);
	 * ClickAction(filter2_op, "Filter Operator"); Select select_filter2 = new
	 * Select(filter2_op); select_filter2.selectByVisibleText("contains");
	 * 
	 * WebElement filter3_value = driver.findElement(By.id("fval1"));
	 * ExplicitWaitElement(filter3_value); enterKeys(filter3_value, "Value", "a");
	 * 
	 * Robot robot = new Robot(); robot.mouseWheel(7);
	 * 
	 * WebElement save_btn = driver.findElement(By.xpath(
	 * "//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[1]"));
	 * ClickAction(save_btn, "Save Button");
	 * 
	 * WebElement updated_view = driver.findElement(By.name("fcf"));
	 * ExplicitWaitElement(updated_view); Select savedView = new
	 * Select(updated_view);
	 * 
	 * driver.switchTo().activeElement(); WebElement view_Displayed =
	 * savedView.getFirstSelectedOption(); String updatedViewName = "ViewUpdated";
	 * 
	 * log.info("The updated View name is displayed in the UI:" +
	 * updatedViewName.equals(view_Displayed.getText()));
	 * report.logTestInfo("The updated View name is displayed in the UI"); }
	 * 
	 * @Test public void Merge_Accounts_TestScript_13() throws InterruptedException
	 * { loginToWeb();
	 * 
	 * WebElement accounts =
	 * driver.findElement(By.xpath("//ul/li[@id='Account_Tab']/a"));
	 * ClickAction(accounts, "Accounts");
	 * 
	 * Thread.sleep(4000); Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement mergeAcc = driver.findElement(By.xpath(
	 * "//div[@class='toolsContentRight']/div/div/ul/li[4]/span/a"));
	 * ClickAction(mergeAcc, "Merge Accounts");
	 * 
	 * WebElement findAcc = driver.findElement(By.id("srch")); enterKeys(findAcc,
	 * "Account", "GAC");
	 * 
	 * WebElement findAccBtn = driver.findElement(By.name("srchbutton"));
	 * ClickAction(findAccBtn, "Find Accounts"); Thread.sleep(3000);
	 * 
	 * WebElement findAcc1 = driver.findElement(By.id("cid0"));
	 * FluentWaitElement(findAcc1);
	 * 
	 * WebElement findAcc2 = driver.findElement(By.id("cid1"));
	 * FluentWaitElement(findAcc2);
	 * 
	 * WebElement nextBtn = driver.findElement(By.name("goNext"));
	 * 
	 * ClickAction(nextBtn, "Next Button");
	 * 
	 * WebElement mergeBtn = driver.findElement(By.name("save"));
	 * FluentWaitElement(mergeBtn); ClickAction(mergeBtn, "Save Button");
	 * 
	 * alert_accept();
	 * 
	 * WebElement recAccount = driver.findElement(By.xpath("//th[@scope='row']/a"));
	 * String actualAccount = "GAC1"; String expAccount = get_Text(recAccount);
	 * 
	 * Assert.assertEquals(actualAccount, expAccount);
	 * log.info("Account is Merged and displayed in the Recent Account");
	 * report.logTestInfo("Account is Merged and displayed in the Recent Account");
	 * 
	 * }
	 * 
	 * @Test public void Create_Account_Report_TestScript_14() throws
	 * InterruptedException, AWTException { loginToWeb();
	 * 
	 * WebElement accounts =
	 * driver.findElement(By.xpath("//ul/li[@id='Account_Tab']/a"));
	 * ClickAction(accounts, "Accounts");
	 * 
	 * Thread.sleep(4000); Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement click_Report =
	 * driver.findElement(By.linkText("Accounts with last activity > 30 days"));
	 * ExplicitWaitElement(click_Report); ClickAction(click_Report,
	 * "Create Report");
	 * 
	 * Thread.sleep(4000); WebElement from_date =
	 * driver.findElement(By.xpath("//*[@id=\"ext-gen152\"]"));
	 * 
	 * FluentWaitElement(from_date); ClickAction(from_date, "From Date:");
	 * 
	 * WebElement select_Fromdate =
	 * driver.findElement(By.cssSelector("#ext-gen276"));
	 * ClickAction(select_Fromdate, "Select From Date:");
	 * 
	 * WebElement to_date = driver.findElement(By.xpath("//img[@id='ext-gen154']"));
	 * ExplicitWaitElement(to_date); ClickAction(to_date, "To Field");
	 * 
	 * WebElement select_Todate =
	 * driver.findElement(By.xpath("//button[@id='ext-gen292']"));
	 * ExplicitWaitElement(select_Todate); ClickAction(select_Todate,
	 * "Select ToDate");
	 * 
	 * WebElement saveBtn =
	 * driver.findElement(By.xpath("//button[@id='ext-gen49']"));
	 * ClickAction(saveBtn, "Save Button");
	 * 
	 * WebElement repName =
	 * driver.findElement(By.id("saveReportDlg_reportNameField"));
	 * enterKeys(repName, "Report Name", "Report1");
	 * 
	 * WebElement repUniqName =
	 * driver.findElement(By.id("saveReportDlg_DeveloperName"));
	 * enterKeys(repUniqName, "Report Unique Name", "Report1");
	 * 
	 * WebElement saveReport = driver.findElement(By.cssSelector("#ext-gen312"));
	 * FluentWaitElement(saveReport); //ClickAction(saveReport, "Save Report");
	 * JavascriptExecutor js = (JavascriptExecutor)driver;
	 * js.executeScript("arguments[0].click;",saveReport);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test public void opportunities_TestScript_15() throws InterruptedException {
	 * 
	 * loginToWeb();
	 * 
	 * WebElement opportunites = driver .findElement(By.xpath(
	 * "//div[@id='tabContainer']/nav/ul/li[@id='Opportunity_Tab']"));
	 * ClickAction(opportunites, "Opportunites");
	 * 
	 * Thread.sleep(4000); Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement select_view = driver.findElement(By.xpath("//*[@id='fcf']"));
	 * ExplicitWaitElement(select_view); Select selectView = new
	 * Select(select_view);
	 * 
	 * List<WebElement> all_opp_options = selectView.getOptions();
	 * 
	 * String[] opp_options = { "All Oppotunities", "Closing Next Month",
	 * "Closing This Month", "My Opportunities", "New Last Week", "New This Week",
	 * "Opportunity PipeLine", "Private", "Recently Viewed Opportunities", "Won" };
	 * 
	 * int i = 0; for (WebElement item : all_opp_options) { if
	 * (item.getText().equalsIgnoreCase(opp_options[i])) log.info(opp_options[i] +
	 * " is displayed");
	 * 
	 * else log.info(opp_options[i] + " is NOT displayed"); i++; }
	 * 
	 * }
	 * 
	 * @Test public void New_opty_TestScript_16() throws InterruptedException {
	 * 
	 * loginToWeb();
	 * 
	 * WebElement opportunites = driver .findElement(By.xpath(
	 * "//div[@id='tabContainer']/nav/ul/li[@id='Opportunity_Tab']"));
	 * ClickAction(opportunites, "Opportunites");
	 * 
	 * Thread.sleep(4000); Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement new_btn = driver.findElement(By.name("new")); ClickAction(new_btn,
	 * "New Opportunity");
	 * 
	 * String actualTitle1 =
	 * "Opportunity Edit: New Opportunity ~ Salesforce - Developer Edition"; String
	 * expectedTitle1 = getPageTitle(driver); Assert.assertEquals(actualTitle1,
	 * expectedTitle1);
	 * 
	 * WebElement oppName = driver.findElement(By.id("opp3")); enterKeys(oppName,
	 * "Opportunity Name", "OPP2");
	 * 
	 * WebElement accName = driver.findElement(By.id("opp4")); enterKeys(accName,
	 * "Account Name", "GC Account");
	 * 
	 * WebElement leadSource = driver.findElement(By.id("opp6")); Select source =
	 * new Select(leadSource); source.selectByVisibleText("Web");
	 * 
	 * WebElement closeDate = driver .findElement(By.xpath(
	 * "//*[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/span/span/a"));
	 * ClickAction(closeDate, "Close Date");
	 * 
	 * WebElement selectStage = driver.findElement(By.id("opp11")); Select stage =
	 * new Select(selectStage); stage.selectByVisibleText("Closed Won");
	 * 
	 * WebElement probability = driver.findElement(By.id("opp12"));
	 * enterKeys(probability, "Probability", "100");
	 * 
	 * WebElement primCampaignSrc = driver.findElement(By.id("opp12"));
	 * enterKeys(primCampaignSrc, "Primary Campaign Source", "Web");
	 * 
	 * WebElement saveBtn =
	 * driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
	 * ClickAction(saveBtn, "Save Button");
	 * 
	 * String actualTitle2 =
	 * "Opportunity Pipeline ~ Salesforce - Developer Edition"; String
	 * expectedTitle2 = getPageTitle(driver); Assert.assertEquals(actualTitle2,
	 * expectedTitle2); }
	 * 
	 * @Test public void Opty_Pipeline_Report_TestScript_17() throws
	 * InterruptedException {
	 * 
	 * loginToWeb();
	 * 
	 * WebElement opportunites = driver .findElement(By.xpath(
	 * "//div[@id='tabContainer']/nav/ul/li[@id='Opportunity_Tab']"));
	 * ClickAction(opportunites, "Opportunites");
	 * 
	 * Thread.sleep(4000); Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement opty_pipeline = driver .findElement(By.xpath(
	 * "//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a"));
	 * 
	 * Thread.sleep(4000); ClickAction(opty_pipeline, "Opportunity Pipeline");
	 * 
	 * String actualTitle2 =
	 * "Opportunity Pipeline ~ Salesforce - Developer Edition"; String
	 * expectedTitle2 = getPageTitle(driver); Assert.assertEquals(actualTitle2,
	 * expectedTitle2); log.info("Opportunities Pipeline page is displayed"); }
	 * 
	 * @Test public void Stuck_Opportunities_Report_TestScript_18() throws
	 * InterruptedException {
	 * 
	 * loginToWeb();
	 * 
	 * WebElement opportunites = driver .findElement(By.xpath(
	 * "//div[@id='tabContainer']/nav/ul/li[@id='Opportunity_Tab']"));
	 * ClickAction(opportunites, "Opportunites");
	 * 
	 * Thread.sleep(4000); Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement stuckOpp = driver.findElement(By.xpath(
	 * "//div[@class='toolsContentLeft']/div[1]//div/ul/li[2]/a"));
	 * 
	 * Thread.sleep(4000); ClickAction(stuckOpp, "Stuck Opportunities");
	 * 
	 * String actualTitle2 = "Stuck Opportunities ~ Salesforce - Developer Edition";
	 * String expectedTitle2 = getPageTitle(driver);
	 * Assert.assertEquals(actualTitle2, expectedTitle2);
	 * log.info("Stuck Opportunities page is displayed"); }
	 * 
	 * @Test public void Quaterly_Summary_report_TestScript_19() throws
	 * InterruptedException {
	 * 
	 * loginToWeb();
	 * 
	 * WebElement opportunites = driver .findElement(By.xpath(
	 * "//div[@id='tabContainer']/nav/ul/li[@id='Opportunity_Tab']"));
	 * ClickAction(opportunites, "Opportunites");
	 * 
	 * Thread.sleep(4000); Close_SwitchTo_Lightning_PopUp();
	 * 
	 * WebElement runReport = driver.findElement(By.xpath(
	 * "//*[@id='lead_summary']/table/tbody/tr[3]/td/input"));
	 * ClickAction(runReport, "Run Report");
	 * 
	 * WebElement repStatus = driver.findElement(By.id("quarter_q")); Select
	 * oppStatus = new Select(repStatus);
	 * 
	 * String expStatus = oppStatus.getFirstSelectedOption().getText();
	 * 
	 * String actStatus = "Current FQ";
	 * 
	 * log.info("Status" + expStatus); Assert.assertEquals(expStatus, actStatus);
	 * 
	 * WebElement repRange = driver.findElement(By.id("open"));
	 * 
	 * Select selRange = new Select(repRange); String expRange =
	 * selRange.getFirstSelectedOption().getText(); log.info("Range" + expRange);
	 * 
	 * String actRange = "Any";
	 * 
	 * Assert.assertEquals(expRange, actRange); log.info("Range is matched");
	 * 
	 * driver.navigate().back(); Thread.sleep(3000);
	 * 
	 * WebElement qSumInterval = driver .findElement(By.xpath(
	 * "//table[@class='opportunitySummary']/tbody/tr/td/select[@id='quarter_q']"));
	 * Select qInterval = new Select(qSumInterval);
	 * qInterval.selectByVisibleText("Previous FQ");
	 * 
	 * WebElement qSumInclude = driver.findElement(By.xpath("//*[@id='open']"));
	 * Select qInclude = new Select(qSumInclude);
	 * qInclude.selectByVisibleText("Closed Opportunities");
	 * 
	 * WebElement runReport1 = driver.findElement(By.xpath(
	 * "//*[@id='lead_summary']/table/tbody/tr[3]/td/input"));
	 * ClickAction(runReport1, "Run Report");
	 * 
	 * WebElement repStatus1 = driver.findElement(By.id("quarter_q"));
	 * 
	 * Select oppStatus1 = new Select(repStatus1);
	 * 
	 * String expStatus1 = oppStatus1.getFirstSelectedOption().getText();
	 * 
	 * String actStatus1 = "Previous FQ";
	 * 
	 * log.info("Status" + expStatus); Assert.assertEquals(expStatus1, actStatus1);
	 * 
	 * WebElement repRange1 = driver.findElement(By.id("open"));
	 * 
	 * Select selRange1 = new Select(repRange1);
	 * 
	 * String expRange1 = selRange1.getFirstSelectedOption().getText();
	 * 
	 * String actRange1 = "Closed";
	 * 
	 * Assert.assertEquals(expRange1, actRange1); log.info("Range is matched");
	 * 
	 * }
	 * 
	 */
}
