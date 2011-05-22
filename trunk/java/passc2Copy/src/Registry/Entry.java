package Registry;

import java.util.*;

import utilities.Address;


public class Entry implements Address
{
	private String destinationId;
	private int portNr;
	public Entry(String theDest, int thePort)
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
	public String toString()
	{
		return destinationId+":"+portNr;
	}
}