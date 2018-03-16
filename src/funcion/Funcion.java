package funcion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import individuo.ComparadorIndividuo;
import individuo.Individuo;
import reproduccion.FactoriaReproduccion;
import reproduccion.Reproduccion;
import seleccion.FactoriaSeleccion;
import seleccion.Seleccion;

public abstract class Funcion<GenotipoF extends Genotipo, FenotipoF extends Fenotipo, FitnessF extends Fitness>
{
	private ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> poblacion;
	private Configuracion configuracion;
	protected Individuo<GenotipoF, FenotipoF, FitnessF> mejorIndividuo;

	private double[] x_generaciones;
	private double[] y_mejor_iteracion;
	private double[] y_mejor_total;
	private double[] y_media;
	private double mejorAbsoluto;

	public Funcion(ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> poblacion, Configuracion configuracion)
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
		ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> elite = null;
		int it = 0;
		algEvalua(poblacion);
		while (it < configuracion.getNum_generaciones() -1)
        {
			
			if(configuracion.getElite() > 0){
				//System.out.println("Elite: " + configuracion.getElite());
				elite = (ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>>)calculaLosMejoresDeLaPoblacion(poblacion, configuracion.getElite());
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
				colocaLaelite(elite);
			}
			
			/*if (elite != null)
			{
				System.out.println("Iteración " + it);			
				for(int i = 0; i < elite.size(); i++)
					System.out.println("Élite " + (i + 1) + ": " + elite.get(i).getFitness().getValorReal());
				System.out.println("-------------");
			}
			*/
			pintar(it);
			algEvalua(poblacion);
		}
	}

	private void algSeleccion(ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> individuos_iniciales)
    {
		FactoriaSeleccion<GenotipoF, FenotipoF, FitnessF> factoriaSeleccion = new FactoriaSeleccion<GenotipoF, FenotipoF, FitnessF>();

		//Obtenemos el mecanismo de seleccion
		Seleccion<GenotipoF, FenotipoF, FitnessF> seleccion = factoriaSeleccion.getSeleccion(this.configuracion.getSeleccion_seleccionada());

		//Seleccionamos los individuos por el mecanismo adecuado
		this.poblacion = seleccion.Selecciona(individuos_iniciales, configuracion, getMaximizar());
	}

	private void algReproduccion(ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> seleccionados){
		FactoriaReproduccion<GenotipoF, FenotipoF, FitnessF> factoriaReproduccion = new FactoriaReproduccion<GenotipoF, FenotipoF, FitnessF>();
		//Obtenemos el mecanismo de reproduccion
		Reproduccion<GenotipoF, FenotipoF, FitnessF> reproduccion = factoriaReproduccion.getReproduccion(this.configuracion.getReproduccion_seleccionada());
		//Reproducimos los individuos y devolvemos la poblacion con los individuos nuevos en ella
			this.poblacion = reproduccion.reproduce(seleccionados, configuracion);
	}

	private void algMutacion(ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> reproducidos)
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
		ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> aux = calculaLosMejoresDeLaPoblacion(poblacion,1);
		FitnessF mejor_poblacion = aux.get(0).getFitness();
		try{
			y_mejor_iteracion[it] = mejor_poblacion.getValorReal();
		}
		catch(Exception e){
			System.out.println("la");
		}
		
		if(getMaximizar()){
			if(mejor_poblacion.getValorReal() > mejorAbsoluto) {
				mejorAbsoluto = mejor_poblacion.getValorReal();
			}
		}
		else{
			if(mejor_poblacion.getValorReal() < mejorAbsoluto) {
				mejorAbsoluto = mejor_poblacion.getValorReal();
			}
		}
		
		y_mejor_total[it] = mejorAbsoluto;
		y_media[it] = calculaMedia();
	}
	
	private double calculaMedia(){
		double total = 0;
		for(int i = 0; i < poblacion.size(); i++){
			total = total + poblacion.get(i).getFitness().getValorReal();
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
	public abstract void algEvalua(ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> poblacion);
	
	public void colocaLaelite(ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> elite)
	{
		poblacion.sort(new ComparadorIndividuo(getMaximizar()));
		
		for(int i = 0; i < elite.size(); i++)
		{
			poblacion.set(poblacion.size() - 1 - i, elite.get(i).clone());
		}
		
	}
	
	public ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> calculaLosMejoresDeLaPoblacion(ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> poblacion, int tam)
	{
		poblacion.sort(new ComparadorIndividuo<GenotipoF, FenotipoF, FitnessF>(getMaximizar()));
		ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>> mejores = new ArrayList<Individuo<GenotipoF, FenotipoF, FitnessF>>();
		for(int i = 0; i < tam; i++)
			mejores.add(poblacion.get(i).clone());
		
		return mejores;
		
	}
	
	public abstract boolean getMaximizar();
}
