package Trafic;
import java.lang.reflect.Method;
import RequestReply.*;
import Registry.HostAdd;
import Marshaller.FunctionCall;
import Marshaller.FunctionCallMarshaller;
import Marshaller.ResultMarshaller;

public class InfoTrafic_Skeleton implements InfoTrafic, Runnable, ByteStreamTransformer {

	private String ip;
	private int port;	private InfoTrafic provider;
	private FunctionCallMarshaller marshaller =	new FunctionCallMarshaller();

	public InfoTrafic_Skeleton(InfoTrafic provider, String ip, int port) {
		 this.provider = provider;
		this.ip = ip;
		this.port = port;
	}

	public byte[] transform(byte[] in) {
		FunctionCall call = marshaller.unmarshal(in);
		return executeService(call);
	}
	
	private byte[] executeService(FunctionCall call) {
		Class registryClass = this.getClass();
		Method methods[] = registryClass.getMethods();
		String[] result = null;
		boolean found = false;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(call.functionName) && sameParameters(methods[i].getParameterTypes(), call.parameters)) {
				found = true;
				Object[] parameters = transformParameters(methods[i].getParameterTypes(), call.parameters);
				try {
					Object functionResult = methods[i].invoke(this, parameters);
					if (functionResult != null) {
						if (functionResult instanceof String[])
							result = (String[])functionResult;
						else {
							result = new String[1];
							result[0] = functionResult.toString();
						}
					}
				} catch (Exception e) {
					//TODO arunca exceptie
					System.out.println("Eroare invocare");
				}
			}
		}
		if (!found) {
			//TODO arunca exceptie
			System.out.println("Nu exista metoda");
		}			
		byte res[] = new byte[1];
		if (result == null) {
			res[0] = 0;
			return res;
		} else
			return ResultMarshaller.marshal(result);
	}
	
	private boolean sameParameters(Class[] functionParameters, Object[] receivedParameters) {
		if (functionParameters.length != receivedParameters.length)
			return false;
		for (int i = 0; i < receivedParameters.length; i++) {
			if (!validClass(functionParameters[i]))
				return false;
		}
		return true;
	}
	
	private boolean validClass(Class className) {
		String name = className.getSimpleName();
		if (name.equals("int") ||
				name.equals("long") ||
				name.equals("float") ||
				name.equals("double") ||  
				name.equals("String"))
			return true;
		return false;
	}
	
	private Object convert(Class className, Object parameter) {
		String name = className.getSimpleName();
		String param = parameter.toString();
		if (name.equals("int"))
			return Integer.parseInt(param.toString());
		if (name.equals("long"))
			return Long.parseLong(param);
		if (name.equals("float"))
			return Float.parseFloat(param);
		if (name.equals("double"))
			return Double.parseDouble(param);
		return parameter;
	}
	
	private Object[] transformParameters(Class[] types, Object[] parameters) {
		Object[] result = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			result[i] = convert(types[i], parameters[i]);
		}
		return result;
	}
	
	public void run() {
		Replyer listener = new Replyer(new HostAdd(ip, port));
		while (true) {
			listener.receive_transform_and_send_feedback(this);
		}
	}
	public int distanta(String orasA, String orasB) {
		return provider.distanta(orasA,orasB);
	}

	public int temperatura() {
		return provider.temperatura();
	}

	public String versiunea() {
		return provider.versiunea();
	}

	public String vremea(int latitudine, int longitudine) {
		return provider.vremea(latitudine,longitudine);
	}

	
}
