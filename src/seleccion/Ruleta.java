package seleccion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import configuracion.Configuracion;
import genotipo.Genotipo;
import individuo.Individuo;

public class Ruleta<GenotipoR extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
		implements Seleccion<GenotipoR, Fenotipo, Fitness> {

	private boolean maximizar;
	
	@Override
	public ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>>
	Selecciona
	(ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>> poblacion,
			Configuracion c, boolean maximizar)
	{
		this.maximizar = maximizar;
		ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>> poblacionfinal = new ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>>();
		TreeMap<Double, Individuo<GenotipoR, Fenotipo, Fitness>> mapaProbabilidadesAcumuladas = calculaFitnessAcumulado(poblacion);
		Random r = new Random();
		while (poblacionfinal.size() < poblacion.size()) {
			double random = r.nextDouble();
			Individuo<GenotipoR, Fenotipo, Fitness> nuevo_individuo = seleccion_alg(mapaProbabilidadesAcumuladas, random);
			//System.out.println("nuevo individuo: " + nuevo_individuo + " tamano de poblacion " + poblacion.size());
			poblacionfinal.add(nuevo_individuo);
		}

		return poblacionfinal;
	}

	private TreeMap<Double, Individuo<GenotipoR, Fenotipo, Fitness>> calculaFitnessAcumulado(
			ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>> poblacion) {
		double fitnessTotal = 0;
		for (int i = 0; i < poblacion.size(); i++) {
			fitnessTotal += (Double) poblacion.get(i).getFitness();
		}

		TreeMap<Double, ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>>> mapa = new TreeMap<Double, ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>>>(java.util.Collections.reverseOrder());

		for (int i = 0; i < poblacion.size(); i++) {
			Double fitness_individuo = (Double) poblacion.get(i).getFitness();
			Individuo<GenotipoR, Fenotipo, Fitness> individuo = poblacion.get(i);
			if (!mapa.containsKey(fitness_individuo)) {
				// No habia ningun individuo con ese fitness
				ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>> array = new ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>>();
				array.add(individuo);
				mapa.put(fitness_individuo, array);
			} else {
				// Ya había un individuo con ese fitness
				mapa.get(fitness_individuo).add(poblacion.get(i));
			}
		}
		
		TreeMap<Double, Individuo<GenotipoR, Fenotipo, Fitness>> mapaProbabilidadesAcumuladas = new TreeMap<Double, Individuo<GenotipoR, Fenotipo, Fitness>>();
		// recorro el mapa
		Iterator it = mapa.entrySet().iterator();
		double acumulada = 0;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>> arrayaux = (ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>>) e
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

	private Individuo<GenotipoR, Fenotipo, Fitness> seleccion_alg(
			TreeMap<Double, Individuo<GenotipoR, Fenotipo, Fitness>> mapa, double random) {
		boolean encontrado = false;
		Individuo<GenotipoR, Fenotipo, Fitness> individuoseleccionado = null;
		int i = 0;
		
		Iterator it = mapa.entrySet().iterator();
		while (it.hasNext() && !encontrado) {
			Map.Entry e = (Map.Entry) it.next();
			if(individuoseleccionado == null) individuoseleccionado = (Individuo<GenotipoR, Fenotipo, Fitness>)e.getValue();
			if ((Double) e.getKey() > random) {
				Individuo<GenotipoR, Fenotipo, Fitness> copia = (Individuo<GenotipoR, Fenotipo, Fitness>) e.getValue();
				individuoseleccionado = new Individuo<GenotipoR, Fenotipo, Fitness>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
				encontrado = true;
			}
		}
		if(individuoseleccionado == null){
			System.out.println("Selección por ruleta: se ha seleccionado un individuo null");
		}
		return individuoseleccionado;
	}

}
