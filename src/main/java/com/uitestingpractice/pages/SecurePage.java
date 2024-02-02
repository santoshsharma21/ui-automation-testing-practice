/**
 * 
 */
package com.uitestingpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Santosh Sharma
 *
 */
public class SecurePage {

	// driver
	WebDriver driver;

	// page objects
	@FindBy(xpath = "//div[@id='flash']//b") 
	private WebElement loginMsg;

	@FindBy(xpath = "//a[@class='button secondary radius']//i")
	private WebElement logoutLinkTxt;

	// constructor
	public SecurePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page action methods
	public String getLoginMessage() {
		String msg = loginMsg.getText();
		return msg;
	}

	public LoginFormPage clickLogout() {
		logoutLinkTxt.click();
		return new LoginFormPage(driver);
	}
}