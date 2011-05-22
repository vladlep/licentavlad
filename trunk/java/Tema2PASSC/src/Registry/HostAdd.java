package Registry;

import java.util.*;
import Commons.Address;

public class HostAdd implements Address
{
	private String destinationId;
	private int portNr;
	
	public HostAdd(String theDest, int thePort)
	{
		destinationId = theDest;
		portNr = thePort;
	}
	public String dest()
	{
		return destinationId;
	}
	public int port()
	{
		return portNr;
	}
}