/**
 * 
 */
package com.uitestingpractice.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Santosh Sharma
 *
 */
public class WebInputsPage {
	
	// page objects
	@FindBy(xpath = "//input[@id='input-number']")
	private WebElement numberInput;
	
	@FindBy(xpath = "//input[@id='input-text']")
	private WebElement textInput;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//input[@id='input-date']")
	private WebElement dateInput;
	
	@FindBy(xpath = "//strong[@id='output-number']")
	private WebElement numberOutput;
	
	@FindBy(xpath = "//strong[@id='output-text']")
	private WebElement textOutput;
	
	@FindBy(xpath = "//strong[@id='output-password']")
	private WebElement passwordOutput;
	
	@FindBy(xpath = "//strong[@id='output-date']")
	private WebElement dateOutput;
	
	@FindBy(xpath = "//button[@id='btn-display-inputs']")
	private WebElement displayInputs;
	
	
	// constructor
	public WebInputsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickDisplayInputsButton() {
		displayInputs.click();
	}

	public void inputValues(int num, String txt, String password, String date) {
		// input number
		numberInput.clear();
		numberInput.sendKeys(String.valueOf(num));
		// input text
		textInput.clear();
		textInput.sendKeys(txt);
		// input password
		passwordInput.clear();
		passwordInput.sendKeys(password);
		// input date
		dateInput.clear();
		dateInput.sendKeys(date);
	}
	
	public boolean verifyInputValues(int expectedNum, String expectedTxt, String expectedPassword, String expectedDate) {
		List<Boolean> inputStatusList = new ArrayList<>();
		inputStatusList.add(numberOutput.getText().equals(String.valueOf(expectedNum)));
		inputStatusList.add(textOutput.getText().equals(expectedTxt));
		inputStatusList.add( passwordOutput.getText().equals(expectedPassword));
		
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate expectedDt = LocalDate.parse(expectedDate, format1);
		LocalDate actualDt = LocalDate.parse(dateOutput.getText(), format2);
		inputStatusList.add(actualDt.equals(expectedDt));
		
		boolean status = true;
		Iterator<Boolean> it = inputStatusList.iterator();
		while(it.hasNext()) {
			if(!it.next() == true) {
				status = false;
				break;
			}
		}
		return status;
	}
}
