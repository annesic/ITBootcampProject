package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {
	
// methods
	
	public SearchResultPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public List<WebElement> getProducts() {
		return this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

	public ArrayList<String> getProductNames() {
		ArrayList<String> products = new ArrayList<String>();
		for (int i = 0; i < this.getProducts().size(); i++) {
			products.add(getProducts().get(i).getText());
		}
		return products;
	}

	public int getProductSize() {
		return getProducts().size();

	}
}
