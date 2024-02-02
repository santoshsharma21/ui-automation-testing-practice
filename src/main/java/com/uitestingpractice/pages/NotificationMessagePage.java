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
public class NotificationMessagePage {
	
	// page objects
	@FindBy(xpath = "//a[normalize-space()='Click here']")
	private WebElement clickLinkTxt;
	
	@FindBy(xpath = "//div[@id='flash']//b")
	private WebElement msg;
	
	// constructor
	public NotificationMessagePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// page action methods
	public void clickToGenerateNotificationMsg() {
		clickLinkTxt.click();
	}
	
	public String getNotificationMsg() {
		String nmsg = msg.getText();
		return nmsg;
	}
}