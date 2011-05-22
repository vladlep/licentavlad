package dnsmessages;

import Commons.Message;
import Registry.Entry;

public class DNSUnmarshaller
{
	public Message unmarshal(byte[] anArray)
	{
		
		/*
		 * sunt 2 tipuri de mesaje pe care le poate primi un server de naming service
		 * sau dns (pe scurt) : 1. subscribe  2. getAddres si 3. unsubscribe ( neimplementat inca) 
		 *  		 */
		String msg = new String(anArray);
		System.out.println("the received message : "+ msg);
		String type = msg.substring(1,msg.indexOf("|"));
		
		if ( type.equals("dnsmessages.SubscribeMessage"))
			{
				
			try
			{	
				
			return new SubscribeMessage( new Entry( msg.substring(msg.indexOf("|") + 1,msg.indexOf(":")), Integer.valueOf(msg.substring(msg.indexOf(":") + 1,msg.indexOf(";")))), msg.substring(msg.indexOf(";")+1, msg.length()) );
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			}
		if ( type.equals("dnsmessages.GetAddressMessage"))
		{
			try
			{					
			return new GetAddressMessage( msg.substring(msg.indexOf("|")+1, msg.length()));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
//	
		return null; // se verifica daca e null si eventual raspundem cu un mesaj de eroare (descriere eroare)
	}

}
