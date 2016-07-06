package br.ifrn.tads.poo.biblioteca.usuario;

public class Usuario {
	private int codUsuario;
	private String nome;
	private String endereco;
	private String cpf;
	
	public Usuario(int codUsuario, String nome, String endereco, String cpf){
		if(cpf.length()	!=	11){
			throw new IllegalArgumentException("CPF	deve	conter	11	dígitos");	
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
	
	
}
