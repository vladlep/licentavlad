package NamingServiceServer;

import Commons.Address;
import Registry.Entry;
import Registry.Registry;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;

public class NamingServiceServer 
{
	public static NamingServiceServer _instance = null;
	private static Registry reg = Registry.instance();
	private Address myAddress;
	private String name;
	private Replyer replyer ;
	private ByteStreamTransformer transf;
	
	public NamingServiceServer()
	{
	myAddress = new Entry("127.0.0.1",5555); // dns e la portul 5555
	name = "NamingService";
	replyer = new Replyer(name,myAddress);
	transf = new NamingServiceTransformer();
	}
	
	public static NamingServiceServer instance()
	{
		if(_instance == null)
			{
			_instance = new NamingServiceServer();
			}
		return _instance;
	}
	
	public static void subscribe(Entry addr, String serviciu)
	{
		reg.put(serviciu,addr);	
	}
	
	public static Address getAddr(String serviciu)
	{
		return reg.get(serviciu);
	}
	
	public static void main(String args[])
	{
	
		while(true)
		{
		NamingServiceServer.instance().replyer.receive_transform_and_send_feedback(_instance.transf);
		}
	}
	
}
