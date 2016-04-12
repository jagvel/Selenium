package com.actitime.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.pages.HomePage;
import com.actitime.pages.LoginPage;

public class ActiTimeLoginTest {

	public WebDriver driver;

	LoginPage loginPage;
	HomePage homePage;

	@Test
	public void loginOnActiTimeApp() {

		loginPage = PageFactory.initElements(driver, LoginPage.class);

		loginPage.validLogin("user", "user");

		homePage = PageFactory.initElements(driver, HomePage.class);

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.linkText("Logout")));

		Assert.assertTrue(homePage.verifyAt.isDisplayed());

		homePage.logOut();
	}

}
