package WebOrderPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WO_002_LP_02 extends Hooks {

	static String webOrderButtonXPath = "//header/nav[1]/div[1]/div[1]/a[1]";
	static String usernameTextAreaXPath = "//input[@id='login-username-input']";
	static String passwordTextAreaXPath = "//input[@id='login-password-input']";
	static String username = "Inar";
	static String password = "Academy";
	static String loginButtonXPath = "//button[@id='login-button']";

	@Test
	@DisplayName("Login With Invalid Username")
	void invalidUserName() {
		driver.findElement(By.xpath(webOrderButtonXPath)).click();
		driver.findElement(By.xpath(usernameTextAreaXPath)).sendKeys(username + "X");
		driver.findElement(By.xpath(passwordTextAreaXPath)).sendKeys(password);
		driver.findElement(By.xpath(loginButtonXPath)).click();
		WebElement alert1 = driver.findElement(By.xpath("//div[@id=\"username-error-alert\"]"));
		WebElement alert2 = driver.findElement(By.xpath("//div[@id=\"password-error-alert\"]"));
		Assertions.assertNotNull(alert1);
		Assertions.assertNotNull(alert2);
		driver.navigate().back();
	}

	@Test
	@DisplayName("Login With Invalid Password")
	void invalidPassword() {
		driver.findElement(By.xpath(webOrderButtonXPath)).click();
		driver.findElement(By.xpath(usernameTextAreaXPath)).sendKeys(username);
		driver.findElement(By.xpath(passwordTextAreaXPath)).sendKeys(password + "X");
		driver.findElement(By.xpath(loginButtonXPath)).click();
		WebElement alert1 = driver.findElement(By.xpath("//div[@id=\"username-error-alert\"]"));
		WebElement alert2 = driver.findElement(By.xpath("//div[@id=\"password-error-alert\"]"));
		Assertions.assertNotNull(alert1);
		Assertions.assertNotNull(alert2);
		driver.navigate().back();
	}

	@Test
	@DisplayName("Login With Invalid Credentials")
	void invalidCredentials() {
		driver.findElement(By.xpath(webOrderButtonXPath)).click();
		driver.findElement(By.xpath(usernameTextAreaXPath)).sendKeys(username + "X");
		driver.findElement(By.xpath(passwordTextAreaXPath)).sendKeys(password + "X");
		driver.findElement(By.xpath(loginButtonXPath)).click();
		WebElement alert1 = driver.findElement(By.xpath("//div[@id=\"username-error-alert\"]"));
		WebElement alert2 = driver.findElement(By.xpath("//div[@id=\"password-error-alert\"]"));
		Assertions.assertNotNull(alert1);
		Assertions.assertNotNull(alert2);
		driver.navigate().back();
	}

}
