package WebOrderPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class WO_003_LP_03 extends Hooks {

	static String webOrderButtonXPath = "//header/nav[1]/div[1]/div[1]/a[1]";
	static String usernameTextAreaXPath = "//input[@id='login-username-input']";
	static String passwordTextAreaXPath = "//input[@id='login-password-input']";
	static String username = "Inar";
	static String password = "Academy";
	static String loginButtonXPath = "//button[@id='login-button']";
	static String logoutButtonXPath = "//button[@id='logout-button']";

	@Test
	@DisplayName("Testing Logout Process")
	void logoutTest() {
		driver.findElement(By.xpath(webOrderButtonXPath)).click();
		driver.findElement(By.xpath(usernameTextAreaXPath)).sendKeys(username);
		driver.findElement(By.xpath(passwordTextAreaXPath)).sendKeys(password);
		driver.findElement(By.xpath(loginButtonXPath)).click();
		driver.findElement(By.xpath(logoutButtonXPath)).click();
		try {
			driver.findElement(By.xpath("//h2[contains(text(),'Login')]"));
			Assertions.assertTrue(true);
		}
		catch (Exception exception) {
			Assertions.fail();
		}
	}

}
