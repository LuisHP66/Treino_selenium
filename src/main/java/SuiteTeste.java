import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Legal para passar uma ordem de execucao para classes, assim podendo setar um certo padrao
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCadastro.class,
        RegrasCadastro.class,
        TesteTreinamento.class
})

public class SuiteTeste {
}
