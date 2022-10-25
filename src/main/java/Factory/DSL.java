package Factory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static Factory.DriveFactory.getDriver;

public class DSL {
    public void escreve(String id_campo,String texto){
        getDriver().findElement(By.id(id_campo)).sendKeys(texto);
    }
    public String ValorCampo(String id_campo){
        return getDriver().findElement(By.id(id_campo)).getAttribute("value");
    }
    public void clicarRadio(By by){
        getDriver().findElement(by).click();
    }
    public void clicarRadio(String id){
        getDriver().findElement(By.id(id));
    }
    public boolean Radio_marcado_verifica(String id_campo){
        return getDriver().findElement(By.id(id_campo)).isSelected();
    }
    public void selecao_combo(String id_campo, String texto){
        WebElement element = getDriver().findElement(By.id(id_campo));
        Select combo = new Select(element);
        combo.selectByVisibleText(texto);
    }
    public void selecaoComboXpath(String xpath){
        getDriver().findElement(By.xpath(xpath)).click();
    }
    public String Obter_valor_combo(String id_campo){
        WebElement element = getDriver().findElement(By.id(id_campo));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }
    public void clica_botao(String id_campo){
        getDriver().findElement(By.id(id_campo)).click();
    }
    public void clica_link(String id_campo){
        getDriver().findElement(By.linkText(id_campo)).click();
    }
    public String obter_texto(By by){
        return getDriver().findElement(by).getText();
    }
    public String alertaObterTextoAcept(){
        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.accept();
        return valor;
    }
    public String alertaObterTextoDimiss(){
        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.dismiss();
        return valor;
    }
    public String ObterTextoDigitaAcept(String texto){
        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.sendKeys(texto);
        alert.accept();
        return valor;
    }
    public String ObterTextoDigitaDimiss(String texto){
        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.sendKeys(texto);
        alert.dismiss();
        return valor;
    }
    public void ClicarButtonTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
        //Procurar coluna do registro
        WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
        int idColuna = ObterIndiceColuna(colunaBusca, tabela);

        //Procurar a linha do registro
        int idLinha = ObterIndiceLinha(valor, tabela, idColuna);

        //Procurar a coluna do botão
        int idColunaBotao = ObterIndiceColuna(colunaBotao, tabela);

        //Clicar no botão
        WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
        celula.findElement(By.xpath(".//input")).click();
    }

    protected int ObterIndiceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
        int idLinha = -1;
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).getText().equals(valor)) {
                idLinha = i + 1;
                break;
            }
        }
        return idLinha;
    }
    protected int ObterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;
        for(int i = 0; i < colunas.size(); i++){
            if (colunas.get(i).getText().equals(coluna)){
                idColuna = i + 1;
                break;
            }
        }
        return idColuna;
    }
}
