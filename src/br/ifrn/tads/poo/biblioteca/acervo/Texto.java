package br.ifrn.tads.poo.biblioteca.acervo;

public class Texto extends ItemAcervo {

	private String autor;
	private int quantidade;

	public Texto(double custo, int codigoItem, String autor) {
		super(custo, codigoItem);
		this.autor=autor;
		quantidade = 1;
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
	
	public void mudarQuantidade(int quantidade){
		this.quantidade += quantidade;
	}

}
