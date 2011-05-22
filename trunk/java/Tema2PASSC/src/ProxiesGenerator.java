import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

import Marshaller.FunctionCall;
import Marshaller.FunctionCallMarshaller;
import Registry.HostAdd;


public class ProxiesGenerator {

	private static void generateStub(String classPath) {
		try {
			int trailSlash = classPath.lastIndexOf("/");
			String className = classPath.substring(trailSlash+1, classPath.length()).replace(".java", "");	
			System.out.println(className);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(classPath)));
			BufferedWriter stubW = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(classPath.replace(".java", "")+"_Stub.java")));
			BufferedWriter skelW = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(classPath.replace(".java", "")+"_Skeleton.java")));
			String line, stubLine = "", skelLine = "";
			boolean wroteFields = false;
			while ((line = br.readLine()) != null) {
				line = line.replace("interface", "class");
				stubLine = line;
				skelLine = line;
				if (line.contains(");")) {
					line = addPublicClause(line);
					stubLine = line.replace(");", ") {\n" + getStubCall(line) + "\n\t}");
					skelLine = line.replace(");", ") {\n" + getSkelCall(line) + "\t}\n");
					if (!wroteFields) {
						stubW.write(getStubVar(className + "_Stub"));
						skelW.write(getSkelVar(className + "_Skeleton"));
						wroteFields = true;
					}
				}
				stubW.write(stubLine.replace(className, className+"_Stub implements " + className) + "\n");
				skelW.write(skelLine.replace(className, className+"_Skeleton implements " + className + ", Runnable, ByteStreamTransformer") + "\n");
				if (line.contains("package")) {
					stubW.write(getStubImport());
					skelW.write(getSkelImport());
				}
			}
			stubW.close();
			skelW.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Clasa nu exista!");
		}
	}
	
	private static String getStubCall(String line) {
		String resultType = line.replaceAll("public *", "").replaceAll("$ |\t*", "").replaceAll(" .*", "");
		String functionName = line.replaceAll(" *\\(.*", "").replaceAll(".* ", "");
		String parameters = line.replaceAll(".*\\(", "").replace(");", "");
		parameters = removeTypes(parameters);
		String res = "";
		res += "\t\tObject[] functionArgs = { " + parameters + " };\n";
		res += "\t\tFunctionCall call = new FunctionCall(\"" + functionName + "\", functionArgs);\n";
		res += "\t\tbyte resultBytes[] = requestor.deliver_and_wait_feedback(new HostAdd(serverIp, serverPort), new FunctionCallMarshaller().marshal(call));\n";
		res += "\t\tString resultString = ResultMarshaller.unmarshall(resultBytes)[0];\n";
		res += "\t\treturn " + returnClause(resultType) + ";\n";
		return res;
	}
	
	private static String getSkelCall(String line) {
		String functionName = line.replaceAll(" *\\(.*", "").replaceAll(".* ", "");
		String parameters = line.replaceAll(".*\\(", "").replace(");", "");
		parameters = removeTypes(parameters);
		String res = "\t\treturn provider." + functionName + "("+parameters + ");\n";
		return res;
	}
	
	private static String returnClause(String result) {
		if (result.equals("int"))
			return "Integer.parseInt(resultString)";
		else if (result.equals("float"))
			return "Float.parseFloat(resultString)";
		else if (result.equals("Double"))
			return "Double.parseDouble(resultString)";
		return "resultString";
	}
	
	private static String removeTypes(String params) {
		String res = "";
		boolean ignored = true;
		for (int i = 0; i < params.length(); i++)
			if (params.charAt(i) == ' ') {
				ignored = !ignored;
			} else if (!ignored) {
				res += params.charAt(i);
			}
		return res;
	}
	
	private static String addPublicClause(String line) {
		return "\t"+"public "+line.replaceAll("$ |\t*", "");
	}

	private static String getSkelVar(String skelName) {
		String baseClass = skelName.replace("_Skeleton", "");
		String inits;
		inits ="\tprivate String ip;";
		inits +="\n\tprivate int port;";
		inits += "\tprivate " + baseClass + " provider;\n";
		inits += "\tprivate FunctionCallMarshaller marshaller =	new FunctionCallMarshaller();\n\n";
		//constructor
		inits += "\tpublic " + skelName + "(" + baseClass + " provider, String ip, int port) {\n\t\t this.provider = provider;\n\t\tthis.ip = ip;\n\t\tthis.port = port;\n\t}\n\n";
		inits += getSkelFunctionality();
		return inits;
	}
	
	private static String getStubVar(String stubName) {
		String inits = "";
		inits +="\tprivate String serverIp;";
		inits +="\n\tprivate int serverPort;";
		inits +="\n\tprivate Requestor requestor;\n\n";
		
		//constructor
		inits += "\tpublic " + stubName + "(String serverIp, int serverPort) {\n";
		inits += "\t\tthis.serverIp = serverIp;\n\t\tthis.serverPort = serverPort;\n\t\tthis.requestor = new Requestor();";
		inits += "\n\t}\n\n";
		return inits;
	}
	
	private static String getStubImport() {
		return "import RequestReply.*;\nimport Marshaller.*;\nimport Registry.*;\n";
	}
	
	private static String getSkelImport() {
		return "import java.lang.reflect.Method;\nimport RequestReply.*;\nimport Registry.HostAdd;\nimport Marshaller.FunctionCall;\nimport Marshaller.FunctionCallMarshaller;\nimport Marshaller.ResultMarshaller;\n";
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			generateStub(args[i]);
		}
	}
	
	private static String getSkelFunctionality() {
		String res = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("skeletonFunctionality")));
			String line = "";
			while ((line = br.readLine()) != null) {
				res += line + "\n";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
}
