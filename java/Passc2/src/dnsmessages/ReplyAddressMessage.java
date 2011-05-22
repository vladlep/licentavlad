package dnsmessages;

import Commons.Address;
import Commons.Message;

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
