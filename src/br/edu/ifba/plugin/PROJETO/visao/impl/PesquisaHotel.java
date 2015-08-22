package br.edu.ifba.plugin.PROJETO.visao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.PROJETO.controle.ControleHotel;
import br.edu.ifba.plugin.PROJETO.modelo.ModeloHotel;
import br.edu.ifba.plugin.PROJETO.modelo.bd.estatico.Hotel;
import br.edu.ifba.plugin.PROJETO.visao.IPesquisaHotel;

@ManagedBean(name = "photel")
@ViewScoped
public class PesquisaHotel implements IPesquisaHotel {
	
	private String erro;
	private String sucesso;
	
	private String id = "";
	private String nome = "";
	private String telefone = "";
	private String endereco = "";
	private String cidade = "";
	private String uf = "";
	
	private List<Hotel> hoteisEncontrados =
			new ArrayList<Hotel>();
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public String getNome() {
		return nome;
	}
	@Override
	public String getTelefone() {
		return telefone;
	}
	@Override
	public String getEndereco() {
		return endereco;
	}
	
	@Override
	public String getCidade() {
		return cidade;
	}
	
	@Override
	public String getUf() {
		return uf;
	}
	
	@Override
	public void atualizaHoteisEncontrados(List<Hotel> hoteis) {		
		this.hoteisEncontrados = hoteis;
	}
		
	public List<Hotel> getHoteis() {
		return this.hoteisEncontrados;
	}
	
	public void ver(String id){
		exibirCadastro(id);
	}
	
	public void pesquisar() {
		System.out.println("pesquisar");
		
		ModeloHotel hotel = new ModeloHotel();
		ControleHotel controle = new ControleHotel();
		
		controle.setModeloHotel(hotel);
		controle.setPesquisaHotel(this);
		
		controle.pesquisar();
	}	
	
	private void exibirCadastro(String id){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getSessionMap().put("idHotel", id);
		try{
			context.redirect("cadastro_hotel.ifba");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void remover(String id){
		sucesso = null;
		erro = null;	
		
		this.id = id;
		
		ModeloHotel modelo = new ModeloHotel();
		ControleHotel controle = new ControleHotel();
		
		controle.setModeloHotel(modelo);
		controle.setPesquisaHotel(this);
		
		controle.remover();		
	}	
	
	public void adicionar(){
		exibirCadastro("");
	}
	
	public String getErro() {
		return erro;
	}

	public String getSucesso() {
		return sucesso;
	}	
	
	@Override
	public void notificaHotelNaoEncontrado() {
		erro = "Nenhum hotel foi encontrado";		
	}

	@Override
	public void notificarHotelRemovido() {
		sucesso = "Hotel removido com sucesso";		
	}

	@Override
	public void notificarErroRemocao() {
		erro = "Não foi possível remover o hotel";		
	}
	
	

}
