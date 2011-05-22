package clientStockMarket;
import dnsmessages.ReplyAddressMessage;
import Commons.Message;
import Registry.Entry;

public class ClientUnmarshaller 
{

		public Message unmarshal(byte[] anArray)
		{
			
			/*
			 * sunt 2 tipuri de mesaje pe care le poate primi un server de naming service
			 * sau dns (pe scurt) : 1. replyaddres  2.get price - de la server 
			 *  		 */
			String msg = new String(anArray);
			System.out.println("the received message : "+ msg);
			String type = msg.substring(1,msg.indexOf("|"));
			
			if ( type.equals("dnsmessages.ReplyAddressMessage"))
				{
					
				try
				{	
					
				return new ReplyAddressMessage( new Entry( msg.substring(msg.indexOf("|") + 1,msg.indexOf(":")), Integer.valueOf(msg.substring(msg.indexOf(":") + 1,msg.indexOf(";")))) );
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				}
			if ( type.equals("clientStockMarket.ReplyPriceMessage"))
			{
				try
				{					
				return new ReplyPriceMessage( Double.valueOf(msg.substring(msg.indexOf("|")+1, msg.length())));
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
