package StockMarketExample;
import RequestReply.*;
import Marshaller.*;
import Registry.*;

public class ComplexStockMarket {
	
	private String serverIp;
	private int serverPort;
	private Requestor requestor;

	public D:\cristi\PASSC-Tema2\src\StockMarketExample\ComplexStockMarket_Stub(String serverIp, int serverPort) {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.requestor = new Requestor();
	}

	public float get_price (String Company) {
		Object[] functionArgs = { Company };
		FunctionCall call = new FunctionCall("get_price", functionArgs);
		byte resultBytes[] = requestor.deliver_and_wait_feedback(new HostAdd(serverIp, serverPort), new FunctionCallMarshaller().marshal(call));
		String resultString = ResultMarshaller.unmarshall(resultBytes)[0];
		return Float.parseFloat(resultString);

	}
	public String whois_highest_today() {
		Object[] functionArgs = {  };
		FunctionCall call = new FunctionCall("whois_highest_today", functionArgs);
		byte resultBytes[] = requestor.deliver_and_wait_feedback(new HostAdd(serverIp, serverPort), new FunctionCallMarshaller().marshal(call));
		String resultString = ResultMarshaller.unmarshall(resultBytes)[0];
		return resultString;

	}
	public String whois_lowest_today() {
		Object[] functionArgs = {  };
		FunctionCall call = new FunctionCall("whois_lowest_today", functionArgs);
		byte resultBytes[] = requestor.deliver_and_wait_feedback(new HostAdd(serverIp, serverPort), new FunctionCallMarshaller().marshal(call));
		String resultString = ResultMarshaller.unmarshall(resultBytes)[0];
		return resultString;

	}
	
}
