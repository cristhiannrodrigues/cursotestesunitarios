package br.ce.cristhiann.servicos;

import org.junit.Assert;
import org.junit.Test;

public class CalculadoraTest {

    @Test
    public void deveSomarDoisValores() {
        //cenario
        Integer v1 = 5;
        Integer v2 = 3;
        Calculadora calc = new Calculadora();

        //acao
        int result = calc.somar(v1, v2);

        //verificacao
        Assert.assertEquals(8, result);

    }

}
