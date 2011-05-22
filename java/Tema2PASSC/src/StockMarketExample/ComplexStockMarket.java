package StockMarketExample;

public interface ComplexStockMarket {
	
	float get_price (String Company);
	String whois_highest_today();
	String whois_lowest_today();
	
}
