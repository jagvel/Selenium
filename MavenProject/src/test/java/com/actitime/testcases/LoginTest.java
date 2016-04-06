package com.actitime.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.actitime.pages.HomePage;
import com.actitime.pages.LoginPage;

public class LoginTest{
	
	public WebDriver driver;
	
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeClass
	public void setUp(){
		driver=new FirefoxDriver();
		driver.get("http://demo.actitime.com/login.do");
		driver.manage().window().maximize();
	}
	@Test
   public void loginOnActiTimeApp(){
		
		
	    loginPage=PageFactory.initElements(driver, LoginPage.class);
		
		loginPage.validLogin("user","user");
		
		
		
		homePage=PageFactory.initElements(driver, HomePage.class);
		
		Wait<WebDriver> wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Logout")));
		
		Assert.assertTrue (homePage.verifyAt.isDisplayed());
				
		homePage.logOut();			
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}

}
