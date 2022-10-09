import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFremes_Janelas {
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
    public void InterageFrame() {
        driver.switchTo().frame("frame1");
        page.setBottonFrame();

        driver.switchTo().alert();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Frame OK!", alerta.getText());
        alerta.accept();

        driver.switchTo().defaultContent();
        page.setNome("Frame OK!");
        Assert.assertEquals("Frame OK!", dsl.ValorCampo("elementosForm:nome"));
    }
    @Test
    public void interageJanela() {
        page.setBottonEasy();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu boa?");
        driver.close();
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu boa?");
    }
    @Test
    public void interageJanela_sem_titulo() {
        page.setBottonHard();

        //window espera String, como estava usando objeto usei (String) para funcionar
        //Iria usar o segundo condigo do handles, porém fica mais dinamico assim,
        //pegando o segundo condigo que seria o da janela que queremos.
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu boa?");
        driver.close();

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("Agora?");
    }
}