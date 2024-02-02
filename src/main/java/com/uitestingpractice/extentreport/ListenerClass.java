/**
 * 
 */
package com.uitestingpractice.extentreport;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.uitestingpractice.base.BaseClass;
import com.uitestingpractice.utilities.TestUtils;

/**
 * @author Santosh Sharma
 *
 */
public class ListenerClass extends MyReportManager implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		setupExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignAuthor("SANTOSH");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - PASSED", ExtentColor.GREEN));
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String throwableMsg = result.getThrowable().getMessage().replaceAll(" ", "-");
			String formatedMsg = "<details> <summary>Throwable Details</summary>" + throwableMsg + "</details>";
			try {
				extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - FAILED", ExtentColor.RED));
				extentTest.fail(MarkupHelper.createLabel(formatedMsg, ExtentColor.RED));
				String imgpath = TestUtils.captureScreen(result.getName(), BaseClass.driver);
				extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(imgpath).build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - SKIPPED", ExtentColor.YELLOW));
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		closeExtentReport();
	}
}