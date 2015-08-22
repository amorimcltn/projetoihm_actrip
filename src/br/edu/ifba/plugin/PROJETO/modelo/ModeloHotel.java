package br.edu.ifba.plugin.PROJETO.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.plugin.PROJETO.modelo.bd.estatico.Hotel;
import br.edu.ifba.plugin.PROJETO.modelo.bd.estatico.HotelDAO;
import br.edu.ifba.plugin.PROJETO.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.PROJETO.modelo.bd.estatico.UsuarioDAO;
import br.edu.ifba.plugin.PROJETO.visao.ICadastroHotel;
import br.edu.ifba.plugin.PROJETO.visao.IPesquisaHotel;


public class ModeloHotel {

	private IPesquisaHotel pesquisaHotel = null;
	private ICadastroHotel cadastroHotel = null;
	List<Hotel> hoteis = new ArrayList<Hotel>();

	
	public void setCadastroHotel(ICadastroHotel cadastro){
		this.cadastroHotel = cadastro;
	}	
	
	public void setPesquisaHotel(IPesquisaHotel pesquisaHotel) {
		this.pesquisaHotel = pesquisaHotel;
	}
	
	public void pesquisar(){		
		
		String nome = pesquisaHotel.getNome();
		
		if (!"".equalsIgnoreCase(nome)){
			hoteis = HotelDAO.getHoteisPorNome(nome);
		}
		
		pesquisaHotel.atualizaHoteisEncontrados(hoteis);
		
		if (hoteis.isEmpty()){
			pesquisaHotel.notificaHotelNaoEncontrado();
		}
		
	}
	
	public void remover(){
		HotelDAO.remover(Integer.parseInt(pesquisaHotel.getId()));
		
		if (hoteis.size() != 0){
			pesquisar();	
		} else {
			pesquisaHotel.atualizaHoteisEncontrados(hoteis);
		}
		
		pesquisaHotel.notificarHotelRemovido();
	}
	
	public void adicionar(){
		Hotel hotel = cadastroHotel.getHotel(); 
		
		hotel.setId(-1);
		if(HotelDAO.gravar(hotel) > 0){
			cadastroHotel.notificarErroGravacao();
		}else{	
		cadastroHotel.notificarUsuarioGravado(hotel);
		}
	}
	
	public void atualizar(){
		Hotel hotel = cadastroHotel.getHotel();
		
		if(HotelDAO.gravar(hotel) > 0){
			cadastroHotel.notificarErroGravacao();
		}else{
			cadastroHotel.notificarUsuarioGravado(hotel);
		}
	}
	
	public void pesquisarParaCadastro(){
		Hotel hotel = HotelDAO.getHotel(cadastroHotel.getId());
			if(hotel == null){
				cadastroHotel.notificarHotelNaoEncontrado();
			}else{
				cadastroHotel.atualizarHotelEncontrado(hotel);
			}
	}	
	
}
