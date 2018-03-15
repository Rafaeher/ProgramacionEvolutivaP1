package funcion;

import java.util.ArrayList;

import configuracion.Configuracion;
import individuo.ComparadorIndividuo;
import individuo.Individuo;
import reproduccion.FactoriaReproduccion;
import reproduccion.Reproduccion;
import seleccion.FactoriaSeleccion;
import seleccion.Seleccion;

public abstract class Funcion<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	private ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion;
	private Configuracion configuracion;
	protected Individuo<Genotipo, Fenotipo, Fitness> mejorIndividuo;

	private double[] x_generaciones;
	private double[] y_mejor_iteracion;
	private double[] y_mejor_total;
	private double[] y_media;
	private double mejorAbsoluto = 0;

	public Funcion(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion, Configuracion configuracion)
	{
		this.poblacion = poblacion;
		this.configuracion = configuracion;
		this.x_generaciones = new double[configuracion.getNum_generaciones()];
		this.y_mejor_iteracion = new double[configuracion.getNum_generaciones()];
		this.y_mejor_total = new double[configuracion.getNum_generaciones()];
		this.y_media = new double[configuracion.getNum_generaciones()];
	}
	
	public void algoritmoGenetico()
	{
		ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> elite = null;
		int it = 0;
		algEvalua(poblacion);
		while (it < configuracion.getNum_generaciones() -1)
        {
			
			if(configuracion.getElite() > 0){
				System.out.println("Elite: " + configuracion.getElite());
				elite = (ArrayList<Individuo<Genotipo, Fenotipo, Fitness>>)calculaLosMejoresDeLaPoblacion(poblacion,configuracion.getElite());
			}
			it++;
			//seleccion
			algSeleccion(poblacion);
			//reproduccion
			if(configuracion.getCruceporcentaje() > 0)
			algReproduccion(poblacion);
			//Mutacion
			if(configuracion.getProb_mutacion() > 0)
			algMutacion(poblacion);
			if(configuracion.getElite() > 0){
				algEvalua(poblacion);
				this.poblacion = (ArrayList<Individuo<Genotipo, Fenotipo, Fitness>>)colocaLaelite(poblacion,elite);
			}
			try{
				if (elite != null)
				{
					System.out.println("Iteración " + it);			
					for(int i = 0; i < elite.size(); i++)
						System.out.println("Élite " + i + ": " + elite.get(i).getFitness());
					System.out.println("-------------");
				}
			}
			catch(Exception e){
				System.out.println(getClass().getName() + ": error con la élite en el algoritmo genético");
			}
			
			pintar(it);
			algEvalua(poblacion);
		}
		System.out.println(it);
	}

	private void algSeleccion(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> individuos_iniciales)
    {
		FactoriaSeleccion<Genotipo, Fenotipo, Fitness> factoriaSeleccion = new FactoriaSeleccion<Genotipo, Fenotipo, Fitness>();

		//Obtenemos el mecanismo de seleccion
		Seleccion<Genotipo, Fenotipo, Fitness> seleccion = factoriaSeleccion.getSeleccion(this.configuracion.getSeleccion_seleccionada());

		//Seleccionamos los individuos por el mecanismo adecuado
		this.poblacion = seleccion.Selecciona(individuos_iniciales, configuracion, getMaximizar());
	}

	private void algReproduccion(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> seleccionados){
		FactoriaReproduccion<Genotipo, Fenotipo, Fitness> factoriaReproduccion = new FactoriaReproduccion<Genotipo, Fenotipo, Fitness>();
		//Obtenemos el mecanismo de reproduccion
		Reproduccion<Genotipo, Fenotipo, Fitness> reproduccion = factoriaReproduccion.getReproduccion(this.configuracion.getReproduccion_seleccionada());
		//Reproducimos los individuos y devolvemos la poblacion con los individuos nuevos en ella
			this.poblacion = reproduccion.reproduce(seleccionados, configuracion);
	}

	private void algMutacion(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> reproducidos)
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
		algEvalua(poblacion);
		//Para pintar
		x_generaciones[it] = it;
		ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> aux = calculaLosMejoresDeLaPoblacion(poblacion,1);
		Double mejor_poblacion = (Double)aux.get(0).getFitness();
		try{
			y_mejor_iteracion[it] = mejor_poblacion;
		}
		catch(Exception e){
			System.out.println("la");
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
		y_media[it] = calculaMedia();
	}
	
	private double calculaMedia(){
		double total = 0;
		for(int i = 0; i < poblacion.size(); i++){
			total = total + (Double)poblacion.get(i).getFitness();
		}
		return (total / poblacion.size());
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
	public double[] getMedia() {
		return y_media;
	}
	public abstract void algEvalua(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion);
	public abstract Object colocaLaelite(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion, 
			ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> elite);
	//public abstract Object calculaLosMejoresDeLaPoblacion(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion, int tam);
	
	public ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> calculaLosMejoresDeLaPoblacion(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion, int tam)
	{
		poblacion.sort(new ComparadorIndividuo<Genotipo, Fenotipo, Fitness>());
		ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> mejores = new ArrayList<Individuo<Genotipo, Fenotipo, Fitness>>();
		for(int i = 0; i < tam; i++)
			mejores.add(poblacion.get(i));
		
		return mejores;
		
	}
	
	public abstract boolean getMaximizar();
}
