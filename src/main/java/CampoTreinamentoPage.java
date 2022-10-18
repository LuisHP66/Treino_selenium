import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
    private static DSL dsl;
    private WebDriver driver;

    public CampoTreinamentoPage(WebDriver driver){
        dsl = new DSL(driver);
    }
    public void setNome(String nome){
        dsl.escreve("elementosForm:nome", nome);
    }
    public void setSobrenome(String sobrenome){
        dsl.escreve("elementosForm:sobrenome", sobrenome);
    }
    public void setSexoFeminino(){
        dsl.clicarRadio("elementosForm:sexo:1");
    }
    public void setSexoMasculino(){
        dsl.clicarRadio("elementosForm:sexo:0");
    }
    public void setSugestao(String texto){
        dsl.escreve("elementosForm:sugestoes", texto);
    }
    public void setGrauEscolar(String grauEscolar){
        dsl.selecao_combo("elementosForm:escolaridade", grauEscolar);
    }
    public void setClick_Me(){
        dsl.clica_botao("buttonSimple");
    }
    public void setVoltar(){
        dsl.clica_link("Voltar");
    }
    public void setCarne(){
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
    }
    public void setFrango(){
        dsl.clicarRadio("elementosForm:comidaFavorita:1");
    }
    public void setPizza(){
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
    }
    public void setVegetariano(){
        dsl.clicarRadio("elementosForm:comidaFavorita:3");
    }
    public void setEsporte(String... valores){
        for(String valor: valores)
        dsl.selecao_combo("elementosForm:esportes", valor);
    }
    public void setOqEsporte(){
        dsl.selecao_combo("elementosForm:esportes", "O que eh esporte?");
    }
    public void setCadastrar(){
        dsl.clica_botao("elementosForm:cadastrar");
    }
    public String obterResultadoCadastro() {
        return dsl.obter_texto("resultado");
    }
    public void setBottonAlert(){
        dsl.clica_botao("alert");
    }
    public void setBottonConfirm(){
        dsl.clica_botao("confirm");
    }
    public void setBottonPronpt(){
        dsl.clica_botao("prompt");
    }
    public void setBottonFrame(){
        dsl.clica_botao("frameButton");
    }
    public void setBottonEasy() {
        dsl.clica_botao("buttonPopUpEasy");
    }
    public void setBottonHard() {
        dsl.clica_botao("buttonPopUpHard");
    }
    public Object obterValorEscolaridade(){
        return dsl.Obter_valor_combo("elementosForm:escolaridade");
    }
}
