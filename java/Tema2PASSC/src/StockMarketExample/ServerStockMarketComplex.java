package StockMarketExample;

import orb.ToyORB;

public class ServerStockMarketComplex implements ComplexStockMarket{

	public float get_price(String Company) {
		return 33600000;
	}
	
	public String whois_highest_today() {
		return "Banca Transilvania";
	}
	
	public String whois_lowest_today() {
		return "SIF Oltenia";
	}
	
	public static void main(String[] args) {
		ServerStockMarketComplex serverBVB = new ServerStockMarketComplex();
		ToyORB.register("BVB", serverBVB);
	}
	
}
