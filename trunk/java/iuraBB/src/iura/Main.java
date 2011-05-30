package iura;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Boala alzheimer = new Boala("Alzheimer", new String[] { "a", "b", "w",
				"z", "h" }, new String[] { "q", "r", "t" });
		Boala cancer = new Boala("Cancer", new String[] { "d", "u", "y" },
				new String[] { "l", "k", "m" });
		Boala parkinson = new Boala("Parkinson", new String[] { "a", "b", "c",
				"e", "f" }, new String[] { "q", "r", "s" });
		Boala gripa = new Boala("Gripa", new String[] { "e", "r", "w", "v" },
				new String[] { "x", "c", "m", "n" });
		Medic oncolog = new Medic("Oncolog", new Boala[] { cancer });
		Medic neurolog = new Medic("Neurolog", new Boala[] { alzheimer,
				parkinson });
		Medic internist = new Medic("Internist", new Boala[] { gripa, cancer });
		BlackBoard fisa = new BlackBoard("Xulescu", new String[] { "a", "b",
				"q", "r", "n" }, new Medic[] { oncolog, neurolog, internist });
		fisa.study();
		System.out.println(fisa);

	}

}
