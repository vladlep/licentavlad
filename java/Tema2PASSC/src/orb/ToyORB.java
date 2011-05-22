package orb;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;

import Marshaller.FunctionCall;
import Marshaller.FunctionCallMarshaller;
import Marshaller.ResultMarshaller;
import Registry.Entry;
import Registry.HostAdd;
import RequestReply.Requestor;

public class ToyORB {
	
	private static final int NAMING_SRV_PORT = 4300;
	private static final String SERVER_IP = "localhost";
	
	public static void register(String name, Object service) {
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			String ip = "localhost";
			while (e.hasMoreElements()){
				//ip placa retea
				NetworkInterface intf = e.nextElement();
				if (intf.getName().equals("eth0")) {
					ip = getIpV4(intf.getInetAddresses());
				}
			}

			//get a free port
			ServerSocket server =
				new ServerSocket(0);
			long port = server.getLocalPort();
			server.close();
			
			Object[] functionArgs = { name, service.getClass().getInterfaces()[0].getName(), ip, port };
			FunctionCall call = new FunctionCall("register", functionArgs);
			Requestor requestor = new Requestor();
			requestor.deliver_and_wait_feedback(
					new HostAdd(SERVER_IP, NAMING_SRV_PORT),
					new FunctionCallMarshaller().marshal(call));
			// interfata remote trebuie sa fie prima implementata
			String intfName = service.getClass().getInterfaces()[0].getName();
			Class skelClass = Class.forName(intfName+"_Skeleton");
			Runnable skeleton = (Runnable)skelClass.getConstructors()[0].newInstance(service, ip, (int)port);
			new Thread(skeleton).start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static Object getObjectRef( String name ) {
		Object[] functionArgs = { name };
		FunctionCall call = new FunctionCall("getObjectReference", functionArgs);
		Requestor requestor = new Requestor();
		byte[] result = requestor.deliver_and_wait_feedback(
				new HostAdd(SERVER_IP, NAMING_SRV_PORT),
				new FunctionCallMarshaller().marshal(call));
		Object[] res = ResultMarshaller.unmarshall(result);
		try {
			Class stubClass = Class.forName(res[2]+"_Stub");
			return stubClass.getConstructors()[0].newInstance(res[0], Integer.parseInt((String)res[1]));
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static Object getObjectByService( String service ) {
		Object[] functionArgs = { service };
		FunctionCall call = new FunctionCall("getObjectByService", functionArgs);
		Requestor requestor = new Requestor();
		byte[] result = requestor.deliver_and_wait_feedback(
				new HostAdd(SERVER_IP, NAMING_SRV_PORT),
				new FunctionCallMarshaller().marshal(call));
		Object[] res = ResultMarshaller.unmarshall(result);
		try {
			Class stubClass = Class.forName(res[2]+"_Stub");
			return stubClass.getConstructors()[0].newInstance(res[0], Integer.parseInt((String)res[1]));
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	
	private static String getIpV4(Enumeration<InetAddress> address) {
		while (address.hasMoreElements()) {
			InetAddress add = address.nextElement();
			if (add.toString().length() < 15)
				return add.toString().replace("/", "");
		}
		return "localhost";
	}
	
}
