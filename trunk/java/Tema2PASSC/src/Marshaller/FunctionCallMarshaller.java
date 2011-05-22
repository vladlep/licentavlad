package Marshaller;

import java.lang.String;

public class FunctionCallMarshaller
{
	
	public byte[] marshal(FunctionCall call)
	{
		String m = " " + call.functionName + ":";
		for (int i = 0; i < call.parameters.length; i++) {
			String separator = (i != call.parameters.length - 1) ? "," : "";
			m += call.parameters[i] + separator; 	
		}
 		byte b[] = new byte[m.length()];
		b = m.getBytes();
		b[0] = (byte)(m.length() - 1);
		return b;
	}
	
	public FunctionCall unmarshal(byte[] anArray)
	{
		String msg = new String(anArray);
		String functionName = msg.substring(0, msg.indexOf(":"));
		String[] arguments = 
			msg.substring(msg.indexOf(":") + 1, msg.length()).split(",");
		if (arguments[0].length() == 0)
			arguments = new String[0];
		return new FunctionCall(functionName, arguments);
	}

}





