package StepDefination;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class browserSetups {

	public static WebDriver driver;

	@Before
	public static void setUp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("http://172.17.1.53:9023/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	}

	@After
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}