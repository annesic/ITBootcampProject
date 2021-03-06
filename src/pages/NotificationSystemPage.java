package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getMessageElement() {
		return this.driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}
	
	// methods
	
	public String getMessage() {
		return this.getMessageElement().getText();
	}
	
	public void waitUntilMessageDisappears() {
		waiter.until(ExpectedConditions.attributeContains((By.xpath("//*[contains(@class, 'system_message')]")),
				"style", "display: none;"));
	}
}
