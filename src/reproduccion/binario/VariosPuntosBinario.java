package reproduccion.binario;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fenotipo.FenotipoReal;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;
import reproduccion.Reproduccion;

public class VariosPuntosBinario<FenotipoVPB extends Fenotipo, Fitness extends Comparable<Fitness>>
		implements Reproduccion<GenotipoBinario, FenotipoReal, Fitness> {

	@Override
	public ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> reproduce(
			ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> p, Configuracion c) {

		ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> poblacionSolucion = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>();
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> poblacionAux = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>(
				p);
		Random r = new Random();
		int i = 0;
		while (i + 1 < p.size()) {
			int Indindividuo1 = r.nextInt(poblacionAux.size() - 1);
			int Indindividuo2 = r.nextInt(poblacionAux.size() - 1);
			double random = r.nextDouble();
			if (random <= c.getCruceporcentaje()) {
				if (p.get(i) != null && p.get(i + 1) != null) {
					Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo1 = poblacionAux.get(Indindividuo1)
							.clone();
					Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo2 = poblacionAux.get(Indindividuo2)
							.clone();

					ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> array_con_2_individuos;
					if (p.get(0).getGenotipo().getNumGenes() == 1) {
						array_con_2_individuos = reproduce_un_gen(individuo1, individuo2);
					} else {
						array_con_2_individuos = reproduce_varios_genes(individuo1, individuo2);
					}

					Individuo<GenotipoBinario, FenotipoReal, Fitness> ind1 = array_con_2_individuos.get(0);
					Individuo<GenotipoBinario, FenotipoReal, Fitness> ind2 = array_con_2_individuos.get(1);

					poblacionSolucion.add(ind1);
					poblacionSolucion.add(ind2);

				}

			} else {
				Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo1 = poblacionAux.get(Indindividuo1).clone();
				Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo2 = poblacionAux.get(Indindividuo2).clone();
				poblacionSolucion.add(individuo1);
				poblacionSolucion.add(individuo2);

			}
			poblacionAux.remove(Indindividuo1);
			poblacionAux.remove(Indindividuo2);
			i = i + 2;
		}
		return poblacionSolucion;
	}

	private ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> reproduce_varios_genes(
			Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo1,
			Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo2) {

		GenotipoBinario genotipoInd1 = individuo1.getGenotipo(), genotipoInd2 = individuo2.getGenotipo();

		Random r = new Random();

		if (r.nextDouble() < 0.5) {
			int genACambiar = r.nextInt(genotipoInd1.getGenes().size()-1);
			GenBinario aux = genotipoInd1.getGenes().get(genACambiar);
				genotipoInd1.setGen(genACambiar, genotipoInd2.getGenes().get(genACambiar));
				genotipoInd2.setGen(genACambiar, aux);
			
		}
		individuo1.getFenotipo();
		individuo2.getFenotipo();
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;

	}

	private ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> reproduce_un_gen(
			Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo1,
			Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo2) {
		GenotipoBinario genotipoInd1 = individuo1.getGenotipo(), genotipoInd2 = individuo2.getGenotipo();

		Random r = new Random();

		if (r.nextDouble() < 0.5) {
			int Acambiar = r.nextInt(genotipoInd1.getGen(0).getTamGen()-1);
			boolean aux = genotipoInd1.getGenes().get(0).getBit(Acambiar);
			genotipoInd1.getGenes().get(0).setBit(Acambiar, genotipoInd2.getGenes().get(0).getBit(Acambiar));
			genotipoInd1.getGenes().get(0).setBit(Acambiar, aux);
		}
		individuo1.getFenotipo();
		individuo2.getFenotipo();
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}
}
