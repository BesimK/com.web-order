package WebOrderPage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class Hooks {

	protected static WebDriver driver;
	static String username = "InarAcademy";
	static String password = "Fk160621.";
	static String baseURL = "test.inar-academy.com/";

	@BeforeAll
	public static void setUpBeforeTest() {
		String browser = System.getProperty("browser", "chrome");
		switch (browser.toLowerCase()) {
			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--start-maximized", "--disable-notifications");
				driver = new FirefoxDriver(firefoxOptions);
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--start-maximized", "--disable-notifications");
				driver = new EdgeDriver(edgeOptions);
				break;
			default:
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized", "--disable-notifications");
				driver = new ChromeDriver(chromeOptions);
				break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://" + username + ":" + password + "@" + baseURL);
	}

	@AfterAll
	public static void tearDownAfterTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}