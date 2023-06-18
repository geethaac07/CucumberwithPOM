package com.automation.basetest;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.Constants;

import com.automation.utility.POMPropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class POMBaseTest {
	public static WebDriver driver;
	protected WebDriverWait waitObj;
	protected Logger log;
	protected String s;
//	protected ExtentReportUtility report = ExtentReportUtility.getInstance();
	protected POMPropertiesUtility propUtility = new POMPropertiesUtility();

	@BeforeTest
	public void setUpForBeforeTest() {
		log = LogManager.getLogger(POMBaseTest.class.getName());
	}

	@BeforeMethod
	@Parameters("browserName")
	public void setUpForBeforeMethod(@Optional("chrome") String browName) {

		Properties propertyFile = propUtility.loadFile("salesforceproperties");
		String url = propUtility.getPropertyValue("salesforce.url");

		launchBrowser(browName);
		goToUrl(url);

	}

//	@AfterMethod
//	public void tearDownAfterMethod() {
//	 		closeBrowser();
//	}

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

	public void goToUrl(String url) {
		driver.get(url);
		log.info("Url is entered in the browser");
	}

	public void closeBrowser() {
		driver.close();
	}
	
	public File getScreenCapture(WebDriver driver) {
		
		String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot scrnShot = (TakesScreenshot)driver;
		File imgFile=scrnShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(Constants.SCREENSHOTS_DIRECTORY_PATH+date+".png");
		try {
			FileUtils.copyFile(imgFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot captured at :" +destFile.getAbsolutePath());
		
		return destFile;
	}
}
