package com.mercury.framework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class commonFunctions {
	
	public static WebDriver driver;
	public static EventFiringWebDriver evtDriver;
	
	@BeforeSuite
	public void beforSuite(){
		Reporter.log("Before Suite", true);
	}

	@BeforeTest
	public void beforTest(){
		Reporter.log("Before Test", true);
	}	
	
	@BeforeMethod
	public void beforMethod(){
		Reporter.log("Before Method", true);
	}
	
	@BeforeClass
	@Parameters({ "url", "browser" })
	public void setup(String url, String browser) {

		switch (browser) {
		case "IExplore":
			System.setProperty("webdriver.ie.driver",
					"Resources/IEDriverServer32bit.exe");
			driver = new InternetExplorerDriver();
			break;
		case "Chrome":
			System.setProperty("webdriver.chrome.driver",
					"Resources/chromedriver32bit.exe");
			driver = new ChromeDriver();
			break;
		case "Firefox":
			try{
			driver = new FirefoxDriver();
			}
			catch(Exception e){
				Reporter.log("Exception is:" + e.getMessage());
			}
			break;
		}
		Reporter.log("Script executing on: " + browser + "browser", true);

		driver.get(url); 
		
		/*if(driver.getCurrentUrl() != url)
		{
			Reporter.log("Page with url: " + url + " not loaded.", true);
			driver.quit();
			
		}*/
			
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		Reporter.log("Url is:" + driver.getCurrentUrl(), true);
		Reporter.log("Title is:" + driver.getTitle(), true);
		
		//Assert.assertEquals("URL did not open", driver.getCurrentUrl(), url);

	}

	@AfterClass
	public void aftrClass() {
		Reporter.log("After class", true);
		driver.quit();
	}	
	
	@AfterSuite
	public void aftrSuite() {
		Reporter.log("After Suite", true);
		
	}
	
	@AfterMethod
	public void close() {
		Reporter.log("After method", true);
		
	}	
	
	public boolean checkWebEle(WebElement ele) {

		/*
		 * if (!ele.isDisplayed()){ Reporter.log("WebElement: " + ele +
		 * " not displayed", true); return false; } else if(!ele.isEnabled()){
		 * Reporter.log("WebElement: " + ele + " not enabled", true); return
		 * false; } else{ Reporter.log("WebElement: " + ele +
		 * " displayed and enabled", true); return true; }
		 */

		Assert.assertTrue(ele.isDisplayed(), "WebElement: " + ele
				+ " not displayed");
		Assert.assertTrue(ele.isEnabled(), "WebElement: " + ele
				+ " not enabled");

		Reporter.log("WebElement: " + ele + " displayed and enabled", true);

		return true;

	}

	public void enterText(WebElement ele, String txt) {

		if (checkWebEle(ele))
			ele.sendKeys(txt);

	}

	public void clickEle(WebElement button) {

		if (checkWebEle(button))
			button.click();

	}

	public static boolean selectOption(String drpdwnName, String optionName) {

		Select contrList = new Select(driver.findElement(By.name(drpdwnName)));
		List<WebElement> options = contrList.getOptions();
		Boolean found = false;
		int optionCount = options.size();

		if (optionCount > 0) {
			for (WebElement option : options) {
				if (option.getText().equals(optionName)) {
					contrList.selectByVisibleText(optionName);
					Reporter.log("Option:" + optionName
							+ "found in the dropdown list:" + drpdwnName, true);
					found = true;
					break;
				}
			}
			if (found != true) {
				Reporter.log("Option:" + optionName
						+ "NOT found in the dropdown list:" + drpdwnName, true);
				return false;
			}

			return true;
		} else {
			Reporter.log("Drop down does not have any options to select");
			return false;
		}

	}

	public static boolean validateAndSelectOption(WebElement drpDwnObj,
			String optionName) {

		Select contrList = new Select(drpDwnObj);
		List<WebElement> options = contrList.getOptions();
		Boolean found = false;
		int optionCount = options.size();

		if (optionCount > 0) {
			for (WebElement option : options) {
				if (option.getText().equals(optionName)) {
					contrList.selectByVisibleText(optionName);
					Reporter.log(
							"Option:" + optionName
									+ "found in the dropdown list:"
									+ drpDwnObj.getText(), true);
					found = true;
					break;
				}
			}
			if (found != true) {
				Reporter.log(
						"Option:" + optionName
								+ "NOT found in the dropdown list:"
								+ drpDwnObj.getText(), true);
				return false;
			}

			return true;
		} else {
			Reporter.log("Drop down does not have any options to select");
			return false;
		}

	}

	public void getAllElements() {

		List<WebElement> editBoxes = driver.findElements(By.tagName("input"));
		List<WebElement> dropDowns = driver.findElements(By.tagName("select"));

		for (WebElement editBox : editBoxes) {
			Reporter.log("Text is : " + editBox.getText() + "Name is : "
					+ editBox.getAttribute("name"), true);
		}
		for (WebElement dropDown : dropDowns) {
			Reporter.log("Text is : " + dropDown.getText() + "Name is : "
					+ dropDown.getAttribute("name"), true);
		}
	}

	public void selectWindow(String titleToMatch) {

		boolean wndFound = false;
		Set<String> wndHandles = driver.getWindowHandles();
		int wndCount = wndHandles.size();
		Reporter.log("Windows Count is :" + wndCount);

		for (String wndHandle : wndHandles) {
			String wndTitle = driver.switchTo().window(wndHandle).getTitle();
			Reporter.log("Window Handle and title are:" + wndHandle + ", "
					+ wndTitle, true);
			if (wndTitle.equalsIgnoreCase(titleToMatch)) {
				driver.switchTo().window(wndHandle);
				Reporter.log("Window title matched and selected child window: "
						+ wndTitle + " ," + wndHandle, true);
				wndFound = true;
				break;
			}
		}
		if (wndFound == false)
			Reporter.log("Window title not matching with any of the windows",
					true);
	}

	public static boolean isTextPresent(String textToVerify) {

		String bodyText;

		bodyText = driver.findElement(By.tagName("body")).getText();
		// Reporter.log(bodyText);

		if (bodyText.contains(textToVerify)) {
			return true;
		} else
			return false;

	}

	public static boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			Reporter.log(e.getMessage(), true);
			return false;

		} finally {

		}
	}

	/*@DataProvider (name = "ExcelData")
	public static Object[][] readExcel(String excelPath, String sheetName, String tableName){
		
		FileInputStream fs = new FileInputStream(excelPath);
		Workbook wb = Workbook.getWorkbook(fs);

		// TO get the access to the sheet
		Sheet sh = wb.getSheet(sheetName);

		// get the table
		Cell startCell = sh.findCell(tableName);
		Cell endCell = sh.findCell(tableName, startCell.getRow(), startCell.getColumn(), 100, 64000, arg5);
		
		// To get the number of rows present in sheet
		int totalNoOfRows = sh.getRows();

		// To get the number of columns present in sheet
		int totalNoOfCols = sh.getColumns();
		
		return Object[][];
		
	}*/

	public static String[][] getTestData(String excelPath, String sheetName,
			String tableName) {

		String[][] testData = null;

		try {

			Workbook workbook = Workbook.getWorkbook(new File(excelPath));
			Sheet sheet = workbook.getSheet(sheetName);

			int ci, cj, rowStart, rowEnd, colStart, colEnd;

			Cell tableStart = sheet.findCell(tableName);

			rowStart = tableStart.getRow();
			colStart = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, colStart + 1,
					rowStart + 1, 100, 64000, false);

			rowEnd = tableEnd.getRow();
			colEnd = tableEnd.getColumn();

			// TestData=new String[10-5-1][10-2-1];
			testData = new String[rowEnd - rowStart - 1][colEnd - colStart - 1];

			ci = 0; // to store data into two dimensional array row wise
			for (int i = rowStart + 1; i < rowEnd; i++, ci++) {

				cj = 0; // to store data into two dimensional array column wise
				for (int j = colStart + 1; j < colEnd; j++, cj++) {
					testData[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return testData;
	}
	
	public static void highlight(WebElement element){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String hlt = " arguments[0].style.border = '4px solid yellow' ";
		jse.executeScript(hlt, element);
	}
	
	public static void takeScreenShot(){
		
		File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try{
			
			String screenShotLoc = "E:\\Selenium-Workspace\\WebDriverProjet\\Resources\\screenshot.png";
			FileUtils.copyFile(screenShot, new File(screenShotLoc));
			
			Reporter.log("<a href = file:///" + screenShotLoc + ">Screen Shot location</a>");
			
		}
		catch(IOException e){
			
			e.printStackTrace();
			
		}
	}
	
	public static String getTimeStamp(){
		Date date = new Date();
		DateFormat dateFrmt = new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
		String curTimeStamp = dateFrmt.format(date);
		return curTimeStamp;
	}


}
