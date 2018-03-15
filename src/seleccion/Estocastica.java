package seleccion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import fitness.FitnessReal;
import genotipo.Genotipo;
import individuo.ComparadorIndividuo;
import individuo.Individuo;

public class Estocastica<GenotipoE extends Genotipo, FenotipoE extends Fenotipo, FitnessE extends Fitness>
		implements Seleccion<GenotipoE, FenotipoE, FitnessE>
{

	private boolean maximizar;
	@Override
	public ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>> Selecciona(
			ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>> poblacion,
			Configuracion c, boolean maximizar) {
		this.maximizar = maximizar;
		
		if(!maximizar){
			//Se trata de un problema de minimizacion
			ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>> aux = new ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>>();
			//Buscamos el mayor individuo de la poblacion para que haga de cmax
			ComparadorIndividuo comparador = new ComparadorIndividuo(true);
			poblacion.sort(comparador);
			double cmax = poblacion.get(0).getFitness().getValorReal() * 1.05;
			for(int i = 0; i< poblacion.size(); i++){
				Individuo<GenotipoE, FenotipoE, FitnessE> individuo = poblacion.get(i).clone();
				double fitness = individuo.getFitness().getValorReal();
				double fitnessDesplazado = Math.abs(cmax - fitness);
				FitnessReal f = new FitnessReal(fitnessDesplazado);
				individuo.setFitness((FitnessE) f);
				aux.add(individuo);
			}
			
			ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>> poblacionfinal = new ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>>();
			TreeMap<Double, Individuo<GenotipoE, FenotipoE, FitnessE>> mapaProbabilidadesAcumuladas = calculaFitnessAcumulado(aux);
			double distancia_marcas = 1.0 / c.getTamano_poblacion();
			double aux_ = (1.0 / c.getTamano_poblacion());
			double posicion = Math.random()* aux_;
			poblacionfinal.add(seleccion_alg(mapaProbabilidadesAcumuladas,posicion));
			for(int i = 1; i < c.getTamano_poblacion(); i++){
				posicion += distancia_marcas;
				poblacionfinal.add(seleccion_alg(mapaProbabilidadesAcumuladas,posicion));
			}
			return poblacionfinal;
		
		}
		else{
			ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>> poblacionfinal = new ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>>();
			TreeMap<Double, Individuo<GenotipoE, FenotipoE, FitnessE>> mapaProbabilidadesAcumuladas = calculaFitnessAcumulado(poblacion);
			double distancia_marcas = 1.0 / c.getTamano_poblacion();
			double aux_ = (1.0 / c.getTamano_poblacion());
			double posicion = Math.random()* aux_;
			poblacionfinal.add(seleccion_alg(mapaProbabilidadesAcumuladas,posicion));
			for(int i = 1; i < c.getTamano_poblacion(); i++){
				posicion += distancia_marcas;
				poblacionfinal.add(seleccion_alg(mapaProbabilidadesAcumuladas,posicion));
			}
			return poblacionfinal;
		}
		
	}

	private TreeMap<Double, Individuo<GenotipoE, FenotipoE, FitnessE>> calculaFitnessAcumulado(
			ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>> poblacion) {
		double fitnessTotal = 0;
		for (int i = 0; i < poblacion.size(); i++) {
			fitnessTotal += poblacion.get(i).getFitness().getValorReal();
		}

		TreeMap<Double, ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>>> mapa = new TreeMap<Double, ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>>>();

		for (int i = 0; i < poblacion.size(); i++) {
			Double fitness_individuo = (Double) poblacion.get(i).getFitness().getValorReal();
			Individuo<GenotipoE, FenotipoE, FitnessE> individuo = poblacion.get(i).clone();
			if (!mapa.containsKey(fitness_individuo)) {
				// No habia ningun individuo con ese fitness
				ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>> array = new ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>>();
				array.add(individuo);
				mapa.put(fitness_individuo, array);
			} else {
				// Ya hab�a un individuo con ese fitness
				mapa.get(fitness_individuo).add(poblacion.get(i));
			}
		}
		
		TreeMap<Double, Individuo<GenotipoE, FenotipoE, FitnessE>> mapaProbabilidadesAcumuladas = new TreeMap<Double, Individuo<GenotipoE, FenotipoE, FitnessE>>();
		// recorro el mapa
		Iterator it = mapa.entrySet().iterator();
		double acumulada = 0;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>> arrayaux = (ArrayList<Individuo<GenotipoE, FenotipoE, FitnessE>>) e
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

	private Individuo<GenotipoE, FenotipoE, FitnessE> seleccion_alg(
			TreeMap<Double, Individuo<GenotipoE, FenotipoE, FitnessE>> mapa, double posicion) {
		boolean encontrado = false;
		Individuo<GenotipoE, FenotipoE, FitnessE> individuoseleccionado = null;
		int i = 0;
		Iterator it = mapa.entrySet().iterator();
		while (it.hasNext() && !encontrado) {
			Map.Entry e = (Map.Entry) it.next();
			//System.out.println("individuo " + i +" " + e.getKey());
			//i++;
			if(individuoseleccionado == null) individuoseleccionado = (Individuo<GenotipoE, FenotipoE, FitnessE>)e.getValue();
			if ((Double) e.getKey() > posicion) {
				Individuo<GenotipoE, FenotipoE, FitnessE> copia = (Individuo<GenotipoE, FenotipoE, FitnessE>) e.getValue();
				individuoseleccionado = copia.clone();
				encontrado = true;
			}
		}
		if(individuoseleccionado == null){
			System.out.println("hola");
		}
		return individuoseleccionado;
	}

}
