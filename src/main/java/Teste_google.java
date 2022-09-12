import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Teste_google {

    @Test
    public void teste(){
        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
            driver.quit();
    }
}
