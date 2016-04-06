package com.mercury.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {
	
	@FindBy(name = "firstName")
	public WebElement firstNameEle;
	
	@FindBy(name = "lastName")
	public WebElement lastNameEle;
	
	@FindBy(name = "phone")
	public WebElement phoneEle;

	@FindBy(name = "userName")
	public WebElement userNameEle;
	
	@FindBy(name = "address1")
	public WebElement address1Ele;

	@FindBy(name = "address2")
	public WebElement address2Ele;
	
	@FindBy(name = "city")
	public WebElement cityEle;
	
	@FindBy(name = "state")
	public WebElement stateEle;
	
	@FindBy(name = "postalCode")
	public WebElement postalCodeEle;
	
	@FindBy(name = "country")
	public WebElement countryEle;
	
	@FindBy(name = "register")
	public WebElement registerEle;
	
	
	

}
