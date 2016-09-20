package com.jpmorgan.jobassigniment.newSuperSimpleStocks;

import java.util.Date;

import com.jpmorgan.jobassigniment.newSuperSimpleStocks.enums.ItemType;
import com.jpmorgan.jobassigniment.newSuperSimpleStocks.enums.TradeType;

public class Trade {
	
	private Date tradeTimeStamp;
	private Double tradePrice;
	private Integer tradeQuantity;
	private TradeType tradeType;
	private double lastDividend;
	private double dividendYeld;
	private double peRatio;
	
	
	public Date getTradeTimeStamp() {
		return tradeTimeStamp;
	}

	public void setTradeTimeStamp(Date tradeTimeStamp) {
		this.tradeTimeStamp = tradeTimeStamp;
	}

	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Integer getTradeQuantity() {
		return tradeQuantity;
	}

	public void setTradeQuantity(Integer tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}

	public TradeType getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		for (TradeType t : TradeType.values()) {
            if (t.name().equalsIgnoreCase(tradeType)){
            	this.tradeType = t;
            }
        }
	}


	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getDividendYeld() {
		return dividendYeld;
	}

	public void setDividendYeld(double dividendYeld) {
		this.dividendYeld = dividendYeld;
	}

	public double getPeRatio() {
		return peRatio;
	}

	public void setPeRatio(double peRatio) {
		this.peRatio = peRatio;
	}
	
	
	/**
	 * Calculate the DividendYeld
	 * @param itemType Common or Preferred 
	 * @param parValue 
	 * @param fixedDividend 
	 * @return DividendYeld
	 */
	public double calculateDividendYeld(ItemType itemType, double parValue, double  fixedDivedend){
        double dividendYeld = 0;
		
		if (itemType == ItemType.Common){
		   dividendYeld = this.lastDividend / this.tradePrice;	
		} else {
		   dividendYeld = (fixedDivedend * parValue) / this.tradePrice;	
		}
		return dividendYeld;
	}
	
	
	/**
	 * Calculate the P/E Ratio 
	 * @return P/E Ratio
	 */
	public double calculatePeRatio(){
		return this.tradePrice / this.dividendYeld;
	}
	

	public Trade(String tradeType, int quantity, double price, double lastDividend, ItemType itemType, double parValue, double fixedDivedend) {
		this.setTradeTimeStamp(new Date());
		this.setTradePrice(price);
		this.setTradeQuantity(quantity);		
		this.setTradeType(tradeType);	
		//save the dividend that will be used for calculate the dividendYeld
		this.setLastDividend(lastDividend);
		this.setDividendYeld(this.calculateDividendYeld(itemType, parValue, fixedDivedend));
		this.setPeRatio(calculatePeRatio());
		
	}
	

}
