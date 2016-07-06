package br.ifrn.tads.poo.biblioteca.app;
import br.ifrn.tads.poo.biblioteca.usuario.Usuario;

public class SistemaBiblioteca {

	public static void main(String[] args) {
		System.out.println("Sistema biblioteca");
		
		System.out.println("Texte criação de usuario");
		Usuario usuario = new Usuario(1234, "Ze", "Rua dos touros", "01649306a74");

	}

}
