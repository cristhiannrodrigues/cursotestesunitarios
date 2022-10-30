package br.ce.cristhiann.servicos;

import br.ce.cristhiann.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {
    public Integer somar(Integer v1, Integer v2) {
        return v1 + v2;
    }


    public int subtrair(Integer v1, Integer v2) {
        return v1 - v2;
    }

    public int dividir(Integer v1, Integer v2) throws NaoPodeDividirPorZeroException {
        if(v1 == 0 || v2 == 0) {
            throw new NaoPodeDividirPorZeroException();
        }

        return v1/v2;
    }

    public int multiplicar(Integer v1, Integer v2) {
        return v1 * v2;
    }
}
