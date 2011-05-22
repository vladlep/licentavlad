package Marshaller;

public class FunctionCall
{
	public String functionName;
	public Object[] parameters;
	
	public FunctionCall(String functionName, Object[] arguments)
	{
		this.functionName = functionName;
		this.parameters = arguments;
	}
	
	public String toString() {
		String res = functionName + "(";
		for (int i = 0; i < parameters.length; i++) {
			String separator = (i != parameters.length - 1) ? ", " : "";
			res += parameters[i] + separator;
		}
		res += ")";
 		return res;
	}
}