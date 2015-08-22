package br.edu.ifba.plugin.PROJETO.visao;

import java.util.List;

import br.edu.ifba.plugin.PROJETO.modelo.bd.estatico.Hotel;

public interface IPesquisaHotel {
	
	public String getId();	
	public String getNome();	
	public String getTelefone();
	public String getEndereco();
	public String getCidade();
	public String getUf();
	
	public void atualizaHoteisEncontrados(List<Hotel> hoteis);
	
	public void notificaHotelNaoEncontrado();
	
	public void notificarHotelRemovido();
	
	public void notificarErroRemocao();	

}
