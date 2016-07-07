package br.ifrn.tads.poo.biblioteca.acervo;

public class Livro extends ItemAcervo{
	
	private String titulo;
	private String autor;
	private String ISBN;
	private Integer edicao;
	private int quantidade;

	public Livro(double custo, int codigoItem, String titulo,String autor,String ISBN, Integer edicao, int quantidade) {
		super(custo, codigoItem);
		this.titulo=titulo;
		this.autor=autor;
		this.ISBN=ISBN;
		this.edicao=edicao;
		this.quantidade=quantidade;
	}
}
