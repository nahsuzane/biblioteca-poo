package br.ifrn.tads.poo.biblioteca;

import java.util.ArrayList;

import br.ifrn.tads.poo.biblioteca.acervo.*;
import br.ifrn.tads.poo.biblioteca.usuario.*;

public class Biblioteca  {
	private String nomeBiblioteca;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Administrador> administradores = new ArrayList <Administrador>();
	private ArrayList<Livro> acervoLivro = new ArrayList<Livro>();
	private ArrayList<Apostila> acervoApostila = new ArrayList<Apostila>();
	private ArrayList<Texto> acervoTexto = new ArrayList<Texto>();
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
	
	public String getNomeB(){
		return nomeBiblioteca;
	}
	public void cadastroUsuario(int codUsuario, String nome, String endereco, String cpf){
		usuarios.add(new Usuario(codUsuario, nome, endereco, cpf));
		
	}
	
	public void cadastroAdmin(int codUsuario, String nome, String endereco, String cpf, String login, String senha){
		administradores.add(new Administrador(codUsuario, nome, endereco, cpf, login, senha));
	}
	
	public void cadastroLivro(double custo, int codigoItem, String titulo,String autor,String ISBN, Integer edicao, int quantidade){
		acervoLivro.add(new Livro(custo, codigoItem, titulo, autor, ISBN, edicao, quantidade));
	}
	
	public void cadastroApostila(double custo, int codigoItem, String titulo, String autor,int quantidade){
		acervoApostila.add(new Apostila(custo, codigoItem, titulo, autor,quantidade));
	}
	
	public void cadastroTexto(double custo, int codigoItem, String autor){
		acervoTexto.add(new Texto(custo, codigoItem, autor));
	}
	
	public ArrayList<Administrador> listarAdmins(){
		return administradores;
	} 
	
	public ArrayList<Livro> listarLivros(){
		return acervoLivro;
	}
	
	public ArrayList<Apostila> listarApostilas(){
		return acervoApostila;
	}
	
	public ArrayList<Texto> listarTextos(){
		return acervoTexto;
	}
	
	public ArrayList<ItemAcervo> listarAcervo(){
		for (int i = 0; i < acervoLivro.size(); i++) {
			acervo.add(acervoLivro.get(i));
		}
		for (int i = 0; i < acervoApostila.size(); i++) {
			acervo.add(acervoApostila.get(i));
		}
		for (int i = 0; i < acervoTexto.size(); i++) {
			acervo.add(acervoTexto.get(i));
		}
		
		return acervo;
	} 
}
