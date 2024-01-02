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

public class WO_013_OP_06 extends Hooks{
    List<String> orderInformation = new ArrayList<>();

    @Test
    @DisplayName("Testing Invalid State Name")
    void testInvalidStateName() throws InterruptedException {
        // Name
        orderInformation.add("Inar Academy");
        // Prod name
        orderInformation.add("MyMoney");
        // quantity
        orderInformation.add("8");
        // date
        orderInformation.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(LocalDate.now()));
        // Street
        orderInformation.add("1100 Congress Ave.");
        // City
        orderInformation.add("Austin");
        // State
        orderInformation.add("+%&^'/*?#");
        // Valid Zip code
        orderInformation.add("78701");
        // Valid Card Type
        orderInformation.add("Visa");
        // Valid Card Number (Visa starts with: 4, Mastercard starts with: 5, American
        // Express starts with: 34, 37.)
        orderInformation.add("4938281746192845");
        // Valid Card Expire Date (format must be mm/yy)
        orderInformation.add("11/28");

        // 2. Click on Weborder link
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
        discountInputField.sendKeys("20");

        // Click on the "Calculate" button.
        WebElement calculateButton = driver.findElement(By.xpath("//button[text()='Calculate']"));
        calculateButton.click();

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
        WebElement visaCheckbox = driver.findElement(By.id("visa"));
        visaCheckbox.click();

        WebElement cardNumberField = driver.findElement(By.id("cardNumber"));
        cardNumberField.sendKeys(orderInformation.get(9));

        WebElement expiryDateField = driver.findElement(By.id("expiryDate"));
        expiryDateField.sendKeys(orderInformation.get(10));

        // click on "process" button
        WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
        processButton.click();

        // check confirmation message
        try {
            WebElement confirmationElement = driver.findElement(By.cssSelector("div[role='alert']"));
            Assertions.fail();
        } catch (Exception ex) {
            Assertions.assertTrue(true);
        }
    }

}