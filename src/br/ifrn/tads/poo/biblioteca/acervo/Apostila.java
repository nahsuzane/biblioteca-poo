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
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public void mudarQuantidade (int quantidade){
		this.quantidade += quantidade;
	}
}
