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
public class LoginFormPage {

	// driver
	WebDriver driver;

	// page objects
	@FindBy(id = "username")
	private WebElement usernameTxtBox;

	@FindBy(id = "password")
	private WebElement passwordTxtBox;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	private WebElement loginBtn;

	@FindBy(xpath = "//div[@id='flash']//b")
	private WebElement loginErrorMsg;

	// constructor
	public LoginFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page action methods
	public void enterLoginDetails(String username, String password) {
		usernameTxtBox.clear();
		usernameTxtBox.sendKeys(username);
		passwordTxtBox.clear();
		passwordTxtBox.sendKeys(password);
	}

	public SecurePage clickLoginButton() {
		loginBtn.click();
		return new SecurePage(driver);
	}
	
	public void clickLogin() {
		loginBtn.click();
	}

	public String getLoginErrorMessage() {
		String msg = loginErrorMsg.getText();
		return msg;
	}
}