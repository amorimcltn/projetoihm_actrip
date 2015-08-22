package br.edu.ifba.plugin.PROJETO.visao;

import br.edu.ifba.plugin.PROJETO.modelo.bd.estatico.Hotel;

public interface ICadastroHotel {
	public int getId();
	public Hotel getHotel();
	
	public void atualizarHotelEncontrado(Hotel hotel);
	public void notificarHotelNaoEncontrado();
	public void notificarUsuarioGravado(Hotel hotel);
	public void notificarErroGravacao();
}
