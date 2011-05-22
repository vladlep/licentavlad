package stockMarket;

import utilities.Message;

public class ReplyPriceMessage implements Message
{
	private double price;
	
	public ReplyPriceMessage(double price)
	{
		this.price = price;
		
	}
	
	public String toString()
	{
		return this.getClass().getName()+"|"+price;
	}

	public double getPrice()
	{
		return price;
	}
}
