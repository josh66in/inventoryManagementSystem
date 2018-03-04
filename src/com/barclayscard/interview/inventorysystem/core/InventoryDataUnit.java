package com.barclayscard.interview.inventorysystem.core;

public class InventoryDataUnit {
	
	private Float costPrice = null;
	private Float sellingPrice = null;
	private Integer currentQuantity = null;
	private Integer quantitySoldSinceLastReport = null;
	private Float profitOrLossSinceLastReport = null;
	

	/**
	 * @param costPrice
	 * @param sellingPrice
	 * @param currentQuantity
	 * @param quantitySoldSinceLastReport
	 * @param profitOrLossSinceLastReport
	 */
	public InventoryDataUnit(Float costPrice, Float sellingPrice, Integer currentQuantity,
			Integer quantitySoldSinceLastReport, Float profitOrLossSinceLastReport) {
		super();
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.currentQuantity = currentQuantity;
		this.quantitySoldSinceLastReport = quantitySoldSinceLastReport;
		this.profitOrLossSinceLastReport = profitOrLossSinceLastReport;
	}
	
	
	
	public Float getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Float costPrice) {
		this.costPrice = costPrice;
	}
	public Float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public Integer getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(Integer currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public Integer getQuantitySoldSinceLastReport() {
		return quantitySoldSinceLastReport;
	}
	public void setQuantitySoldSinceLastReport(Integer quantitySoldSinceLastReport) {
		this.quantitySoldSinceLastReport = quantitySoldSinceLastReport;
	}
	public Float getProfitOrLossSinceLastReport() {
		return profitOrLossSinceLastReport;
	}
	public void setProfitOrLossSinceLastReport(Float profitOrLossSinceLastReport) {
		this.profitOrLossSinceLastReport = profitOrLossSinceLastReport;
	}
	
	
	
}
