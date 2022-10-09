import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteRegras_preenchimento {
    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

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
    public void Regra_nome() {
        page.setCadastrar();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alerta.getText());
        alerta.accept();
    }

    @Test
    public void Regra_sobrenome() {
        page.setNome("Luis Henrique");
        page.setCadastrar();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
        alerta.accept();
    }

    @Test
    public void Regra_sexo() {
        page.setNome("Luis Henrique");
        page.setSobrenome("Petsch");
        page.setCadastrar();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alerta.getText());
        alerta.accept();
    }

    @Test
    public void Regra_comidaFavorita() {
        page.setNome("Luis Henrique");
        page.setSobrenome("Petsch");
        page.setSexoMasculino();

        //Validação com carne e vegetariano
        page.setCarne();
        page.setVegetariano();
        page.setCadastrar();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta.getText());
        alerta.accept();

        //Validação com frango, carne e vegetariano
        page.setFrango();
        page.setCadastrar();
        Alert alerta1 = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta1.getText());
        alerta1.accept();

        //Validação com frango e vegetariano
        page.setCarne();
        page.setCadastrar();
        Alert alerta2 = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta2.getText());
        alerta2.accept();
     }

    @Test
    public void Regra_esporte() {
        page.setNome("Luis Henrique");
        page.setSobrenome("Petsch");
        page.setSexoMasculino();
        page.setCarne();

        page.setNatacao();
        page.setOqEsporte();
        page.setCadastrar();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta.getText());
        alerta.accept();

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.deselectAll();
        page.setFutebol();
        page.setOqEsporte();
        page.setCadastrar();
        Alert alerta1 = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta1.getText());
        alerta1.accept();

        combo.deselectAll();
        page.setCorrida();
        page.setOqEsporte();
        page.setCadastrar();
        Alert alerta2 = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta2.getText());
        alerta2.accept();

        combo.deselectAll();
        page.setKarate();
        page.setOqEsporte();
        page.setCadastrar();
        Alert alerta3 = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta3.getText());
        alerta3.accept();
    }
}