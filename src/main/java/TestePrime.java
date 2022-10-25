import Factory.DSL;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static Factory.DriveFactory.getDriver;
import static Factory.DriveFactory.killDriver;

public class TestePrime {
        private DSL dsl;

    @Before
    public void inicializa(){
        getDriver();
        dsl = new DSL();
    }
    @After
    public void termina(){
        killDriver();
    }

    @Test
    public void deveInteragirComRadioPrime(){
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=deaa6");
        dsl.clicarRadio(By.xpath("//label[.='Option1']/..//span"));
        Assert.assertTrue(dsl.Radio_marcado_verifica("j_idt344:console:0"));
    }
    @Test
    public void deveInteragirComComboPrime(){
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=bfa37");
        dsl.selecaoComboXpath("//input[@id='j_idt343:option_focus']/../..//span");
        dsl.selecaoComboXpath("//ul[@id='j_idt343:option_items']/li[.='Option1']");
        Assert.assertEquals("Option1", dsl.obter_texto(By.id("j_idt343:option_1")));
    }
}
