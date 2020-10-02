package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {
	
	@Test(priority = 0)
	public void editProfileTest() throws InterruptedException {

		LocationPopupPage popupPage = new LocationPopupPage(this.driver, js, waiter);
		LoginPage loginPage = new LoginPage(this.driver, js, waiter);
		NotificationSystemPage notificationPage = new NotificationSystemPage(this.driver, js, waiter);
		ProfilePage profilePage = new ProfilePage(this.driver, js, waiter);
		AuthPage authPage = new AuthPage(this.driver, js, waiter);

		driver.navigate().to(baseURL + "/guest-user/login-form");
		popupPage.closePopup();
		loginPage.login(email, password);
		Assert.assertTrue(notificationPage.getMessage().contains("Login Successfull"), "[ERROR] Login failure!");

		driver.navigate().to(baseURL + "/member/profile");
		profilePage.changeInformation("Marcus", "Kraus", "Strassen 57", "+32485967411", "42100", "United Kingdom",
				"Bristol", "Avon");
		Thread.sleep(500);

		Assert.assertTrue(notificationPage.getMessage().contains("Setup Successful"), "[ERROR] Setup failure!");
		notificationPage.waitUntilMessageDisappears();

		authPage.logout();
		Assert.assertTrue(notificationPage.getMessage().contains("Logout Successfull!"), "[ERROR] Logout failure!");
		notificationPage.waitUntilMessageDisappears();
	}

	@Test(priority = 3)
	public void profileImageTest() throws InterruptedException, IOException {

		LocationPopupPage popupPage = new LocationPopupPage(this.driver, js, waiter);
		LoginPage loginPage = new LoginPage(this.driver, js, waiter);
		NotificationSystemPage notificationPage = new NotificationSystemPage(this.driver, js, waiter);
		ProfilePage profilePage = new ProfilePage(this.driver, js, waiter);
		AuthPage authPage = new AuthPage(this.driver, js, waiter);

		driver.navigate().to(baseURL + "/guest-user/login-form");
		popupPage.closePopup();
		loginPage.login(email, password);
		Assert.assertTrue(notificationPage.getMessage().contains("Login Successfull"), "[ERROR] Login failure!");

		driver.navigate().to(baseURL + "/member/profile");
		String imagePath = new File("images/projectPhoto.jpg").getCanonicalPath();
		profilePage.uploadPhoto(imagePath);
		waiter.until(ExpectedConditions.visibilityOf(notificationPage.getMessageElement()));
		Assert.assertTrue(notificationPage.getMessage().contains("Profile Image Uploaded Successfully"),
				"[ERROR] Photo Upload failure!");
		notificationPage.waitUntilMessageDisappears();

		profilePage.removePhoto();
		Assert.assertTrue(notificationPage.getMessage().contains("Profile Image Deleted Successfully"),
				"[ERROR] Photo Removal failure!");
		notificationPage.waitUntilMessageDisappears();
		authPage.logout();
		Assert.assertTrue(notificationPage.getMessage().contains("Logout Successfull!"), "[ERROR] Logout failure!");
		notificationPage.waitUntilMessageDisappears();
	}
}
