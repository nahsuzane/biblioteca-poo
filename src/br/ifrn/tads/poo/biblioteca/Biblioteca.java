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
	private ArrayList<ItemAcervo> reservados = new ArrayList<ItemAcervo>();
	
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
		//diminuir qtd dos itens no acervo
		for (int i = 0; i < acervoLivro.size(); i++) {
			if(acervoLivro.get(i).getCodigoItem() == codItem){
				acervoLivro.get(i).mudarQuantidade((-1));
				i = acervoLivro.size();
			}
		}
		for (int i = 0; i < acervoApostila.size(); i++) {
			if(acervoApostila.get(i).getCodigoItem() == codItem){
				acervoApostila.get(i).mudarQuantidade((-1));
				i = acervoApostila.size();
			}
		}
		for (int i = 0; i < acervoTexto.size(); i++) {
			if(acervoTexto.get(i).getCodigoItem() == codItem){
				acervoTexto.get(i).mudarQuantidade((-1));
				i = acervoTexto.size();
			}
		}
		
	}
	
	public void devolverItem(int codItemDev, int codUsuarioDev){
		//remover do arraylist alugados do usuario
		for (int i = 0; i < administradores.size(); i++) {
			if(administradores.get(i).getCodUsuario() == codUsuarioDev){
				for (int j = 0; j < administradores.get(i).alugadosUsers().size(); j++) {
					if(administradores.get(i).alugadosUsers().get(j).getCodigoItem() == codItemDev){
						administradores.get(i).devolverItemUser(j);
						j = administradores.get(i).alugadosUsers().size();
					}
				}
				i = administradores.size();
			}
		}
		for (int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getCodUsuario() == codUsuarioDev){
				for (int j = 0; j < usuarios.get(i).alugadosUsers().size(); j++) {
					if(usuarios.get(i).alugadosUsers().get(j).getCodigoItem() == codItemDev){
						usuarios.get(i).devolverItemUser(j);
						j = usuarios.get(i).alugadosUsers().size();
					}
				}
				i = usuarios.size();
			}
		}
		//remover do arreylist alugados da biblioteca
		for (int i = 0; i < alugados.size(); i++) {
			if(alugados.get(i).getCodigoItem() == codItemDev){
				alugados.remove(i);
				i = alugados.size();
			}
		}
		//aumentar qtd dos itens no acervo
				for (int i = 0; i < acervoLivro.size(); i++) {
					if(acervoLivro.get(i).getCodigoItem() == codItemDev){
						acervoLivro.get(i).mudarQuantidade((1));
						i = acervoLivro.size();
					}
				}
				for (int i = 0; i < acervoApostila.size(); i++) {
					if(acervoApostila.get(i).getCodigoItem() == codItemDev){
						acervoApostila.get(i).mudarQuantidade((1));
						i = acervoApostila.size();
					}
				}
				for (int i = 0; i < acervoTexto.size(); i++) {
					if(acervoTexto.get(i).getCodigoItem() == codItemDev){
						acervoTexto.get(i).mudarQuantidade((1));
						i = acervoTexto.size();
					}
				}
	}
	
	public void deletarItem(int codItemDell){
		for (int i = 0; i < acervo.size(); i++) {
			if (acervo.get(i).getCodigoItem()==codItemDell){
				acervo.remove(i);
			}
		}
		for (int i = 0; i < acervoLivro.size(); i++) {
			if (acervoLivro.get(i).getCodigoItem()==codItemDell){
				acervoLivro.remove(i);
			}
		}
		for (int i = 0; i < acervoApostila.size(); i++) {
			if (acervoApostila.get(i).getCodigoItem()==codItemDell){
				acervoApostila.remove(i);
			}
		}
		for (int i = 0; i < acervoTexto.size(); i++) {
			if (acervoTexto.get(i).getCodigoItem()==codItemDell){
				acervoTexto.remove(i);
			}
		}
	}
	
	public void deletarAdmin(int codUserDell){
		if (administradores.size()>1){
			for (int i = 0; i < administradores.size(); i++) {
				if (administradores.get(i).getCodUsuario()==codUserDell){
					administradores.remove(i);
					
				}
			}
		}
	}
	public void deletarUser(int codUserDell){
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getCodUsuario()==codUserDell){
				usuarios.remove(i);
				
			}
		}
	}
	
	public void reservarItem(int codItemRes, int codUserRes){
		
	}
	
	
} 

