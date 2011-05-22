package Marshaller;

public class FunctionCall
{
	public String functionName;
	public Object[] arguments;
	
	public FunctionCall(String functionName, Object[] arguments)
	{
		this.functionName = functionName;
		this.arguments = arguments;
	}
	
	public String toString() {
		String res = functionName + "(";
		for (int i = 0; i < arguments.length; i++) {
			String separator = (i != arguments.length - 1) ? ", " : "";
			res += arguments[i] + separator;
		}
		res += ")";
 		return res;
	}
}