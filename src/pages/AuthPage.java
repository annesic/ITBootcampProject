package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getMyAccountDropDown() {
		return this.driver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/ul/li/a"));
	}

	public WebElement getMyAccountBtn() {
		return this.driver.findElement(By.xpath("//*[@class='my-account-dropdown']/ul/li[1]/a"));
	}

	public WebElement getLogoutBtn() {
		return this.driver.findElement(By.xpath("//*[@class='my-account-dropdown']/ul/li[2]/a"));
	}

//	method

	public void logout() {
		this.getMyAccountDropDown().click();
		this.getLogoutBtn().click();
	}
}
