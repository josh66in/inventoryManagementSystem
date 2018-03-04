package com.barclayscard.interview.inventorysystem.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.barclayscard.interview.inventorysystem.core.Database;
import com.barclayscard.interview.inventorysystem.transactions.Transactions;

public class TransactionsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		Database.getDataBase().clear();
	}

	@Test
	public void testOne() {
//		fail("Not yet implemented");
		Transactions txn = new Transactions();
		
		txn.parse("create Book01 10.50 13.79");
		txn.parse("create Food01 1.47 3.98");
		txn.parse("create Med01 30.63 34.29");
		txn.parse("create Tab01 57.00 84.98");
		txn.parse("updateBuy Tab01 100");
		txn.parse("updateSell Tab01 2");
		txn.parse("updateBuy Food01 500");
		txn.parse("updateBuy Book01 100");
		txn.parse("updateBuy Med01 100");
		txn.parse("updateSell Food01 1");
		txn.parse("updateSell Food01 1");
		txn.parse("updateSell Tab01 2");
		txn.parse("report");
		txn.parse("delete Book01");
		txn.parse("updateSell Tab01 5");
		txn.parse("create Mobile01 10.51 44.56");
		txn.parse("updateBuy Mobile01 250");
		txn.parse("updateSell Food01 5");
		txn.parse("updateSell Mobile01 4");
		txn.parse("updateSell Med01 10");
		txn.parse("report");
	}
	
	@Test
	public void testTwo() {
//		fail("Not yet implemented");
		Transactions txn = new Transactions();
		
		txn.parse("create Book02 11.50 11.79");
		txn.parse("create Food11 1.47 13.98");
		txn.parse("create Med001 130.63 35.29");
		txn.parse("create Tab011 51.10 85.00");
		txn.parse("updateBuy Tab011 110");
		txn.parse("updateSell Tab011 20");
		txn.parse("updateBuy Food11 50");
		txn.parse("updateBuy Book02 10");
		txn.parse("updateBuy Med001 200");
		txn.parse("updateSell Food11 11");
		txn.parse("updateSell Food11 12");
		txn.parse("updateSell Tab011 3");
		txn.parse("report");
		txn.parse("delete Book02");
		txn.parse("delete Tab011");
		txn.parse("create Mobile11 100.51 144.56");
		txn.parse("updateBuy Mobile11 200");
		txn.parse("updateSell Food11 2");
		txn.parse("updateSell Mobile11 40");
		txn.parse("updateSell Med001 100");
		txn.parse("report");
	}

}
