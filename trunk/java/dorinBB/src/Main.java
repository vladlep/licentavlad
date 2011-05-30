import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		Boala alzheimer = new Boala("Alzheimer", new Integer[]{2,3,6,8,12,16}, new Integer[]{9,10,18});
		Boala cancer = new Boala("Cancer", new Integer[]{77,12,15,18,23,0}, new Integer[]{25,90,87,102,166});
		Boala gripa = new Boala("Gripa",new Integer[]{17,19,3,71,23,15,88}, new Integer[]{66,2,0,12});
		Boala parkinson = new Boala("Parkinson", new Integer[]{2,3,8,5,32,18,101}, new Integer[]{9,10,15});
		Medic oncolog = new Medic("Oncolog",new Boala[]{cancer});
		Medic neurolog = new Medic("Neurolog", new Boala[]{alzheimer,parkinson});
		Medic internist = new Medic("Internist", new Boala[]{gripa,cancer});
		BlackBoard fisa = new BlackBoard("Xulescu",new Integer[]{2,3,10,9},new Medic[]{
				oncolog, neurolog, internist
		});
		fisa.study();
		System.out.println(fisa);
	}
}
