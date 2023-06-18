package com.automation.pages.home;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.automation.pages.base.POMBasePage;

public class HomePage extends POMBasePage {

	@FindBy(xpath = "//*[@id='content_wrap:j_id10']/div/h2")
	WebElement getStarted;
	@FindBy(xpath = "//*[@id='home_Tab']/a")
	WebElement homeTab;
	@FindBy(xpath = "//*[@id='Contact_Tab']/a")
	WebElement contactTab;
	@FindBy(xpath = "//*[@id='Account_Tab']/a")
	WebElement accountTab;
	@FindBy(xpath = "//*[@id='Opportunity_Tab']/a")
	WebElement opportunityTab;
	@FindBy(xpath = "//*[@id='userNav-arrow']")
	WebElement userMenu;
	@FindBy(xpath = "//*[@id='userNav-menuItems']/a")
	List<WebElement> userMenuDropdown;

	@FindBy(xpath = "//*[@id='userNav-menuItems']/a[1]")
	WebElement myProfile;
	@FindBy(xpath = "//*[@id='userNav-menuItems']/a[2]")
	WebElement mySettings;

	@FindBy(linkText = "Logout")
	WebElement logout;
	@FindBy(id = "tsidButton")
	WebElement topRightDropdown;

	@FindBy(xpath = "//div[@id='tsid-menuItems']/a[6]")
	WebElement salesforceChatter;
	@FindBy(id = "report_Tab")
	WebElement reportsTab;

	@FindBy(xpath = "//ul/li[@id='Account_Tab']/a")
	WebElement accounts;

	@FindBy(xpath = "//*[@id='userNav-menuItems']/a[3]")
	WebElement dev_console;
	
	@FindBy(xpath = "//*[@id='AllTab_Tab']") WebElement allTabs;
	

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getTextGetStarted() {
		return GetText(getStarted);
	}

	public void clickUserProfile() {
		ClickElement(userMenu, "User Profile Drop Down");
	}

	public WebDriver clickLogout() {
		ClickElement(logout, "Logout Button");
		return driver;
	}

	public void clickOnAllTabs() {
		ClickElement(allTabs, "User Profile Drop Down");
	}
	public void verifyReportsTab() throws InterruptedException {
		Thread.sleep(3000);
		ExplicitWaitElement(reportsTab);
		Verify_ElementDisplay(reportsTab, "Reports Tab");
	}
	public void checkUserMenuOptions() throws InterruptedException {

		clickUserProfile();

		String[] userMenuAllOptions = { "My Profile", "My Settings", "Developer Console",
				"Switch to Lightning Experience", "Logout" };

		int i = 0;
		for (WebElement item : userMenuDropdown) {
			if (item.getText().equalsIgnoreCase(userMenuAllOptions[i]))
//				log.info(userMenuAllOptions[i] + " is displayed");
				System.out.println(userMenuAllOptions[i] + " is displayed");
			else
//				log.error(userMenuAllOptions[i] + " is NOT displayed");
				System.out.println(userMenuAllOptions[i] + " is NOT displayed");
			i++;
		}
	}

	public WebDriver clickMyProfile() {
		if (myProfile.isDisplayed())
			ClickElement(myProfile, "My Profile");
		else
			System.out.println("Element not found");
		return driver;
	}

	public WebDriver clickMySettings() {
		if (mySettings.isDisplayed())
			ClickElement(mySettings, "My Settings");
		else
			System.out.println("My Settings Element not found");
		return driver;
	}

	public void clickDevConsole() {
		if (dev_console.isDisplayed())
			ClickElement(dev_console, "Developer Console");

		else
			System.out.println("Developer Console element is Not found");
	}

	public WebDriver clickAccounts() {
		if (accounts.isDisplayed())
			ClickElement(accountTab, "Accounts Tab");
		else
			System.out.println("Accounts element is not found");
		return driver;
	}
	
	public void switchToParentWindow() {
		String parent_window = driver.getWindowHandle();
		Set<String> all_windows = driver.getWindowHandles();

		for (String s : all_windows) {
			if (!s.equals(parent_window)) {
				driver.switchTo().window(s);
				driver.close();
				driver.switchTo().window(parent_window);
			}
		}
	}
}
