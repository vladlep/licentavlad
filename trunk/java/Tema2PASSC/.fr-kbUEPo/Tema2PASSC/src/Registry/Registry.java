package Registry;

import java.lang.reflect.Method;
import java.util.Hashtable;

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
		hTable.put(theKey, theEntry);
	}
	
	public Entry get(String aKey)
	{
		return (Entry)hTable.get(aKey);
	}
	
}




