package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;

public class MealItemTest extends BasicTest {

	@Test(priority = 0)
	public void addMealToCartTest() throws InterruptedException {

		LocationPopupPage popupPage = new LocationPopupPage(this.driver, js, waiter);
		NotificationSystemPage notificationPage = new NotificationSystemPage(this.driver, js, waiter);
		MealPage mealPage = new MealPage(this.driver, js, waiter);

		driver.navigate().to(baseURL + "meal/lobster-shrimp-chicken-quesadilla-combo");
		popupPage.closePopup();
		mealPage.addToCard("6");
		Thread.sleep(500);
		Assert.assertTrue(notificationPage.getMessage().contains("The Following Errors Occurred:" + 
				 "\n" + "Please Select Location"));
		notificationPage.waitUntilMessageDisappears();
		popupPage.openPopup();
		popupPage.setTheLocation("City Center - Albany");
		Thread.sleep(2000);
		mealPage.addToCard("3");
		Assert.assertTrue(notificationPage.getMessage().contains("Meal Added To Cart"),
				"[ERROR] Meal hasn`t been Added To Cart!");
	}

	@Test(priority = 3)
	public void addMealToFavouriteTest() throws InterruptedException {

		LocationPopupPage popupPage = new LocationPopupPage(this.driver, js, waiter);
		NotificationSystemPage notificationPage = new NotificationSystemPage(this.driver, js, waiter);
		MealPage mealPage = new MealPage(this.driver, js, waiter);
		LoginPage loginPage = new LoginPage(this.driver, js, waiter);

		driver.navigate().to(baseURL + "meal/lobster-shrimp-chicken-quesadilla-combo");
		popupPage.closePopup();
		mealPage.addToFavourite();
		Assert.assertTrue(notificationPage.getMessage().contains("Please login first!"), "[ERROR] occured!");
		notificationPage.waitUntilMessageDisappears();
		Thread.sleep(1500);

		driver.navigate().to(baseURL + "/guest-user/login-form");
		loginPage.login(email, password);
		driver.navigate().to(baseURL + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addToFavourite();
		Thread.sleep(1500);
		Assert.assertTrue(notificationPage.getMessage().contains("Product has been added to your favorites."),
				"[ERROR] occured!");
	}
	
	@Test(priority = 6)
	public void clearCartTest() throws InterruptedException, IOException {

		LocationPopupPage popupPage = new LocationPopupPage(this.driver, js, waiter);
		NotificationSystemPage notificationPage = new NotificationSystemPage(this.driver, js, waiter);
		MealPage mealPage = new MealPage(this.driver, js, waiter);
		CartSummaryPage cartPage = new CartSummaryPage(this.driver, js, waiter);
		SoftAssert softAssert = new SoftAssert();
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(1);

		driver.navigate().to(baseURL + "meals");
		popupPage.setTheLocation("City Center - Albany");
		Thread.sleep(1500);
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);	
			String meal = row.getCell(0).getStringCellValue();
			driver.navigate().to(meal);
			mealPage.addToCard("1");
			softAssert.assertTrue(notificationPage.getMessage().contains("Meal Added To Cart"), "[ERROR] Cart is empty!");
		}
		
		Thread.sleep(1500);
		softAssert.assertAll();
		
		cartPage.allClear();
		Assert.assertTrue(notificationPage.getMessage().contains("All meals removed from Cart successfully"),
				"[ERROR] meals removal failure!");

		wb.close();
		fis.close();
	}
}
