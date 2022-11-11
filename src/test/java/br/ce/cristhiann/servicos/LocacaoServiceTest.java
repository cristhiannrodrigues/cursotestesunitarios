package br.ce.cristhiann.servicos;

import br.ce.cristhiann.entidades.Filme;
import br.ce.cristhiann.entidades.Locacao;
import br.ce.cristhiann.entidades.Usuario;
import br.ce.cristhiann.exceptions.FilmeSemEstoqueException;
import br.ce.cristhiann.exceptions.LocadoraException;
import br.ce.cristhiann.utils.DataUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class LocacaoServiceTest {

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        service = new LocacaoService();
    }

    @Test
    public void deveAlugarFilme() throws Exception {

        //cenario
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
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0 , 5.0);

        //acao
        service.alugarFilme(usuario, filme);

    }

    //FORMA ROBUSTA
    @Test
    public void testeLocacao_filmeSemEstoque_2() {

        //cenario
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

    @Test
    public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueException {

        //cenario
        Filme filme = new Filme("Filme 1", 0 , 5.0);

        //acao
        try {
            service.alugarFilme(null, filme);
        } catch (LocadoraException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void devePagar75Pct() {

        //cenario
        Usuario usuario = new Usuario("User 1");
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0)
        );
        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);
        //verificaocao
    }

}
