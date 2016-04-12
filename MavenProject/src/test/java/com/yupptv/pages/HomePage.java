package com.yupptv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	@FindBy(className = "lang_pref dropdown-toggle")
	public WebElement langEle;
	
	@FindBy(xpath = "//Input[@name='Telugu']")
	public WebElement TeluguEle;
	



}
