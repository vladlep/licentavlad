package Registry;

import java.util.*;
import Commons.Address;

public class Entry implements Address
{
	private String destinationId;
	private int portNr;
	private String serviceType;
	
	public Entry(String theDest, int thePort, String serviceType)
	{
		destinationId = theDest;
		portNr = thePort;
		this.serviceType = serviceType;
	}
	public String dest()
	{
		return destinationId;
	}
	public int port()
	{
		return portNr;
	}
	public String getService() {
		return serviceType;
	}
}