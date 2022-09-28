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

    @Before
    public void inicializa(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }
    @After
    public void termina(){
        driver.quit();
    }
    @Test
    public void Regra_nome() {
        dsl.clica_botao("elementosForm:cadastrar");
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alerta.getText());
        alerta.accept();
    }

    @Test
    public void Regra_sobrenome() {
        dsl.escreve("elementosForm:nome", "Luis Henrique");
        dsl.clica_botao("elementosForm:cadastrar");
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
        alerta.accept();
    }

    @Test
    public void Regra_sexo() {
        dsl.escreve("elementosForm:nome", "Luis Henrique");
        dsl.escreve("elementosForm:sobrenome", "Petsch");
        dsl.clica_botao("elementosForm:cadastrar");
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alerta.getText());
        alerta.accept();
    }

    @Test
    public void Regra_comidaFavorita() {
        dsl.escreve("elementosForm:nome", "Luis Henrique");
        dsl.escreve("elementosForm:sobrenome", "Petsch");
        dsl.clica_botao("elementosForm:sexo:0");

        //Validação com carne e vegetariano
        dsl.clica_botao("elementosForm:comidaFavorita:0");
        dsl.clica_botao("elementosForm:comidaFavorita:3");
        dsl.clica_botao("elementosForm:cadastrar");
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta.getText());
        alerta.accept();

        //Validação com frango, carne e vegetariano
        dsl.clica_botao("elementosForm:comidaFavorita:1");
        dsl.clica_botao("elementosForm:cadastrar");
        Alert alerta1 = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta1.getText());
        alerta1.accept();

        //Validação com frango e vegetariano
        dsl.clica_botao("elementosForm:comidaFavorita:0");
        dsl.clica_botao("elementosForm:cadastrar");
        Alert alerta2 = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta2.getText());
        alerta2.accept();
     }

    @Test
    public void Regra_esporte() {
        dsl.escreve("elementosForm:nome", "Luis Henrique");
        dsl.escreve("elementosForm:sobrenome", "Petsch");
        dsl.clica_botao("elementosForm:sexo:0");
        dsl.clica_botao("elementosForm:comidaFavorita:0");
        dsl.selecao_combo("elementosForm:esportes", "Natacao");
        dsl.selecao_combo("elementosForm:esportes", "O que eh esporte?");
        dsl.clica_botao("elementosForm:cadastrar");
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta.getText());
        alerta.accept();

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.deselectAll();
        dsl.selecao_combo("elementosForm:esportes", "Futebol");
        dsl.selecao_combo("elementosForm:esportes", "O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta1 = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta1.getText());
        alerta1.accept();

        combo.deselectAll();
        dsl.selecao_combo("elementosForm:esportes", "Corrida");
        dsl.selecao_combo("elementosForm:esportes", "O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta2 = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta2.getText());
        alerta2.accept();

        combo.deselectAll();
        dsl.selecao_combo("elementosForm:esportes", "Karate");
        dsl.selecao_combo("elementosForm:esportes", "O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta3 = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alerta3.getText());
        alerta3.accept();
    }
}