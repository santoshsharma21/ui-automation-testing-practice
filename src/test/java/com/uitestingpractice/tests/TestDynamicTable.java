package com.uitestingpractice.tests;

import org.testng.annotations.Test;

import com.uitestingpractice.base.BaseClass;
import com.uitestingpractice.pages.DynamicTablePage;
import com.uitestingpractice.utilities.ConfigReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestDynamicTable extends BaseClass {
	
	ConfigReader config;
	DynamicTablePage dynamicTablePage;
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		config = new ConfigReader();
		String url = config.getDynamicTableUrl();
		setupDriver(browser,url);
	}

	@AfterMethod
	public void afterMethod() {
		quitDriver();
	}

	@Test
	public void validateCpuLoad() {
		dynamicTablePage = new DynamicTablePage(driver);
		boolean status = dynamicTablePage.verifyCpuLoad();
		Assert.assertTrue(status);
	}
}