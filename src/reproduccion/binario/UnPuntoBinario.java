package reproduccion.binario;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import decodificador.Decodificador;
import individuo.Individuo;
import reproduccion.Reproduccion;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import fenotipo.Fenotipo;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGenReal;
import fitness.Fitness;
import fitness.FitnessReal;

public class UnPuntoBinario<FenotipoUPB extends Fenotipo, FitnessUPB extends Fitness>
		implements Reproduccion<GenotipoBinario, FenotipoReal, FitnessUPB> {


	@Override
	public ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> reproduce(
			ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> p, Configuracion c) {

		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> poblacionSolucion = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>>();
		Random r = new Random();

			int i = 0;
			while (i + 1 < p.size()) {
				double random = r.nextDouble();
				if (random <= c.getCruceporcentaje()) {
					if (p.get(i) != null && p.get(i + 1) != null) {
						// --------
						Individuo<GenotipoBinario, FenotipoUPB, FitnessUPB> copia = (Individuo<GenotipoBinario, FenotipoUPB, FitnessUPB>) p
								.get(i);
						GenotipoBinario genotipo_aux = (GenotipoBinario) copia.getGenotipo();

						ArrayList<GenBinario> array_genes = new ArrayList<GenBinario>(genotipo_aux.getGenes());
						GenotipoBinario genotipo_sol = new GenotipoBinario(array_genes);
						FenotipoReal fenotipo_aux = (FenotipoReal) copia.getFenotipo();
						ArrayList<FenotipoGenReal> array_fenotipo = new ArrayList<FenotipoGenReal>(
								fenotipo_aux.getCaracteristicas());
						FenotipoReal fenotipo_sol = new FenotipoReal();
						fenotipo_sol.setCaracteristicas(array_fenotipo);
						FitnessReal fitness = (FitnessReal) copia.getFitness();

						Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo1 = new Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>(
								genotipo_sol, fenotipo_sol, (FitnessUPB) fitness);
						// ---------
						Individuo<GenotipoBinario, FenotipoUPB, FitnessUPB> copia1 = (Individuo<GenotipoBinario, FenotipoUPB, FitnessUPB>) p
								.get(i + 1);
						GenotipoBinario genotipo_aux1 = (GenotipoBinario) copia1.getGenotipo();

						ArrayList<GenBinario> array_genes1 = new ArrayList<GenBinario>(genotipo_aux1.getGenes());
						GenotipoBinario genotipo_sol1 = new GenotipoBinario(array_genes1);
						FenotipoReal fenotipo_aux1 = (FenotipoReal) copia1.getFenotipo();
						ArrayList<FenotipoGenReal> array_fenotipo1 = new ArrayList<FenotipoGenReal>(
								fenotipo_aux1.getCaracteristicas());
						FenotipoReal fenotipo_sol1 = new FenotipoReal();
						fenotipo_sol1.setCaracteristicas(array_fenotipo1);
						FitnessReal fitness1 = (FitnessReal) copia.getFitness();

						Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo2 = new Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>(
								genotipo_sol1, fenotipo_sol1, (FitnessUPB) fitness);
						// ---------
						ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> array_con_2_individuos;
						if(p.get(0).getGenotipo().getNumGenes() == 1){
							array_con_2_individuos = reproduce_un_gen(individuo1, individuo2);
						}
						else{
							array_con_2_individuos = reproduce_varios_genes(individuo1, individuo2);
						}
						
						// try {

						Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> ind1 = array_con_2_individuos.get(0);
						Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> ind2 = array_con_2_individuos.get(1);
						
						poblacionSolucion.add(ind1);
						poblacionSolucion.add(ind2);

					}


				} else {
					Individuo<GenotipoBinario, FenotipoUPB, FitnessUPB> copia = (Individuo<GenotipoBinario, FenotipoUPB, FitnessUPB>) p.get(i);
					GenotipoBinario genotipo_aux = (GenotipoBinario) copia.getGenotipo();

					ArrayList<GenBinario> array_genes = new ArrayList<GenBinario>(genotipo_aux.getGenes());
					GenotipoBinario genotipo_sol = new GenotipoBinario(array_genes);
					FenotipoReal fenotipo_aux = (FenotipoReal) copia.getFenotipo();
					ArrayList<FenotipoGenReal> array_fenotipo = new ArrayList<FenotipoGenReal>(
							fenotipo_aux.getCaracteristicas());
					FenotipoReal fenotipo_sol = new FenotipoReal();
					fenotipo_sol.setCaracteristicas(array_fenotipo);
					FitnessReal fitness = (FitnessReal) copia.getFitness();
					Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo1 = new Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>(
							genotipo_sol, fenotipo_sol, (FitnessUPB) fitness);
					// ---------
					Individuo<GenotipoBinario, FenotipoUPB, FitnessUPB> copia1 = (Individuo<GenotipoBinario, FenotipoUPB, FitnessUPB>) p
							.get(i + 1);
					GenotipoBinario genotipo_aux1 = (GenotipoBinario) copia1.getGenotipo();

					ArrayList<GenBinario> array_genes1 = new ArrayList<GenBinario>(genotipo_aux1.getGenes());
					GenotipoBinario genotipo_sol1 = new GenotipoBinario(array_genes1);
					FenotipoReal fenotipo_aux1 = (FenotipoReal) copia1.getFenotipo();
					ArrayList<FenotipoGenReal> array_fenotipo1 = new ArrayList<FenotipoGenReal>(
							fenotipo_aux1.getCaracteristicas());
					FenotipoReal fenotipo_sol1 = new FenotipoReal();
					fenotipo_sol1.setCaracteristicas(array_fenotipo1);
					FitnessReal fitness1 = (FitnessReal) copia.getFitness();
					Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuo2 = new Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>(
							genotipo_sol1, fenotipo_sol1, (FitnessUPB) fitness);

					// ----------------------------------------
					poblacionSolucion.add(individuo1);
					poblacionSolucion.add(individuo2);

				}
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
		FenotipoReal fenotipo = new FenotipoReal();
		FenotipoGenReal fenotipoDelGen = new FenotipoGenReal(individuo1.getFenotipo().getCaracteristicas().get(0).getMin(),
				individuo1.getFenotipo().getCaracteristicas().get(0).getMax(),
				individuo1.getFenotipo().getCaracteristicas().get(0).getPrecision());
		ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGenReal>();
		fenotiposDeTodosLosGenes.add(fenotipoDelGen);
		fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);

		// Se obtiene un array con todos los fenotipos de TODOS los genes
		 Decodificador.decodifica(individuo1.getGenotipo(), fenotipo);

		fenotipo.getCaracteristicas().get(0).setFenotipodelgen(fenotipo.get(0).getFenotipodelgen());
		Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuosol1 = new Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>(
				individuo1.getGenotipo());
		individuosol1.setFenotipo(fenotipo);

		FenotipoReal fenotipo2 = new FenotipoReal();
		FenotipoGenReal fenotipoDelGen2 = new FenotipoGenReal(individuo2.getFenotipo().getCaracteristicas().get(0).getMin(),
				individuo2.getFenotipo().getCaracteristicas().get(0).getMax(),
				individuo2.getFenotipo().getCaracteristicas().get(0).getPrecision());
		ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes2 = new ArrayList<FenotipoGenReal>();
		fenotiposDeTodosLosGenes2.add(fenotipoDelGen2);
		fenotipo2.setCaracteristicas(fenotiposDeTodosLosGenes2);

		Decodificador.decodifica(individuo2.getGenotipo(), fenotipo2);

		fenotipo2.getCaracteristicas().get(0).setFenotipodelgen(fenotipo2.get(0).getFenotipodelgen());
		Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuosol2 = new Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>(
				individuo2.getGenotipo());
		individuosol2.setFenotipo(fenotipo2);

		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>>();
		sol.add(individuosol1);
		sol.add(individuosol2);

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
		// ------
		FenotipoReal fenotipo = new FenotipoReal();
		ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGenReal>();
		for (int i = 0; i < individuo1.getFenotipo().getCaracteristicas().size(); i++) {
			FenotipoGenReal fenotipoDelGen = new FenotipoGenReal(individuo1.getFenotipo().getCaracteristicas().get(i).getMin(),
					individuo1.getFenotipo().getCaracteristicas().get(i).getMax(),
					individuo1.getFenotipo().getCaracteristicas().get(i).getPrecision());
			fenotiposDeTodosLosGenes.add(fenotipoDelGen);
		}
		fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
		Decodificador.decodifica(individuo1.getGenotipo(), fenotipo);
		for (int i = 0; i < fenotipo.getCaracteristicas().size(); i++) {
			fenotipo.getCaracteristicas().get(i).setFenotipodelgen(fenotipo.get(i).getFenotipodelgen());
		}
		// fenotipo.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_del_individuo_i.get(0));
		Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuosol1 = new Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>(
				individuo1.getGenotipo());
		individuosol1.setFenotipo(fenotipo);
		// --------
		FenotipoReal fenotipo2 = new FenotipoReal();
		ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes2 = new ArrayList<FenotipoGenReal>();
		for (int i = 0; i < individuo2.getFenotipo().getCaracteristicas().size(); i++) {
			FenotipoGenReal fenotipoDelGen = new FenotipoGenReal(individuo2.getFenotipo().getCaracteristicas().get(i).getMin(),
					individuo2.getFenotipo().getCaracteristicas().get(i).getMax(),
					individuo2.getFenotipo().getCaracteristicas().get(i).getPrecision());
			fenotiposDeTodosLosGenes2.add(fenotipoDelGen);
		}
		fenotipo2.setCaracteristicas(fenotiposDeTodosLosGenes2);
		Decodificador.decodifica(individuo2.getGenotipo(), fenotipo2);
		for (int i = 0; i < fenotipo2.getCaracteristicas().size(); i++) {
			fenotipo2.getCaracteristicas().get(i).setFenotipodelgen(fenotipo2.get(i).getFenotipodelgen());
		}
		// fenotipo2.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_del_individuo_i2.get(0));
		Individuo<GenotipoBinario, FenotipoReal, FitnessUPB> individuosol2 = new Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>(
				individuo2.getGenotipo());
		individuosol2.setFenotipo(fenotipo2);

		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessUPB>>();
		sol.add(individuosol1);
		sol.add(individuosol2);

		return sol;
	}

}
