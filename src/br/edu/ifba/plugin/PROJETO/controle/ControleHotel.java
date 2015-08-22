package br.edu.ifba.plugin.PROJETO.controle;

import br.edu.ifba.plugin.PROJETO.modelo.ModeloHotel;
import br.edu.ifba.plugin.PROJETO.visao.ICadastroHotel;
import br.edu.ifba.plugin.PROJETO.visao.IPesquisaHotel;


public class ControleHotel {

	private IPesquisaHotel pesquisaHotel;
	private ModeloHotel modeloHotel;
	private ICadastroHotel cadastroHotel;
	
	public void setPesquisaHotel(IPesquisaHotel pesquisa) {
		this.pesquisaHotel = pesquisa;
	}

	public void setModeloHotel(ModeloHotel modelo) {
		this.modeloHotel = modelo;
	}

	public void setCadastroHotel(ICadastroHotel cadastro) {
		this.cadastroHotel = cadastro;
	}
	
	public void pesquisar() {
		modeloHotel.setPesquisaHotel(pesquisaHotel);
		modeloHotel.pesquisar();
	}
	
	public void pesquisarParaCadastro() {
		modeloHotel.setCadastroHotel(cadastroHotel);
		modeloHotel.pesquisarParaCadastro();
	}
	
	public void remover(){
		modeloHotel.setPesquisaHotel(pesquisaHotel);
		modeloHotel.remover();
	}
	
	public void adicionar(){
		modeloHotel.setCadastroHotel(cadastroHotel);
		modeloHotel.adicionar();
	}
	
	public void gravar(){
		modeloHotel.setCadastroHotel(cadastroHotel);
		if((cadastroHotel.getId() == -1)){
			modeloHotel.adicionar();
		}else{
			modeloHotel.atualizar();		
		}
	}	
}
