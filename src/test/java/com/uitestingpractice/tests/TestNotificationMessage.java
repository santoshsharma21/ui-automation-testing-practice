package com.uitestingpractice.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.uitestingpractice.base.BaseClass;
import com.uitestingpractice.pages.NotificationMessagePage;
import com.uitestingpractice.utilities.ConfigReader;

public class TestNotificationMessage extends BaseClass {

	ConfigReader config;
	NotificationMessagePage notificationMsgPage;

	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		config = new ConfigReader();
		String url = config.getNotificationMsgUrl();
		setupDriver(browser, url);
	}

	@AfterMethod
	public void afterMethod() {
		quitDriver();
	}

	@Test
	public void validateNotificationMessage() {
		notificationMsgPage = new NotificationMessagePage(driver);
		notificationMsgPage.clickToGenerateNotificationMsg();
		String actualMsg = notificationMsgPage.getNotificationMsg();

		boolean status = false;
		if (actualMsg.equals("Action successful") || actualMsg.equals("Action unsuccessful, please try again")) {
			status = true;
		}
		Assert.assertTrue(status);
	}
}