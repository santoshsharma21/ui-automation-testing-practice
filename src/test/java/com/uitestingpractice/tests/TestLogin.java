package com.uitestingpractice.tests;

import org.testng.annotations.Test;

import com.uitestingpractice.base.BaseClass;
import com.uitestingpractice.pages.LoginFormPage;
import com.uitestingpractice.pages.SecurePage;
import com.uitestingpractice.utilities.ConfigReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestLogin extends BaseClass {

	ConfigReader config;
	LoginFormPage loginPage;
	SecurePage securePage;

	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		config = new ConfigReader();
		String url = config.getLoginFormUrl();
		setupDriver(browser, url);
	}

	@AfterMethod
	public void afterMethod() {
		quitDriver();
	}

	@Test(priority = 0)
	public void loginWithValidData() {
		loginPage = new LoginFormPage(driver);
		loginPage.enterLoginDetails("practice", "SuperSecretPassword!");
		securePage = loginPage.clickLoginButton();
		String actualLoginMsg = securePage.getLoginMessage();
		String expectedLoginMsg = "You logged into a secure area!";
		boolean status = actualLoginMsg.equals(expectedLoginMsg);
		Assert.assertTrue(status);
	}

	@Test(priority = 1)
	public void loginWithInvalidUsername() {
		loginPage = new LoginFormPage(driver);
		loginPage.enterLoginDetails("invalidUsername", "SuperSecretPassword!");
		loginPage.clickLoginButton();
		String actualErrorMsg = loginPage.getLoginErrorMessage();
		String expectedErrorMsg = "Your username is invalid!";
		boolean status = actualErrorMsg.equals(expectedErrorMsg);
		Assert.assertTrue(status);
	}

	@Test(priority = 2)
	public void loginWithInvalidPassword() {
		loginPage = new LoginFormPage(driver);
		loginPage.enterLoginDetails("practice", "invalidPassword");
		loginPage.clickLoginButton();
		String actualErrorMsg = loginPage.getLoginErrorMessage();
		String expectedErrorMsg = "Your password is invalid!";
		boolean status = actualErrorMsg.equals(expectedErrorMsg);
		Assert.assertTrue(status);
	}
}