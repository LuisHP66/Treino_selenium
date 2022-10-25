package Test;

import Factory.DSL;
import Page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static Factory.DriveFactory.getDriver;
import static Factory.DriveFactory.killDriver;

public class TesteFremesJanelas {
    private DSL dsl;
    private CampoTreinamentoPage page;

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
    public void InterageFrame() {
        getDriver().switchTo().frame("frame1");
        page.setBottonFrame();

        getDriver().switchTo().alert();
        Alert alerta = getDriver().switchTo().alert();
        Assert.assertEquals("Frame OK!", alerta.getText());
        alerta.accept();

        getDriver().switchTo().defaultContent();
        page.setNome("Frame OK!");
        Assert.assertEquals("Frame OK!", dsl.ValorCampo("elementosForm:nome"));
    }
    @Test
    public void interageJanela() {
        page.setBottonEasy();
        getDriver().switchTo().window("Popup");
        getDriver().findElement(By.tagName("textarea")).sendKeys("Deu boa?");
        getDriver().close();
        getDriver().switchTo().window("");
        getDriver().findElement(By.tagName("textarea")).sendKeys("Deu boa?");
    }
    @Test
    public void interageJanela_sem_titulo() {
        page.setBottonHard();

        //window espera String, como estava usando objeto usei (String) para funcionar
        //Iria usar o segundo condigo do handles, porém fica mais dinamico assim,
        //pegando o segundo condigo que seria o da janela que queremos.
        getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
        getDriver().findElement(By.tagName("textarea")).sendKeys("Deu boa?");
        getDriver().close();

        getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
        getDriver().findElement(By.tagName("textarea")).sendKeys("Agora?");
    }
}
