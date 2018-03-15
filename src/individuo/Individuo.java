package individuo;

import configuracion.Mutacion_enum;
import decodificador.Decodificador;
import fenotipo.Fenotipo;
import genotipo.Genotipo;
import mutacion.FactoriaMutacion;
import mutacion.Mutacion;

public class Individuo<GenotipoI extends Genotipo, FenotipoI extends Fenotipo, Fitness extends Comparable<Fitness>>
{
	GenotipoI genotipo;
	FenotipoI fenotipo;
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
	public Individuo(GenotipoI genotipoE)
	{
		genotipo = genotipoE;
	}

	public Individuo(GenotipoI g, FenotipoI f, Fitness fit) {
		genotipo =g;
		fenotipo = f;
		fitness = fit;
	}
	/**
	 * Indica si el individuo actual es peor, igual o mejor que el otro
	 * 
	 * @param otro: un individuo
	 * @return 
	 */
	public int compara(Individuo<GenotipoI, FenotipoI, Fitness> otro)
	{
		return fitness.compareTo(otro.fitness);
	}
	
	/**
	 * Introduce una mutación en el genotipo del individuo
	 */
	public Individuo<GenotipoI,FenotipoI,Fitness> muta(Mutacion_enum tipoMutacion, double prob_mutacion)
	{
		
		FactoriaMutacion<GenotipoI,FenotipoI,Fitness> f = new FactoriaMutacion<GenotipoI,FenotipoI,Fitness>();
		Mutacion<GenotipoI,FenotipoI,Fitness> mutacion = f.getMutacion(tipoMutacion);
		mutacion.muta(genotipo,prob_mutacion);
		return this;
	}

	
	/**
	 * Calcula y obtiene el fenotipo del individuo
	 * 
	 * @return fenotipo: el fenotipo del individuo
	 */
	public FenotipoI getFenotipo()
	{
		Decodificador.decodifica(genotipo, fenotipo);
		return fenotipo;
	}
	
	/**
	 * Obtiene el genotipo de un individuo
	 * 
	 * @return genotipo
	 */
	public GenotipoI getGenotipo()
	{
		return genotipo;
	}

	/**
	 * Asigna un nuevo valor al genotipo
	 *
	 * @param genotipoE: genotipo de entrada
	 */
	public void setGenotipo(GenotipoI genotipoE)
	{
		genotipo = genotipoE;
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
	public void setFenotipo(FenotipoI fenotipoE)
	{
		fenotipo = fenotipoE;
	}

	/**
	 * Clona un individuo
	 */
	public Individuo<GenotipoI, FenotipoI, Fitness> clone()
	{
		Individuo<GenotipoI, FenotipoI, Fitness> clon = new Individuo<GenotipoI, FenotipoI, Fitness>();
		clon.genotipo = (GenotipoI) genotipo.clone();
		clon.fenotipo = (FenotipoI) fenotipo.clone();
		clon.fitness = fitness;
		
		return clon;
	}
}
