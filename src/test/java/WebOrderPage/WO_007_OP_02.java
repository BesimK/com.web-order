package WebOrderPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*  1-) Open the URL.
    2-) Click "WebOrder" button on top bar.
    3-) Enter valid username "Inar" and password "Academy".
    4-) Navigate to the order page.
    5-) Select "FamilyAlbum" from Product dropdown.
    6-) Enter "3" as quantity number.
    7-) Enter "17" as discount percentage.
    8-) Enter "Inar Academy" as Name.
    9-) Enter "1100 Congress Ave" as Street.
    10-) Enter "Austin" as City.
    11-) Enter "TX" State.
    12-) Enter "78701" as Zip Code(number).
    13-) Select "Mastercard" as Card Type.
    14-) Enter "5162738261027163" as Card Number.
    15-) Enter "11/28" Expire Date(mm/yy format)
    16-) Click "Process"" button.
    17-) Verify the invalid Product Information error message is displayed.
 */
public class WO_007_OP_02 extends Hooks {

	List<String> orderInformation = new ArrayList<>();

	@Test
	@DisplayName("Testing Process Button Without Calculating Price")
	void clickProcessButtonWithoutCalculating() throws InterruptedException {
		// Name
		orderInformation.add("Inar Academy");
		// Prod name
		orderInformation.add("FamilyAlbum");
		// quantity
		orderInformation.add("3");
		// date
		orderInformation.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(LocalDate.now()));
		// Street
		orderInformation.add("1100 Congress Ave.");
		// City
		orderInformation.add("Austin");
		// State
		orderInformation.add("TX");
		// Valid Zip code
		orderInformation.add("78701");
		// Valid Card Type
		orderInformation.add("Mastercard");
		// Valid Card Number (Visa starts with: 4, Mastercard starts with: 5, American
		// Express starts with: 34, 37.)
		orderInformation.add("5162738261027163");
		// Valid Card Expire Date (format must be mm/yy)
		orderInformation.add("11/28");

		// 2. Click on weborder link
		WebElement webOrderLink = driver.findElement(By.xpath("//a[@href='/weborder']"));
		webOrderLink.click();

		// 3. Enter "Inar" as username and "Academy" password.
		WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
		WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

		userNameInputField.sendKeys("Inar");
		passwordInputField.sendKeys("Academy");

		// Click on the "Login" button.
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		// Verify that the user is successfully logged in.
		WebElement heading = driver.findElement(By.id("welcome-heading"));
		String headingText = heading.getText();

		Assertions.assertEquals("Welcome, Inar!", headingText, "Heading Text is wrong");

		// 4 - Navigate to the order page.
		WebElement orderTabLink = driver.findElement(By.cssSelector("#order-tab > a"));
		orderTabLink.click();

		// select product as MyMoney
		WebElement productDropdownElement = driver.findElement(By.id("productSelect"));
		Select productDropdown = new Select(productDropdownElement);

		productDropdown.selectByVisibleText(orderInformation.get(1));

		// Enter "8" as quantity number.
		WebElement quantityInputField = driver.findElement(By.id("quantityInput"));
		quantityInputField.sendKeys(orderInformation.get(2));

		// Enter "20" as discount percentage.
		WebElement discountInputField = driver.findElement(By.id("discountInput"));
		discountInputField.sendKeys("17");

		// Click on the "Calculate" button.
		WebElement calculateButton = driver.findElement(By.xpath("//button[text()='Calculate']"));
		// calculateButton.click();

		// Enter customer information
		WebElement nameField = driver.findElement(By.id("name"));
		WebElement streetField = driver.findElement(By.id("street"));
		WebElement cityField = driver.findElement(By.id("city"));
		WebElement stateField = driver.findElement(By.id("state"));
		WebElement zipCodeField = driver.findElement(By.id("zip"));

		nameField.sendKeys(orderInformation.get(0));
		streetField.sendKeys(orderInformation.get(4));
		cityField.sendKeys(orderInformation.get(5));
		stateField.sendKeys(orderInformation.get(6));
		zipCodeField.sendKeys(orderInformation.get(7));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1000)");
		Thread.sleep(1000);

		// Enter payment info
		WebElement visaCheckbox = driver.findElement(By.id("mastercard"));
		visaCheckbox.click();

		WebElement cardNumberField = driver.findElement(By.id("cardNumber"));
		cardNumberField.sendKeys(orderInformation.get(9));

		WebElement expiryDateField = driver.findElement(By.id("expiryDate"));
		expiryDateField.sendKeys(orderInformation.get(10));

		// click on "process" button
		WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
		processButton.click();

		js.executeScript("window.scroll(0,-1500)");
		Thread.sleep(1000);

		// verify confirmation message
		WebElement confirmationElement = driver
			.findElement(By.xpath("//em[contains(text(),'Fix errors in Product Information')]"));
		String message = confirmationElement.getText();
		String expectedMsg = "Fix errors in Product Information";
		Assertions.assertEquals(expectedMsg, message, "Confirmation Message is wrong!");
	}

}
