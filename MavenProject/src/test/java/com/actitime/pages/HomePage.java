package com.actitime.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(linkText="Logout")
	public WebElement logOut;
	
	@FindBy(xpath="//span[text()='New']")
	public WebElement verifyAt;
	
	public void logOut(){
		
		
		logOut.click();
	}
}
