import Factory.DSL;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Factory.DriveFactory.getDriver;
import static Factory.DriveFactory.killDriver;

public class TesteSincronismo {
    private DSL dsl;
    private  CampoTreinamentoPage page;
    @Before
    public void inicializa(){
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }
    @After
    public void termina(){
        killDriver();
    }
    @Test
    public void interageRepostaDemoradaImplicita()throws InterruptedException{
        dsl.clica_botao("buttonDelay");
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.escreve("novoCampo", "Deu certo?");
    }
    @Test
    public void interageRepostaDemoradaExplicita()throws InterruptedException {
        dsl.clica_botao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo", "Deu certo?");
    }
}
