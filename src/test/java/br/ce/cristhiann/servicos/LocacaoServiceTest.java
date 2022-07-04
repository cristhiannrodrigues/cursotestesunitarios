package br.ce.cristhiann.servicos;

import br.ce.cristhiann.entidades.Filme;
import br.ce.cristhiann.entidades.Locacao;
import br.ce.cristhiann.entidades.Usuario;
import br.ce.cristhiann.utils.DataUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void teste() throws Exception {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2 , 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        error.checkThat(locacao.getValor(), is(5.0));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

    }

    //FORMA ELEGANTE
    @Test(expected = Exception.class)
    public void testeLocacao_filmeSemEstoque() throws Exception {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0 , 5.0);

        //acao
        service.alugarFilme(usuario, filme);

    }

    //FORMA ROBUSTA
    @Test
    public void testeLocacao_filmeSemEstoque_2() {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0 , 5.0);

        //acao
        try {
            service.alugarFilme(usuario, filme);
            Assert.fail("Deveria ser lancado excecao");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), is("Filme sem estoque."));
        }

    }

    //NOVA
    @Test
    public void testeLocacao_filmeSemEstoque_3() throws Exception {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0 , 5.0);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("Filme sem estoque.");

        //acao
        service.alugarFilme(usuario, filme);

    }

}
