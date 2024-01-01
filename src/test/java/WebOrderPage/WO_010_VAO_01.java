package WebOrderPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * 1-) Open the URL 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the view all order page. 5-) Click "Add
 * More Data" "4" times. 6-) Click "Check All" button. 7-) Verify all orders selected.
 */
public class WO_010_VAO_01 extends Hooks {

	static String webOrderButtonXPath = "//header/nav[1]/div[1]/div[1]/a[1]";
	static String usernameTextAreaXPath = "//input[@id='login-username-input']";
	static String passwordTextAreaXPath = "//input[@id='login-password-input']";
	static String username = "Inar";
	static String password = "Academy";
	static String loginButtonXPath = "//button[@id='login-button']";

	List<String> orderInformation = new ArrayList<>();

	@Test
	@DisplayName("Testing Add Order Button ")
	void addOrderButton() throws InterruptedException {
		driver.findElement(By.xpath(webOrderButtonXPath)).click();
		driver.findElement(By.xpath(usernameTextAreaXPath)).sendKeys(username);
		driver.findElement(By.xpath(passwordTextAreaXPath)).sendKeys(password);
		driver.findElement(By.xpath(loginButtonXPath)).click();
		String result = driver.findElement(By.xpath("//h1[@id='welcome-heading']")).getText();
		String expected = "Welcome, Inar!";
		//
		Assertions.assertEquals(expected, result);
		driver.findElement(By.xpath("//a[contains(text(),'View all orders')]")).click();
		for (int i = 0; i < 4; i++) {
			driver.findElement(By.xpath("//button[contains(text(),'Add More Data')]")).click();
		}

		driver.findElement(By.xpath("//button[contains(text(),'Check All')]")).click();
		for (int i = 1; i < 5; i++) {
			boolean lo = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div[1]/input[1]")).isSelected();
			Assertions.assertTrue(lo);
		}
	}

}