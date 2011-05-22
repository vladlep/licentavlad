package clientStockMarket;

import RequestReply.*;
import Registry.*;
import Commons.Address;
import Commons.Marshaller;
import Commons.Message;


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