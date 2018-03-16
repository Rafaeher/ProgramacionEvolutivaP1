package reproduccion.Real;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fenotipo.FenotipoReal;
import fitness.Fitness;
import genotipo.GenotipoReal;
import genotipo.genes.GenReal;
//import genotipo.GenotipoBinario;
//import genotipo.genes.GenBinario;
import individuo.Individuo;
import reproduccion.Reproduccion;

public class VariosPuntosReal<FenotipoVPB extends Fenotipo, FitnessVPB extends Fitness>
		implements Reproduccion<GenotipoReal, FenotipoReal, FitnessVPB> {

	@Override
	public ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>> reproduce(
			ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>> p, Configuracion c) {

		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>> poblacionSolucion = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>>();
		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>> poblacionAux = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>>(
				p);
		Random r = new Random();
		int i = 0;
		while (i + 1 < p.size()) {
			int Indindividuo1 = r.nextInt(poblacionAux.size() - 1);
			int Indindividuo2 = r.nextInt(poblacionAux.size() - 1);
			double random = r.nextDouble();
			if (random <= c.getCruceporcentaje()) {
				if (p.get(i) != null && p.get(i + 1) != null) {
					Individuo<GenotipoReal, FenotipoReal, FitnessVPB> individuo1 = poblacionAux.get(Indindividuo1)
							.clone();
					Individuo<GenotipoReal, FenotipoReal, FitnessVPB> individuo2 = poblacionAux.get(Indindividuo2)
							.clone();

					ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>> array_con_2_individuos;

					array_con_2_individuos = reproduce_varios_genes(individuo1, individuo2);

					Individuo<GenotipoReal, FenotipoReal, FitnessVPB> ind1 = array_con_2_individuos.get(0);
					Individuo<GenotipoReal, FenotipoReal, FitnessVPB> ind2 = array_con_2_individuos.get(1);

					poblacionSolucion.add(ind1);
					poblacionSolucion.add(ind2);

				}

			} else {
				Individuo<GenotipoReal, FenotipoReal, FitnessVPB> individuo1 = poblacionAux.get(Indindividuo1)
						.clone();
				Individuo<GenotipoReal, FenotipoReal, FitnessVPB> individuo2 = poblacionAux.get(Indindividuo2)
						.clone();
				poblacionSolucion.add(individuo1);
				poblacionSolucion.add(individuo2);

			}
			poblacionAux.remove(Indindividuo1);
			poblacionAux.remove(Indindividuo2);
			i = i + 2;
		}
		return poblacionSolucion;

	}

	private ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>> reproduce_varios_genes(
			Individuo<GenotipoReal, FenotipoReal, FitnessVPB> individuo1,
			Individuo<GenotipoReal, FenotipoReal, FitnessVPB> individuo2) {
		
		GenotipoReal genotipoInd1 = individuo1.getGenotipo(), genotipoInd2 = individuo2.getGenotipo();

		Random r = new Random();

		if (r.nextDouble() < 0.5) {
			int genACambiar = r.nextInt(genotipoInd1.getGenes().size());
			GenReal aux = genotipoInd1.getGenes().get(genACambiar);
				genotipoInd1.setGen(genACambiar, genotipoInd2.getGenes().get(genACambiar));
				genotipoInd2.setGen(genACambiar, aux);
			
		}
		individuo1.getFenotipo();
		individuo2.getFenotipo();
		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>> sol = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessVPB>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;

	}

}
