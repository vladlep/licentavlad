package Marshaller;

import java.lang.String;

public class FunctionCallMarshaller
{
	
	public byte[] marshal(FunctionCall call)
	{
		String m = " " + call.functionName + ":";
		for (int i = 0; i < call.arguments.length; i++) {
			String separator = (i != call.arguments.length - 1) ? "," : "";
			m += call.arguments[i] + separator; 	
		}
 		byte b[] = new byte[m.length()];
		b = m.getBytes();
		b[0] = (byte)m.length();
		return b;
	}
	
	public FunctionCall unmarshal(byte[] anArray)
	{
		String msg = new String(anArray);
		String functionName = msg.substring(0, msg.indexOf(":"));
		String[] arguments = 
			msg.substring(msg.indexOf(":") + 1, msg.length()).split(",");
		return new FunctionCall(functionName, arguments);
	}

}





