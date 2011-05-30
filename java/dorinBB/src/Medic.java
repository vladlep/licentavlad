
import java.io.*;

public class Medic {
	public String specializare;
	public Boala [] boliDeCompetenta;
	public Medic(String specializare, Boala [] boliDeCompetenta){
		this.specializare = specializare;
		this.boliDeCompetenta = boliDeCompetenta;
	}
	private int consultatieAmanuntita(BlackBoard fisaMedicala, Boala boala, int sumaControl) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(int i : boala.simptomeAcute){
			if(!fisaMedicala.simptome.contains(i)){
				System.out.print("Medic " + this.specializare +  "Aveti simptomul" + i  + 
						" pentru boala: " + boala.name + "? Y/N ");
				String ras = in.readLine();
				if(ras.compareTo("Y")==0){
					fisaMedicala.simptome.add(i);
					sumaControl += 2;
				    if(sumaControl >= 5){
				    	return sumaControl;
				    }
				}
			}else{
				if(fisaMedicala.simptome.contains(i)){
					sumaControl += 1;
					if(sumaControl >= 5){
				    	return sumaControl;
				    }
				}
			}
		}
		for(int i : boala.simptomeSecundare){
			if(!fisaMedicala.simptome.contains(i)){
				System.out.print("Medic " + this.specializare +  "Aveti simptomul" + i  + 
						" pentru boala: " + boala.name + "? Y/N ");
				String ras = in.readLine();
				if(ras.compareTo("Y")==0){
					fisaMedicala.simptome.add(i);
					sumaControl += 1;
				    if(sumaControl >= 5){
				    	return sumaControl;
				    }
				}
			}else{
				if(fisaMedicala.simptome.contains(i)){
					sumaControl += 1;
					if(sumaControl >= 5){
				    	return sumaControl;
				    }
				}
			}
		}
		return sumaControl;
	}
	public void filtrare(BlackBoard fisaMedicala) throws IOException{
		int simptomeAcuteGasite = 0;
		for(Boala i : this.boliDeCompetenta){
			int sumaControl = 0;
			for(int simptomAcut : i.simptomeAcute){
				for(int simptomFisa : fisaMedicala.simptome){
					if(simptomAcut == simptomFisa){
						sumaControl += 2;
						simptomeAcuteGasite ++;
					}
				}
			}
			if(sumaControl > 0){
				if(sumaControl >= 5){
					fisaMedicala.boliSigure.add(i);
					if(!fisaMedicala.mediciCompetenti.contains(this)){
						fisaMedicala.mediciCompetenti.add(this);
					}
				}else{
					sumaControl = consultatieAmanuntita(fisaMedicala ,i, sumaControl);
					if(sumaControl >= 5){
						fisaMedicala.boliSigure.add(i);
						if(!fisaMedicala.mediciCompetenti.contains(this)){
							fisaMedicala.mediciCompetenti.add(this);
						}
						System.out.println("Medic "+ this.specializare + 
								" am depistat boala " + i.name);
					}else{
						if(sumaControl >= 3){
							fisaMedicala.boliPosibile.add(i);
							if(!fisaMedicala.mediciCompetenti.contains(this)){
								fisaMedicala.mediciCompetenti.add(this);
							}
							System.out.println("Medic "+ this.specializare + 
									" am depistat ca posibila boala " + i.name);
						}else{
							System.out.println("Sunt medic " + this.specializare + "  nu pot diagnostica boala: " + i.name);
							//fisaMedicala.mediciParticipanti.remove(this);
						}
					}
				}
			}
		}
		if(simptomeAcuteGasite == 0){
			System.out.println("Sunt medic " + this.specializare + " ies de la masa definoitiv nu am putut sa diagnostichez nici o boala");
			//nfisaMedicala.mediciParticipanti.remove(this);
		}else{
			System.out.println("Sunt medic " + this.specializare + " ies de la masa definitiv ");
		}
	}
}
