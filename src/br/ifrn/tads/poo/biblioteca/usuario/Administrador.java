package br.ifrn.tads.poo.biblioteca.usuario;
public class Administrador extends Usuario{

	public Administrador(int codUsuario, String nome, String endereco, String cpf) {
		super(codUsuario, nome, endereco, cpf);
	}
	
	public void cadastroUsuario(int codUsuario, String nome, String endereco, String cpf){
		Usuario usuario = new Usuario(codUsuario, nome, endereco, cpf);
		
	}
}
