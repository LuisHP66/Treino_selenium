import Factory.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static Factory.DriveFactory.getDriver;
import static Factory.DriveFactory.killDriver;

public class TesteRegrasPreenchimento {
    private DSL dsl;
    private CampoTreinamentoPage page;

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
    public void Regra_nome() {
        page.setCadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoAcept());
    }

    @Test
    public void Regra_sobrenome() {
        page.setNome("Luis Henrique");
        page.setCadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoAcept());
    }

    @Test
    public void Regra_sexo() {
        page.setNome("Luis Henrique");
        page.setSobrenome("Petsch");
        page.setCadastrar();
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoAcept());
    }

    @Test
    public void Regra_comidaFavorita() {
        page.setNome("Luis Henrique");
        page.setSobrenome("Petsch");
        page.setSexoMasculino();
        page.setCarne();
        page.setVegetariano();
        page.setCadastrar();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoAcept());
     }

    @Test
    public void Regra_esporte() {
        page.setNome("Luis Henrique");
        page.setSobrenome("Petsch");
        page.setSexoMasculino();
        page.setCarne();
        page.setEsporte("Natacao", "O que eh esporte?");
        page.setCadastrar();
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoAcept());
    }
}