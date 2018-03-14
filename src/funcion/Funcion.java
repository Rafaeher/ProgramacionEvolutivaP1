package funcion;

import java.util.ArrayList;

import configuracion.Configuracion;
import genotipo.Genotipo;
import individuo.Individuo;
import reproduccion.FactoriaReproduccion;
import reproduccion.Reproduccion;
import seleccion.FactoriaSeleccion;
import seleccion.Seleccion;

public abstract class Funcion<GenotipoF extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	private ArrayList<Individuo<GenotipoF, Fenotipo, Fitness>> poblacion;
	private Configuracion configuracion;
	protected Individuo<GenotipoF, Fenotipo, Fitness> mejorIndividuo;

	private double[] x_generaciones;
	private double[] y_mejor_iteracion;
	private double[] y_mejor_total;
	private double mejorAbsoluto = 0;

	public Funcion(ArrayList<Individuo<GenotipoF, Fenotipo, Fitness>> poblacion, Configuracion configuracion)
	{
		this.poblacion = poblacion;
		this.configuracion = configuracion;
		this.x_generaciones = new double[configuracion.getNum_generaciones()];
		this.y_mejor_iteracion = new double[configuracion.getNum_generaciones()];
		this.y_mejor_total = new double[configuracion.getNum_generaciones()];
	}
	
	public void algoritmoGenetico()
	{
		
		int it = 0;
		algEvalua(poblacion);
		while (it < configuracion.getNum_generaciones() -1)
        {
			it++;
			//seleccion
			algSeleccion(poblacion);
			//reproduccion
			if(configuracion.getCruceporcentaje() > 0)
			algReproduccion(poblacion);
			//Mutacion
			if(configuracion.getProb_mutacion() > 0)
			algMutacion(poblacion);
		
			System.out.println(it);
			pintar(it);
			algEvalua(poblacion);
		}
	}

	private void algSeleccion(ArrayList<Individuo<GenotipoF, Fenotipo, Fitness>> individuos_iniciales)
    {
		FactoriaSeleccion<GenotipoF, Fenotipo, Fitness> factoriaSeleccion = new FactoriaSeleccion<GenotipoF, Fenotipo, Fitness>();

		//Obtenemos el mecanismo de seleccion
		Seleccion<GenotipoF, Fenotipo, Fitness> seleccion = factoriaSeleccion.getSeleccion(this.configuracion.getSeleccion_seleccionada());

		//Seleccionamos los individuos por el mecanismo adecuado
		poblacion = seleccion.Selecciona(individuos_iniciales, configuracion, getMaximizar());
	}

	private void algReproduccion(ArrayList<Individuo<GenotipoF, Fenotipo, Fitness>> seleccionados){
		FactoriaReproduccion<GenotipoF, Fenotipo, Fitness> factoriaReproduccion = new FactoriaReproduccion<GenotipoF, Fenotipo, Fitness>();
		//Obtenemos el mecanismo de reproduccion
		Reproduccion<GenotipoF, Fenotipo, Fitness> reproduccion = factoriaReproduccion.getReproduccion(this.configuracion.getReproduccion_seleccionada());
		//Reproducimos los individuos y devolvemos la poblacion con los individuos nuevos en ella
		poblacion = reproduccion.reproduce(seleccionados, configuracion);
	}

	private void algMutacion(ArrayList<Individuo<GenotipoF, Fenotipo, Fitness>> reproducidos)
	{
		//FactoriaMutacion factoriaMutacion = FactoriaMutacion.instanciar();
		//Obtenemos el mecanismo de muta
		//Mutacion mutacion = factoriaMutacion.getMutacion(this.configuracion.getMutacion_seleccionada());
		//Mutamos y devolvemos una poblacion con los individuos mutados
		for(int i = 0; i < reproducidos.size(); i++)
		{
				reproducidos.get(i).muta(configuracion.getMutacion_seleccionada(),configuracion.getProb_mutacion());
		}
	}
	
	private void pintar(int it) {
		//Para pintar
		x_generaciones[it] = it;
		Double mejor_poblacion = (Double)calculaElMejorDeLaPoblacion(poblacion).getFitness();
		try{
			y_mejor_iteracion[it] = mejor_poblacion;
		}
		catch(Exception e){
			System.err.println("Excepción en pintar de Funcion");
		}
		
		if(getMaximizar()){
			if(mejor_poblacion > mejorAbsoluto) {
				mejorAbsoluto = mejor_poblacion;
			}
		}
		else{
			if(mejor_poblacion < mejorAbsoluto) {
				mejorAbsoluto = mejor_poblacion;
			}
		}
		
		y_mejor_total[it] = mejorAbsoluto;
	}

	public double[] getGeneraciones() {
		return x_generaciones;
	}
	public double[] getmejoriteracion(){
		return y_mejor_iteracion;
	}
	public double[] gety_mejor_total(){
		return y_mejor_total;
	}
	
	
	public Individuo<GenotipoF, Fenotipo, Fitness> calculaElMejorDeLaPoblacion(ArrayList<Individuo<GenotipoF, Fenotipo, Fitness>> poblacion)
	{
		Individuo<GenotipoF, Fenotipo, Fitness> mejor = poblacion.get(0);
		
		for(int i = 1; i < poblacion.size(); i++)
			if (mejor.peor(poblacion.get(i)))
				mejor = poblacion.get(i);
		
		return mejor;
	}
	
	public abstract void algEvalua(ArrayList<Individuo<GenotipoF, Fenotipo, Fitness>> poblacion);

	public abstract boolean getMaximizar();
}
