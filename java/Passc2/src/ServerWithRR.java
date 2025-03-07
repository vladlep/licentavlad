
import dnsmessages.*;
import RequestReply.*;
import Registry.*;
import Commons.Address;
import Commons.Marshaller;
import Commons.Message;
 

class ServerTransformer implements ByteStreamTransformer
{
	private MessageServer originalServer;

	public ServerTransformer(MessageServer s)
	{
		originalServer = s;
	}

	public byte[] transform(byte[] in)
	{
	/*	Message msg;
		Marshaller m = new Marshaller();
		//msg = m.unmarshal(in);

	//	Message answer = originalServer.get_answer(msg);

		byte[] bytes = m.marshal(answer);
		return bytes;
*/
		return null;
	}
}


class MessageServer 
{
	public Message get_answer(Message msg)
	{
	///	System.out.println("Server received " + msg.data + " from " + msg.sender);
		//Message answer = new Message("Server", "I am alive");
		return null;// answer;
	}
}

public class ServerWithRR
{
	public static void main(String args[])
	{
		new Configuration();
		
		ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());
		
		Address myAddr = Registry.instance().get("Server");
		
		Replyer r = new Replyer("Server", myAddr);

		while (true) {
		  r.receive_transform_and_send_feedback(transformer);
		}
		

	}

}