package br.ce.cristhiann.servicos;

import static br.ce.cristhiann.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.cristhiann.entidades.Filme;
import br.ce.cristhiann.entidades.Locacao;
import br.ce.cristhiann.entidades.Usuario;
import br.ce.cristhiann.exceptions.FilmeSemEstoqueException;
import br.ce.cristhiann.exceptions.LocadoraException;
import br.ce.cristhiann.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws LocadoraException, FilmeSemEstoqueException {

		if(filme.getEstoque() == 0) {
			throw new FilmeSemEstoqueException();
		}

		if(usuario == null) {
			throw new LocadoraException("Usuario vazio.");
		}

		if(filme == null) {
			throw new LocadoraException("Filme vazio.");
		}

		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

}