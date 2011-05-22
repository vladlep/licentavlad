package NamingServiceServer;

import dnsmessages.DNSUnmarshaller;
import dnsmessages.GetAddressMessage;
import dnsmessages.ReplyAddressMessage;
import dnsmessages.SubscribeMessage;
import Commons.Address;
import Commons.Marshaller;
import Commons.Message;
import Registry.Entry;
import RequestReply.ByteStreamTransformer;

public class NamingServiceTransformer implements ByteStreamTransformer
{


	public byte[] transform(byte[] in) 
	{
		Message mes;
		Marshaller marsh = new Marshaller();
		DNSUnmarshaller unmarsh = new DNSUnmarshaller();
		mes = unmarsh.unmarshal(in);
		
		if(mes instanceof SubscribeMessage)
		{
			// apelez serverul Naming.subscribe()
			NamingServiceServer.subscribe((Entry)(((SubscribeMessage)mes).getAddress()),((SubscribeMessage)mes).getService());
			return null; // ar trebui sa creez un tip special de measaj de tip Info / textsimplu
		}
		if(mes instanceof GetAddressMessage)
		{
			Address addr = NamingServiceServer.getAddr(((GetAddressMessage)mes).getServiciu());
			return marsh.marshal(new ReplyAddressMessage(addr));
		}
		return null;

	}

}
