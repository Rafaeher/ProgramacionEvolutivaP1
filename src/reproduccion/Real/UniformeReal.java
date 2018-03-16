package reproduccion.Real;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fenotipo.FenotipoReal;
import fitness.Fitness;

import genotipo.GenotipoReal;
import genotipo.genes.GenReal;
import individuo.Individuo;
import reproduccion.Reproduccion;

public class UniformeReal<FenotipoUB extends Fenotipo, FitnessUB extends Fitness>
		implements Reproduccion<GenotipoReal, FenotipoReal, FitnessUB> {

	private static final double P_CRUCE_GEN = 0.5;

	@Override
	public ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>> reproduce(
			ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>> p, Configuracion c) {

		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>> poblacionSolucion = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>>();
		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>> poblacionAux = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>>(
				p);
		Random r = new Random();

		int i = 0;
		while (i + 1 < p.size()) {
			int Indindividuo1 = r.nextInt(poblacionAux.size() - 1);
			int Indindividuo2 = r.nextInt(poblacionAux.size() - 1);
			double random = r.nextDouble();
			if (random <= c.getCruceporcentaje()) {

				if (p.get(i) != null && p.get(i + 1) != null) {
					Individuo<GenotipoReal, FenotipoReal, FitnessUB> individuo1 = poblacionAux.get(Indindividuo1)
							.clone();
					Individuo<GenotipoReal, FenotipoReal, FitnessUB> individuo2 = poblacionAux.get(Indindividuo2)
							.clone();

					ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>> array_con_2_individuos;

					array_con_2_individuos = reproduce_varios_genes(individuo1, individuo2);

					Individuo<GenotipoReal, FenotipoReal, FitnessUB> ind1 = array_con_2_individuos.get(0);
					Individuo<GenotipoReal, FenotipoReal, FitnessUB> ind2 = array_con_2_individuos.get(1);

					poblacionSolucion.add(ind1);
					poblacionSolucion.add(ind2);

				}

			} else {
				Individuo<GenotipoReal, FenotipoReal, FitnessUB> individuo1 = poblacionAux.get(Indindividuo1).clone();
				Individuo<GenotipoReal, FenotipoReal, FitnessUB> individuo2 = poblacionAux.get(Indindividuo2).clone();
				poblacionSolucion.add(individuo1);
				poblacionSolucion.add(individuo2);

			}
			poblacionAux.remove(Indindividuo1);
			poblacionAux.remove(Indindividuo2);
			i = i + 2;
		}
		return poblacionSolucion;
	}

	private ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>> reproduce_varios_genes(
			Individuo<GenotipoReal, FenotipoReal, FitnessUB> individuo1,
			Individuo<GenotipoReal, FenotipoReal, FitnessUB> individuo2) {
		GenotipoReal genotipoInd1 = individuo1.getGenotipo(), genotipoInd2 = individuo2.getGenotipo();

		Random r = new Random();
		
		for(int i = 0; i < genotipoInd1.getNumGenes(); i++){
			if (r.nextDouble() < P_CRUCE_GEN) {
				
				GenReal aux = genotipoInd1.getGenes().get(i);
				genotipoInd1.setGen(i, genotipoInd2.getGenes().get(i));
				genotipoInd2.setGen(i, aux);
				
			}
		}
		individuo1.getFenotipo();
		individuo2.getFenotipo();
		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>> sol = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUB>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}

}
