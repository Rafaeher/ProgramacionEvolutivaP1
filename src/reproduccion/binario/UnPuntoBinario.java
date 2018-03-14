package reproduccion.binario;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import decodificador.Decodificador;
import individuo.Individuo;
import reproduccion.Reproduccion;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGen;

public class UnPuntoBinario<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
		implements Reproduccion<GenotipoBinario, FenotipoReal, Fitness> {
	//private ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> poblacion;

	@Override
	public ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> reproduce(
			ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> p, Configuracion c) {
		// ArrayList<Individuo<GenotipoBinario,FenotipoReal,Fitness>>
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> poblacionSolucion =
				new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>();
		// publacion_solucion = new
		// ArrayList<Individuo<GenotipoBinario,FenotipoReal,Fitness>>(poblacion.size());
		
		// Los individuos solo tienen un gen
		if (p.get(0).getGenotipo().getNumGenes() == 1) {
			// Los individuos solo tienen un gen
			Random r = new Random();
			int i = 0;
			while (i + 1 < p.size()) {
				double random = r.nextDouble();
				if (random <= c.getCruceporcentaje()) {
					if (p.get(i) != null && p.get(i + 1) != null) {
						// Para comprobar que no nos salimos del array
						
						//--------
						Individuo<Genotipo, Fenotipo, Fitness> copia = (Individuo<Genotipo, Fenotipo, Fitness>) p.get(i);
						GenotipoBinario genotipo_aux = (GenotipoBinario)copia.getGenotipo();
						ArrayList<GenBinario> array_genes = new ArrayList<GenBinario>(genotipo_aux.getGenes());
						GenotipoBinario genotipo_sol = new GenotipoBinario(array_genes);
						FenotipoReal fenotipo_aux = (FenotipoReal)copia.getFenotipo();
						ArrayList<FenotipoGen> array_fenotipo = new ArrayList<FenotipoGen>(fenotipo_aux.getCaracteristicas());
						FenotipoReal fenotipo_sol = new FenotipoReal();
						fenotipo_sol.setCaracteristicas(array_fenotipo);
						Double fitness = new Double((double) copia.getFitness());
						Individuo<GenotipoBinario, FenotipoReal, Fitness>individuo1 = new Individuo<GenotipoBinario, FenotipoReal, Fitness>(
								genotipo_sol, fenotipo_sol, (Fitness)fitness);
						//---------
						Individuo<Genotipo, Fenotipo, Fitness> copia1 = (Individuo<Genotipo, Fenotipo, Fitness>) p.get(i+1);
						GenotipoBinario genotipo_aux1 = (GenotipoBinario)copia1.getGenotipo();
						ArrayList<GenBinario> array_genes1 = new ArrayList<GenBinario>(genotipo_aux1.getGenes());
						GenotipoBinario genotipo_sol1 = new GenotipoBinario(array_genes1);
						FenotipoReal fenotipo_aux1= (FenotipoReal)copia1.getFenotipo();
						ArrayList<FenotipoGen> array_fenotipo1 = new ArrayList<FenotipoGen>(fenotipo_aux1.getCaracteristicas());
						FenotipoReal fenotipo_sol1 = new FenotipoReal();
						fenotipo_sol1.setCaracteristicas(array_fenotipo1);
						Double fitness1 = new Double((double) copia.getFitness());
						Individuo<GenotipoBinario, FenotipoReal, Fitness>individuo2 = new Individuo<GenotipoBinario, FenotipoReal, Fitness>(
								genotipo_sol1, fenotipo_sol1, (Fitness)fitness);
						//---------
						ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> array_con_2_individuos = reproduce_un_gen(
								individuo1, individuo2);
						// try {
						Individuo<GenotipoBinario, FenotipoReal, Fitness> ind1 = array_con_2_individuos.get(0);
						Individuo<GenotipoBinario, FenotipoReal, Fitness> ind2 = array_con_2_individuos.get(1);
						// poblacion.add(i,ind1);
						// poblacion.add(i+1,ind2);
						poblacionSolucion.add(ind1);
						poblacionSolucion.add(ind2);
						// }
						// catch(Exception e) {
						// System.out.println("hola");
						// }

					}

				}
				else{
					//---------------------------------------
					
					//--------
					Individuo<Genotipo, Fenotipo, Fitness> copia = (Individuo<Genotipo, Fenotipo, Fitness>) p.get(i);
					GenotipoBinario genotipo_aux = (GenotipoBinario)copia.getGenotipo();
					ArrayList<GenBinario> array_genes = new ArrayList<GenBinario>(genotipo_aux.getGenes());
					GenotipoBinario genotipo_sol = new GenotipoBinario(array_genes);
					FenotipoReal fenotipo_aux = (FenotipoReal)copia.getFenotipo();
					ArrayList<FenotipoGen> array_fenotipo = new ArrayList<FenotipoGen>(fenotipo_aux.getCaracteristicas());
					FenotipoReal fenotipo_sol = new FenotipoReal();
					fenotipo_sol.setCaracteristicas(array_fenotipo);
					Double fitness = new Double((double) copia.getFitness());
					Individuo<GenotipoBinario, FenotipoReal, Fitness>individuo1 = new Individuo<GenotipoBinario, FenotipoReal, Fitness>(
							genotipo_sol, fenotipo_sol, (Fitness)fitness);
					//---------
					Individuo<Genotipo, Fenotipo, Fitness> copia1 = (Individuo<Genotipo, Fenotipo, Fitness>) p.get(i+1);
					GenotipoBinario genotipo_aux1 = (GenotipoBinario)copia1.getGenotipo();
					ArrayList<GenBinario> array_genes1 = new ArrayList<GenBinario>(genotipo_aux1.getGenes());
					GenotipoBinario genotipo_sol1 = new GenotipoBinario(array_genes1);
					FenotipoReal fenotipo_aux1= (FenotipoReal)copia1.getFenotipo();
					ArrayList<FenotipoGen> array_fenotipo1 = new ArrayList<FenotipoGen>(fenotipo_aux1.getCaracteristicas());
					FenotipoReal fenotipo_sol1 = new FenotipoReal();
					fenotipo_sol1.setCaracteristicas(array_fenotipo1);
					Double fitness1 = new Double((double) copia.getFitness());
					Individuo<GenotipoBinario, FenotipoReal, Fitness>individuo2 = new Individuo<GenotipoBinario, FenotipoReal, Fitness>(
							genotipo_sol1, fenotipo_sol1, (Fitness)fitness);
					
					//----------------------------------------
					poblacionSolucion.add(individuo1);
					poblacionSolucion.add(individuo2);
					
				}

				i = i + 2;
			}
			/*
			 * for(int i = 0; i < poblacion.size() - 1; i = i + 2 ) { double
			 * random = r.nextDouble(); if( random <= c.getCruceporcentaje()) {
			 * if(poblacion.get(i) != null && poblacion.get(i+1) != null) {
			 * //Para comprobar que no nos salimos del array
			 * ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>
			 * array_con_2_individuos = reproduce_un_gen(poblacion.get(i),
			 * poblacion.get(i+1)); //try {
			 * publacion_solucion.add(array_con_2_individuos.get(0));
			 * publacion_solucion.add(array_con_2_individuos.get(1)); //}
			 * //catch(Exception e) { // System.out.println("hola"); //}
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 */
		} else {
			System.out.println("no tendria que entrar");
			{
				// Los individuos tienen mas de un gen
				Random r = new Random();
				int i = 0;
				while (i + 1 < p.size()) {
					double random = r.nextDouble();
					if (random <= c.getCruceporcentaje()) {
						if (p.get(i) != null && p.get(i + 1) != null) {
							//----
							Individuo<Genotipo, Fenotipo, Fitness> copia = (Individuo<Genotipo, Fenotipo, Fitness>) p.get(i);
							GenotipoBinario genotipo_aux = (GenotipoBinario)copia.getGenotipo();
							ArrayList<GenBinario> array_genes = new ArrayList<GenBinario>(genotipo_aux.getGenes());
							FenotipoReal fenotipo_aux = (FenotipoReal)copia.getFenotipo();
							ArrayList<FenotipoGen> array_fenotipo = new ArrayList<FenotipoGen>(fenotipo_aux.getCaracteristicas());
							Double fitness = new Double((double) copia.getFitness());
							Individuo<GenotipoBinario, FenotipoReal, Fitness>individuo1 = new Individuo<GenotipoBinario, FenotipoReal, Fitness>(
									genotipo_aux, fenotipo_aux, (Fitness)fitness);
							//----
							Individuo<Genotipo, Fenotipo, Fitness> copia1 = (Individuo<Genotipo, Fenotipo, Fitness>) p.get(i+1);
							GenotipoBinario genotipo_aux1 = (GenotipoBinario)copia1.getGenotipo();
							ArrayList<GenBinario> array_genes1 = new ArrayList<GenBinario>(genotipo_aux1.getGenes());
							FenotipoReal fenotipo_aux1 = (FenotipoReal)copia1.getFenotipo();
							ArrayList<FenotipoGen> array_fenotipo1 = new ArrayList<FenotipoGen>(fenotipo_aux1.getCaracteristicas());
							Double fitness1 = new Double((double) copia1.getFitness());
							Individuo<GenotipoBinario, FenotipoReal, Fitness>individuo2 = new Individuo<GenotipoBinario, FenotipoReal, Fitness>(
									genotipo_aux1, fenotipo_aux1, (Fitness)fitness1);
							//-----
							// Para comprobar que no nos salimos del array
							ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> array_con_2_individuos = reproduce_varios_genes(
									individuo1, individuo2);
							// try {
							Individuo<GenotipoBinario, FenotipoReal, Fitness> ind1 = array_con_2_individuos.get(0);
							Individuo<GenotipoBinario, FenotipoReal, Fitness> ind2 = array_con_2_individuos.get(1);
							poblacionSolucion.add(ind1);
							poblacionSolucion.add(ind2);
							

						}

					}

					i = i + 2;
				}

			}

		}
		return poblacionSolucion;
	}

	private ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> reproduce_un_gen(
			Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo1,
			Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo2) {
		Random r = new Random();
		int tamano_gen = individuo1.getGenotipo().getGenes().get(0).getTamGen();
		int random = r.nextInt(tamano_gen);
		for (int i = random; i < tamano_gen; i++) {
			boolean aux = individuo1.getGenotipo().getGenes().get(0).getGen().get(i);
			individuo1.getGenotipo().getGenes().get(0).getGen().set(i,
					individuo2.getGenotipo().getGenes().get(0).getGen().get(i));

			individuo2.getGenotipo().getGenes().get(0).getGen().set(i, aux);

		}
		/*// Actualizamos los fenotipos de los individuos
		ArrayList<Double> fenotipo_double_del_individuo_1 = (ArrayList<Double>) Decodificador
				.decodifica(individuo1.getGenotipo(), individuo1.getFenotipo());
		ArrayList<Double> fenotipo_double_del_individuo_2 = (ArrayList<Double>) Decodificador
				.decodifica(individuo2.getGenotipo(), individuo2.getFenotipo());

		FenotipoReal fenotipo_individuo1 = individuo1.getFenotipo();
		FenotipoReal fenotipo_individuo2 = individuo2.getFenotipo();

		// fenotipo_individuo1.setCaracteristicas(fenotipo_double_del_individuo_1);
		// fenotipo_individuo2.setCaracteristicas(fenotipo_double_del_individuo_2);

		fenotipo_individuo1.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_double_del_individuo_1.get(0));
		fenotipo_individuo2.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_double_del_individuo_2.get(0));

		individuo1.setFenotipo(fenotipo_individuo1);
		individuo2.setFenotipo(fenotipo_individuo2);
*/
		
		FenotipoReal fenotipo = new FenotipoReal();
		FenotipoGen fenotipoDelGen = new FenotipoGen(individuo1.getFenotipo().getCaracteristicas().get(0).getMin(),individuo1.getFenotipo().getCaracteristicas().get(0).getMax(),individuo1.getFenotipo().getCaracteristicas().get(0).getPrecision());
		ArrayList<FenotipoGen> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGen>();
		fenotiposDeTodosLosGenes.add(fenotipoDelGen);
		fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
		//Se obtiene un array con todos los fenotipos de TODOS los genes
		ArrayList<Double> fenotipo_del_individuo_i = (ArrayList<Double>)Decodificador.decodifica(individuo1.getGenotipo(), fenotipo);
		//fenotipoDelGen.setFenotipodelgen(fenotipo_del_individuo_i.get(0));
		//fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
		fenotipo.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_del_individuo_i.get(0));
		Individuo<GenotipoBinario,FenotipoReal,Fitness> individuosol1 = new Individuo<GenotipoBinario,FenotipoReal,Fitness>(individuo1.getGenotipo());
		individuosol1.setFenotipo(fenotipo);
		
		
		FenotipoReal fenotipo2 = new FenotipoReal();
		FenotipoGen fenotipoDelGen2 = new FenotipoGen(individuo2.getFenotipo().getCaracteristicas().get(0).getMin(),individuo2.getFenotipo().getCaracteristicas().get(0).getMax(),individuo2.getFenotipo().getCaracteristicas().get(0).getPrecision());
		ArrayList<FenotipoGen> fenotiposDeTodosLosGenes2 = new ArrayList<FenotipoGen>();
		fenotiposDeTodosLosGenes2.add(fenotipoDelGen2);
		fenotipo2.setCaracteristicas(fenotiposDeTodosLosGenes2);
		//Se obtiene un array con todos los fenotipos de TODOS los genes
		ArrayList<Double> fenotipo_del_individuo_i2 = (ArrayList<Double>)Decodificador.decodifica(individuo2.getGenotipo(), fenotipo2);
		//fenotipoDelGen.setFenotipodelgen(fenotipo_del_individuo_i.get(0));
		//fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
		fenotipo2.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_del_individuo_i2.get(0));
		Individuo<GenotipoBinario,FenotipoReal,Fitness> individuosol2 = new Individuo<GenotipoBinario,FenotipoReal,Fitness>(individuo2.getGenotipo());
		individuosol2.setFenotipo(fenotipo2);
		
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>();
		sol.add(individuosol1);
		sol.add(individuosol2);

		return sol;
	}

	private ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> reproduce_varios_genes(
			Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo1,
			Individuo<GenotipoBinario, FenotipoReal, Fitness> individuo2) {
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
			ArrayList<Boolean> gen1 = new ArrayList<Boolean>(individuo1.getGenotipo().getGenes().get(i).getGen());
			aux1.setGen(gen1);
			GenBinario aux2 = new GenBinario(individuo2.getGenotipo().getGenes().get(i).getTamGen());
			ArrayList<Boolean> gen2 = new ArrayList<Boolean>(individuo2.getGenotipo().getGenes().get(i).getGen());
			aux2.setGen(gen2);
			genesIndividuo1.set(i, aux2);
			genesIndividuo2.set(i, aux1);
		}
		individuo1.getGenotipo().setGenes(genesIndividuo1);
		individuo2.getGenotipo().setGenes(genesIndividuo2);
		//------
		
		// Actualizamos los fenotipos de los individuos
		ArrayList<Double> fenotipo_double_del_individuo_1 = (ArrayList<Double>) Decodificador
				.decodifica(individuo1.getGenotipo(), individuo1.getFenotipo());
		ArrayList<Double> fenotipo_double_del_individuo_2 = (ArrayList<Double>) Decodificador
				.decodifica(individuo2.getGenotipo(), individuo2.getFenotipo());

		FenotipoReal fenotipo_individuo1 = individuo1.getFenotipo();
		FenotipoReal fenotipo_individuo2 = individuo2.getFenotipo();

		for (int i = 0; i < fenotipo_individuo1.getCaracteristicas().size(); i++) {
			fenotipo_individuo1.getCaracteristicas().get(i).setFenotipodelgen(fenotipo_double_del_individuo_1.get(i));
			
		}
		for (int i = 0; i < fenotipo_individuo2.getCaracteristicas().size(); i++) {
			
			fenotipo_individuo2.getCaracteristicas().get(i).setFenotipodelgen(fenotipo_double_del_individuo_2.get(i));
		}

		individuo1.setFenotipo(fenotipo_individuo1);
		individuo2.setFenotipo(fenotipo_individuo2);

		ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}

}
