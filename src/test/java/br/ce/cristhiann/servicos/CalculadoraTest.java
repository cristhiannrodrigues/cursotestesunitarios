package br.ce.cristhiann.servicos;

import br.ce.cristhiann.exceptions.NaoPodeDividirPorZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

    Calculadora calculadora;

    @Before
    public void init() {
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores() {
        //cenario
        Integer v1 = 5;
        Integer v2 = 3;

        //acao
        int result = calculadora.somar(v1, v2);

        //verificacao
        Assert.assertEquals(8, result);

    }

    @Test
    public void deveSubtrairDoisValores() {
        //cenario
        Integer v1 = 5;
        Integer v2 = 3;
        //acao
        int result = calculadora.subtrair(v1, v2);
        //verificacao
        Assert.assertEquals(2, result);
    }

    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
        Integer v1 = 5;
        Integer v2 = 3;

        int result = calculadora.dividir(v1, v2);

        Assert.assertEquals(1, result);
    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void naoPodeDividirPorZero() throws NaoPodeDividirPorZeroException {
        Integer v1 = 10;
        Integer v2 = 0;

        calculadora.dividir(v1, v2);

    }

    @Test
    public void deveMultiplicarDoisValores() {
        Integer v1 = 5;
        Integer v2 = 3;

        int result = calculadora.multiplicar(v1, v2);

        Assert.assertEquals(15, result);
    }

}
