import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Teste_Treinamento {

    @Test
    public void testeTextField(){

        //Drive do firefox
        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                    "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //System.getProperty("user.dir"), pega raiz do projeto
        //Importei os arquivos para executar em qualquer maquina.
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");


        //Pegando um campo pelo id e usando: sendKeys("escrita") informo o texto do campo.
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Luis Henrique");
        //Validando o campo para ver se o preenchimento esta correto.
        Assert.assertEquals("Luis Henrique", driver.findElement(By.id("elementosForm:nome"))
                .getAttribute("value"));


        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Petsch");
        Assert.assertEquals("Petsch", driver.findElement(By.id("elementosForm:sobrenome"))
                .getAttribute("value"));


        driver.quit();
    }
    @Test
    public void testeCampoSugestao(){

        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");


        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("testestestes");
        Assert.assertEquals("testestestes", driver.findElement(By.id("elementosForm:sugestoes"))
                .getAttribute("value"));


        driver.quit();
    }
    @Test
    public void testeRadioButton() {

        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());


        driver.quit();
    }
}
