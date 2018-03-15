package reproduccion.binario;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import individuo.Individuo;
import reproduccion.Reproduccion;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import fenotipo.Fenotipo;
import fenotipo.FenotipoReal;
import fitness.Fitness;

public class UnPuntoBinario<FenotipoUPB extends Fenotipo, FitnessUPB extends Fitness>
		implements Reproduccion<GenotipoBinario, FenotipoReal, FitnessUPB> {


	@Override
	public ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> reproduce(
			ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> p, Configuracion c)
	{
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> poblacionSolucion = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>>();
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> poblacionAux = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>>(p);

		Random r = new Random();

			int i = 0;
			while (i + 1 < p.size()) {
				int Indindividuo1 = r.nextInt(poblacionAux.size()-1);
				int Indindividuo2 = r.nextInt(poblacionAux.size()-1);
				double random = r.nextDouble();
				if (random <= c.getCruceporcentaje()) {
					System.out.println("Porcentaje de cruce " + c.getCruceporcentaje());
					if (p.get(i) != null && p.get(i + 1) != null) {
						Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo1 = poblacionAux.get(Indindividuo1).clone();
						Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo2 = poblacionAux.get(Indindividuo2).clone();

						ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> array_con_2_individuos;

						if(p.get(0).getGenotipo().getNumGenes() == 1){
							array_con_2_individuos = reproduce_un_gen(individuo1, individuo2);
						}
						else{
							array_con_2_individuos = reproduce_varios_genes(individuo1, individuo2);
						}

						Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> ind1 = array_con_2_individuos.get(0);
						Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> ind2 = array_con_2_individuos.get(1);
						
						poblacionSolucion.add(ind1);
						poblacionSolucion.add(ind2);

					}


				} else {

					Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo1 = poblacionAux.get(Indindividuo1).clone();
					Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo2 = poblacionAux.get(Indindividuo2).clone();
					
					poblacionSolucion.add(individuo1);
					poblacionSolucion.add(individuo2);

				}
				poblacionAux.remove(Indindividuo1);
				poblacionAux.remove(Indindividuo2);
				i = i + 2;
			}
		return poblacionSolucion;
	}

	private ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> reproduce_un_gen(
			Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo1,
			Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo2) {
		Random r = new Random();
		int tamano_gen = individuo1.getGenotipo().getGenes().get(0).getTamGen();
		int random = r.nextInt(tamano_gen);
		for (int i = random; i < tamano_gen; i++)
		{
			boolean aux = individuo1.getGenotipo().getGenes().get(0).getCodigo().get(i);
			individuo1.getGenotipo().getGenes().get(0).getCodigo().set(i,
					individuo2.getGenotipo().getGenes().get(0).getCodigo().get(i));

			individuo2.getGenotipo().getGenes().get(0).getCodigo().set(i, aux);

		}
		individuo1.getFenotipo();
		individuo2.getFenotipo();
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}

	private ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> reproduce_varios_genes(
			Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo1,
			Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo2) {
		Random r = new Random();

		int num_genes = individuo1.getGenotipo().getNumGenes();
		if (num_genes > 2) {
			System.out.println("tendria que haber varios dos genes");
		}
		int random = r.nextInt(num_genes);
		ArrayList<GenBinario> genesIndividuo1 = new ArrayList<GenBinario>(individuo1.getGenotipo().getGenes());
		ArrayList<GenBinario> genesIndividuo2 = new ArrayList<GenBinario>(individuo2.getGenotipo().getGenes());
		for (int i = random; i < num_genes; i++) {
			GenBinario aux1 = new GenBinario(individuo1.getGenotipo().getGenes().get(i).getTamGen());
			ArrayList<Boolean> gen1 = new ArrayList<Boolean>(individuo1.getGenotipo().getGenes().get(i).getCodigo());
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
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}

}
