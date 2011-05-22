package dnsmessages;

import utilities.Address;
import utilities.Message;

public class ReplyAddressMessage implements Message
{
	private Address addr;
	
	public ReplyAddressMessage(Address ad)
	{
		this.addr = ad;
	}
	
	public String toString()
	{
		return (this.getClass().getName()+"|"+addr.dest()+":"+addr.port());
	}
	
	public Address getAddress()
	{
		return addr;
	}
	
}
