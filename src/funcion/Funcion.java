package funcion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import genotipo.Genotipo;
import individuo.ComparadorIndividuo;
import individuo.Individuo;
import reproduccion.FactoriaReproduccion;
import reproduccion.Reproduccion;
import seleccion.FactoriaSeleccion;
import seleccion.Seleccion;

public abstract class Funcion<GenotipoF extends Genotipo, FenotipoF extends Fenotipo, Fitness extends Comparable<Fitness>>
{
	private ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> poblacion;
	private Configuracion configuracion;
	protected Individuo<GenotipoF, FenotipoF, Fitness> mejorIndividuo;

	private double[] x_generaciones;
	private double[] y_mejor_iteracion;
	private double[] y_mejor_total;
	private double[] y_media;
	private double mejorAbsoluto = 0;

	public Funcion(ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> poblacion, Configuracion configuracion)
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
		ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> elite = null;
		int it = 0;
		algEvalua(poblacion);
		while (it < configuracion.getNum_generaciones() -1)
        {
			
			if(configuracion.getElite() > 0){
				System.out.println("Elite: " + configuracion.getElite());
				elite = (ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>>)calculaLosMejoresDeLaPoblacion(poblacion,configuracion.getElite());
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
				this.poblacion = colocaLaelite(poblacion,elite);
			}
			
			if (elite != null)
			{
				System.out.println("Iteración " + it);			
				for(int i = 0; i < elite.size(); i++)
					System.out.println("Élite " + (i + 1) + ": " + elite.get(i).getFitness());
				System.out.println("-------------");
			}
			
			pintar(it);
			algEvalua(poblacion);
		}
	}

	private void algSeleccion(ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> individuos_iniciales)
    {
		FactoriaSeleccion<GenotipoF, FenotipoF, Fitness> factoriaSeleccion = new FactoriaSeleccion<GenotipoF, FenotipoF, Fitness>();

		//Obtenemos el mecanismo de seleccion
		Seleccion<GenotipoF, FenotipoF, Fitness> seleccion = factoriaSeleccion.getSeleccion(this.configuracion.getSeleccion_seleccionada());

		//Seleccionamos los individuos por el mecanismo adecuado
		this.poblacion = seleccion.Selecciona(individuos_iniciales, configuracion, getMaximizar());
	}

	private void algReproduccion(ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> seleccionados){
		FactoriaReproduccion<GenotipoF, FenotipoF, Fitness> factoriaReproduccion = new FactoriaReproduccion<GenotipoF, FenotipoF, Fitness>();
		//Obtenemos el mecanismo de reproduccion
		Reproduccion<GenotipoF, FenotipoF, Fitness> reproduccion = factoriaReproduccion.getReproduccion(this.configuracion.getReproduccion_seleccionada());
		//Reproducimos los individuos y devolvemos la poblacion con los individuos nuevos en ella
			this.poblacion = reproduccion.reproduce(seleccionados, configuracion);
	}

	private void algMutacion(ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> reproducidos)
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
		ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> aux = calculaLosMejoresDeLaPoblacion(poblacion,1);
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
	public abstract void algEvalua(ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> poblacion);
	public abstract ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> colocaLaelite(ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> poblacion, 
			ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> elite);
	//public abstract Object calculaLosMejoresDeLaPoblacion(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion, int tam);
	
	public ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> calculaLosMejoresDeLaPoblacion(ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> poblacion, int tam)
	{
		poblacion.sort(new ComparadorIndividuo<GenotipoF, FenotipoF, Fitness>(getMaximizar()));
		ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>> mejores = new ArrayList<Individuo<GenotipoF, FenotipoF, Fitness>>();
		for(int i = 0; i < tam; i++)
			mejores.add(poblacion.get(i));
		
		return mejores;
		
	}
	
	public abstract boolean getMaximizar();
}
