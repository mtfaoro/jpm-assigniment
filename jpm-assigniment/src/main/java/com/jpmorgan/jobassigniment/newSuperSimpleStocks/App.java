package com.jpmorgan.jobassigniment.newSuperSimpleStocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jpmorgan.jobassigniment.newSuperSimpleStocks.enums.ItemType;
import com.jpmorgan.jobassigniment.newSuperSimpleStocks.enums.TradeType;

public class App {
	
	public static void main( String[] args ){
		
		HashMap<String, Item> stockItens = new HashMap<String, Item>();	
		stockItens.put("TEA", new Item("TEA", ItemType.Common, 0.0, 0.0, 100.0));
		stockItens.put("POP", new Item("POP", ItemType.Common, 8.0, 0.0, 100.0));
		stockItens.put("ALE", new Item("ALE", ItemType.Common, 23.0, 0.0, 60.0));
		stockItens.put("GIN", new Item("GIN", ItemType.Preferred, 8.0, 0.02, 100.0));
		stockItens.put("JOE", new Item("JOE", ItemType.Common, 13.0, 0.0, 250.0));
		
		Scanner s = new Scanner(System.in);
		while(true){
			
			System.out.println("--------Stock Item List ------------ ");
			for (Map.Entry<String, Item> i : stockItens.entrySet()) {
				System.out.println(i.getKey());
			}
			
			System.out.println("Please choose a Stock Item (or type 'exit' to stop):");
			String option = s.next();
			
	        if (option.equalsIgnoreCase("exit")){
	        	break;
	        }
	        
	        Item selectedItem = stockItens.get(option.toUpperCase());
	        
	        if (selectedItem == null){
	        	System.out.println("***ERROR*** Please type a valid stock item!!!");
	        	continue;
	        }
	        
	        System.out.println("Please type a Quantity");
	        int quantity = 0;
	        
	        try {
	        	quantity = s.nextInt();
	        } catch (Exception e){
	        	System.out.println("***ERROR*** Ivalid Quantity!!!");
		        continue;
	        }
	        
	        System.out.println("Available Trades");
	        for (TradeType t : TradeType.values()) {
	            System.out.println(t.name());
	        }
	        
	        System.out.println("Please type a trade (Buy/Sell)");
	        String tradeType = s.next();
	        
	        boolean validTrade = false;
	        
	        for (TradeType t : TradeType.values()) {
	            if (t.name().equalsIgnoreCase(tradeType)){
	            	validTrade = true;
	            }
	        }
	        
	        if (!validTrade){
	        	System.out.println("***ERROR*** Please type a valid trade type!!!");
	        	continue;
	        }
	        
	        System.out.println("Please type a trade price");
	        double price = 0.0;
	        try {
	           price = s.nextDouble();
	        } catch (Exception e){
	           System.out.println("***ERROR*** Ivalid Price!!!");
	           continue;
	        }
	        
	        //save trade
	        Trade trade = selectedItem.insertTrade(tradeType, quantity, price);
	        stockItens.put(selectedItem.getStockSymbol(), selectedItem);
	        
	        //display DividendoYeld and P/E Ratio
	        System.out.println("-----------------------------------------------------------");
	        System.out.println("DividendYeld = " + trade.getDividendYeld());
	        System.out.println("P/E Ratio = " + trade.getPeRatio());
	        System.out.println("-----------------------------------------------------------");
			
		}
		
	    s.close();
	    
	    //Display all the recorded trades
	    System.out.println("");
	    System.out.println("-----------------------------------------------------------");
	    for (Map.Entry<String, Item> i : stockItens.entrySet()) {
			Item item = i.getValue();
			System.out.println(item.getStockSymbol() + "------Recorded Trades----------");
			List<Trade> trades = item.getTrades();
			for (int j = 0; j < trades.size(); j++){
				Trade trade = trades.get(j);
				System.out.println("Type: " + trade.getTradeType() + ", " +
						           "Time: " + trade.getTradeTimeStamp() + ", " +
						           "Quantity: " + trade.getTradeQuantity() + ", " +
						           "Price: " + trade.getTradePrice() + ", " +
						           "LastDividend: " + trade.getLastDividend() + ", " +
						           "DividendYeld: " + trade.getDividendYeld() + ", " +
						           "P/E Ratio: " + trade.getPeRatio());
			}		
	    }
	    
	    //Display Volume Weighted Stock Price
	    System.out.println("-----------------------------------------------------------");
	    System.out.println("--------------Volume Weighted Stock Price------------------");
	    
	    for (Map.Entry<String, Item> i : stockItens.entrySet()) {
			Item item = i.getValue();
			System.out.println(item.getStockSymbol() + " - " + item.calculateVWSP());
		}
	    System.out.println("-----------------------------------------------------------");
	    System.out.println("");
	    
	    //Calculate and Display Geometric Mean
	    System.out.println("-----------------------------------------------------------");
	    System.out.println("------------------Geometric Mean---------------------------");
	    
	    
	    double totalPrice = 0.0;
	    int totalQuantity = 0;
	    
	    for (Map.Entry<String, Item> i : stockItens.entrySet()) {
	    	Item item = i.getValue();
	    	totalPrice += item.getTotalPrice();
	    	totalQuantity += item.getTotalQuantity();
	    }
	    
	    System.out.println("Total Quantity = " + totalQuantity);
	    System.out.println("Total Price = " + totalPrice);
	    
	    double geometricMean = Math.pow(totalPrice, 1.0 / totalQuantity);
	    System.out.println("Geometric Mean = " + geometricMean);
	    
	}

}
