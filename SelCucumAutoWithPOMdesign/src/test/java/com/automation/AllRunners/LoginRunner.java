package com.automation.AllRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
					features = { "src/test/resources/features/allLogin.feature" }, 
					glue = {"com.automation.AllStepDefs" }, 
					dryRun = false, 
					plugin = { "pretty", "html:target/cucumber1.html",
				"json:target/cucumber1.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })

public class LoginRunner extends AbstractTestNGCucumberTests {

}
