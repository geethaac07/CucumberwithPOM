package com.automation.pages.mysettings;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.automation.pages.base.POMBasePage;
import com.automation.utility.POMPropertiesUtility;

public class CustomizeTabsPage extends POMBasePage{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public static Robot robot = null;

	public POMPropertiesUtility propUtility = new POMPropertiesUtility();
	public Properties dataPropFile = propUtility.loadFile("salesforcedataproperties");
//	public String lastNameProp = propUtility.getPropertyValue("user.lastname");
//	String username = propUtility.getPropertyValue("sf.valid.username");

	String custAppOption = propUtility.getPropertyValue("custApp.name");
	String availTabText = propUtility.getPropertyValue("availtab.name");
	
	
	@FindBy(xpath = "//*[@id='CustomizeTabs_font']")
	WebElement customizeTabs;
	@FindBy(xpath = "//*[@id='p4']")
	WebElement custApp;
	@FindBy(xpath = "//*[@id='duel_select_0']")
	WebElement availTabs;
	@FindBy(xpath = "//*[@id='duel_select_0_right']/img")
	WebElement addToTab;
	@FindBy(xpath = "//*[@id='bottomButtonRow']/input[1]")
	WebElement saveTabBtn;

	@FindBy(xpath = "//*[@id='duel_select_1']")
	WebElement selectTabs;
	@FindBy(xpath = "//*[@id='duel_select_0_left']/img")
	WebElement removeFromTab;

	
	
	public CustomizeTabsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void clickCustTabs() throws InterruptedException, AWTException {
		Thread.sleep(3000);
		robot = new Robot();
		robot.mouseWheel(6);
		js.executeScript("arguments[0].click();", customizeTabs);
		ClickElement(customizeTabs, "Cust tabs");
	}

	public void selectSalesForceChatCustApp() {
		Select selectCustApp = new Select(custApp);
		selectCustApp.selectByVisibleText(custAppOption);
	}

	public void selectAvailTabs() throws InterruptedException, AWTException {
		Select selectAvailTabs = new Select(availTabs);
//		List<WebElement> allOptions = selectAvailTabs.getOptions();
//
//		for (WebElement item : allOptions) {
//			if (item.getText().equals(availTabText)) {
//				log.info(availTabText + " is displayed");
			selectAvailTabs.selectByVisibleText(availTabText);
				addToTab();
				clickSaveTab();
				// System.out.println(availTabText+ " is displayed");
//			}
//				else {
//				 log.info(availTabText + " is NOT displayed");
//				// removeandAddReportsTab(); //if Reports tab is already in the selected tab,
////				clickDisplayLayout();
////				clickCustTabs();
////				selectSalesForceChatCustApp();
////				removeFromSelectTabs();
//				Select removeSelectTabs = new Select(selectTabs);
//				removeSelectTabs.selectByVisibleText(availTabText);
//				removeTab();
//				clickSaveTab();
//				clickDisplayLayout();
//				clickCustTabs();
//				selectSalesForceChatCustApp();
//				selectAvailTabs();
//				addToTab();
//				clickSaveTab();
//			}
//		}

	}

	public void removeFromSelectTabs() {
		Select removeSelectTabs = new Select(selectTabs);
		removeSelectTabs.selectByVisibleText(availTabText);
	}

	public void removeandAddReportsTab() throws InterruptedException, AWTException {
		// remove it from selected Tab, save. then repeat from display & layout step
		removeFromSelectTabs();
		removeTab();
		clickSaveTab();
//		clickDisplayLayout();
		clickCustTabs();
		selectSalesForceChatCustApp();
		selectAvailTabs();
		addToTab();
		clickSaveTab();

	}

	public void addToTab() {
		if (addToTab.isDisplayed())
			ClickElement(addToTab, "Add to Tab");
		else
			System.out.println("Add to Tab element not found");
	}

	public void removeTab() {
		if (removeFromTab.isDisplayed())
			ClickElement(removeFromTab, "Remove from Tab");
		else
			System.out.println("Remove from tab element not found");
	}

	public void clickSaveTab() {
		if (saveTabBtn.isDisplayed())
			ClickElement(saveTabBtn, "Save the Tab");
		else
			System.out.println("Tab not found");
	}


	
}
