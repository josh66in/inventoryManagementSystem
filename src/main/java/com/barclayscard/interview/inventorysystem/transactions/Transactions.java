package com.barclayscard.interview.inventorysystem.transactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.barclayscard.interview.inventorysystem.core.DatabaseDao;

public class Transactions {
	
	DatabaseDao db = new DatabaseDao();
	
	//parses each command and directs appropriate action.
	public void parse(String str) {
		
		StringTokenizer stkr = new StringTokenizer(str);
		
		while(stkr.hasMoreTokens()) {
			
			String st = stkr.nextToken();
			
			switch(st) {
				case "create":
						String itemName = stkr.nextToken();
						Float costPrice = Float.parseFloat(stkr.nextToken());
						Float sellingPrice = Float.parseFloat(stkr.nextToken());
						db.create(itemName, costPrice, sellingPrice);
						break;
				case "delete":
						String itemName1 = stkr.nextToken();
						db.delete(itemName1);
						break;
				case "updateBuy":
						String itemName2 = stkr.nextToken();
						Integer quantity = Integer.parseInt(stkr.nextToken());
						db.updateBuy(itemName2, quantity);
						break;
				case "updateSell":
						String itemName3 = stkr.nextToken();
						Integer quantity1 = Integer.parseInt(stkr.nextToken());
						db.updateSell(itemName3, quantity1);
						break;
				case "report":
						db.report();
						break;
				case "updateSellPrice":
						String itemName4 = stkr.nextToken();
						Float newSellPrice = Float.parseFloat(stkr.nextToken());
						db.updateSellPrice(itemName4, newSellPrice);
						break;
				default:
						System.out.println("Cannot locate this case. Ignoring.");
						break;
			}
		}
	}

	public DatabaseDao getDb() {
		return db;
	}

	public static void main(String[] args) {
		
		Transactions txn = new Transactions();
		
		String str = new String();
		
		while(!str.equalsIgnoreCase("EOF")) {
				
				try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
					str = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				txn.parse(str);			
		}
	}

}
