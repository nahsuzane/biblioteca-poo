package br.ifrn.tads.poo.biblioteca.acervo;

import java.util.Date;

public class ItemAcervo {
	private int codigoItem;
	private double custo;
	private boolean pago;
	private Date dataAluguel; 
	private Date dataDevolucao;
	
	
	public ItemAcervo(double custo, int codigoItem) {
		this.custo=custo;
		this.codigoItem=codigoItem;		
	}

	public int getCodigoItem() {
		return codigoItem;
	}


	public void setCodigoItem(int codigoItem) {
		this.codigoItem = codigoItem;
	}


	public double getCusto() {
		return custo;
	}


	public void setCusto(double custo) {
		this.custo = custo;
	}


	public boolean isPago() {
		return pago;
	}


	public void setPago(boolean pago) {
		this.pago = pago;
	}


	public Date getDataDevolucao() {
		return dataDevolucao;
	}


	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
}
