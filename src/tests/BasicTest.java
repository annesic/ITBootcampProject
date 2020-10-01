package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class BasicTest {

		protected WebDriver driver;
		protected JavascriptExecutor js;
		protected WebDriverWait waiter;
		protected String baseURL = "http://demo.yo-meals.com/";
		protected String email = "customer@dummyid.com";
		protected String password = "12345678a";

		@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
			this.driver = new ChromeDriver();
			this.driver.manage().window().maximize();
			js = (JavascriptExecutor) this.driver;
			this.waiter = new WebDriverWait(this.driver, 20);
			this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}

		@AfterMethod
		public void afterMethod(ITestResult result) throws IOException {
			if (result.getStatus() == ITestResult.FAILURE) {
				TakesScreenshot scrShot = ((TakesScreenshot) driver);
				File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
				String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss'.png'").format(new Date());
				File DestFile = new File("screenshots/" + fileName);
				FileHandler.copy(SrcFile, DestFile);
			}
		}

		@AfterClass
		public void afterClass() {
			this.driver.close();
		}
	}

