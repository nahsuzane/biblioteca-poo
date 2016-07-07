package br.ifrn.tads.poo.biblioteca.acervo;
public class Apostila extends ItemAcervo{
	
	private String titulo;
	private String autor;
	private int quantidade;
	
	public Apostila(double custo, int codigoItem, String titulo, String autor,int quantidade) {
		super(custo, codigoItem);
		this.titulo=titulo;
		this.autor=autor;
		this.quantidade=quantidade;
		
	}
}
