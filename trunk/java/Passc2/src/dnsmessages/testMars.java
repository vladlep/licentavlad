package dnsmessages;

import Commons.Marshaller;
import Commons.Message;
import Registry.Entry;

public class testMars 
{
// this is a test class for the marshaling and unmarsalling of the msg
	
	public static void main(String args[])
	{
	Marshaller marsh = new Marshaller();
	Message m = new GetAddressMessage("ion");
	byte[] b = marsh.marshal(m);
	System.out.println("The message has been marsh : "+b);
	DNSUnmarshaller unm = new DNSUnmarshaller();
	Message result = unm.unmarshal(b);
	System.out.println("The message has been unmarsh"+result.toString());
	}
	
}
