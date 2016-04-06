package com.actitime.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(name="username")
	public  WebElement userNameField;
	
	@FindBy(name="pwd")
	public  WebElement passwordField;
	
	@FindBy(xpath="//a[@id='loginButton']/div")
	public  WebElement login;
	
	
	public void validLogin(String userName,String password){
		
		userNameField.sendKeys(userName);
		passwordField.sendKeys(password);
		login.click();
		
	}
	
}
