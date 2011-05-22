package Marshaller;

public class ResultMarshaller {

	public static byte[] marshal(Object result[])
	{
		String m = " ";
		for (int i = 0; i < result.length; i++) {
			String separator = (i != result.length - 1) ? "," : "";
			m += result[i] + separator; 	
		}
 		byte b[] = new byte[m.length()];
		b = m.getBytes();
		b[0] = (byte)(m.length() - 1);
		return b;
	}
	
	public static String[] unmarshall(byte[] anArray)
	{
		String msg = new String(anArray);
		String[] arguments = 
			msg.substring(0, msg.length()).split(",");
		return arguments;
	}
	
}
