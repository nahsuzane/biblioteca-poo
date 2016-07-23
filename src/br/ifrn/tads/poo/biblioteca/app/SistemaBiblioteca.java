package br.ifrn.tads.poo.biblioteca.app;
import br.ifrn.tads.poo.biblioteca.usuario.*;
import br.ifrn.tads.poo.biblioteca.Biblioteca;

import java.util.Scanner;
import java.util.ArrayList;

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
				if(log.equals(biblioteca.listarAdmins().get(0).getLogin()) && sen.equals(biblioteca.listarAdmins().get(0).getSenha()) ){
					conectar = true;
				}else{
					System.out.println("Loguin ou Senha esta incorreto");
				}
			}
			System.out.println("Conectado no sistema da Biblioteca " + biblioteca.getNomeB());
		////////////////////////////////////////////////////////////////////////////////////////////
			
		//////////////////////////////FUNÇÕES POSSIVEIS DO ADMINISTRADOR////////////////////////////
			
		////////////////////////////////////////////////////////////////////////////////////////////
		}
		
	}
}
