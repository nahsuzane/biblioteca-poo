package br.ifrn.tads.poo.biblioteca.acervo;

public class Texto extends ItemAcervo {

	private String autor;

	public Texto(double custo, int codigoItem, String autor) {
		super(custo, codigoItem);
		this.autor=autor;
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

}
