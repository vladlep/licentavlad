package dnsmessages;

import Commons.Address;
import Commons.Message;

public class SubscribeMessage implements Message
{
	
	
	private Address servAddr ;
	private String service;
	
	public SubscribeMessage(Address servAddr, String service )
	{
		this.servAddr = servAddr;
		 
		this.service = service;
		
	}
	
	public Address getAddress()
	{
		return servAddr;
	}
	
	public String getService()
	{
		return service;
	}
	public int getPort()
	{
		return servAddr.port();
		
	}
	
	public String toString()
	{
		String m="";
		m = this.getClass().getName()+"|"+servAddr.dest()+":"+servAddr.port()+";"+"service"; 
		return m;
	}
}
