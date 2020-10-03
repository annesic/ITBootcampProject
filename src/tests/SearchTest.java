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

import pages.LocationPopupPage;
import pages.SearchResultPage;

public class SearchTest extends BasicTest {

	@Test
	public void searchTest() throws InterruptedException, IOException {

		LocationPopupPage popupPage = new LocationPopupPage(this.driver, js, waiter);
		SearchResultPage resultPage = new SearchResultPage(this.driver, js, waiter);
		SoftAssert softAssert = new SoftAssert();
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		driver.navigate().to(baseURL + "meals");
		popupPage.setTheLocation("City Center - Albany");

		for (int i = 1; i <= 6; i++) {
			Thread.sleep(1000);
			XSSFRow row = sheet.getRow(i);
			popupPage.openPopup();

			String newLocation = row.getCell(0).getStringCellValue();
			popupPage.setTheLocation(newLocation);
			Thread.sleep(1000);
			String order = row.getCell(1).getStringCellValue();
			driver.navigate().to(order);


			int expectedProductCount =(int) row.getCell(2).getNumericCellValue();
			int actualProductCount = resultPage.getProductSize();
			Assert.assertEquals(actualProductCount, expectedProductCount, "[ERROR] Number of Products is not Equal");
			
			for (int j = 3; j < 3 + row.getCell(2).getNumericCellValue(); j++) {
				String expectedResult = row.getCell(j).getStringCellValue();
				String actualResult = resultPage.getProductNames().get(j - 3);
				softAssert.assertTrue(actualResult.contains(expectedResult), "[ERROR] Product Names Are Different!");
				Thread.sleep(1000);
			}
		}
		softAssert.assertAll();
		wb.close();
		fis.close();
	}
}
