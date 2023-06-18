package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class POMPropertiesUtility {
	private FileInputStream fis = null;
	private Properties propertyFile = null;

	public Properties loadFile(String filename) {
		propertyFile = new Properties();
		String propertyFilePath = null;

		switch (filename) {
		case "logindataproperties":
			propertyFilePath = Constants.LOGIN_DATA_PROPERTIES;
			break;
		case "studentregistrationproperties":
			propertyFilePath = Constants.STUDENT_PROPERTIES;
			break;
		case "salesforceproperties":
			propertyFilePath = Constants.SALESFORCE_PROPERTIES;
			break;
		case "salesforcedataproperties":
			propertyFilePath = Constants.SALESFORCE_TESTDATA_PROPERTIES;
			break;
		}
		try {
			fis = new FileInputStream(propertyFilePath);
			try {
				propertyFile.load(fis);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return propertyFile;
	}

	public String getPropertyValue(String Key) {
		String value = propertyFile.getProperty(Key);
		return value;
	}

	public void writeDataToPropertyFile(File path, String key, String value) {

		Properties propertyFile = new Properties();
		propertyFile.setProperty(key, value);
		try {
			propertyFile.store(new FileOutputStream(path), "updating data");

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
