package br.edu.ifba.plugin.PROJETO.visao.impl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.PROJETO.controle.ControleHotel;
import br.edu.ifba.plugin.PROJETO.modelo.ModeloHotel;
import br.edu.ifba.plugin.PROJETO.modelo.bd.estatico.Hotel;
import br.edu.ifba.plugin.PROJETO.visao.ICadastroHotel;

@ManagedBean(name="cadhotel")
@ViewScoped
public class CadastroHotel implements ICadastroHotel {

	private static final String ERRO_NOME_NAO_INFORMADO = "Nome dever ser informado!";
	private static final String ERRO_TEL_NAO_INFORMADO = "Telefone dever ser informado!";
	private static final String ERRO_END_NAO_INFORMADO = "Endereço dever ser informado!";
	private static final String ERRO_CIDADE_NAO_INFORMADO = "Cidade dever ser informada!";
	private static final String ERRO_UF_NAO_INFORMADO = "UF dever ser informado!";
	public boolean gravado = false;
	private String id="";
	private Hotel hotel = new Hotel();

	public CadastroHotel(){
		ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
		id = (String) contexto.getSessionMap().get("idHotel");
		
		if((id != null)&&(!id.isEmpty())){
		recuperarHotel();
		}
	}
	
	public void recuperarHotel(){
		ModeloHotel modelo = new ModeloHotel();
		ControleHotel controle = new ControleHotel();
		controle.setCadastroHotel(this);
		controle.setModeloHotel(modelo);
		controle.pesquisarParaCadastro();
	}
	
	@Override
	public int getId() {
		int iid = 0;
		if((id != null)&&(!id.isEmpty())){
			iid = Integer.parseInt(id);
		}
		return iid;
	}

	@Override
	public Hotel getHotel() {
		return hotel;
	}

	@Override
	public void atualizarHotelEncontrado(Hotel hotel) {
		this.hotel = hotel;		
	}

	public void gravar(){
		gravado = false;
		boolean flag = true;
		if((hotel.getNome() == null) || (hotel.getNome().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:nome", new FacesMessage(ERRO_NOME_NAO_INFORMADO, ERRO_NOME_NAO_INFORMADO));
			flag = false;
		}
		if((hotel.getTelefone() == null) || (hotel.getTelefone().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:telefone", new FacesMessage(ERRO_TEL_NAO_INFORMADO, ERRO_TEL_NAO_INFORMADO));
			flag = false;
		}
		if((hotel.getEndereco() == null) || (hotel.getEndereco().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:endereco", new FacesMessage(ERRO_END_NAO_INFORMADO, ERRO_END_NAO_INFORMADO));
			flag = false;
		}
		if((hotel.getCidade() == null) || (hotel.getCidade().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:cidade", new FacesMessage(ERRO_CIDADE_NAO_INFORMADO, ERRO_CIDADE_NAO_INFORMADO));
			flag = false;
		}
		if((hotel.getUf() == null) || (hotel.getUf().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:uf", new FacesMessage(ERRO_UF_NAO_INFORMADO, ERRO_UF_NAO_INFORMADO));
			flag = false;
		}		
		if (flag){
			gravarHotel();
		}
	}	
	
	public void gravarHotel(){
		ModeloHotel modelo = new ModeloHotel();
		ControleHotel controle = new ControleHotel();
		
		controle.setCadastroHotel(this);
		controle.setModeloHotel(modelo);
		
		controle.gravar();
	}	
	
	@Override
	public void notificarHotelNaoEncontrado() {
		
	}

	@Override
	public void notificarUsuarioGravado(Hotel hotel) {
		gravado = true;	
	}

	public boolean getGravado(){
		return gravado;
	}	
	
	@Override
	public void notificarErroGravacao() {
		// TODO Auto-generated method stub
		
	}

}
