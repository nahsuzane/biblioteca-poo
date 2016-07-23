package br.ifrn.tads.poo.biblioteca.app;
import br.ifrn.tads.poo.biblioteca.usuario.*;
import br.ifrn.tads.poo.biblioteca.Biblioteca;

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
					System.out.println("8)Cadastrar Usuario\n9)Cadastrar Administrador\n0)Sair\n");
					int op0 = Integer.parseInt(s.nextLine());
					switch(op0){
		/////////////////////////////FUNCIONALIDADE CADASTRAR USUARIO////////////////////////	
					
					case 8:
						System.out.println("Digite um codigo para o Usuario");
						int codUser = Integer.parseInt(s.nextLine());
						System.out.println("Digite o nome do Usuario");
						String nomeUser = s.nextLine();
						System.out.println("Digite o endereço do Usuario");
						String endUser = s.nextLine();
						System.out.println("Digite o cpf do Usuario");
						String cpfUser = s.nextLine();
					
						biblioteca.cadastroUsuario(codUser, nomeUser, endUser, cpfUser);
						break;
						
		/////////////////////////////FUNCIONALIDADE CADASTRAR ADMINISTRADOR////////////////////////////////		
						
					case 9:
						System.out.println("Digite um codigo para o Administrador");
						int codAdm = Integer.parseInt(s.nextLine());
						System.out.println("Digite o nome do Administrador");
						String nomeAdm = s.nextLine();
						System.out.println("Digite o endereço do Administrador");
						String endAdm = s.nextLine();
						System.out.println("Digite o cpf do Administrador");
						String cpfAdm = s.nextLine();
						System.out.println("Digite um loguin para o Administrador");
						String logAdm = s.nextLine();
						System.out.println("Digite uma senha para o Administrador");
						String senAdm = s.nextLine();
					
						biblioteca.cadastroAdmin(codAdm, nomeAdm, endAdm, cpfAdm, logAdm, senAdm);
						break;
						
		//////////////////////////// FUNÇÃO PARA SAIR /////////////////////////////////////////////				
				
					case 0:
						System.out.println("Saiu do sistema");
						executar = false;
						break;
						
		//////////////////////////// OPÇÃO INVALIDA ///////////////////////////////////////////////				
					
					default:
						System.out.println("Não existe essa Opção");
						break;
					}
					
		//////////////////////////// TRATAMENTO DE EXEÇÕES ///////////////////////////////////////			
				
				}catch(NumberFormatException exception){
					System.out.println("Digite um numero inteiro");
				}catch(IllegalArgumentException exception){
					System.out.println("CPF	deve conter	11	dígitos");
				}
				
			}while(executar==true);
		////////////////////////////////////////////////////////////////////////////////////////////
		}
	}
}
