package com.mercury.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.mercury.framework.commonFunctions;
import com.mercury.pages.HomePage;
import com.mercury.pages.RegisterPage;

public class RegisterTest extends commonFunctions {

	HomePage homePg;
	RegisterPage regPg;

	@Test
	public void registerUser() {

		homePg = PageFactory.initElements(driver, HomePage.class);
		homePg.registerEle.click();

		regPg = PageFactory.initElements(driver, RegisterPage.class);

		enterText(regPg.firstNameEle, "Venku");
		enterText(regPg.lastNameEle, "Podduri");
		enterText(regPg.phoneEle, "43434334");
		enterText(regPg.userNameEle, "ven_erte");
		enterText(regPg.address1Ele, "house colony");
		enterText(regPg.cityEle, "blr");
		enterText(regPg.stateEle, "ka");
		enterText(regPg.postalCodeEle, "34343434");

		validateAndSelectOption(regPg.countryEle, "INDIA");
		validateAndSelectOption(regPg.countryEle, "ANGOLA");

		clickEle(regPg.registerEle);
	}

}
