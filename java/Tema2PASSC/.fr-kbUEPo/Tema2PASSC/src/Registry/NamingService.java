package Registry;

import java.lang.reflect.Method;

import Marshaller.FunctionCall;
import Marshaller.FunctionCallMarshaller;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;

public class NamingService implements ByteStreamTransformer {
	
	private NamingService() {}
	private FunctionCallMarshaller marshaller = 
		new FunctionCallMarshaller();
	
	private static void powerOn() {
		Replyer listener = new Replyer(new Entry("localhost", 4300));
		NamingService namingSrv = new NamingService();
		while (true) {
			listener.receive_transform_and_send_feedback(namingSrv);
		}
	}
	
	public static void main(String[] args) {
		powerOn();
	}
	
	public void register(String name) {
		
	}
	
	public void getObjectReference(String name) {
		
	}
	
	public byte[] transform(byte[] in) {
		FunctionCall call = marshaller.unmarshal(in);
		System.out.println(call);
		System.out.println(this.getClass().getName());
		Class registryClass = this.getClass();
		Method methods[] = registryClass.getMethods();
		boolean found = false;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(call.functionName)) {
				found = true;
			}
		}
		if (!found) {
			//THROW EXCEPTION
			System.out.println("Nu exista metoda");
		}			
		byte res[] = new byte[1];
		res[0] = 10;
		return res;
	}
	
}
