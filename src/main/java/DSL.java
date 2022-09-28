import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    private static WebDriver driver;
    public DSL(WebDriver driver){
        this.driver = driver;
    }
    public void escreve(String id_campo,String texto){
        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }
    public String ValorCampo(String id_campo){
        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }
    public void clicarRadio(String id_campo){
        driver.findElement(By.id(id_campo)).click();
    }
    public boolean Radio_marcado_verifica(String id_campo){
        return driver.findElement(By.id(id_campo)).isSelected();
    }
    public void selecao_combo(String id_campo, String texto){
        WebElement element = driver.findElement(By.id(id_campo));
        Select combo = new Select(element);
        combo.selectByVisibleText(texto);
    }
    public String Obter_valor_combo(String id_campo){
        WebElement element = driver.findElement(By.id(id_campo));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }
    public void clica_botao(String id_campo){
        driver.findElement(By.id(id_campo)).click();
    }
    public void clica_link(String id_campo){
        driver.findElement(By.linkText(id_campo)).click();
    }
    public String obter_texto(By by){
        return driver.findElement(by).getText();
    }
    public String obter_texto(String id_campo) {
        return obter_texto(By.id(id_campo));
    }
}
