package com.yupptv.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.mercury.framework.commonFunctions;
import com.yupptv.pages.HomePage;

public class LangTest extends commonFunctions {

	HomePage homePg;

	@Test
	public void selectLang() {

		homePg = PageFactory.initElements(driver, HomePage.class);
		// homePg.langEle.click();

		// homePg.TeluguEle.

		WebElement ele = driver.findElement(By
				//.xpath("//div/div/a[@class = 'lang_pref dropdown-toggle']"));
				.xpath("//div/a[@class = 'lang_pref dropdown-toggle']"));
		ele.click();

		WebElement tel = driver.findElement(By
				//.xpath("//div/ul/li[2]/label/input[@name='Telugu']"));
				.xpath("//div/ul/li[2]/label/input[@name='Telugu']"));
		tel.click();

	}

}
