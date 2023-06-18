package com.automation.pages.mysettings;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.POMBasePage;

public class CalenderReminderPage extends POMBasePage{

	@FindBy(id = "testbtn")
	WebElement testReminder;
	@FindBy(xpath = "//*[@id='summary0']/div/text()")
	WebElement sampleEvent;

	public CalenderReminderPage(WebDriver driver) {
		super(driver);		
	}
	public void clickTestReminder() {
		if (testReminder.isDisplayed())
			ClickElement(testReminder, "Open Test Reminder");
		else
			log.info("TestReminder element is not found");
	}

	public void verifySampleEvent() {
		String parent_window = driver.getWindowHandle();
		Set<String> all_windows = driver.getWindowHandles();
		for (String s : all_windows) {
			if (!s.equals(parent_window)) {
				driver.switchTo().window(s);
				Verify_ElementDisplay(sampleEvent, "Sample Event");
				driver.close();

			}
		}
	}
}
