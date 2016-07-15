package br.ifrn.tads.poo.biblioteca.usuario;
public class Administrador extends Usuario{
	String login;
	String senha;

	public Administrador(int codUsuario, String nome, String endereco, String cpf, String login, String senha) {
		super(codUsuario, nome, endereco, cpf);
		this.login = login;
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
