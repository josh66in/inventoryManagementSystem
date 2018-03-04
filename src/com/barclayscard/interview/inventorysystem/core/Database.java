package com.barclayscard.interview.inventorysystem.core;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Database {
	
	//Can be made synchronizedhashmap or concurrenthashmap as need be for thread safety.
	//simulates the database. hence there it is static as the database is only one.
	public static Map<ItemNameUnit, List<InventoryDataUnit>> dataBase = new HashMap<ItemNameUnit, List<InventoryDataUnit>>();
	
	public static Map<ItemNameUnit, List<InventoryDataUnit>> getDataBase() {
		return dataBase;
	}

	//Creates hashmap with arraylist as value which holds all the price changes. The question statement mentioned two but
	//this can hold more price changes.
	public boolean create(String itemName, Float costPrice, Float sellingPrice) {
		
		ItemNameUnit inu = new ItemNameUnit(itemName, true);
		
		if(dataBase.containsKey(inu)) {
			
			System.out.println("ItemName " + itemName + " exists but is deleted. It will be undeleted "
					+ "and unreported profit or loss records will be reported.");
			
			InventoryDataUnit idu = new InventoryDataUnit(costPrice, sellingPrice, null, null, null);
			List<InventoryDataUnit> iduList = dataBase.get(inu);
			iduList.add(idu);
			dataBase.put(inu, iduList);
			return true;
			
		}
		
		inu.setIsDeleted(false);
		
		if(dataBase.containsKey(inu)) {
			
			System.out.println("ItemName " + inu.getItemName() + " that you are trying to add already Exists. ERROR. "
					+ "Please try a different commmand.");
			return false;
			
		}
		
		InventoryDataUnit idu = new InventoryDataUnit(costPrice, sellingPrice, new Integer(0), 
				new Integer(0), new Float(0));
		List<InventoryDataUnit> iduList = new ArrayList<InventoryDataUnit>();
		iduList.add(idu);
		ItemNameUnit inu1 = new ItemNameUnit(itemName, false);
		dataBase.put(inu1, iduList);
		
//		System.out.println("Item Added.");
		
		return true;
		
	}
	
	// Marks the item as deleted but does not actually delete item.
	public boolean delete(String itemName) {
		
		ItemNameUnit inu = new ItemNameUnit(itemName, true);
		ItemNameUnit inu1 = new ItemNameUnit(itemName, false);
		
		if(dataBase.containsKey(inu)) {
			
			System.out.println("ItemName " + itemName + " exists but is already deleted. There may be "
					+ "profit or loss records not reported and they will be reported in future reports "
					+ "if not already reported.");
			
			return true;
		}
		
		List<InventoryDataUnit> iduList = dataBase.get(inu1);
				
		dataBase.remove(inu1);
		
		dataBase.put(inu, iduList);
			
		return true;
	}
	
	public boolean updateBuy(String itemName, Integer quantity) {
		
		ItemNameUnit inu = new ItemNameUnit(itemName, false);
		
		if(!dataBase.containsKey(inu)) {
			
			System.out.println("ItemName " + itemName + " does not exists or is already deleted. ERROR.");

			return false;
		}
		
		List<InventoryDataUnit> iduList = dataBase.get(inu);
		
		InventoryDataUnit idu = iduList.get(iduList.size() - 1);
		
		idu.setCurrentQuantity(idu.getCurrentQuantity() + quantity);
		
		return true;
		
	}
	
	public boolean updateSell(String itemName, Integer quantity) {
		
		ItemNameUnit inu = new ItemNameUnit(itemName, false);
		
		if(!dataBase.containsKey(inu)) {
			
			System.out.println("ItemName " + itemName + " does not exists or is already deleted. ERROR.");

			return false;
		}
		
		List<InventoryDataUnit> iduList = dataBase.get(inu);
		
		InventoryDataUnit idu = iduList.get(iduList.size() - 1);
		
		int newQty = idu.getCurrentQuantity() - quantity;
		
		//checks for oversold status.
		if(newQty <= 0) {
			
			System.out.println("updateSell command resulted in negative quantity. There were " + 
			idu.getCurrentQuantity() + " existing and quantity to deduct is " + quantity + " resulting "
					+ "in " + newQty + ". As a result only current available quantity will be sold.");
			idu.setCurrentQuantity(0);
			idu.setProfitOrLossSinceLastReport(idu.getProfitOrLossSinceLastReport() + 
					(idu.getCurrentQuantity() * (idu.getSellingPrice() - idu.getCostPrice())));
			idu.setQuantitySoldSinceLastReport(idu.getQuantitySoldSinceLastReport() + idu.getCurrentQuantity());
			return true;
		}
		
		idu.setCurrentQuantity(newQty);
		//updates value for profit or loss since last report. When the report is generated this is zeroed.
		idu.setProfitOrLossSinceLastReport(idu.getProfitOrLossSinceLastReport() + 
				(quantity * (idu.getSellingPrice() - idu.getCostPrice())));
		//updates value for qty since last report and when report is generated this is zeroed.
		idu.setQuantitySoldSinceLastReport(idu.getQuantitySoldSinceLastReport() + quantity);
		
		return true;
		
	}
	
	//adds a new element to arraylist and zeros the previously held last element's qty to zero
	public boolean updateSellPrice(String itemName, Float newSellPrice) {
		
		ItemNameUnit inu = new ItemNameUnit(itemName, false);
		
		if(!dataBase.containsKey(inu)) {
			
			System.out.println("ItemName " + itemName + " does not exists or is already deleted. ERROR.");

			return false;
		}
		
		List<InventoryDataUnit> iduList = dataBase.get(inu);
		
		InventoryDataUnit idu = iduList.get(iduList.size() - 1);
		
		InventoryDataUnit iduNew = new InventoryDataUnit(idu.getCostPrice(), newSellPrice, 
				idu.getCurrentQuantity(), new Integer(0), new Float(0));
		
		idu.setCurrentQuantity(0);
		
		iduList.add(iduNew);
		
		return true;
	}
	
	public void report() {
		
		DecimalFormat df = new DecimalFormat("####.00");
		DecimalFormat df1 = new DecimalFormat("####.##");
		
		Map<ItemNameUnit, List<InventoryDataUnit>> sortedDataBase = new TreeMap<ItemNameUnit, List<InventoryDataUnit>>();
		
		sortedDataBase = new TreeMap<ItemNameUnit, List<InventoryDataUnit>>(dataBase);		
		
		System.out.println("\t\t\t\tINVENTORY REPORT");
		System.out.println("Item Name" + "\t\t\t" + "Bought At" + "\t\t\t\t" + "Sold At" + "\t\t\t\t" + "AvailableQty" 
				+ "\t\t\t" + "Value");
		System.out.println("---------" + "\t\t\t" + "---------" + "\t\t\t\t" + "-------" + "\t\t\t\t" + "------------" 
				+ "\t\t\t" + "-----");
		
		Iterator<ItemNameUnit> itr = sortedDataBase.keySet().iterator();
		
		Float costPriceOfDeletedItems = new Float(0);
		Float profitOrLossSinceLastReportGeneration = new Float(0);
		Float totalValue = new Float(0);
		
		
		while(itr.hasNext()) {
			
			ItemNameUnit inu = itr.next();
			List<InventoryDataUnit> iduList = sortedDataBase.get(inu);
			
			Iterator<InventoryDataUnit> itr1 = iduList.iterator();
			
			while(itr1.hasNext()) {
				
				InventoryDataUnit idu = itr1.next();
				
				if(inu.getIsDeleted())
					costPriceOfDeletedItems += (idu.getCostPrice() * idu.getCurrentQuantity());
				
				else if(iduList.size() == 1 || idu.getProfitOrLossSinceLastReport() != 0 || idu.getQuantitySoldSinceLastReport() != 0) {
					
					System.out.println(inu.getItemName() + "\t\t\t" + df.format(idu.getCostPrice()) + "\t\t\t\t" 
					+ df.format(idu.getSellingPrice()) + 
							"\t\t\t\t" + df1.format(idu.getCurrentQuantity()) + "\t\t\t" 
					+ df.format(idu.getCurrentQuantity() * idu.getCostPrice()));
					
					profitOrLossSinceLastReportGeneration += idu.getProfitOrLossSinceLastReport();
					idu.setProfitOrLossSinceLastReport(new Float(0));
					idu.setQuantitySoldSinceLastReport(new Integer(0));
					
					totalValue += idu.getCostPrice() * idu.getCurrentQuantity();
					
				}
			}
			
		}
		
		System.out.println("---------" + "------------" + "---------" + "----------------" + "-------" + "----------------" + "------------" 
				+ "------------" + "-----");
		System.out.println("Total Value" + "\t\t\t\t"  
				+ "\t\t\t\t" + "\t\t\t\t" + df.format(totalValue));
		System.out.println("Profit since previous report" + "\t\t\t\t" 
				+ "\t\t\t\t" + "\t\t\t\t"  + df.format(profitOrLossSinceLastReportGeneration - costPriceOfDeletedItems));
		
	}

}
