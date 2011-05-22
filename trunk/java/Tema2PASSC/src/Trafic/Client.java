package Trafic;

import orb.ToyORB;

public class Client {

	public static void main(String[] args) {
		ServerInfo info = new ServerInfo();
		ToyORB.register("gps", info);
		System.out.println(((InfoTrafic)ToyORB.getObjectByService("Trafic.InfoTrafic")).temperatura());
	}
	
}
