package utilities;


public class Marshaller
{
	public byte[] marshal(Message theMsg)
	{
	
		String m =" "+theMsg.toString();
		System.out.println(" the message before transf to byte :"+theMsg );
		byte b[] = new byte[m.length()];
		b = m.getBytes();
		b[0] = (byte)m.length();
		return b;
	}
	
}





