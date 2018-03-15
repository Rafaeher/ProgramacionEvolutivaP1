package seleccion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGenReal;
import fitness.Fitness;
import genotipo.Genotipo;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;

public class Ruleta<GenotipoR extends Genotipo, FenotipoR extends Fenotipo, FitnessR extends Fitness>
		implements Seleccion<GenotipoR, FenotipoR, FitnessR> {

	private boolean maximizar;
	
	@Override
	public ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>>
	Selecciona
	(ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>> poblacion,
			Configuracion c, boolean maximizar)
	{
		this.maximizar = maximizar;
		ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>> poblacionfinal = new ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>>();
		TreeMap<Double, Individuo<GenotipoR, FenotipoR, FitnessR>> mapaProbabilidadesAcumuladas = calculaFitnessAcumulado(poblacion);
		Random r = new Random();
		while (poblacionfinal.size() < poblacion.size()) {
			double random = r.nextDouble();
			Individuo<GenotipoR, FenotipoR, FitnessR> nuevo_individuo = seleccion_alg(mapaProbabilidadesAcumuladas, random);
			//System.out.println("nuevo individuo: " + nuevo_individuo + " tamano de poblacion " + poblacion.size());
			poblacionfinal.add(nuevo_individuo);
		}

		return poblacionfinal;
	}

	private TreeMap<Double, Individuo<GenotipoR, FenotipoR, FitnessR>> calculaFitnessAcumulado(
			ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>> poblacion) {
		double fitnessTotal = 0;
		for (int i = 0; i < poblacion.size(); i++) {
			fitnessTotal += (Double) poblacion.get(i).getFitness().getValorReal();
		}

		TreeMap<Double, ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>>> mapa = new TreeMap<Double, ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>>>(java.util.Collections.reverseOrder());

		for (int i = 0; i < poblacion.size(); i++) {
			Double fitness_individuo = (Double) poblacion.get(i).getFitness().getValorReal();
			Individuo<GenotipoR, FenotipoR, FitnessR> individuo = poblacion.get(i);
			if (!mapa.containsKey(fitness_individuo)) {
				// No habia ningun individuo con ese fitness
				ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>> array = new ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>>();
				array.add(individuo);
				mapa.put(fitness_individuo, array);
			} else {
				// Ya había un individuo con ese fitness
				mapa.get(fitness_individuo).add(poblacion.get(i));
			}
		}
		
		TreeMap<Double, Individuo<GenotipoR, FenotipoR, FitnessR>> mapaProbabilidadesAcumuladas = new TreeMap<Double, Individuo<GenotipoR, FenotipoR, FitnessR>>();
		// recorro el mapa
		Iterator it = mapa.entrySet().iterator();
		double acumulada = 0;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>> arrayaux = (ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>>) e
					.getValue();
			for (int i = 0; i < arrayaux.size(); i++) {
				acumulada = acumulada + ((Double) e.getKey() / fitnessTotal);
				mapaProbabilidadesAcumuladas.put(acumulada, arrayaux.get(i));
			}

		}
		return mapaProbabilidadesAcumuladas;
	}
	/*
	 * ArrayList<Double> prob_seleccion = new ArrayList<Double>(); double acumulada
	 * = 0;for( int i = 0;i<poblacion.size();i++) { acumulada = acumulada +
	 * ((Double) poblacion.get(i).getFitness() / fitnessTotal);
	 * prob_seleccion.add(acumulada); }return prob_seleccion;
	 */
	// }

	private Individuo<GenotipoR, FenotipoR, FitnessR> seleccion_alg(
			TreeMap<Double, Individuo<GenotipoR, FenotipoR, FitnessR>> mapa, double random) {
		boolean encontrado = false;
		Individuo<GenotipoR, FenotipoR, FitnessR> individuoseleccionado = null;
		Iterator it = mapa.entrySet().iterator();
		while (it.hasNext() && !encontrado) {
			Map.Entry e = (Map.Entry) it.next();
			if(individuoseleccionado == null) individuoseleccionado = (Individuo<GenotipoR, FenotipoR, FitnessR>)e.getValue();
			if ((Double) e.getKey() > random) {
				Individuo<GenotipoR, FenotipoR, FitnessR> copia = (Individuo<GenotipoR, FenotipoR, FitnessR>) e.getValue();
				individuoseleccionado = copia.clone();
				encontrado = true;
			}
		}
		if(individuoseleccionado == null){
			System.out.println("Selección por ruleta: se ha seleccionado un individuo null");
		}
		return individuoseleccionado;
	}

}
