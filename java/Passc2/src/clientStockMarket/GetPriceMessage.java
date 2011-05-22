package clientStockMarket;

import Commons.Message;

public class GetPriceMessage implements Message 
{
	private String name; // ar putea fi folosit pt a gasi pretul dupa numele unei companii de exemplu
	
	public String toString()
	{
		return this.getClass().getName()+"|"+name;
	}	
	
}
