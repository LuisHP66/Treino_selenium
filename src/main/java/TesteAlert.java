import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TesteAlert {
    @Test
    public void testeAlerta(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("alert")).click();
//      Mudando foco da tela para o alerta
        Alert alert = driver.switchTo().alert();
//      Armazenando o texo do alerta para escrever depois
        String texto = alert.getText();
//      Validando a mensagem do alerta
        Assert.assertEquals("Alert Simples", alert.getText());
//      Aceitando o alerta
        alert.accept();
//      Escrevendo o texto armazenado no campo especificado
        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);

        driver.quit();
    }
    @Test
    public void testeConfirm(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

//      Utilizando a seleção confirmar
        driver.findElement(By.id("confirm")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alerta.getText());
        alerta.accept();
//      Aumentando tempo limite para achar o componente
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals("Confirmado", alerta.getText());
        alerta.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Confirmado");
        driver.findElement(By.id("elementosForm:nome")).clear();

//      Utilizando a seleção cancelar
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("confirm")).click();
        Assert.assertEquals("Confirm Simples", alerta.getText());
        alerta.dismiss();
//      Aumentando tempo limite para achar o componente
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals("Negado", alerta.getText());
        alerta.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Negado");

        driver.quit();
    }
    @Test
    public void testePronpt(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //Maneira que Everton explicou para time
        //WebDriverWait wait = new WebDriverWait(driver,10);
        //wait.until(web -> {return web.findElement(By.id("prompt")).isDisplayed();});

        driver.findElement(By.id("prompt")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("11");
        alerta.dismiss();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(web -> {return web.switchTo().alert();});
        Assert.assertEquals("Era null?", alerta.getText());
        alerta.dismiss();
        WebDriverWait wait1 = new WebDriverWait(driver,10);
        wait.until(web -> {return web.switchTo().alert();});
        Assert.assertEquals(":(", alerta.getText());
        alerta.accept();

        driver.findElement(By.id("prompt")).click();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("11");
        alerta.dismiss();
        WebDriverWait wait2 = new WebDriverWait(driver,10);
        wait.until(web -> {return web.switchTo().alert();});
        Assert.assertEquals("Era null?", alerta.getText());
        alerta.accept();
        WebDriverWait wait3 = new WebDriverWait(driver,10);
        wait.until(web -> {return web.switchTo().alert();});
        Assert.assertEquals(":D", alerta.getText());
        alerta.accept();

        driver.findElement(By.id("prompt")).click();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("11");
        alerta.accept();
        WebDriverWait wait4 = new WebDriverWait(driver,10);
        wait.until(web -> {return web.switchTo().alert();});
        Assert.assertEquals("Era 11?", alerta.getText());
        alerta.accept();
        WebDriverWait wait5 = new WebDriverWait(driver,10);
        wait.until(web -> {return web.switchTo().alert();});
        Assert.assertEquals(":D", alerta.getText());
        alerta.accept();

        driver.findElement(By.id("prompt")).click();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("11");
        alerta.accept();
        WebDriverWait wait6 = new WebDriverWait(driver,10);
        wait.until(web -> {return web.switchTo().alert();});
        Assert.assertEquals("Era 11?", alerta.getText());
        alerta.dismiss();
        WebDriverWait wait7 = new WebDriverWait(driver,10);
        wait.until(web -> {return web.switchTo().alert();});
        Assert.assertEquals(":(", alerta.getText());
        alerta.accept();

        driver.quit();
    }
}
