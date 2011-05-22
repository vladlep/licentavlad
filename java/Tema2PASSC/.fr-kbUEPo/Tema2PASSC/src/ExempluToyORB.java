import Marshaller.FunctionCall;
import Marshaller.FunctionCallMarshaller;
import Registry.Entry;
import Registry.NamingService;
import RequestReply.Requestor;


public class ExempluToyORB {

	public static void main(String[] args) {
		Object[] functionArgs = {3, 2.5, "test"};
		FunctionCall call = new FunctionCall("register", functionArgs);
		Requestor requestor = new Requestor();
		requestor.deliver_and_wait_feedback(new Entry("localhost", 4300), new FunctionCallMarshaller().marshal(call));
	}
	
}
