import Factory.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static Factory.DriveFactory.getDriver;
import static Factory.DriveFactory.killDriver;

public class TesteTreinamento {
    private DSL dsl;
    private  CampoTreinamentoPage page;
    @Before
    public void inicializa(){
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }
    @After
    public void termina(){
        killDriver();
    }
    @Test
    public void testeTextField() {
//        Pegando um campo pelo id e usando: sendKeys("escrita") informo o texto do campo.
        page.setNome("Luis Henrique");
//        Validando o campo para ver se o preenchimento esta correto.
        Assert.assertEquals("Luis Henrique", dsl.ValorCampo("elementosForm:nome"));

        page.setSobrenome("Petsch");
        Assert.assertEquals("Petsch", dsl.ValorCampo("elementosForm:sobrenome"));
    }
    @Test
    public void testeCampoSugestao() {
        page.setSugestao("testestestes");
        Assert.assertEquals("testestestes", dsl.ValorCampo("elementosForm:sugestoes"));
    }
    @Test
    public void testeRadioButton() {
//        Seleção botão sexo feminino
        page.setSexoFeminino();
        Assert.assertTrue(dsl.Radio_marcado_verifica("elementosForm:sexo:1"));
//        Seleção botão sexo masculino
        page.setSexoMasculino();
        Assert.assertTrue(dsl.Radio_marcado_verifica("elementosForm:sexo:0"));
    }

    @Test
    public void testeComboBox() {
//        Para clicar no combobox e selecionar.
        page.setGrauEscolar("2o grau completo");
        /*
        Também posso usar:
        combo.selectByIndex(3);
        combo.selectByValue("superior");
         */
        Assert.assertEquals("2o grau completo", dsl.Obter_valor_combo("elementosForm:escolaridade"));
    }

    @Test
    public void testeVerificaValoresComboBox() {
//        Para clicar no combobox e selecionar.
        WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
//        Valida quantas opções existem no ComboBox
        Assert.assertEquals(8, options.size());

//        Valida as opções presentes no ComboBox
        int qtdCampos = 0;
        for (WebElement option : options) {
//            for repete e adiciona mais um a cada nome presente no comboBox de acordo com a lista
            List<String> elementosComparacao = List.of("Mestrado", "Doutorado", "Especializacao", "Superior",
                    "2o grau completo", "2o grau incompleto", "1o grau completo", "1o grau incompleto");
            if (elementosComparacao.contains(option.getText())) {
                qtdCampos++;
            }
        }
//        Após passar por toda a lista ele valida se tem 8 opções presentes
//        Se um da lista não estiver presente ja acusa erro
        Assert.assertEquals(qtdCampos, 8);
    }

    @Test
    public void testeComboBoxMultiplaEscolha() {
//      Para clicar no combobox e selecionar mais de uma opção
        WebElement element =getDriver().findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        page.setEsporte("Futebol", "Corrida");

//      Validando as opções
        List<WebElement> options = combo.getOptions();
        int qtdCampos = 0;
        for (WebElement option : options) {
            //for repete e adiciona mais um a cada nome presente no comboBox de acordo com a lista
            List<String> elementosComparacao = List.of("Futebol", "Natacao", "Corrida", "Karate", "O que eh esporte?");
            if (elementosComparacao.contains(option.getText())) {
                qtdCampos++;
            }
        }
//      Após passar por toda a lista ele valida se tem 5 opções presentes
//      Se um da lista não estiver presente ja acusa erro
        Assert.assertEquals(qtdCampos, 5);
    }

    @Test
    public void validaBotao() {
        page.setClick_Me();
//      Validando o botão
        WebElement botao = getDriver().findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void testeLinks() {
        page.setVoltar();
        Assert.assertEquals("Voltou!", page.obterResultadoCadastro());
    }

    @Test
    public void testeBuscaTextos() {
//      Busca o texto em qualquer lugar na tela de acordo com tag body(corpo html)
//        Assert.assertTrue(driver.findElement(By.tagName("body"))
//                .getText().contains("Campo de Treinamento"));

//      Buscando titulo Campo de Treinamento na tela
        Assert.assertEquals("Campo de Treinamento", dsl.obter_texto(By.tagName("h3")));
//      Buscando "Cuidado onde clica, muitas armadilhas..."
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
                dsl.obter_texto(By.className("facilAchar")));
    }
    @Test
    public void deveClicarBotaoTabela(){
        dsl.ClicarButtonTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
    }
}