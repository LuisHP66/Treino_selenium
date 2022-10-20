import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {
    private WebDriver driver;
    private DSL dsl;
    private  CampoTreinamentoPage page;

    @Before
    public void inicializa(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }
    @After
    public void termina(){
        driver.quit();
    }
    @Test
    public void testeAlerta(){
        page.setBottonAlert();
//      Mudando foco da tela para o alerta
        Alert alert = driver.switchTo().alert();
//      Armazenando o texo do alerta para escrever depois
        String texto = alert.getText();
//      Validando a mensagem do alerta
        Assert.assertEquals("Alert Simples", alert.getText());
//      Aceitando o alerta
        alert.accept();
//      Escrevendo o texto armazenado no campo especificado
        page.setNome(texto);
    }
    @Test
    public void testeConfirm(){
//      Utilizando a seleção confirmar
        page.setBottonConfirm();
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoAcept());
        Assert.assertEquals("Confirmad", dsl.alertaObterTextoAcept());
        page.setNome("Confirmado");
        driver.findElement(By.id("elementosForm:nome")).clear();

//      Utilizando a seleção cancelar
        page.setBottonConfirm();
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoAcept());
        Assert.assertEquals("Negado", dsl.alertaObterTextoAcept());
        page.setNome("Negado");
    }
    @Test
    public void testePronpt(){
        //Maneira que Everton explicou para time
        //WebDriverWait wait = new WebDriverWait(driver,10);
        //wait.until(web -> {return web.findElement(By.id("prompt")).isDisplayed();});

        page.setBottonPronpt();
        Assert.assertEquals("Digite um numero", dsl.ObterTextoDigitaDimiss("11"));
        Assert.assertEquals("Era null?", dsl.alertaObterTextoDimiss());
        Assert.assertEquals(":(", dsl.alertaObterTextoAcept());

        page.setBottonPronpt();
        Assert.assertEquals("Digite um numero", dsl.ObterTextoDigitaDimiss("11"));
        Assert.assertEquals("Era null?", dsl.alertaObterTextoAcept());
        Assert.assertEquals(":D", dsl.alertaObterTextoAcept());

        page.setBottonPronpt();
        Assert.assertEquals("Digite um numero", dsl.ObterTextoDigitaAcept("11"));
        Assert.assertEquals("Era 11?", dsl.alertaObterTextoAcept());
        Assert.assertEquals(":D", dsl.alertaObterTextoAcept());

        page.setBottonPronpt();
        Assert.assertEquals("Digite um numero", dsl.ObterTextoDigitaAcept("11"));
        Assert.assertEquals("Era 11?", dsl.alertaObterTextoDimiss());
        Assert.assertEquals(":(", dsl.alertaObterTextoAcept());
    }
}
