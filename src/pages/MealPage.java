package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getQuantity() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public WebElement getAddMealBtn() {
		return this.driver.findElement(
				By.xpath("//*[@class='d-flex align-items-center justify-content-between flex-lg--col']/a"));
	}

	public WebElement getFavourite() {
		return this.driver.findElement(By.xpath("//*[@class='product-detail-image']/a"));
	}

//	 methods

	public void addToCard(String quantity) {
		this.getQuantity().sendKeys(Keys.CONTROL, "a");
		this.getQuantity().sendKeys(quantity);
		js.executeScript("arguments[0].click()", this.getAddMealBtn());
	}

	public void addToFavourite() {
		js.executeScript("arguments[0].click()", this.getFavourite());
	}
}
