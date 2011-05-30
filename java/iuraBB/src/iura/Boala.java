package iura;

import java.util.ArrayList;

public final class Boala {
	public String name;
	public ArrayList<String> simpAcute;
	public ArrayList<String> simpMedii;

	public Boala(String name, String[] _simpAcute, String[] _simpMedii) {
		this.name = name;
		this.simpAcute = new ArrayList<String>();
		this.simpMedii = new ArrayList<String>();
		for (String i : _simpAcute)
			this.simpAcute.add(i);
		for (String j : _simpMedii)
			this.simpMedii.add(j);
	}

}
