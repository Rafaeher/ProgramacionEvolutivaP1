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

public class UnPuntoReal<FenotipoUPB extends Fenotipo, FitnessUPB extends Fitness>
		implements Reproduccion<GenotipoReal, FenotipoReal, FitnessUPB> {

	@Override
	public ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>> reproduce(
			ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>> p, Configuracion c) {
		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>> poblacionSolucion = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>>();
		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>> poblacionAux = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>>(
				p);

		Random r = new Random();

		int i = 0;
		while (i + 1 < p.size()) {
			int Indindividuo1 = r.nextInt(poblacionAux.size() - 1);
			int Indindividuo2 = r.nextInt(poblacionAux.size() - 1);
			double random = r.nextDouble();
			if (random <= c.getCruceporcentaje()) {
				System.out.println("Porcentaje de cruce " + c.getCruceporcentaje());
				if (p.get(i) != null && p.get(i + 1) != null) {
					Individuo<GenotipoReal, FenotipoReal, FitnessUPB> individuo1 = poblacionAux.get(Indindividuo1)
							.clone();
					Individuo<GenotipoReal, FenotipoReal, FitnessUPB> individuo2 = poblacionAux.get(Indindividuo2)
							.clone();

					ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>> array_con_2_individuos;

					array_con_2_individuos = reproduce_varios_genes(individuo1, individuo2);

					Individuo<GenotipoReal, FenotipoReal, FitnessUPB> ind1 = array_con_2_individuos.get(0);
					Individuo<GenotipoReal, FenotipoReal, FitnessUPB> ind2 = array_con_2_individuos.get(1);

					poblacionSolucion.add(ind1);
					poblacionSolucion.add(ind2);

				}

			} else {

				Individuo<GenotipoReal, FenotipoReal, FitnessUPB> individuo1 = poblacionAux.get(Indindividuo1)
						.clone();
				Individuo<GenotipoReal, FenotipoReal, FitnessUPB> individuo2 = poblacionAux.get(Indindividuo2)
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

	private ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>> reproduce_varios_genes(
			Individuo<GenotipoReal, FenotipoReal, FitnessUPB> individuo1,
			Individuo<GenotipoReal, FenotipoReal, FitnessUPB> individuo2) {
		Random r = new Random();

		int num_genes = individuo1.getGenotipo().getNumGenes();
		int random = r.nextInt(num_genes);
		ArrayList<GenReal> genesIndividuo1 = new ArrayList<GenReal>(individuo1.getGenotipo().getGenes());
		ArrayList<GenReal> genesIndividuo2 = new ArrayList<GenReal>(individuo2.getGenotipo().getGenes());
		for (int i = random; i < num_genes; i++) {
			GenReal aux1 = new GenReal(individuo1.getGenotipo().getGenes().get(i).getTamGen());
			Double gen1 = new Double(individuo1.getGenotipo().getValorDeGen(i, indiceValor));
			aux1.setCodigo(gen1);
			GenBinario aux2 = new GenBinario(individuo2.getGenotipo().getGenes().get(i).getTamGen());
			ArrayList<Boolean> gen2 = new ArrayList<Boolean>(individuo2.getGenotipo().getGenes().get(i).getCodigo());
			aux2.setCodigo(gen2);
			genesIndividuo1.set(i, aux2);
			genesIndividuo2.set(i, aux1);
		}
		individuo1.getGenotipo().setGenes(genesIndividuo1);
		individuo2.getGenotipo().setGenes(genesIndividuo2);
		
		individuo1.getFenotipo();
		individuo2.getFenotipo();
		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>> sol = new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessUPB>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}

}
