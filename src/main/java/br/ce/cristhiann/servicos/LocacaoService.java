package br.ce.cristhiann.servicos;

import static br.ce.cristhiann.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.cristhiann.entidades.Filme;
import br.ce.cristhiann.entidades.Locacao;
import br.ce.cristhiann.entidades.Usuario;
import br.ce.cristhiann.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
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