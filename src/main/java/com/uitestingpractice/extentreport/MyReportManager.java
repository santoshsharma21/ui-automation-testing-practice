/**
 * 
 */
package com.uitestingpractice.extentreport;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.uitestingpractice.utilities.ConfigReader;

/**
 * @author Santosh Sharma
 *
 */
public class MyReportManager {

	public static ExtentSparkReporter sparkReport;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	ConfigReader config = new ConfigReader();

	public void setupExtentReport() {
		String dateTime = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(new Date());
		String reportName = "Test-Report_" + dateTime + ".html";
		String destFolder = System.getProperty("user.dir") + "/test-report/" + reportName;

		sparkReport = new ExtentSparkReporter(destFolder);
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("Test Execution Report");
		sparkReport.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.AUTHOR,
				ViewName.CATEGORY, ViewName.DEVICE }).apply();

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReport);
		extentReports.setSystemInfo("Project Name", "UI Automation Testing Practice");
		extentReports.setSystemInfo("Base URL", config.getBaseUrl());
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java version", System.getProperty("java.version"));
	}

	public void closeExtentReport() {
		extentReports.flush();
	}
}