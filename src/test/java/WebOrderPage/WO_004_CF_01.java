package WebOrderPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WO_004_CF_01 extends Hooks {

	static String webOrderButtonXPath = "//header/nav[1]/div[1]/div[1]/a[1]";
	static String usernameTextAreaXPath = "//input[@id='login-username-input']";
	static String passwordTextAreaXPath = "//input[@id='login-password-input']";
	static String username = "Inar";
	static String password = "Academy";
	static String loginButtonXPath = "//button[@id='login-button']";

	@Test
	@DisplayName("Testing Calculate Functionality With Valid Inputs")
	void calculateTest() throws InterruptedException {
		driver.findElement(By.xpath(webOrderButtonXPath)).click();
		driver.findElement(By.xpath(usernameTextAreaXPath)).sendKeys(username);
		driver.findElement(By.xpath(passwordTextAreaXPath)).sendKeys(password);
		driver.findElement(By.xpath(loginButtonXPath)).click();
		driver.findElement(By.xpath("//a[contains(text(),'Order')]")).click();
		WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='productSelect']"));
		Select dropdownElement = new Select(dropdownMenu);
		dropdownElement.selectByValue("HomeDecor");
		driver.findElement(By.xpath("//input[@id='quantityInput']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@id='discountInput']")).sendKeys("15");
		driver.findElement(By.xpath("//button[contains(text(),'Calculate')]")).click();
		String result = driver.findElement(By.xpath("//input[@id='totalInput']")).getAttribute("value");
		String expected = String.valueOf((5 * 150) - ((5 * 150) * 15 / 100));
		Assertions.assertEquals(expected, result);
	}

}
