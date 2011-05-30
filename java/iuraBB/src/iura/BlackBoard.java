package iura;

import java.io.IOException;
import java.util.*;

public final class BlackBoard {
	public String numePacient;
	public ArrayList<Medic> mediciParticipanti;
	public ArrayList<Medic> mediciCompetenti;
	public ArrayList<Boala> boliSigure;
	public ArrayList<Boala> boliPosibile;
	public ArrayList<String> simptome;

	public BlackBoard(String numePacient, String[] _simptome,
			Medic[] _mediciParticipanti) {
		this.numePacient = numePacient;
		this.mediciParticipanti = new ArrayList<Medic>();
		this.mediciCompetenti = new ArrayList<Medic>();
		this.boliSigure = new ArrayList<Boala>();
		this.boliPosibile = new ArrayList<Boala>();
		this.simptome = new ArrayList<String>();
		for (String i : _simptome) {
			simptome.add(i);
		}
		for (Medic i : _mediciParticipanti) {
			this.mediciParticipanti.add(i);
		}
	}

	public void study() throws IOException {
		for (Medic m : this.mediciParticipanti) {
			m.ramanereLaMasa(this);
		}
	}

	public String toString() {
		String res = "";
		for (Medic i : this.mediciCompetenti) {
			for (Boala j : this.boliSigure) {
				for (Boala k : i.boliDeCompetenta) {
					if ((j.name).equals(k.name)) {
						res += "Medicul " + i.titlu
								+ " a diagnosticat pacientul cu boala: "
								+ j.name + " boala sigura\n";
					}
				}
			}
			for (Boala j : this.boliPosibile) {
				for (Boala k : i.boliDeCompetenta) {
					if ((j.name).equals(k.name)) {
						res += "Medicul " + i.titlu
								+ " suspecteaza pacientul de boala: " + j.name
								+ " boala posibila\n";
					}
				}
			}
		}
		return res;
	}
}
