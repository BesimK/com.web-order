package WebOrderPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WO_005_CF_02 extends Hooks {

	static String webOrderButtonXPath = "//header/nav[1]/div[1]/div[1]/a[1]";
	static String usernameTextAreaXPath = "//input[@id='login-username-input']";
	static String passwordTextAreaXPath = "//input[@id='login-password-input']";
	static String username = "Inar";
	static String password = "Academy";
	static String loginButtonXPath = "//button[@id='login-button']";

	@Test
	@DisplayName("Testing Calculate Functionality With Invalid Inputs")
	void calculateTestWithoutProperInput() {
		driver.findElement(By.xpath(webOrderButtonXPath)).click();
		driver.findElement(By.xpath(usernameTextAreaXPath)).sendKeys(username);
		driver.findElement(By.xpath(passwordTextAreaXPath)).sendKeys(password);
		driver.findElement(By.xpath(loginButtonXPath)).click();
		driver.findElement(By.xpath("//a[contains(text(),'Order')]")).click();
		WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='productSelect']"));
		Select dropdownElement = new Select(dropdownMenu);
		dropdownElement.selectByValue("ScreenSaver");
		driver.findElement(By.xpath("//input[@id='discountInput']")).sendKeys("20");
		driver.findElement(By.xpath("//button[contains(text(),'Calculate')]")).click();
		try {
			driver.findElement(By.xpath("//em[contains(text(),\"Field 'Quantity' must be greater than zero.\")]"));
		}
		catch (Exception ex) {
			Assertions.fail();
		}
	}

}
