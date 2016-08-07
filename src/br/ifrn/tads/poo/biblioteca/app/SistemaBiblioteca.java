package br.ifrn.tads.poo.biblioteca.app;
import br.ifrn.tads.poo.biblioteca.usuario.*;
import br.ifrn.tads.poo.biblioteca.Biblioteca;
import br.ifrn.tads.poo.biblioteca.acervo.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
			
		//////////////////////////////LOGIN NO SISTEMA DA BIBLIOTECA /////////////////////////////
			boolean conectar = false;
			while(conectar == false){
				System.out.println("Digite o Login:");
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
			
		//////////////////////////////FUNÃ‡Ã•ES POSSIVEIS DO ADMINISTRADOR////////////////////////////
			boolean executar=true;
			do{
				try{
					System.out.println("Escolha uma funÃ§Ã£o...");
					System.out.println("1)Alugar Itens\n2)Devolver Item\n3)Verificar itens em aluguel\n4)Atualizar dados de item\n5)Atualizar dados de usuario\n6)Listar Itens no Acervo/Todos Usuarios\n7)Listar usuarios\n8)Cadastrar Item ao Acervo\n9)Funcionalidades Usuarios/Administrador\n0)Sair\n");
					int op0 = Integer.parseInt(s.nextLine());
					switch(op0){
					
		/////////////////////////FUNCIONALIDADE ALUGAR ITENS/////////////////////////////////
					
					case 1:
						boolean existirCodItem = false;
						System.out.println("Digite o codigo do item que vai ser alugado");
						int codItem = Integer.parseInt(s.nextLine());
						
						for (int i = 0; i < biblioteca.listarAcervo().size(); i++) {
							if(biblioteca.listarAcervo().get(i).getCodigoItem() == codItem){
								existirCodItem = true;
								System.out.println("Codigo de um(a) " + biblioteca.listarAcervo().get(i).getClass().getSimpleName());///colocar o nome do livro
								i = biblioteca.listarAcervo().size();
							}
						}
						
						if(existirCodItem){
							for (int i = 0; i < biblioteca.listarLivros().size(); i++) {
								if (biblioteca.listarLivros().get(i).getCodigoItem() == codItem){
									if (biblioteca.listarLivros().get(i).getQuantidade() <= 0){
										existirCodItem = false;
									}
									int qtdRes = 0;
									for (int j = 0; j < biblioteca.listarReservados().size(); j++) {
										if (codItem == biblioteca.listarReservados().get(j).getCodigoItem()){
											qtdRes++;
										}
									}
									if (biblioteca.listarLivros().get(i).getQuantidade() <= qtdRes){
										existirCodItem = false;
									}
								}
							}							
						}
						
						if(existirCodItem){
							for (int i = 0; i < biblioteca.listarApostilas().size(); i++) {
								if (biblioteca.listarApostilas().get(i).getCodigoItem() == codItem){
									if (biblioteca.listarApostilas().get(i).getQuantidade() <= 0){
										existirCodItem = false;
									}
									int qtdRes = 0;
									for (int j = 0; j < biblioteca.listarReservados().size(); j++) {
										if (codItem == biblioteca.listarReservados().get(j).getCodigoItem()){
											qtdRes++;
										}
									}
									if (biblioteca.listarApostilas().get(i).getQuantidade() <= qtdRes){
										existirCodItem = false;
									}
								}
							}							
						}
						
						if(existirCodItem){
							for (int i = 0; i < biblioteca.listarTextos().size(); i++) {
								if (biblioteca.listarTextos().get(i).getCodigoItem() == codItem){
									if (biblioteca.listarTextos().get(i).getQuantidade() <= 0){
										existirCodItem = false;
									}
									int qtdRes = 0;
									for (int j = 0; j < biblioteca.listarReservados().size(); j++) {
										if (codItem == biblioteca.listarReservados().get(j).getCodigoItem()){
											qtdRes++;
										}
									}
									if (biblioteca.listarTextos().get(i).getQuantidade() <= qtdRes){
										existirCodItem = false;
									}
								}
							}							
						}
						
						if(existirCodItem){
							boolean existirCodUsuario = false;
							System.out.println("Digite o codigo do usuario que ira alugar");
							int codUsuario = Integer.parseInt(s.nextLine());
							
							for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
								if(biblioteca.listarUsers().get(i).getCodUsuario() == codUsuario){
									existirCodUsuario = true;
									System.out.println("Codigo do(a) " + biblioteca.listarUsers().get(i).getClass().getSimpleName() + " " + biblioteca.listarUsers().get(i).getNome());
									i = biblioteca.listarUsers().size();
								}
							}
							
							if(!existirCodUsuario){
								for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
									if(biblioteca.listarAdmins().get(i).getCodUsuario() == codUsuario){
										existirCodUsuario = true;
										System.out.println("Codigo do(a) " + biblioteca.listarAdmins().get(i).getClass().getSimpleName() + " " + biblioteca.listarAdmins().get(i).getNome());
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
									System.out.println("Item alugado");
								}else{
									System.out.println("Digite uma quantidade maior que 0");
								}
								
							}else{
								System.out.println("NÃ£o existe usuario com esse codigo");
							}
						}else{
							System.out.println("NÃ£o existe itens com esse codigo no acervo, o item esta em falta ou o item esta reservado");
						}
						
						break;
					
		/////////////////////////FUNCIONALIDADE DEVOLVER ITEM ///////////////////////////////
						
					case 2:
						boolean existirCodUsuarioDev = false;
						System.out.println("Digite o codigo do usuario da devoluÃ§Ã£o");
						int codUsuarioDev = Integer.parseInt(s.nextLine());
						
						for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
							if(biblioteca.listarUsers().get(i).getCodUsuario() == codUsuarioDev){
								existirCodUsuarioDev = true;
								System.out.println("Codigo do(a) " + biblioteca.listarUsers().get(i).getClass().getSimpleName() + " " + biblioteca.listarUsers().get(i).getNome());
								for (int j = 0; j < biblioteca.listarUsers().get(i).alugadosUsers().size(); j++){
									System.out.println(
										"Item " + biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem() +
										", Data do aluguel " + biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataAluguel() +
										", Data da DevoluÃ§Ã£o " + biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataDevolucao()
									);
								}
								System.out.println("Digite o codigo do item em devoluÃ§Ã£o");
								int codItemDev = Integer.parseInt(s.nextLine());
								boolean existirCodItemDev = false;
								for (int j = 0; j < biblioteca.listarUsers().get(i).alugadosUsers().size(); j++){
									if(biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem() == codItemDev	){
										existirCodItemDev = true;
										biblioteca.listarUsers().get(i).alugadosUsers().get(j).calcMulta();
										if(biblioteca.listarUsers().get(i).alugadosUsers().get(j).getPago() == true){
											System.out.println("Aluguel pago");
											System.out.println("Multa de devoluÃ§Ã£o R$" + biblioteca.listarUsers().get(i).alugadosUsers().get(j).getMulta());
										}else{
											System.out.println("Aluguel nÃ£o pago, falta pagar R$" + biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCusto());
											System.out.println("Multa de devoluÃ§Ã£o R$" + biblioteca.listarUsers().get(i).alugadosUsers().get(j).getMulta());
											System.out.println("Total a pagar R$" + (biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCusto() + biblioteca.listarUsers().get(i).alugadosUsers().get(j).getMulta()));
										}
										System.out.println("Concluir DevoluÃ§Ã£o? 1-sim, *0-nÃ£o");
										int resp = Integer.parseInt(s.nextLine());
										if(resp == 1){
											biblioteca.devolverItem(codItemDev, codUsuarioDev);
											System.out.println("DevoluÃ§Ã£o Concluida");
										}else{
											System.out.println("DevoluÃ§Ã£o Cancelada");
										}
										j = biblioteca.listarUsers().get(i).alugadosUsers().size();
									}
								}
								if(!existirCodItemDev){
									System.out.println("Item nÃ£o existe nos alugados desse usuario");
								}
								i = biblioteca.listarUsers().size();
							}
						}
						
						if(!existirCodUsuarioDev){
							for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
								if(biblioteca.listarAdmins().get(i).getCodUsuario() == codUsuarioDev){
									existirCodUsuarioDev = true;
									System.out.println("Codigo do(a) " + biblioteca.listarAdmins().get(i).getClass().getSimpleName() + " " + biblioteca.listarUsers().get(i).getNome());
									for (int j = 0; j < biblioteca.listarAdmins().get(i).alugadosUsers().size(); j++){
										System.out.println(
											"Item " + biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem() +
											", Data do aluguel " + biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataAluguel() +
											", Data da DevoluÃ§Ã£o " + biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataDevolucao()
										);
									}
									System.out.println("Digite o codigo do item em devoluÃ§Ã£o");
									int codItemDev = Integer.parseInt(s.nextLine());
									boolean existirCodItemDev = false;
									for (int j = 0; j < biblioteca.listarAdmins().get(i).alugadosUsers().size(); j++){
										if(biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem() == codItemDev	){
											existirCodItemDev = true;
											biblioteca.listarAdmins().get(i).alugadosUsers().get(j).calcMulta();
											if(biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getPago() == true){
												System.out.println("Aluguel pago");
												System.out.println("Multa de devoluÃ§Ã£o R$" + biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getMulta());
											}else{
												System.out.println("Aluguel nÃ£o pago, falta pagar R$" + biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCusto());
												System.out.println("Multa de devoluÃ§Ã£o R$" + biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getMulta());
												System.out.println("Total a pagar R$" + (biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCusto() + biblioteca.listarUsers().get(i).alugadosUsers().get(j).getMulta()));
											}
											System.out.println("Concluir DevoluÃ§Ã£o? 1-sim, *0-nÃ£o");
											int resp = Integer.parseInt(s.nextLine());
											if(resp == 1){
												biblioteca.devolverItem(codItemDev, codUsuarioDev);
												System.out.println("DevoluÃ§Ã£o Concluida");
											}else{
												System.out.println("DevoluÃ§Ã£o Cancelada");
											}
											j = biblioteca.listarAdmins().get(i).alugadosUsers().size();
										}
									}
									if(!existirCodItemDev){
										System.out.println("Item nÃ£o existe nos alugados desse usuario");
									}
									i = biblioteca.listarAdmins().size();
								}
							}
						} 
						break;
		///////////////////////// FUNCIONALIDADE VERIFICAR ITENS EM ALUGUEL//////////////////
					case 3:
						System.out.println("1)Buscar por código de item\n2)Buscar por código de usuário\n3)Listar todos\nn*)Cancelar");
						int op3 = Integer.parseInt(s.nextLine());
						switch (op3){
						/////// BUSCAR POR CÓDIGO DE ITEM ////////
						case 1:
							System.out.println("Digite o código do item: ");
							boolean itemAlugado=false; 
							int codItemB= Integer.parseInt(s.nextLine());
							for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
								for (int j = 0; j < biblioteca.listarAdmins().get(i).alugadosUsers().size(); j++) {
									if (biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem()==codItemB){
										if(biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getPago()==true){
											System.out.println(
													"Código do item "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem()+ 
													" : Data do aluguel "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataAluguel()+
													" : Data devolução "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataDevolucao()+
													" : Pago : usuario "+biblioteca.listarAdmins().get(i).getNome()+ 
													" - " +biblioteca.listarAdmins().get(i).getCodUsuario()
												); 
										}else{ 
											System.out.println(
													"Código do item "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem()+ 
													" : Data do aluguel "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataAluguel()+
													" : Data devolução "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataDevolucao()+
													" : Devendo : usuario "+biblioteca.listarAdmins().get(i).getNome()+ 
													" - " +biblioteca.listarAdmins().get(i).getCodUsuario()
												);
										}
										itemAlugado=true;
									}
								}	
							}
							for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
								for (int j = 0; j < biblioteca.listarUsers().get(i).alugadosUsers().size(); j++) {
									if (biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem()==codItemB){
										if(biblioteca.listarUsers().get(i).alugadosUsers().get(j).getPago()==true){
											System.out.println(
													"Código do item "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem()+ 
													" : Data do aluguel "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataAluguel()+
													" : Data devolução "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataDevolucao()+
													" : Pago : usuario "+biblioteca.listarUsers().get(i).getNome()+ 
													" - " +biblioteca.listarUsers().get(i).getCodUsuario()
												); 
										}else{
											System.out.println(
													"Código do item "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem()+ 
													" : Data do aluguel "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataAluguel()+
													" : Data devolução "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataDevolucao()+
													" : Devendo : usuario "+biblioteca.listarUsers().get(i).getNome()+ 
													" - " +biblioteca.listarUsers().get(i).getCodUsuario()
												);
										}
										itemAlugado=true;
									}
								}	
							}
							if (!itemAlugado) {
								System.out.println("Item não encontrado entre os alugados");
							}
							break;
						
						/////// BUSCAR POR CÓGDIGO DE USUARIO //////
						case 2:
							System.out.println("Digite o código do usuário: ");
							boolean UserAluguel=false; 
							int codUsuarioB= Integer.parseInt(s.nextLine());
							for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
								if (biblioteca.listarAdmins().get(i).getCodUsuario()==codUsuarioB){	
									for (int j = 0; j < biblioteca.listarAdmins().get(i).alugadosUsers().size(); j++) {
										if(biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getPago()==true){
											System.out.println(
													"Código do item "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem()+ 
													" : Data do aluguel "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataAluguel()+
													" : Data devolução "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataDevolucao()+
													" : Pago : usuario "+biblioteca.listarAdmins().get(i).getNome()+ 
													" - " +biblioteca.listarAdmins().get(i).getCodUsuario()
													); 
										}else{ 
											System.out.println(
													"Código do item "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem()+ 
													" : Data do aluguel "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataAluguel()+
													" : Data devolução "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataDevolucao()+
													" : Devendo : usuario "+biblioteca.listarAdmins().get(i).getNome()+ 
													" - " +biblioteca.listarAdmins().get(i).getCodUsuario()
													);
										}
									}
									UserAluguel=true; 
								}
							}
							for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
								if (biblioteca.listarUsers().get(i).getCodUsuario()==codUsuarioB){	
									for (int j = 0; j < biblioteca.listarUsers().get(i).alugadosUsers().size(); j++) {
										if(biblioteca.listarUsers().get(i).alugadosUsers().get(j).getPago()==true){
											System.out.println(
													"Código do item "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem()+ 
													" : Data do aluguel "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataAluguel()+
													" : Data devolução "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataDevolucao()+
													" : Pago : usuario "+biblioteca.listarUsers().get(i).getNome()+ 
													" - " +biblioteca.listarUsers().get(i).getCodUsuario()
													); 
										}else{ 
											System.out.println(
													"Código do item "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem()+ 
													" : Data do aluguel "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataAluguel()+
													" : Data devolução "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataDevolucao()+
													" : Devendo : usuario "+biblioteca.listarUsers().get(i).getNome()+ 
													" - " +biblioteca.listarUsers().get(i).getCodUsuario()
													);
										}
									} 
									UserAluguel=true; 
								}
							}
							
							if (!UserAluguel) {
								System.out.println("Usuário não encontrado");
							}
							break;
							
						/////// LISTAR TODOS ////////
						case 3: 
							for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
								for (int j = 0; j < biblioteca.listarAdmins().get(i).alugadosUsers().size(); j++) {
									if(biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getPago()==true){
										System.out.println(
												"Código do item "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem()+ 
												" : Data do aluguel "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataAluguel()+
												" : Data devolução "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataDevolucao()+
												" : Pago : usuario "+biblioteca.listarAdmins().get(i).getNome()+ 
												" - " +biblioteca.listarAdmins().get(i).getCodUsuario()
											); 
									}else{
										System.out.println(
												"Código do item "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getCodigoItem()+ 
												" : Data do aluguel "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataAluguel()+
												" : Data devolução "+biblioteca.listarAdmins().get(i).alugadosUsers().get(j).getDataDevolucao()+
												" : Devendo : usuario "+biblioteca.listarAdmins().get(i).getNome()+ 
												" - " +biblioteca.listarAdmins().get(i).getCodUsuario()
											);
									}
									
								}
								
							}
							for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
								for (int j = 0; j < biblioteca.listarUsers().get(i).alugadosUsers().size(); j++) {
									if(biblioteca.listarUsers().get(i).alugadosUsers().get(j).getPago()==true){
										System.out.println(
												"Código do item "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem()+ 
												" : Data do aluguel "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataAluguel()+
												" : Data devolução "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataDevolucao()+
												" : Pago : usuario "+biblioteca.listarUsers().get(i).getNome()+ 
												" - " +biblioteca.listarUsers().get(i).getCodUsuario()
											); 
									}else{
										System.out.println(
												"Código do item "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getCodigoItem()+ 
												" : Data do aluguel "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataAluguel()+
												" : Data devolução "+biblioteca.listarUsers().get(i).alugadosUsers().get(j).getDataDevolucao()+
												" : Devendo : usuario "+biblioteca.listarUsers().get(i).getNome()+ 
												" - " +biblioteca.listarUsers().get(i).getCodUsuario()
											); 
									}
									
								}
								
							}
							break; 
						default:
							System.out.println("Cancelado");
							break;
						
						 
						}
						break;
						
		/////////////////////////FUNCIONALIDADE RESERVAR ITEM ////////////////////////////////
						
					case 4:
						System.out.println("1)Reservar Item\n2)Retirar Item da Reserva\n3)Listar Itens Reservados\nn*)Cancelar");
						int op4 = Integer.parseInt(s.nextLine());
						switch (op4){
						case 1:
							System.out.println("Digite o codigo do item a ser reservado");
							int codItemRes=Integer.parseInt(s.nextLine());
							boolean existItemRes = false;
							for (int i = 0; i < biblioteca.listarAcervo().size(); i++) {
								if(biblioteca.listarAcervo().get(i).getCodigoItem()==codItemRes){
									existItemRes = true;
									i = biblioteca.listarAcervo().size();
								}else{
									System.out.println("Item não existe");
								}
							}
							if(existItemRes){
								System.out.println("Digite o codigo do usuario da reserva");
								int codUserRes=Integer.parseInt(s.nextLine());							
								boolean existUserRes = false;
								for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
									if(biblioteca.listarAdmins().get(i).getCodUsuario() == codUserRes){
										existUserRes = true;
										biblioteca.reservarItem(codItemRes, codUserRes);
										i = biblioteca.listarAdmins().size();
									}
								}
								if(!existUserRes){
									for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
										if(biblioteca.listarUsers().get(i).getCodUsuario() == codUserRes){
											existUserRes = true;
											biblioteca.reservarItem(codItemRes, codUserRes);
											i = biblioteca.listarUsers().size();
										}
									}
								}
								if(!existUserRes){
									System.out.println("Usuario não existe");
								}
							}
							break;
						
						case 2:
							System.out.println("Digite o codigo do usuario que ira ter removido o item da reserva");
							int codUserTirRes=Integer.parseInt(s.nextLine());
							boolean existUserTirRes = false;
							for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
								if(biblioteca.listarAdmins().get(i).getCodUsuario()==codUserTirRes){
									existUserTirRes = true;
									for (int j = 0; j < biblioteca.listarAdmins().get(i).reservadoUsers().size(); j++) {
										System.out.println(
											"Item " + biblioteca.listarAdmins().get(i).reservadoUsers().get(j).getCodigoItem() + 
											" reservado no dia " + biblioteca.listarAdmins().get(i).reservadoUsers().get(j).getDataAluguel()
										);
									}
									System.out.println("Digite o codigo do Item para remover reserva da reserva");
									int codItemTirRes=Integer.parseInt(s.nextLine());							
									boolean existItemTirRes = false;
									for (int j = 0; j < biblioteca.listarAdmins().get(i).reservadoUsers().size(); j++) {
										if(biblioteca.listarAdmins().get(i).reservadoUsers().get(j).getCodigoItem() == codItemTirRes){
											existItemTirRes = true;
											biblioteca.removerReserva(codItemTirRes, codUserTirRes);
											j = biblioteca.listarAdmins().size();
										}
									}
									if(!existItemTirRes){
										System.out.println("Item não existe entre os reservados");
									}
									i = biblioteca.listarAdmins().size();
								}
							}
							if(!existUserTirRes){
								for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
									if(biblioteca.listarUsers().get(i).getCodUsuario()==codUserTirRes){
										existUserTirRes = true;
										for (int j = 0; j < biblioteca.listarUsers().get(i).reservadoUsers().size(); j++) {
											System.out.println(
												"Item " + biblioteca.listarUsers().get(i).reservadoUsers().get(j).getCodigoItem() + 
												" reservado no dia " + biblioteca.listarUsers().get(i).reservadoUsers().get(j).getDataAluguel()
											);
										}
										System.out.println("Digite o codigo do Item para remover reserva da reserva");
										int codItemTirRes=Integer.parseInt(s.nextLine());							
										boolean existItemTirRes = false;
										for (int j = 0; j < biblioteca.listarUsers().get(i).reservadoUsers().size(); j++) {
											if(biblioteca.listarUsers().get(i).reservadoUsers().get(j).getCodigoItem() == codItemTirRes){
												existItemTirRes = true;
												biblioteca.removerReserva(codItemTirRes, codUserTirRes);
												j = biblioteca.listarUsers().size();
											}
										}
										if(!existItemTirRes){
											System.out.println("Item não existe entre os reservados");
										}
										i = biblioteca.listarUsers().size();
									}
								}
							}
							if(!existUserTirRes){
								System.out.println("Usuario não existe");
							}
							
							break;
						case 3:
							for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
								for (int j = 0; j < biblioteca.listarAdmins().get(i).reservadoUsers().size(); j++) {
									System.out.println(
										"Reservado dia " + biblioteca.listarAdmins().get(i).reservadoUsers().get(j).getDataAluguel() +
										" : Item " + biblioteca.listarAdmins().get(i).reservadoUsers().get(j).getCodigoItem() +
										" : Usuario " + biblioteca.listarAdmins().get(i).getNome() + ", " + biblioteca.listarAdmins().get(i).getCodUsuario()
									);
								}
							}
							for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
								for (int j = 0; j < biblioteca.listarUsers().get(i).reservadoUsers().size(); j++) {
									System.out.println(
										"Reservado dia " + biblioteca.listarUsers().get(i).reservadoUsers().get(j).getDataAluguel() +
										" : Item " + biblioteca.listarUsers().get(i).reservadoUsers().get(j).getCodigoItem() +
										" : Usuario " + biblioteca.listarUsers().get(i).getNome() + ", " + biblioteca.listarUsers().get(i).getCodUsuario()
									);
								}
							}
							break;
						default:
							System.out.println("Cancelado");
							break;
						}
						break;
						
		/////////////////////////FUNCIONALIDADE AUTALIZAR DADOS DO ITEM //////////////////////
					case 5:
						System.out.println("1)Atualizar Livro\n2)Atualizar apostila\n3)Atualizar texto\nn*)Cancelar");
						int op5 = Integer.parseInt(s.nextLine());
						switch (op5){
						case 1:
							System.out.println("Digite o codigo do livro a ser atualizado");
							int codLivroAtualizar = Integer.parseInt(s.nextLine());
							boolean existLivroAtualizar = false;
							for (int i = 0; i < biblioteca.listarLivros().size(); i++) {
								if(biblioteca.listarLivros().get(i).getCodigoItem()==codLivroAtualizar){
									existLivroAtualizar=true;
									System.out.println("1)Atualizar custo\n2)Atualizar titulo\n3)Atualizar autor\n4)Atualizar ISBN\n5)Atualizar edição\n6)Atualizar quantidade\nn*)Cancelar\n");
									int op51=Integer.parseInt(s.nextLine());
									switch (op51){
									case 1:
										System.out.println("Digite o novo custo");
										double novoCusto=Double.parseDouble(s.nextLine());
										biblioteca.listarLivros().get(i).setCusto(novoCusto);
										break;
									case 2:
										System.out.println("Digite o novo titulo");
										String novoTitulo=s.nextLine();
										biblioteca.listarLivros().get(i).setTitulo(novoTitulo);
										break;
									case 3:
										System.out.println("Digite o novo autor");
										String novoAutor=s.nextLine();
										biblioteca.listarLivros().get(i).setAutor(novoAutor);
										break;
									case 4:
										System.out.println("Digite o novo ISBN");
										String novoiSBN = s.nextLine();
										biblioteca.listarLivros().get(i).setISBN(novoiSBN);
										break;
									case 5:
										System.out.println("Digite a nova edição");
										Integer novaEdicao = Integer.parseInt(s.nextLine());
										biblioteca.listarLivros().get(i).setEdicao(novaEdicao);
										break;
									case 6:
										System.out.println("Digite a nova quantidade");
										int novaQuantidade = Integer.parseInt(s.nextLine());
										biblioteca.listarLivros().get(i).setQuantidade(novaQuantidade);
										break;
									default:
										System.out.println("Cancelado");
										break;
										
									} 
								}
							}
							if(!existLivroAtualizar){
								System.out.println("Item nao existe");
							}
							break;
						case 2:
							System.out.println("Digite o codigo da apostila a ser atualizada");
							int codApostilaAtualizar = Integer.parseInt(s.nextLine());
							boolean existApostilaAtualizar = false;
							for (int i = 0; i < biblioteca.listarApostilas().size(); i++) {
								if(biblioteca.listarApostilas().get(i).getCodigoItem()==codApostilaAtualizar){
									existApostilaAtualizar=true;
									System.out.println("1)Atualizar custo\n2)Atualizar titulo\n3)Atualizar autor\n4)Atualizar quantidade\nn*)Cancelar\n");
									int op52=Integer.parseInt(s.nextLine());
									switch (op52){
									case 1:
										System.out.println("Digite o novo custo");
										double novoCusto=Double.parseDouble(s.nextLine());
										biblioteca.listarApostilas().get(i).setCusto(novoCusto);
										break;
									case 2:
										System.out.println("Digite o novo titulo");
										String novoTitulo=s.nextLine();
										biblioteca.listarApostilas().get(i).setTitulo(novoTitulo);
										break;
									case 3:
										System.out.println("Digite o novo autor");
										String novoAutor=s.nextLine();
										biblioteca.listarApostilas().get(i).setAutor(novoAutor);
										break;
									case 4:
										System.out.println("Digite a nova quantidade");
										int novaQuantidade = Integer.parseInt(s.nextLine());
										biblioteca.listarApostilas().get(i).setQuantidade(novaQuantidade);
										break;
									default:
										System.out.println("Cancelado");
										break;
										
									} 
								}
							}
							if (!existApostilaAtualizar){
								System.out.println("Apostila nao existe");
							}
							break;
						case 3:
							System.out.println("Digite o codigo do texto a ser atualizado");
							int codTextoAtualizar = Integer.parseInt(s.nextLine());
							boolean existTextoAtualizar = false;
							for (int i = 0; i < biblioteca.listarTextos().size(); i++) {
								if(biblioteca.listarTextos().get(i).getCodigoItem()==codTextoAtualizar){
									existTextoAtualizar=true;
									System.out.println("1)Atualizar custo\n2)Atualizar autor\nn*)Cancelar\n");
									int op53=Integer.parseInt(s.nextLine());
									switch (op53){
									case 1:
										System.out.println("Digite o novo custo");
										double novoCusto=Double.parseDouble(s.nextLine());
										biblioteca.listarApostilas().get(i).setCusto(novoCusto);
										break;
									case 2:
										System.out.println("Digite o novo autor");
										String novoAutor=s.nextLine();
										biblioteca.listarApostilas().get(i).setAutor(novoAutor);
										break;
									default:
										System.out.println("Cancelado");
										break;
										
									} 
								}
							}
							if (!existTextoAtualizar){
								System.out.println("Texto nao existe");
							}
							break;
						default:
							System.out.println("Cancelado");
							break;
						}
						break;
		/////////////////////////FUNCIONALIDADE ATUALIZAR DADOS USUARIO//////////////////////
					case 6:
						System.out.println("1)Atualizar dados do usuario\n2)Atualizar dados administrador\nn*)Cancelar");
						int op6 = Integer.parseInt(s.nextLine());
						switch (op6){
						case 1:
							System.out.println("Digite o codigo do usuario a ser atualizado");
							int codUsuarioAtualizar = Integer.parseInt(s.nextLine());
							boolean existUsuarioAtualizar = false;
							for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
								if(biblioteca.listarUsers().get(i).getCodUsuario()==codUsuarioAtualizar){
									existUsuarioAtualizar=true;
									System.out.println("1)Atualizar nome\n2)Atualizar endereço\n3)Atualizar cpf\nn*)Cancelar\n");
									int op61=Integer.parseInt(s.nextLine());
									switch (op61){
									case 1:
										System.out.println("Digite o novo nome");
										String novoNome=s.nextLine();
										biblioteca.listarUsers().get(i).setNome(novoNome);
										break;
									case 2:
										System.out.println("Digite o novo endereço");
										String novoEndereco=s.nextLine();
										biblioteca.listarUsers().get(i).setEndereco(novoEndereco);
										break;
									case 3:
										System.out.println("Digite o novo Cpf");
										String novoCpf = s.nextLine();
										biblioteca.listarUsers().get(i).setCpf(novoCpf);
										break;
									default:
										System.out.println("Cancelado");
										break;
										
									} 
								}
							}
							if(!existUsuarioAtualizar){
								System.out.println("Usuario nao existe");
							}
							break;
						case 2:
							System.out.println("Digite o codigo do administrador a ser atualizada");
							int codAdminAtualizar = Integer.parseInt(s.nextLine());
							boolean existAdminAtualizar = false;
							for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
								if(biblioteca.listarAdmins().get(i).getCodUsuario()==codAdminAtualizar){
									existAdminAtualizar=true;
									System.out.println("1)Atualizar nome\n2)Atualizar endereço\n3)Atualizar cpf\n4)Atualizar senha para login\nn*)Cancelar\n");
									int op62=Integer.parseInt(s.nextLine());
									switch (op62){
									case 1:
										System.out.println("Digite o novo nome");
										String novoNome=s.nextLine();
										biblioteca.listarAdmins().get(i).setNome(novoNome);
										break;
									case 2:
										System.out.println("Digite o novo endereço");
										String novoEndereco=s.nextLine();
										biblioteca.listarAdmins().get(i).setEndereco(novoEndereco);
										break;
									case 3:
										System.out.println("Digite o novo cpf");
										String novoCpf=s.nextLine();
										biblioteca.listarAdmins().get(i).setCpf(novoCpf);
										break;
									case 4:
										System.out.println("Digite a nova senha");
										String novaSenha = s.nextLine();
										biblioteca.listarAdmins().get(i).setSenha(novaSenha);
										break;
									default:
										System.out.println("Cancelado");
										break;
										
									} 
								}
							}
							if (!existAdminAtualizar){
								System.out.println("Administrador nao existe");
							}
							break;
						default:
							System.out.println("Cancelado");
							break;
						}
						break;
		/////////////////////////FUNCIONALIDADE LISTAR ITENS NO ACERVO / TODOS OS USUARIOS ///////////////////////
					
					case 7:
						System.out.println("1)Listar livros\n2)Listar apostilas\n3)Listar textos\n4)Listas todos os itens\n5)Listar todos os usuarios\nn*)Cancelar");
						int op7 = Integer.parseInt(s.nextLine());
						switch(op7){
						
						///// LISTAR LIVROS /////
						
						case 1:
							for (int i = 0; i < biblioteca.listarLivros().size(); i++) {
								System.out.println(
									biblioteca.listarLivros().get(i).getCodigoItem() + " : " +
									biblioteca.listarLivros().get(i).getTitulo() + " : " +
									biblioteca.listarLivros().get(i).getAutor() + " : EdiÃ§Ã£o-" +
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
							
						///// LISTAR TODOS OS ITENS/////
						
						case 4:
							for (int i = 0; i < biblioteca.listarLivros().size(); i++) {
								System.out.println(
									biblioteca.listarLivros().get(i).getCodigoItem() + " : " +
									biblioteca.listarLivros().get(i).getTitulo() + " : " +
									biblioteca.listarLivros().get(i).getAutor() + " : EdiÃ§Ã£o-" +
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
						
						///// LISTAR TODOS OS USUARIOS /////
							
						case 5:
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
									System.out.println("Digite a ediÃ§Ã£o do Livro");
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
											System.out.println("Este codigo nÃ£o pertence a um Livro");
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
											System.out.println("Este codigo nÃ£o pertence a uma apostila");
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
						System.out.println("1)Deletar item do acervo\n2)Deletar usuario\n3)Cadastrar novo usuario\n4)Cadastrar novo administrador\nn*)Cancelar\n");
						boolean existirUser;
						boolean existirCPF;
						int op9 = Integer.parseInt(s.nextLine());
						switch(op9){
						///// DELETAR ITEM DO ACERVO////
							case 1:
								System.out.println("Digite o código do item a ser deletado");
								int codItemDell = Integer.parseInt(s.nextLine());
								boolean existItemDell=false;
								for (int i = 0; i < biblioteca.listarAcervo().size(); i++) {
									if(biblioteca.listarAcervo().get(i).getCodigoItem()==codItemDell){
										existItemDell=true;
									}
								}
								if (existItemDell){
									biblioteca.deletarItem(codItemDell);
									System.out.println("Item Deletado");
								}else{
									System.out.println("Item não existe");
								}
								break;
						///// DELETAR USUARIO /////
							case 2:
								System.out.println("Digite o codigo do usuario a ser deletado");
								int codUserDell = Integer.parseInt(s.nextLine());
								boolean existUserDell=false;
								boolean existAdminDell=false;
								for (int i = 0; i < biblioteca.listarAdmins().size(); i++) {
									if(biblioteca.listarAdmins().get(i).getCodUsuario()==codUserDell){
										existAdminDell=true;
									}
								}
								for (int i = 0; i < biblioteca.listarUsers().size(); i++) {
									if(biblioteca.listarUsers().get(i).getCodUsuario()==codUserDell){
										existUserDell=true;
									}
								}
								if (existAdminDell||existUserDell){
									if (existAdminDell){
										biblioteca.deletarAdmin(codUserDell);
									}
									if (existUserDell){
										biblioteca.deletarUser(codUserDell);
									}
									System.out.println("Usuario/Administrador deletado");
								}else{
									System.out.println("Usuario não existe");
								}
								break;
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
										System.out.println("Digite o endereÃ§o do Usuario");
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
										System.out.println("Digite o endereÃ§o do Administrador");
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
						
		//////////////////////////// OPÃ‡ÃƒO INVALIDA ///////////////////////////////////////////////				
					
					default:
						System.out.println("NÃ£o existe essa OpÃ§Ã£o");
						break;
					}
					
		//////////////////////////// TRATAMENTO DE EXEÃ‡Ã•ES ////////////////////////////////////////			
				
				}catch(NumberFormatException exception){
					System.out.println("Digite um numero");
				}catch(IllegalArgumentException exception){
					System.out.println("CPF	deve conter	11	dÃ­gitos");
				}
				
			}while(executar==true);
		////////////////////////////////////////////////////////////////////////////////////////////
		}
	}
}
