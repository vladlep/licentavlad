package dnsmessages;
import utilities.Address;
import utilities.Message;

public class UnsubscribeMessage implements Message 
{
	private Address addr;
	
	public UnsubscribeMessage(Address ad)
	{
		this.addr = ad;
	}
	
	public Address getAddress()
	{
		return addr;
	}
	
	public String toString()
	{
		return addr.toString();
	}
	
}
