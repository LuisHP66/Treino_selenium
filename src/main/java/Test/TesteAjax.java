package Test;

import Factory.DSL;
import Factory.DriveFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Factory.DriveFactory.*;

public class TesteAjax {
    private DSL dsl;
    @Before
    public void inicializa(){
        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=6b6a7");
        dsl = new DSL();
    }

    @After
    public void termina(){
        killDriver();
    }
    @Test
    public void testAjax(){
        dsl.escreve("j_idt343:name", "Teste");
        dsl.clica_botao("j_idt343:j_idt347");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.id("j_idt343:display"), "Teste"));
        Assert.assertEquals("Teste", dsl.obter_texto(By.id("j_idt343:display")));
    }
}
