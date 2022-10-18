import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Teste_Cadastro {
    private WebDriver driver;
    private DSL dsl;
    private  CampoTreinamentoPage page;
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
    public void Cadastro() {
//      Nome
        page.setNome("Luis Henrique");
//      Sobrenome
        page.setSobrenome("Petsch");
//      Seleção botão sexo masculino
        page.setSexoMasculino();
//      Escolhendo comida favorita
        page.setPizza();
//      Seleção grau de escolaridade
        page.setGrauEscolar("2o grau completo");
        Assert.assertEquals("2o grau completo", page.obterValorEscolaridade());
//      Selecionando os esportes praticados
        page.setEsporte("Futebol", "Corrida");
//      Adicionando sugestões
        page.setSugestao("testestestes");
//      Validando texto da tela
        Assert.assertEquals("Status: Nao cadastrado", page.obterResultadoCadastro());
//      Clicando no cadastrar
        page.setCadastrar();
//      Validando textos que apareceram apos o cadastro
        Assert.assertEquals("Cadastrado!\n" +
                        "Nome: Luis Henrique\n" +
                        "Sobrenome: Petsch\n" +
                        "Sexo: Masculino\n" +
                        "Comida: Pizza\n" +
                        "Escolaridade: 2graucomp\n" +
                        "Esportes: Futebol Corrida\n" +
                        "Sugestoes: testestestes",
                page.obterResultadoCadastro());
   }
}
