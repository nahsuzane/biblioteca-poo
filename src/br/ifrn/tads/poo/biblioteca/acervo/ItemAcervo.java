package br.ifrn.tads.poo.biblioteca.acervo;

import java.util.Date;

public class ItemAcervo {
	private double custo;
	private Date dataAluguel; 
	private Date dataDevolucao;
	private int codigoItem;
	private boolean pago;
	
	public ItemAcervo(double custo, int codigoItem) {
		this.custo=custo;
		this.codigoItem=codigoItem;		
	}
}
