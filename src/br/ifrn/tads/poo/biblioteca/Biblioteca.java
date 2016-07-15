package br.ifrn.tads.poo.biblioteca;

import java.util.ArrayList;

import br.ifrn.tads.poo.biblioteca.acervo.*;
import br.ifrn.tads.poo.biblioteca.usuario.*;

public class Biblioteca  {
	private String nomeBiblioteca;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Administrador> administradores = new ArrayList <Administrador>();
	private ArrayList<ItemAcervo> acervo = new ArrayList<ItemAcervo>();
	
	public Biblioteca(String nomeBiblioteca){
		this.nomeBiblioteca = nomeBiblioteca;
		if(administradores.isEmpty()){
			administradores.add(new Administrador(0000, "Admin", "Inexistente", "00000000000", "admin", "admin"));
		}	
	}
	
	public void novoNomeBiblioteca(String nomeBiblioteca){
		this.nomeBiblioteca = nomeBiblioteca;
	}
	
	public void cadastroUsuario(int codUsuario, String nome, String endereco, String cpf){
		usuarios.add(new Usuario(codUsuario, nome, endereco, cpf));
		
	}
	
	public void cadastroAdmin(int codUsuario, String nome, String endereco, String cpf, String login, String senha){
		administradores.add(new Administrador(codUsuario, nome, endereco, cpf, login, senha));
	}
	
	public void cadastroLivro(double custo, int codigoItem, String titulo,String autor,String ISBN, Integer edicao, int quantidade){
		acervo.add(new Livro(custo, codigoItem, titulo, autor, ISBN, edicao, quantidade));
	}
	
	public void cadastroApostila(double custo, int codigoItem, String titulo, String autor,int quantidade){
		acervo.add(new Apostila(custo, codigoItem, titulo, autor,quantidade));
	}
	
	public void cadastroTexto(double custo, int codigoItem, String autor){
		acervo.add(new Texto(custo, codigoItem, autor));
	}
}
