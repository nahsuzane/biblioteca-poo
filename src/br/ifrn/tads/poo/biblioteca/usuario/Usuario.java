package br.ifrn.tads.poo.biblioteca.usuario;

import java.util.ArrayList;

import br.ifrn.tads.poo.biblioteca.acervo.ItemAcervo;

public class Usuario {
	private int codUsuario;
	private String nome;
	private String endereco;
	private String cpf;
	private ArrayList<ItemAcervo> alugadosUser = new ArrayList<ItemAcervo>();
	private ArrayList<ItemAcervo> reservadosUser = new ArrayList<ItemAcervo>();
	
	public Usuario(int codUsuario, String nome, String endereco, String cpf){
		if(cpf.length()	!=	11){
			throw new IllegalArgumentException("CPF	deve conter	11	dígitos");	
		}
			
		for(int i =	0; i < cpf.length(); i++){	
			char c = cpf.charAt(i);	
			if(!Character.isDigit(c)){
				throw new IllegalArgumentException("CPF deve conter apenas números");	
			}		
		}	
		
		this.codUsuario = codUsuario;
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
	}
	
	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void alugarUser(ItemAcervo item){
		alugadosUser.add(item);
	}
	
	public void devolverItemUser(int posItem){
		alugadosUser.remove(posItem);
	}
	
	public ArrayList<ItemAcervo> alugadosUsers(){
		return alugadosUser;
	}
	
	public void reservadoUser(ItemAcervo item){
		alugadosUser.add(item);
	}
	
	public void devolverReservadoUser(int posItem){
		alugadosUser.remove(posItem);
	}
	
	public ArrayList<ItemAcervo> reservadoUsers(){
		return alugadosUser;
	}
	
}
