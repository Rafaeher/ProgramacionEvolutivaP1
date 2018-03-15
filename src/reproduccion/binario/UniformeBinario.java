package reproduccion.binario;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fenotipo.FenotipoReal;
import individuo.Individuo;
import reproduccion.Reproduccion;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;

public class UniformeBinario<FenotipoUB extends Fenotipo, Fitness extends Comparable<Fitness>>
		implements Reproduccion<GenotipoBinario, FenotipoUB, Fitness> {

	private static double P_CRUCE_GEN = 0.2;

	@Override
	public ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> reproduce(
			ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> p, Configuracion c) {
		

		ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> poblacionSolucion = new ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>>();
		ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> poblacionAux = new ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>>(p);
		Random r = new Random();

			int i = 0;
			while (i + 1 < p.size()) {
				int Indindividuo1 = r.nextInt(poblacionAux.size()-1);
				int Indindividuo2 = r.nextInt(poblacionAux.size()-1);
				double random = r.nextDouble();
				if (random <= c.getCruceporcentaje()) {
					if (p.get(i) != null && p.get(i + 1) != null) {
						Individuo<GenotipoBinario, FenotipoUB, Fitness> individuo1 = poblacionAux.get(Indindividuo1).clone();
						Individuo<GenotipoBinario, FenotipoUB, Fitness> individuo2 = poblacionAux.get(Indindividuo2).clone();

						ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> array_con_2_individuos;
						if(p.get(0).getGenotipo().getNumGenes() == 1){
							array_con_2_individuos = reproduce_un_gen(individuo1, individuo2);
						}
						else{
							array_con_2_individuos = reproduce_varios_genes(individuo1, individuo2);
						}

						Individuo<GenotipoBinario, FenotipoUB, Fitness> ind1 = array_con_2_individuos.get(0);
						Individuo<GenotipoBinario, FenotipoUB, Fitness> ind2 = array_con_2_individuos.get(1);
						
						poblacionSolucion.add(ind1);
						poblacionSolucion.add(ind2);

					}


				} else {
					Individuo<GenotipoBinario, FenotipoUB, Fitness> individuo1 = poblacionAux.get(Indindividuo1).clone();
					Individuo<GenotipoBinario, FenotipoUB, Fitness> individuo2 = poblacionAux.get(Indindividuo2).clone();
					poblacionSolucion.add(individuo1);
					poblacionSolucion.add(individuo2);

				}
				poblacionAux.remove(Indindividuo1);
				poblacionAux.remove(Indindividuo2);
				i = i + 2;
			}
		return poblacionSolucion;
	}

	private ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> reproduce_varios_genes(
			Individuo<GenotipoBinario, FenotipoUB, Fitness> individuo1,
			Individuo<GenotipoBinario, FenotipoUB, Fitness> individuo2) {
		GenotipoBinario genotipoInd1 = individuo1.getGenotipo(), genotipoInd2 = individuo2.getGenotipo();

		Random r = new Random();
		
		for(int i = 0; i < genotipoInd1.getNumGenes(); i++){
			if (r.nextDouble() < P_CRUCE_GEN) {
				
				GenBinario aux = genotipoInd1.getGenes().get(i);
					genotipoInd1.setGen(i, genotipoInd2.getGenes().get(i));
					genotipoInd2.setGen(i, aux);
				
			}
		}
		individuo1.getFenotipo();
		individuo2.getFenotipo();
		ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}

	private ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> reproduce_un_gen(
			Individuo<GenotipoBinario, FenotipoUB, Fitness> individuo1,
			Individuo<GenotipoBinario, FenotipoUB, Fitness> individuo2) {
		GenotipoBinario genotipoInd1 = individuo1.getGenotipo(), genotipoInd2 = individuo2.getGenotipo();
		
		for(int i = 0; i<genotipoInd1.getGen(0).getTamGen();i++ ){
			
			boolean aux = genotipoInd1.getGenes().get(0).getBit(i);
			genotipoInd1.getGenes().get(0).setBit(i, genotipoInd2.getGenes().get(0).getBit(i));
			genotipoInd1.getGenes().get(0).setBit(i, aux);
		}
		
		individuo1.getFenotipo();
		individuo2.getFenotipo();
		ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoUB, Fitness>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}

}
