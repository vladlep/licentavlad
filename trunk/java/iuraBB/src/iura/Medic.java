package iura;

import java.io.*;
import java.util.ArrayList;

public class Medic {
	public String titlu;
	public ArrayList<Boala> boliDeCompetenta;

	public Medic(String titlu, Boala[] boliDeCompetenta) {
		this.titlu = titlu;
		this.boliDeCompetenta = new ArrayList<Boala>();
		for (Boala s : boliDeCompetenta)
			this.boliDeCompetenta.add(s);
	}

	public void ramanereLaMasa(BlackBoard bb) throws IOException {
		int k = 0;
		int contor = 0;
		for (Boala b : this.boliDeCompetenta) {
			// int contor = 0;
			for (String str : b.simpAcute) {
				for (String str1 : bb.simptome) {
					if (str1.equals(str)) {
						contor++;
						k++;
						break;
					}
				}
			}

			if (contor > 0)
				consulta(bb, b);
			else {
				System.out.println("medicul " + this.titlu
						+ " nu poate trata boala " + b.name);
			}
			// System.out.println(contor);
		}
		if (k == 0) {
			System.out.println("medicul:" + this.titlu + " a iesit de la masa");
		}

	}

	public void consulta(BlackBoard bb, Boala b) throws IOException {
		int suma = 0;
		// Boala boala;

		for (String str : bb.simptome) {
			// int suma = 0;
			for (String str1 : b.simpAcute) {
				if (str1.compareTo(str) == 0)
					suma += 2;
			}
			for (String str2 : b.simpMedii) {
				if (str2.compareTo(str) == 0)
					suma += 1;
			}
		}
		// System.out.println("boala" + b.name + suma);
		if (suma >= 5) {
			if (!bb.mediciCompetenti.contains(this)) {
				bb.mediciCompetenti.add(this);
			}
			if (!bb.boliSigure.contains(b))
				bb.boliSigure.add(b);
		} else {
			if (suma >= 3) {
				testareSuplim(bb, b);

				if (!bb.mediciCompetenti.contains(this)) {
					bb.mediciCompetenti.add(this);

				}
				if (!bb.boliPosibile.contains(b) && !bb.boliSigure.contains(b))
					bb.boliPosibile.add(b);
			}

		}

	}

	public void testareSuplim(BlackBoard bb, Boala alfa) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// System.out.println("Here!");
		for (String str : alfa.simpAcute) {
			if (!bb.simptome.contains(str)) {
				System.out.print("Medic " + this.titlu + "Aveti simptomul"
						+ str + " pentru boala: " + alfa.name + "? Y/N ");
				String ras = in.readLine();

				if (ras.compareTo("Y") == 0) {
					bb.simptome.add(str);
					consulta(bb, alfa);
					break;
				}

			}
		}
		for (String str : alfa.simpMedii) {
			if (!bb.simptome.contains(str)) {
				System.out.print("Medic " + this.titlu + "Aveti simptomul:"
						+ str + " pentru boala: " + alfa.name + "? Y/N ");
				String ras = in.readLine();
				if (ras.compareTo("Y") == 0) {
					bb.simptome.add(str);
					consulta(bb, alfa);
					break;
				}

			}
		}
	}

}
