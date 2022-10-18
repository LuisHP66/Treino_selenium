import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

        page.setCarne();
        page.setVegetariano();
        page.setCadastrar();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta.getText());
        alerta.accept();
     }

    @Test
    public void Regra_esporte() {
        page.setNome("Luis Henrique");
        page.setSobrenome("Petsch");
        page.setSexoMasculino();
        page.setCarne();

        page.setEsporte("Natacao", "O que eh esporte?");
        page.setCadastrar();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta.getText());
        alerta.accept();
    }
}