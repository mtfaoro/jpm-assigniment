package com.jpmorgan.jobassigniment.newSuperSimpleStocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.jpmorgan.jobassigniment.newSuperSimpleStocks.enums.ItemType;
import com.jpmorgan.jobassigniment.newSuperSimpleStocks.enums.TradeType;

public class Item {
	private String stockSymbol;
	private ItemType type;
	private Double lastDividend;
	private Double fixedDivedend;
	private Double parValue;
	private List<Trade> trades;
	
	
	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDivedend() {
		return fixedDivedend;
	}

	public void setFixedDivedend(Double fixedDivedend) {
		this.fixedDivedend = fixedDivedend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}
	
	
	public List<Trade> getTrades() {
		return trades;
	}
	
	/**
	 * Insert the trade typed by the user
	 * 
	 * @param tradeType Buy or Sell 
	 * @param quantity Quantity defined by the user for this trade
	 * @param price Price defined by the user for this trade
	 * @return trade
	 */
	public Trade insertTrade(String tradeType, Integer quantity, Double price){
		Trade trade = new Trade(tradeType, quantity, price, this.lastDividend, this.type, this.parValue, this.fixedDivedend);
		this.lastDividend = trade.getDividendYeld();
		trades.add(trade);
		
		return trade;
	}
	
	/**
	 * Calculate Volume Weighted Stock Price based on trades in past 15 minutes
	 *
	 * @return Volume Weighted Stock Price
	 */
	public double calculateVWSP(){
		Date now = new Date();
		Date initialTime = new Date (now.getTime() - (15 * 60 * 1000));
		double vwsp = 0.0;
		int totalQuantity = 0;
		
		for (int i = 0; i < trades.size(); i++){
			Trade t = trades.get(i);
			if (t.getTradeTimeStamp().before(initialTime)){
				continue;
			}else {
			   vwsp += t.getTradePrice() * t.getTradeQuantity();
			   totalQuantity += t.getTradeQuantity();
			}	
		}
		return vwsp /totalQuantity;
	}
	
	/**
	 * Get the item's total quantity traded 
	 *
	 * @return item's total quantity
	 */
	public int getTotalQuantity(){
		int total = 0;
		for (int i = 0; i < trades.size(); i++){
			Trade t = trades.get(i);
			total += t.getTradeQuantity();
		}
		return total;
	}
	
	/**
	 * Get the item's total value traded 
	 *
	 * @return item's total value
	 */
	public double getTotalPrice(){
		double total = 0.0;
		for (int i = 0; i < trades.size(); i++){
			Trade t = trades.get(i);
			total += t.getTradePrice();
		}
		return total;
	}

	
	public Item(String symbol, ItemType type, Double lastDividend, Double fixedDividend, Double parValue) {
		setStockSymbol(symbol);
		setType(type);
		setLastDividend(lastDividend);
		setFixedDivedend(fixedDividend);
		setParValue(parValue);
		trades = new ArrayList<Trade>();
	}
}
