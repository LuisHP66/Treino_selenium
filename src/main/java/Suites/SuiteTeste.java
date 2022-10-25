package Suites;

import Test.RegrasCadastro;
import Test.TesteCadastro;
import Test.TesteTreinamento;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static Factory.DriveFactory.killDriver;

//Legal para passar uma ordem de execucao para classes, assim podendo setar um certo padrao
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCadastro.class,
        RegrasCadastro.class,
})

public class SuiteTeste {
    @AfterClass
    public static void finalizaTudo(){
        killDriver();
    }
}
