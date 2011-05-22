package StockMarketExample;

import orb.ToyORB;

public class ClientStockMarketComplex {

	public static void main(String[] args) {
		ComplexStockMarket stockBVB = (ComplexStockMarket)ToyORB.getObjectRef("BVB");
		System.out.println(stockBVB.get_price("Aiurea"));
		System.out.println(stockBVB.whois_highest_today());
	}
	
}
