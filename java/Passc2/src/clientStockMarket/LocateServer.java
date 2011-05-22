package clientStockMarket;

import Commons.Address;
import Commons.Marshaller;
import Registry.Entry;
import RequestReply.ByteStreamTransformer;
import RequestReply.Requestor;


public class LocateServer 
{
	private static LocateServer _instance;
	private Requestor requestor;
	private Address dnsAddress;
	private ByteStreamTransformer transformer;
	private Marshaller marsh;
	public LocateServer()
	{
		requestor = new Requestor("ClientLocateServe");
		dnsAddress = new Entry("127.0.0.1", 5555);
		transformer = new ClientTransformer();
		marsh = new Marshaller();
	}
	
	public LocateServer instance()
	{
		if(_instance == null)
			_instance = new LocateServer();
		return _instance;
	}
	
	public static StockMarketInterface locate(String serviciu)
	{
		//marsh.
		//instance.requestor.deliver_and_wait_feedback(dnsAddress, marsh. )
		return null;
		
	}
	
	
}
