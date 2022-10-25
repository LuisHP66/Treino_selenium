package Factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriveFactory {
    private static WebDriver driver;

    private DriveFactory(){}

    public static WebDriver getDriver(){
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }
        return driver;
    }
    public static void killDriver(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
