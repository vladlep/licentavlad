package Trafic;
import RequestReply.*;
import Marshaller.*;
import Registry.*;

public class InfoTrafic_Stub implements InfoTrafic {

	private String serverIp;
	private int serverPort;
	private Requestor requestor;

	public InfoTrafic_Stub(String serverIp, int serverPort) {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.requestor = new Requestor();
	}

	public int distanta(String orasA, String orasB) {
		Object[] functionArgs = { orasA,orasB };
		FunctionCall call = new FunctionCall("distanta", functionArgs);
		byte resultBytes[] = requestor.deliver_and_wait_feedback(new HostAdd(serverIp, serverPort), new FunctionCallMarshaller().marshal(call));
		String resultString = ResultMarshaller.unmarshall(resultBytes)[0];
		return Integer.parseInt(resultString);

	}
	public int temperatura() {
		Object[] functionArgs = {  };
		FunctionCall call = new FunctionCall("temperatura", functionArgs);
		byte resultBytes[] = requestor.deliver_and_wait_feedback(new HostAdd(serverIp, serverPort), new FunctionCallMarshaller().marshal(call));
		String resultString = ResultMarshaller.unmarshall(resultBytes)[0];
		return Integer.parseInt(resultString);

	}
	public String versiunea() {
		Object[] functionArgs = {  };
		FunctionCall call = new FunctionCall("versiunea", functionArgs);
		byte resultBytes[] = requestor.deliver_and_wait_feedback(new HostAdd(serverIp, serverPort), new FunctionCallMarshaller().marshal(call));
		String resultString = ResultMarshaller.unmarshall(resultBytes)[0];
		return resultString;

	}
	public String vremea(int latitudine, int longitudine) {
		Object[] functionArgs = { latitudine,longitudine };
		FunctionCall call = new FunctionCall("vremea", functionArgs);
		byte resultBytes[] = requestor.deliver_and_wait_feedback(new HostAdd(serverIp, serverPort), new FunctionCallMarshaller().marshal(call));
		String resultString = ResultMarshaller.unmarshall(resultBytes)[0];
		return resultString;

	}
	
}
