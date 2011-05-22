package Registry;

import java.lang.reflect.Method;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.corba.se.pept.protocol.MessageMediator;

import Marshaller.FunctionCall;
import Marshaller.FunctionCallMarshaller;
import Marshaller.ResultMarshaller;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;

public class NamingService implements ByteStreamTransformer {
	
	private FunctionCallMarshaller marshaller = 
		new FunctionCallMarshaller();
	
	private NamingService() {}
	
	private static void powerOn() {
		Replyer listener = new Replyer(new HostAdd("localhost", 4300));
		NamingService namingSrv = new NamingService();
		while (true) {
			listener.receive_transform_and_send_feedback(namingSrv);
		}
	}
	
	public static void main(String[] args) {
		powerOn();
	}
	
	public void register(String name, String service, String ip, int port) {
		Registry.instance().put(name, new Entry(ip, port, service));
	}
	
	public String[] getObjectReference(String name) {
		String res[] = new String[3];
		res[0] = Registry.instance().get(name).dest();
		res[1] = String.valueOf(Registry.instance().get(name).port());
		res[2] = Registry.instance().get(name).getService();
		return res;
	}
	
	public String[] getObjectByService(String service) {
		String res[] = new String[3];
		Entry obj = Registry.instance().getByService(service);
		res[0] = obj.dest();
		res[1] = String.valueOf(obj.port());
		res[2] = obj.getService();
		return res;
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
					if (functionResult != null)
						result = (String[])functionResult;
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
	
}
