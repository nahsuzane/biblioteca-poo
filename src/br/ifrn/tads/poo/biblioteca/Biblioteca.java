package br.ifrn.tads.poo.biblioteca;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	private ArrayList<ItemAcervo> alugados = new ArrayList<ItemAcervo>();
	
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
	
	public ArrayList<Usuario> listarUsers(){
		return usuarios;
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
		acervo.clear();
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
	
	public void alugar(int codItem, int codUsuario, int qtdDiasAlugados, double valorPago){
		for (int i = 0; i < listarAcervo().size(); i++) {
			if(listarAcervo().get(i).getCodigoItem() == codItem){
				alugados.add(new ItemAcervo(listarAcervo().get(i).getCusto(), codItem)); //cria novo objeto para simbolizar o aluguel
				alugados.get(alugados.size()-1).setDataAluguel(new Date());//add a data do aluguel do item
				//pega a data de aluguel e calcula a data de devolução;
				Calendar cal = Calendar.getInstance();
				cal.setTime(alugados.get(alugados.size()-1).getDataAluguel());
				cal.add(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + (qtdDiasAlugados-1));
				alugados.get(alugados.size()-1).setDataDevolucao(cal.getTime());
				//statos do pagamento
				if ((listarAcervo().get(i).getCusto()*qtdDiasAlugados) <= valorPago){
					alugados.get(alugados.size()-1).setPago(true);
				}else{
					alugados.get(alugados.size()-1).setPago(false);
					alugados.get(alugados.size()-1).setCusto((listarAcervo().get(i).getCusto()*qtdDiasAlugados) - valorPago);
				}
				
				
				//Adiciona o item no arreylist alugados do usuario/adimin
				for (int j = 0; j < administradores.size(); j++) {
					if(administradores.get(j).getCodUsuario() == codUsuario){
						administradores.get(j).alugarUser(alugados.get(alugados.size()-1));
					}
				}
				for (int j = 0; j < usuarios.size(); j++) {
					if(usuarios.get(j).getCodUsuario() == codUsuario){
						usuarios.get(j).alugarUser(alugados.get(alugados.size()-1));
					}
				}	
			}
		}
		
		for (int i = 0; i < acervoLivro.size(); i++) {
			if(acervoLivro.get(i).getCodigoItem() == codItem){
				acervoLivro.get(i).setQuantidade((-1));
				i = acervoLivro.size();
			}
		}
		for (int i = 0; i < acervoApostila.size(); i++) {
			if(acervoApostila.get(i).getCodigoItem() == codItem){
				acervoApostila.get(i).setQuantidade((-1));
				i = acervoApostila.size();
			}
		}
		
	}
	
}
