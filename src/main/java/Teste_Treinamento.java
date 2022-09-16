import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Teste_Treinamento {

    @Test
    public void testeTextField(){

//        Drive do firefox
        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                    "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
//        System.getProperty("user.dir"), pega raiz do projeto
//        Importei os arquivos para executar em qualquer maquina.
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

//        Pegando um campo pelo id e usando: sendKeys("escrita") informo o texto do campo.
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Luis Henrique");
//        Validando o campo para ver se o preenchimento esta correto.
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

//        Seleção botão sexo feminino
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());
//        Seleção botão sexo masculino
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        driver.quit();
    }
    @Test
    public void testeComboBox() {

        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

//        Para clicar no combobox e selecionar.
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByVisibleText("2o grau completo");
        /*
        Também posso usar:
        combo.selectByIndex(3);
        combo.selectByValue("superior");
         */
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());

        driver.quit();
    }
    @Test
    public void testeVerificaValoresComboBox() {

        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

//        Para clicar no combobox e selecionar.
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
//        Valida quantas opções existem no ComboBox
        Assert.assertEquals(8, options.size());

//        Valida as opções presentes no ComboBox
        int qtdCampos = 0;
        for (WebElement option: options){
//            for repete e adiciona mais um a cada nome presente no comboBox de acordo com a lista
            List<String> elementosComparacao = List.of("Mestrado", "Doutorado", "Especializacao", "Superior",
            "2o grau completo", "2o grau incompleto", "1o grau completo", "1o grau incompleto");
            if(elementosComparacao.contains(option.getText())){
                qtdCampos++;
            }
        }
//        Após passar por toda a lista ele valida se tem 8 opções presentes
//        Se um da lista não estiver presente ja acusa erro
        Assert.assertEquals(qtdCampos, 8);
        driver.quit();
    }
    @Test
    public void testeComboBoxMultiplaEscolha() {

        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

//      Para clicar no combobox e selecionar mais de uma opção
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Futebol");
        combo.selectByVisibleText("Corrida");

//      Validando as opções
        List<WebElement> options = combo.getOptions();
        int qtdCampos = 0;
        for (WebElement option: options){
            //for repete e adiciona mais um a cada nome presente no comboBox de acordo com a lista
            List<String> elementosComparacao = List.of("Futebol", "Natacao", "Corrida", "Karate", "O que eh esporte?");
            if(elementosComparacao.contains(option.getText())){
                qtdCampos++;
            }
        }
//      Após passar por toda a lista ele valida se tem 5 opções presentes
//      Se um da lista não estiver presente ja acusa erro
        Assert.assertEquals(qtdCampos, 5);
        driver.quit();
    }
    @Test
    public void validaBotao(){

        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

//      Criando elemento do botão para ser mais facil declarar o componente.
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
//      Validando o botão
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

        driver.quit();
    }
    @Test
    @Ignore
    public void testeLinks(){

        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.linkText("Voltar")).click();
    }
    @Test
    public void testeBuscaTextos(){

        System.setProperty("webdriver.gecko.driver", "/home/luis/Documentos/Drivers/" +
                "geckodriver-v0.31.0-linux64/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        Assert.assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Campo de Treinamento"));

        driver.quit();
    }
}