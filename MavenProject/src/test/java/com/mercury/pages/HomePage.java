package com.mercury.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	@FindBy(linkText = "REGISTER")
	public WebElement registerEle;
	
	
/*	
public void validLogin(String userName,String password){
		
		userNameField.sendKeys(userName);
		passwordField.sendKeys(password);
		login.click();
		
	}*/

}
