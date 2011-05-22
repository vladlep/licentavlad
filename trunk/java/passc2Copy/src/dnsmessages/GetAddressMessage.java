package dnsmessages;

import utilities.Message;

public class GetAddressMessage implements Message 
{

	private String serviciu;
	
	public GetAddressMessage(String serviciu)
	{
		this.serviciu = serviciu;
		
	}
	
	public String toString()
	{
		return (this.getClass().getName()+"|"+serviciu);
	}
	
	public String getServiciu()
	{
		return serviciu;
	}
	
}
