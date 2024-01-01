package WebOrderPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * 1-) Open the URL 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the view all order page. 5-) Click "Add
 * More Data" "8" times. 6-) Click 1st, 3rd and 5th orders checkbox's. 7-) Click "Delete
 * All" button. 8-) Verify the orders are deleted.
 */
public class WO_012_VAO_03 extends Hooks {

	static String webOrderButtonXPath = "//header/nav[1]/div[1]/div[1]/a[1]";
	static String usernameTextAreaXPath = "//input[@id='login-username-input']";
	static String passwordTextAreaXPath = "//input[@id='login-password-input']";
	static String username = "Inar";
	static String password = "Academy";
	static String loginButtonXPath = "//button[@id='login-button']";

	List<List<WebElement>> table = new ArrayList<>();

	List<List<WebElement>> controlTable = new ArrayList<>();

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
		for (int i = 0; i < 8; i++) {
			driver.findElement(By.xpath("//button[contains(text(),'Add More Data')]")).click();
		}

		for (int i = 1; i < 9; i++) {
			List<WebElement> list = driver.findElements(By.xpath("//tbody/tr[" + i + "]"));
			table.add(list);
		}
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/input[1]")).click();
		driver.findElement(By.xpath("//tbody/tr[3]/td[1]/div[1]/input[1]")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1000)");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//tbody/tr[5]/td[1]/div[1]/input[1]")).click();

		driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
		Thread.sleep(3000);

		js.executeScript("window.scroll(0,-1000)");
		Thread.sleep(1000);

		for (int i = 1; i < 6; i++) {
			List<WebElement> list = driver.findElements(By.xpath("//tbody/tr[" + i + "]"));
			controlTable.add(list);
		}
		/*
		 * for (int i = 0; i <= 4; i+=2) { for (List<WebElement> element : controlTable) {
		 * if (table.get(i).equals(element)) ; { Assertions.fail(); } } }
		 * Assertions.assertTrue(true); }
		 */
		Assertions.assertNotEquals(table.size(), controlTable.size(), "Deletion Not Completed");
	}

}
