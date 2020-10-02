package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

// webelement & select
	
	public WebElement getCountry() {
		return this.driver.findElement(By.id("user_country_id"));
	}

	public Select getCountrySelected() {
		Select select = new Select(this.getCountry());
		return select;
	}

	public WebElement getState() {
		return this.driver.findElement(By.id("user_state_id"));
	}

	public Select getStateSelected() {
		Select select = new Select(this.getState());
		return select;
	}

	public WebElement getCity() {
		return this.driver.findElement(By.id("user_city"));
	}

	public Select getCitySelected() {
		Select select = new Select(this.getCity());
		return select;
	}

	public WebElement getSubmitButton() {
		return this.driver.findElement(
				By.xpath("//*[@class='col-lg-12 col-md-12 col-sm-12 col-lg-12 align--right']/fieldset/input"));
	}

//	photo 

	public WebElement getPhotoUploadButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[1]"));
	}

	public WebElement getUpload() {
		return this.driver.findElement(By.xpath("//*[@id='form-upload']/input"));
	}

	public void uploadPhoto(String photoPath) {
		js.executeScript("arguments[0].click();", this.getPhotoUploadButton());
		this.getUpload().sendKeys(photoPath);
	}

	public WebElement getRemovePhotoBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]"));
	}

	public void removePhoto() {
		js.executeScript("arguments[0].click();", this.getRemovePhotoBtn());
	}

// change information method
	
	public void changeInformation(String firstname, String lastname, String address, String phone, String zipcode,
			String country, String state, String city) throws InterruptedException {

		this.getFirstName().sendKeys(Keys.CONTROL, "a");
		this.getFirstName().sendKeys(firstname);
		this.getLastName().sendKeys(Keys.CONTROL, "a");
		this.getLastName().sendKeys(lastname);
		this.getAddress().sendKeys(Keys.CONTROL, "a");
		this.getAddress().sendKeys(address);
		this.getPhone().sendKeys(Keys.CONTROL, "a");
		this.getPhone().sendKeys(phone);
		this.getZipCode().sendKeys(Keys.CONTROL, "a");
		this.getZipCode().sendKeys(zipcode);

		this.getCountrySelected().selectByVisibleText(country);
		Thread.sleep(1500);
		this.getStateSelected().selectByVisibleText(state);
		Thread.sleep(1500);
		this.getCitySelected().selectByVisibleText(city);
		Thread.sleep(1500);

		js.executeScript("arguments[0].click()", this.getSubmitButton());

	}
}

