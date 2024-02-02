/**
 * 
 */
package com.uitestingpractice.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Santosh Sharma
 *
 */
public class ConfigReader {

	private Properties pro;

	public ConfigReader() {
		File fs = new File(System.getProperty("user.dir") + "./configurations/config.properties");
		try {
			FileInputStream fileinput = new FileInputStream(fs);
			pro = new Properties();
			pro.load(fileinput);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getBaseUrl() {
		return pro.getProperty("base_url");
	}
	
	public String getDynamicTableUrl() {
		return pro.getProperty("dynamictable_url");
	}	
	
	public String getWebInputsUrl() {
		return pro.getProperty("webinputs_url");
	}
	
	public String getNotificationMsgUrl() {
		return pro.getProperty("notificationMsg_url"); 
	}
	
	public String getLoginFormUrl() {
		return pro.getProperty("loginform_url"); 
	}
}
