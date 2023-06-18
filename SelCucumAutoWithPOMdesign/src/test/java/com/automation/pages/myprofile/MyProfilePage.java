package com.automation.pages.myprofile;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;

import com.automation.pages.base.POMBasePage;

public class MyProfilePage extends POMBasePage {

	@FindBy(xpath = "//*[@id='moderatorMutton']")
	WebElement editDropDown;

	@FindBy(xpath = "//*[@id=\"chatterTab\"]/div[1]/div/div[1]/div[1]/div/ul/li[2]/a")
	WebElement editProfile;

	@FindBy(id = "publisherAttachTextPost")
	WebElement postLink;
	@FindBy(xpath = "//iframe[@class ='cke_wysiwyg_frame cke_reset']")
	WebElement postFrame;
	@FindBy(id = "publishersharebutton")
	WebElement shareButton;

	@FindBy(xpath = "//input[@id='lastName']")
	WebElement lastName;
	@FindBy(css = "input.zen-btn:nth-child(1)")
	WebElement saveAllBtn;
	@FindBy(xpath = "//*[@id='contactTab']/a")
	WebElement contacTab;
	@FindBy(xpath = "//*[@id='firstName']")
	WebElement firstName;
	
	
	
	
	public MyProfilePage(WebDriver driver) {
		super(driver);

	}

	public void clickEditDropDown() throws InterruptedException {
		Thread.sleep(4000);
		ClickElement(editDropDown, "Edit DropDown");
	}

	public void clickEditProfile() throws InterruptedException {

		if (editProfile.isDisplayed())
			ClickElement(editProfile, "Click Edit Profile");
		else
			System.out.println("Element is not displayed");
	}

	public void clickPostLink() {
		if (postLink.isDisplayed())
			ClickElement(postLink, "Post Link");
		else
			System.out.println("Post Link Element is not displayed");
	}

	public void clickPostFrame() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (postFrame.isDisplayed())
			ClickElement(postFrame, "Post Frame");
		else
			System.out.println("Post Frame Element is not displayed");
	}

	public void clickshareButton() {
		if (shareButton.isDisplayed())
			ClickElement(shareButton, "Share Button");
		else
			System.out.println("Share Button Element is not displayed");
	}
public void switchToFrame() {
	driver.switchTo().frame(2);//to switch to the Edit Profile frame

}
	public void enterLastName() {
		if (lastName.isDisplayed())
			EnterText(lastName, "Last Name", "AChellapandi");
		else
			System.out.println("Last Name Element not found");
	}

	public void clickOnSaveButton() {
		ExplicitWaitElement(firstName);
		if (firstName.isDisplayed())
			ClickElement(saveAllBtn, "Save Button");
		else
			System.out.println("Save Button Element not found");
	}

	public void clickOnContactTab() {
		if (contacTab.isDisplayed())
			ClickElement(contacTab, "Contact Tab");
		else
			System.out.println("Contact tab element not found");
	}

	public void enterMessageToPost() {
		clickPostFrame();
		Actions actions = new Actions(driver);
		actions.sendKeys("Hi everyone!").build().perform();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		clickshareButton();
	}
}
