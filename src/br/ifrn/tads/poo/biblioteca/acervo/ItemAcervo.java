package br.ifrn.tads.poo.biblioteca.acervo;

import java.util.Date;

public class ItemAcervo {
	private int codigoItem;
	private double custo;
	private boolean pago;
	private Date dataAluguel; 
	private Date dataDevolucao;
	private Double multa;
	
	
	public ItemAcervo(double custo, int codigoItem) {
		this.custo=custo;
		this.codigoItem=codigoItem;		
	}

	public Date getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(Date dataAluguel) {
		this.dataAluguel = dataAluguel;
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


	public boolean getPago() {
		return pago;
	}


	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	public double getMulta() {
		return multa;
	}


	public void calcMulta() {
		long tempo1 = new Date().getTime();  
	    long tempo2 = dataDevolucao.getTime(); 
	    if (tempo1 > tempo2){
		    long difTempo = tempo2 - tempo1;  	     
		    int dias = (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000)); 
			this.multa = (dias * custo * 1.25);
	    }
	    else{
	    	this.multa = 0.0;
	    }
	}


	public Date getDataDevolucao() {
		return dataDevolucao;
	}


	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
}
