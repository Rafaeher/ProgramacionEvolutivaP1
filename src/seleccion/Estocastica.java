package seleccion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import configuracion.Configuracion;
import genotipo.Genotipo;
import individuo.Individuo;

public class Estocastica<GenotipoE extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
		implements Seleccion<GenotipoE, Fenotipo, Fitness>
{

	private boolean maximizar;
	@Override
	public ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>> Selecciona(
			ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>> poblacion,
			Configuracion c, boolean maximizar) {
		this.maximizar = maximizar;
		ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>> poblacionfinal = new ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>>();
		TreeMap<Double, Individuo<GenotipoE, Fenotipo, Fitness>> mapaProbabilidadesAcumuladas = calculaFitnessAcumulado(poblacion);
		double distancia_marcas = 1 / c.getTamano_poblacion();

		double posicion = Math.random()* (1 / c.getTamano_poblacion());
		poblacionfinal.add(seleccion_alg(mapaProbabilidadesAcumuladas,posicion));
		for(int i = 1; i < c.getTamano_poblacion(); i++){
			posicion += distancia_marcas;
			poblacionfinal.add(seleccion_alg(mapaProbabilidadesAcumuladas,posicion));
		}
		

		return poblacionfinal;
	}

	private TreeMap<Double, Individuo<GenotipoE, Fenotipo, Fitness>> calculaFitnessAcumulado(
			ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>> poblacion) {
		double fitnessTotal = 0;
		for (int i = 0; i < poblacion.size(); i++) {
			fitnessTotal += (Double) poblacion.get(i).getFitness();
		}

		TreeMap<Double, ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>>> mapa = new TreeMap<Double, ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>>>();

		for (int i = 0; i < poblacion.size(); i++) {
			Double fitness_individuo = (Double) poblacion.get(i).getFitness();
			Individuo<GenotipoE, Fenotipo, Fitness> individuo = poblacion.get(i);
			if (!mapa.containsKey(fitness_individuo)) {
				// No habia ningun individuo con ese fitness
				ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>> array = new ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>>();
				array.add(individuo);
				mapa.put(fitness_individuo, array);
			} else {
				// Ya había un individuo con ese fitness
				mapa.get(fitness_individuo).add(poblacion.get(i));
			}
		}
		
		TreeMap<Double, Individuo<GenotipoE, Fenotipo, Fitness>> mapaProbabilidadesAcumuladas = new TreeMap<Double, Individuo<GenotipoE, Fenotipo, Fitness>>();
		// recorro el mapa
		Iterator it = mapa.entrySet().iterator();
		double acumulada = 0;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>> arrayaux = (ArrayList<Individuo<GenotipoE, Fenotipo, Fitness>>) e
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

	private Individuo<GenotipoE, Fenotipo, Fitness> seleccion_alg(
			TreeMap<Double, Individuo<GenotipoE, Fenotipo, Fitness>> mapa, double posicion) {
		boolean encontrado = false;
		Individuo<GenotipoE, Fenotipo, Fitness> individuoseleccionado = null;
		int i = 0;
		/*mapa.descendingKeySet();
		Iterator it1 = mapa.entrySet().iterator();
		System.out.println("----------Sin ordenar------------" );
		while (it1.hasNext() && !encontrado) {
			Map.Entry e = (Map.Entry) it1.next();
			Individuo<Genotipo, Fenotipo, Fitness> copia = (Individuo<Genotipo, Fenotipo, Fitness>) e.getValue();
			System.out.println("individuo " + i +" " + e.getKey()+ " Fitness individuo " + copia.getFitness() );
			i++;
		}*/
		/*Map<Double, Individuo<Genotipo, Fenotipo, Fitness>> treeMap = new TreeMap<Double, Individuo<Genotipo, Fenotipo, Fitness>>(mapa);
		Iterator it2 = treeMap.entrySet().iterator();
		System.out.println("----------ordenado------------" );
		while (it2.hasNext() && !encontrado) {
			Map.Entry e = (Map.Entry) it2.next();
			Individuo<Genotipo, Fenotipo, Fitness> copia = (Individuo<Genotipo, Fenotipo, Fitness>) e.getValue();
			System.out.println("individuo " + i +" " + e.getKey()+ " Fitness individuo " + copia.getFitness() );
			i++;
		}*/
		
		Iterator it = mapa.entrySet().iterator();
		while (it.hasNext() && !encontrado) {
			Map.Entry e = (Map.Entry) it.next();
			//System.out.println("individuo " + i +" " + e.getKey());
			//i++;
			if(individuoseleccionado == null) individuoseleccionado = (Individuo<GenotipoE, Fenotipo, Fitness>)e.getValue();
			if ((Double) e.getKey() > posicion) {
				Individuo<GenotipoE, Fenotipo, Fitness> copia = (Individuo<GenotipoE, Fenotipo, Fitness>) e.getValue();
				individuoseleccionado = new Individuo<GenotipoE, Fenotipo, Fitness>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
				encontrado = true;
			}
		}
		if(individuoseleccionado == null){
			System.out.println("hola");
		}
		return individuoseleccionado;
	}

}
