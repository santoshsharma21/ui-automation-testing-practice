/**
 * 
 */
package com.uitestingpractice.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Santosh Sharma
 *
 */
public class DynamicTablePage {

	// initalize driver
	WebDriver driver;

	// page objects
	@FindBy(xpath = "//table[@class='table table-striped']//thead//tr//th")
	private List<WebElement> cols;

	@FindBy(xpath = "//table[@class='table table-striped']//tbody//tr")
	private List<WebElement> rows;

	@FindBy(xpath = "//p[@id='chrome-cpu']")
	private WebElement cpuValue;

	// constructor
	public DynamicTablePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// action method
	public int getNcols() {
		return cols.size();
	}

	public int getNrows() {
		return rows.size();
	}

	public String getCpuValue() {
		return cpuValue.getText();
	}

	public boolean verifyCpuLoad() {
		
		int nrows = getNrows();
		int ncols = getNcols();
		String expectedCpuValue = getCpuValue();
		
		String actualCpuValue = "";

		for (int row = 1; row <= nrows; row++) {
			String rowName = driver
					.findElement(
							By.xpath("//table[@class='table table-striped']//tbody//tr[" + row + "]//td[" + 1 + "]"))
					.getText();
			if (rowName.equalsIgnoreCase("chrome")) {
				for (int col = 1; col <= ncols; col++) {
					String colName = driver
							.findElement(By.xpath("//table[@class='table table-striped']//thead//tr//th[" + col + "]"))
							.getText();
					if (colName.equalsIgnoreCase("cpu")) {
						actualCpuValue = driver.findElement(By.xpath(
								"//table[@class='table table-striped']//tbody//tr[" + row + "]//td[" + col + "]"))
								.getText();
						break;
					}
				}
			}
		}
		boolean status = expectedCpuValue.contains(actualCpuValue);
		return status;
	}
}
