package Registry;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import Commons.Address;
import Marshaller.FunctionCall;
import Marshaller.FunctionCallMarshaller;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;

public class Registry 
{
	private Hashtable<String, Address> hTable = new Hashtable<String, Address>();
	private static Registry _instance = null;

	private Registry() { }

	public static Registry instance()
	{
		if (_instance == null)
			_instance = new Registry();
		return _instance;
	}

	public void put(String theKey, Entry theEntry)
	{
		System.out.println("S-a inregistrat " + theKey + " cu adresa "+ theEntry.dest() + ":" + theEntry.port() + " serviciul " + theEntry.getService());
		hTable.put(theKey, theEntry);
	}
	
	public Entry get(String aKey)
	{
		return (Entry)hTable.get(aKey);
	}
	
	public Entry getByService(String service) {
		Set<String> servers = hTable.keySet();
		Iterator<String> it = servers.iterator();
		while (it.hasNext()) {
			Entry entry = (Entry)hTable.get(it.next());
			if (entry.getService().equals(service))
				return entry;
		}
		return null;
	}
	
}




