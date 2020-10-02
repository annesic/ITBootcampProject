package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {
	
	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getLocationHeader() {
		return this.driver.findElement(By.xpath("//*[@class='location-selector']/a"));
	}

	public WebElement getCloseElement() {
		return this.driver.findElement(By.className("close-btn-white"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmitButton() {
		return this.driver.findElement(By.name("btn_submit"));
	}
	
//	 methods
	
	public void openPopup() {
		this.getLocationHeader().click();
	}

	public void setTheLocation(String locationName) {
		this.getKeyword().click();
		String argumentValue = this.getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), argumentValue);
		js.executeScript("arguments[0].click()", this.getSubmitButton());
	}

	public void closePopup() {
		this.getCloseElement().click();
	}
}

