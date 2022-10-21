import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Legal para passar uma ordem de execucao para classes, assim podendo setar um certo padrao
@RunWith(Suite.class)
@Suite.SuiteClasses({
        Teste_Cadastro.class,
        Regras_Cadastro.class,
        Teste_Treinamento.class
})

public class SuiteTeste {
}
