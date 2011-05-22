package stockMarket;

import utilities.Address;
import utilities.Marshaller;
import utilities.Message;
import RequestReply.*;
import Registry.*;


public class ClientStockMarket
{
	public static void main(String args[])
	{
		StockMarketInterface server;
		
		
	}
	private void locateServer()
	{
		LocateServer.locate("StockMarket");
	}

}