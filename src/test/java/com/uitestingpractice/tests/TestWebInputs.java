package com.uitestingpractice.tests;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.uitestingpractice.base.BaseClass;
import com.uitestingpractice.pages.WebInputsPage;
import com.uitestingpractice.utilities.ConfigReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestWebInputs extends BaseClass {
	
	ConfigReader config;
	Faker faker;
	WebInputsPage webInputsPage;
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		config = new ConfigReader();
		String url = config.getWebInputsUrl();
		setupDriver(browser, url);
	}

	@AfterMethod
	public void afterMethod() {
		quitDriver();
	}

	@Test
	public void validateWebInputs() {
		faker = new Faker();
		int num = faker.number().numberBetween(1, 50);
		String text = faker.name().firstName();
		String password = faker.internet().password();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());;
		webInputsPage = new WebInputsPage(driver);
		webInputsPage.inputValues(num, text, password, date);
		webInputsPage.clickDisplayInputsButton();
		boolean status = webInputsPage.verifyInputValues(num, text, password, date);
		Assert.assertTrue(status);
	}
}