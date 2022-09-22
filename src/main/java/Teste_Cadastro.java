import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Teste_Cadastro {
    @Test
    public void Cadastro(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

//      Nome
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Luis Henrique");

//      Sobrenome
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Petsch");

        //        Seleção botão sexo masculino
        driver.findElement(By.id("elementosForm:sexo:0")).click();

//      Escolhendo comida favorita
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

//      Seleção grau de escolaridade
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByVisibleText("2o grau completo");

//      Selecionando os esportes praticados
        element = driver.findElement(By.id("elementosForm:esportes"));
        combo = new Select(element);
        combo.selectByVisibleText("Futebol");
        combo.selectByVisibleText("Corrida");

        //Adicionando sugestões e validando
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("testestestes");

        Assert.assertEquals("Status: Nao cadastrado",
                driver.findElement(By.id("resultado")).getText());
        //Clicando no cadastrar
            driver.findElement(By.id("elementosForm:cadastrar")).click();

        //Validando textos que apareceram apos o cadastro
        Assert.assertEquals("Cadastrado!\n" +
                        "Nome: Luis Henrique\n" +
                        "Sobrenome: Petsch\n" +
                        "Sexo: Masculino\n" +
                        "Comida: Pizza\n" +
                        "Escolaridade: 2graucomp\n" +
                        "Esportes: Futebol Corrida\n" +
                        "Sugestoes: testestestes",
                driver.findElement(By.id("resultado")).getText());
        driver.quit();
    }
}
