package br.ifrn.tads.poo.biblioteca.app;
import br.ifrn.tads.poo.biblioteca.usuario.*;
import br.ifrn.tads.poo.biblioteca.Biblioteca;
import br.ifrn.tads.poo.biblioteca.acervo.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class SistemaBiblioteca {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Sistema biblioteca");
		System.out.println("Digite o Nome da Biblioteca:");
		String nomeB = s.nextLine();
		Biblioteca biblioteca = new Biblioteca(nomeB);
		
		boolean sistema = true;
		while(sistema){
			
		//////////////////////////////LOGUIN NO SISTEMA DA BIBLIOTECA /////////////////////////////
			boolean conectar = false;
			while(conectar == false){
				System.out.println("Digite o Loguin:");
				String log = s.nextLine();
				System.out.println("Digite a Senha:");
				String sen = s.nextLine();
				for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
					if(log.equals(biblioteca.listarAdmins().get(i).getLogin()) && sen.equals(biblioteca.listarAdmins().get(i).getSenha()) ){
						conectar = true;
						i = biblioteca.listarAdmins().size();
					}
					if(i == biblioteca.listarAdmins().size()-1 && conectar == false){
						System.out.println("Login ou Senha esta incorreto");
					}
				}
			}
			System.out.println("Conectado no sistema da Biblioteca " + biblioteca.getNomeB());
		////////////////////////////////////////////////////////////////////////////////////////////
			
		//////////////////////////////FUNÇÕES POSSIVEIS DO ADMINISTRADOR////////////////////////////
			boolean executar=true;
			do{
				try{
					System.out.println("Escolha uma função...");
					System.out.println("1)Alugar Itens\n6)Listar Itens no Acervo\n7)Listar/Buscar usuarios\n8)Cadastrar Item ao Acervo\n9)Funcionalidades Usuarios/Administrador\n0)Sair\n");
					int op0 = Integer.parseInt(s.nextLine());
					switch(op0){
					
		/////////////////////////FUNCIONALIDADE ALUGAR ITENS/////////////////////////////////
					
					case 1:
						//////////IMCOMPLETO COLOCAR OS NOMES DOS ITEM////////////////
						boolean existirCodItem = false;
						System.out.println("Digite o codigo do item que vai ser alugado");
						int codItem = Integer.parseInt(s.nextLine());
						
						for (int i = 0; i < biblioteca.listarAcervo().size(); i++) {
							if(biblioteca.listarAcervo().get(i).getCodigoItem() == codItem){
								existirCodItem = true;
								System.out.println("Codigo do(a) " + biblioteca.listarAcervo().get(i).getClass().getSimpleName());///colocar o nome do livro
								i = biblioteca.listarAcervo().size();
							}
						}
						
						if(existirCodItem){
							boolean existirCodUsuario = false;
							System.out.println("Digite o codigo do usuario que ira alugar");
							int codUsuario = Integer.parseInt(s.nextLine());
							
							for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
								if(biblioteca.listarUsers().get(i).getCodUsuario() == codUsuario){
									existirCodUsuario = true;
									System.out.println("Codigo do(a) " + biblioteca.listarUsers().get(i).getClass().getSimpleName() + biblioteca.listarUsers().get(i).getNome());
									i = biblioteca.listarUsers().size();
								}
							}
							
							if(!existirCodUsuario){
								for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
									if(biblioteca.listarAdmins().get(i).getCodUsuario() == codUsuario){
										existirCodUsuario = true;
										System.out.println("Codigo do(a) " + biblioteca.listarAdmins().get(i).getClass().getSimpleName() + biblioteca.listarAdmins().get(i).getNome());
										i = biblioteca.listarAdmins().size();
									}
								}
							}
							
							if(existirCodUsuario){
								System.out.println("Digite a quantidades de dias que passara alugado");
								int qtdDiasAlugado = Integer.parseInt(s.nextLine());
								
								if(qtdDiasAlugado > 0){
									for (int i = 0; i < biblioteca.listarAcervo().size(); i++) {
										if(biblioteca.listarAcervo().get(i).getCodigoItem() == codItem){
											System.out.println("Custo do aluguel R$" + (qtdDiasAlugado*biblioteca.listarAcervo().get(i).getCusto()));
											i = biblioteca.listarAcervo().size();
										}
									}
									System.out.println("Digite o valor pago na hora do aluguel");
									double valorPago = Double.parseDouble(s.nextLine());
									biblioteca.alugar(codItem, codUsuario, qtdDiasAlugado, valorPago);
								}else{
									System.out.println("Digite uma quantidade maior que 0");
								}
								
							}else{
								System.out.println("Não existe usuario com esse codigo");
							}
						}else{
							System.out.println("Não existe itens com esse codigo no acervo");
						}
						
						break;
					
		/////////////////////////FUNCIONALIDADE LISTAR ITENS NO ACERVO///////////////////////
					
					case 6:
						System.out.println("1)Listar livros\n2)Listar apostilas\n3)Listar textos\n4)Listas todos\nn*)Cancelar");
						int op6 = Integer.parseInt(s.nextLine());
						switch(op6){
						
						///// LISTAR LIVROS /////
						
						case 1:
							for (int i = 0; i < biblioteca.listarLivros().size(); i++) {
								System.out.println(
									biblioteca.listarLivros().get(i).getCodigoItem() + " : " +
									biblioteca.listarLivros().get(i).getTitulo() + " : " +
									biblioteca.listarLivros().get(i).getAutor() + " : Edição-" +
									biblioteca.listarLivros().get(i).getEdicao() + " : " +
									biblioteca.listarLivros().get(i).getISBN() + " : " +
									biblioteca.listarLivros().get(i).getQuantidade() + " : Custo R$" +
									biblioteca.listarLivros().get(i).getCusto()
								);
							}
							break;
							
						///// LISTAR APOSTILAS /////
						
						case 2:
							for (int i = 0; i < biblioteca.listarApostilas().size(); i++) {
								System.out.println(
									biblioteca.listarApostilas().get(i).getCodigoItem() + " : " +
									biblioteca.listarApostilas().get(i).getTitulo() + " : " +
									biblioteca.listarApostilas().get(i).getAutor() + " : " +
									biblioteca.listarApostilas().get(i).getQuantidade() + " : Custo R$" +
									biblioteca.listarApostilas().get(i).getCusto()
								);
							}
							break;
							
						///// LISTAR APOSTILAS /////
						
						case 3:
							for (int i = 0; i < biblioteca.listarTextos().size(); i++) {
								System.out.println(
									biblioteca.listarTextos().get(i).getCodigoItem() + " : " +
									biblioteca.listarTextos().get(i).getAutor() + " : " +
									biblioteca.listarTextos().get(i).getCusto()
								);
							}
							break;
							
						///// LISTAR TODOS /////
						
						case 4:
							for (int i = 0; i < biblioteca.listarLivros().size(); i++) {
								System.out.println(
									biblioteca.listarLivros().get(i).getCodigoItem() + " : " +
									biblioteca.listarLivros().get(i).getTitulo() + " : " +
									biblioteca.listarLivros().get(i).getAutor() + " : Edição-" +
									biblioteca.listarLivros().get(i).getEdicao() + " : " +
									biblioteca.listarLivros().get(i).getISBN() + " : " +
									biblioteca.listarLivros().get(i).getQuantidade() + " : Custo R$" +
									biblioteca.listarLivros().get(i).getCusto()
								);
							}
							
							for (int i = 0; i < biblioteca.listarApostilas().size(); i++) {
								System.out.println(
									biblioteca.listarApostilas().get(i).getCodigoItem() + " : " +
									biblioteca.listarApostilas().get(i).getTitulo() + " : " +
									biblioteca.listarApostilas().get(i).getAutor() + " : " +
									biblioteca.listarApostilas().get(i).getQuantidade() + " : Custo R$" +
									biblioteca.listarApostilas().get(i).getCusto()
								);
							}
							
							for (int i = 0; i < biblioteca.listarTextos().size(); i++) {
								System.out.println(
									biblioteca.listarTextos().get(i).getCodigoItem() + " : " +
									biblioteca.listarTextos().get(i).getAutor() + " : " +
									biblioteca.listarTextos().get(i).getCusto()
								);
							}
							break;
						
						default:
							System.out.println("Cancelado\n");
							break;
							
						}
						break;
					
		/////////////////////////FUNCIONALIDADE LISTAR/BUSCAR USUARIOS///////////////////////	
						
					case 7:
						System.out.println("1)\n2)\n3)Listar Usuarios\nn*)Cancelar");
						int op7 = Integer.parseInt(s.nextLine());
						switch(op7){
						
						///// LISTAR USUARIOS /////
						
						case 3:
							for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
								System.out.println(
									biblioteca.listarAdmins().get(i).getClass().getSimpleName() + " : " +
									biblioteca.listarAdmins().get(i).getCodUsuario() + " : " + 
									biblioteca.listarAdmins().get(i).getNome() + " : " + 
									biblioteca.listarAdmins().get(i).getCpf() + " : " + 
									biblioteca.listarAdmins().get(i).getEndereco() 
								);
							}
							
							for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
								System.out.println(
									biblioteca.listarUsers().get(i).getClass().getSimpleName() + " : " +
									biblioteca.listarUsers().get(i).getCodUsuario() + " : " + 
									biblioteca.listarUsers().get(i).getNome() + " : " + 
									biblioteca.listarUsers().get(i).getCpf() + " : " + 
									biblioteca.listarUsers().get(i).getEndereco() 
								);
							}
							break;
							
						///// CANCELAR /////		
								
						default:
							System.out.println("Cancelado\n");
							break;
						}
						break;
						
		/////////////////////////FUNCIONALIDADE CADASTRAR ITEM AO ACERVO/////////////////////
					
					case 8:
						System.out.println("1)Cadastrar livro\n2)Cadastar apostila\n3)cadastrar texto\nn*)Cancelar");
						boolean existir;
						int op8 = Integer.parseInt(s.nextLine());
						switch(op8){
						
						///// CADASTAR LIVRO /////
						
							case 1:
								existir = false;
								System.out.println("Digite um codigo para o Livro");
								int codLivro = Integer.parseInt(s.nextLine());
								
								for (int i = 0; i < biblioteca.listarAcervo().size(); i++) {
									if(biblioteca.listarAcervo().get(i).getCodigoItem() == codLivro){
										existir = true;
										System.out.println("Codigo ja cadastrado em um(a) " + biblioteca.listarAcervo().get(i).getClass().getSimpleName());
										i = biblioteca.listarAcervo().size();
									}
								}
								
								if(!existir){
									System.out.println("Digite o titulo do Livro");
									String tituloLivro = s.nextLine();
									System.out.println("Digite o autor do Livro");
									String autorLivro = s.nextLine();
									System.out.println("Digite a edição do Livro");
									Integer edicaoLivro = Integer.parseInt(s.nextLine());
									System.out.println("Digite ao ISBN do Livro");
									String ISBNLivro = s.nextLine();
									System.out.println("Digite o custo do aluguel do Livro");
									Double custoLivro = Double.parseDouble(s.nextLine());
									System.out.println("Digite a quantidade de livro");
									int qtdLivro = Integer.parseInt(s.nextLine());
									
									biblioteca.cadastroLivro(custoLivro, codLivro, tituloLivro, autorLivro, ISBNLivro, edicaoLivro, qtdLivro);
								}else{
									System.out.println("Digite a quantidade a ser adicionada:");
									int qtdLivro = Integer.parseInt(s.nextLine());
									if(qtdLivro >= 0){
										boolean add = false;
										for (int i = 0; i < biblioteca.listarLivros().size(); i++) {
											if(biblioteca.listarLivros().get(i).getCodigoItem() == codLivro){
												biblioteca.listarLivros().get(i).setQuantidade(qtdLivro);
												i = biblioteca.listarLivros().size();
												add = true;
											}
										}
										if(!add){
											System.out.println("Este codigo não pertence a um Livro");
										}
									}
								}			
								
								break;
								
						///// CADASTAR APOSTILA /////
								
							case 2:
								existir = false;
								System.out.println("Digite um codigo para a Apostila");
								int codApostila = Integer.parseInt(s.nextLine());
								
								for (int i = 0; i < biblioteca.listarAcervo().size(); i++) {
									if(biblioteca.listarAcervo().get(i).getCodigoItem() == codApostila){
										existir = true;
										System.out.println("Codigo ja cadastrado em um(a) " + biblioteca.listarAcervo().get(i).getClass().getSimpleName());
										i = biblioteca.listarAcervo().size();
									}
								}
								
								if(!existir){
									System.out.println("Digite o titulo da Apostila");
									String tituloApostila = s.nextLine();
									System.out.println("Digite o autor da Apostila");
									String autorApostila = s.nextLine();
									System.out.println("Digite o custo do aluguel da Apostila");
									Double custoApostila = Double.parseDouble(s.nextLine());
									System.out.println("Digite a quantidade de Apostilas");
									int qtdApostila = Integer.parseInt(s.nextLine());
									
									biblioteca.cadastroApostila(custoApostila, codApostila, tituloApostila, autorApostila, qtdApostila);
								}else{
									System.out.println("Digite a quantidade a ser adicionada:");
									int qtdApostila = Integer.parseInt(s.nextLine());
									if(qtdApostila >= 0){
										boolean add = false;
										for (int i = 0; i < biblioteca.listarApostilas().size(); i++) {
											if(biblioteca.listarApostilas().get(i).getCodigoItem() == codApostila){
												biblioteca.listarApostilas().get(i).setQuantidade(qtdApostila);
												i = biblioteca.listarApostilas().size();
												add = true;
											}
										}
										if(!add){
											System.out.println("Este codigo não pertence a uma apostila");
										}
									}
								}
								
								break;
								
						///// CADASTAR TEXTO /////
								
							case 3:
								existir = false;
								System.out.println("Digite um codigo para o Texto");
								int codTexto = Integer.parseInt(s.nextLine());
								
								for (int i = 0; i < biblioteca.listarAcervo().size(); i++) {
									if(biblioteca.listarAcervo().get(i).getCodigoItem() == codTexto){
										existir = true;
										System.out.println("Codigo ja cadastrado em um(a) " + biblioteca.listarAcervo().get(i).getClass().getSimpleName());
										i = biblioteca.listarAcervo().size();
									}
								}
								
								if(!existir){
									System.out.println("Digite o autor do Texto");
									String autorTexto = s.nextLine();
									System.out.println("Digite o custo do aluguel do Texto");
									Double custoTexto = Double.parseDouble(s.nextLine());
									
									biblioteca.cadastroTexto(custoTexto, codTexto, autorTexto);
								}
								
								break;
		
						///// CANCELAR /////		
								
							default:
								System.out.println("Cancelado\n");
								break;
								
						}
						break;
					
		/////////////////////////////FUNCIONALIDADES USUARIOS/ADMINISTRADOR////////////////////////	
					
					case 9:
						System.out.println("3)Cadastrar novo usuario\n4)Cadastrar novo administrador\nn*)Cancelar\n");
						boolean existirUser;
						boolean existirCPF;
						int op9 = Integer.parseInt(s.nextLine());
						switch(op9){
						
						///// CADASTRAR USUARIO /////
						
							case 3:
								existirUser = false;
								existirCPF = false;
								System.out.println("Digite um codigo para o Usuario");
								int codUser = Integer.parseInt(s.nextLine());
								
								for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
									if(biblioteca.listarUsers().get(i).getCodUsuario() == codUser){
										existirUser = true;
										i = biblioteca.listarUsers().size();
									}		
								}
								
								if(!existirUser){		

									System.out.println("Digite o cpf do Usuario");
									String cpfUser = s.nextLine();
									
									for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
										if(biblioteca.listarUsers().get(i).getCpf().equals(cpfUser)){
											existirCPF = true;
											i = biblioteca.listarUsers().size();
										}		
									}
									if(!existirCPF){
										System.out.println("Digite o nome do Usuario");
										String nomeUser = s.nextLine();
										System.out.println("Digite o endereço do Usuario");
										String endUser = s.nextLine();
									
										biblioteca.cadastroUsuario(codUser, nomeUser, endUser, cpfUser);
										System.out.println("Novo usuario cadastrado\n");
									}else{
										System.out.println("CPF ja pertence a outro usuario");
									}
								}else{
									System.out.println("Codigo ja pertence a outro usuario");
								}
								break;
							
						///// CADASTRAR ADMINISTRADOR /////
								
							case 4:
								existirUser = false;
								existirCPF = false;
								System.out.println("Digite um codigo para o Administrador");
								int codAdm = Integer.parseInt(s.nextLine());
								for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
									if(biblioteca.listarAdmins().get(i).getCodUsuario() == codAdm){
										existirUser = true;
										i = biblioteca.listarAdmins().size();
									}		
								}
								
								if(!existirUser){		
									System.out.println("Digite o cpf do Administrador");
									String cpfAdm = s.nextLine();
									for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
										if(biblioteca.listarAdmins().get(i).getCpf().equals(cpfAdm)){
											existirCPF = true;
											i = biblioteca.listarAdmins().size();
										}		
									}
									if(!existirCPF){
										System.out.println("Digite o nome do Administrador");
										String nomeAdm = s.nextLine();
										System.out.println("Digite o endereço do Administrador");
										String endAdm = s.nextLine();
										System.out.println("Digite um loguin para o Administrador");
										String logAdm = s.nextLine();
										System.out.println("Digite uma senha para o Administrador");
										String senAdm = s.nextLine();
									
										biblioteca.cadastroAdmin(codAdm, nomeAdm, endAdm, cpfAdm, logAdm, senAdm);
										System.out.println("Novo administrador cadastrado\n");
									}
								}
								break;
								
						///// CANCELAR /////	
								
							default:
								System.out.println("Cancelado\n");
								break;
								
						}
						break;						
						
		//////////////////////////// FUNCIONALIDADE SAIR //////////////////////////////////////////				
				
					case 0:
						System.out.println("Saiu do sistema");
						executar = false;
						break;
						
		//////////////////////////// OPÇÃO INVALIDA ///////////////////////////////////////////////				
					
					default:
						System.out.println("Não existe essa Opção");
						break;
					}
					
		//////////////////////////// TRATAMENTO DE EXEÇÕES ////////////////////////////////////////			
				
				}catch(NumberFormatException exception){
					System.out.println("Digite um numero");
				}catch(IllegalArgumentException exception){
					System.out.println("CPF	deve conter	11	dígitos");
				}
				
			}while(executar==true);
		////////////////////////////////////////////////////////////////////////////////////////////
		}
	}
}
