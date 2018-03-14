package individuo;

import java.util.ArrayList;


import configuracion.Mutacion_enum;
import decodificador.Decodificador;
import mutacion.FactoriaMutacion;
import mutacion.Mutacion;

public class Individuo<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	Genotipo genotipo;
	Fenotipo fenotipo;
	Fitness fitness;

	/**
	 * Constructora por defecto
	 */
	public Individuo() { }

	/**
	 * Constructora a partir de un genotipo dado
	 *
	 * @param genotipoE: genotipo de Entrada
	 */
	public Individuo(Genotipo genotipoE)
	{
		genotipo = genotipoE;
	}

	public Individuo(Genotipo g, Fenotipo f, Fitness fit) {
		genotipo =g;
		fenotipo = f;
		fitness = fit;
	}
	/**
	 * Indica si el individuo actual es peor que el otro
	 * 
	 * @param otro: un individuo
	 * @return true si el individuo actual es peor que el otro, false en otro caso
	 */
	public boolean peor(Individuo<Genotipo, Fenotipo, Fitness> otro)
	{
		return fitness.compareTo(otro.fitness) < 0;
	}
	
	/**
	 * Introduce una mutación en el genotipo del individuo
	 */
	public Individuo<Genotipo,Fenotipo,Fitness> muta(Mutacion_enum tipoMutacion, double prob_mutacion)
	{
		
		FactoriaMutacion<Genotipo,Fenotipo,Fitness> f = new FactoriaMutacion<Genotipo,Fenotipo,Fitness>();
		Mutacion<Genotipo,Fenotipo,Fitness> mutacion = f.getMutacion(tipoMutacion);
		mutacion.muta(genotipo,prob_mutacion);
		return this;
	}

	
	/**
	 * Calcula y obtiene el fenotipo del individuo
	 * 
	 * @return fenotipo: el fenotipo del individuo
	 */
	public Fenotipo getFenotipo()
	{
		Decodificador.decodifica(genotipo, fenotipo);
		return fenotipo;
	}
	
	/**
	 * Obtiene el genotipo de un individuo
	 * 
	 * @return genotipo
	 */
	public Genotipo getGenotipo()
	{
		return genotipo;
	}

	/**
	 * Asigna un nuevo valor al genotipo
	 *
	 * @param genotipoE: genotipo de entrada
	 */
	public void setGenotipo(Genotipo genotipoE)
	{
		genotipo = genotipoE;
	}

	/**
	 * Cruza progenitores y devuelve descendientes
	 * 
	 * @param progenitores: lista de individuos
	 * @return descendientes lista de individuos cruzados
	 */
	public static ArrayList<Individuo> cruce(final ArrayList<Individuo> progenitores)
	{
		//TODO
		return null;
	}

	/**
	 * Asigna un nuevo valor al fitness
	 *
	 * @param fitnessE: el valor de entrada para el fitness
	 */
	public void setFitness(Fitness fitnessE)
	{
		fitness = fitnessE;
	}

	/**
	 * Obtiene el fitness
	 *
	 * @return fitness: el fitness del individuo
	 */
	public Fitness getFitness()
	{
		return fitness;
	}
	public void setFenotipo(Fenotipo fenotipoE)
	{
		fenotipo = fenotipoE;
	}

	
}
