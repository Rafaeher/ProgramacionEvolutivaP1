package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import individuo.ComparadorIndividuo;
import individuo.Individuo;

public class Truncamiento<GenotipoT extends Genotipo, FenotipoT extends Fenotipo, FitnessT extends Fitness>
		implements Seleccion<GenotipoT, FenotipoT, FitnessT>
{
	
	private double TRUNC = 0.5;
	private boolean maximizar;


	@Override
	public ArrayList<Individuo<GenotipoT, FenotipoT, FitnessT>> selecciona(
			ArrayList<Individuo<GenotipoT, FenotipoT, FitnessT>> poblacion, Configuracion c, boolean maximizar) {
		this.maximizar = maximizar;
		return Alg(poblacion,c);
	}
	private ArrayList<Individuo<GenotipoT, FenotipoT, FitnessT>> Alg(
			ArrayList<Individuo<GenotipoT, FenotipoT, FitnessT>> poblacion, Configuracion c){
		
		ArrayList<Individuo<GenotipoT, FenotipoT, FitnessT>> sol = new ArrayList<Individuo<GenotipoT, FenotipoT, FitnessT>>();
		int individuosASeleccionar = (int) (TRUNC * poblacion.size());
		int vecesPorIndividuo = (int) (1/TRUNC);
		ComparadorIndividuo<GenotipoT, FenotipoT, FitnessT> comparator = new ComparadorIndividuo<GenotipoT, FenotipoT, FitnessT>(maximizar);
		poblacion.sort(comparator);
		int i = 0;
 		while(i < individuosASeleccionar){
 			for(int j = 0; j < vecesPorIndividuo; j++){
 				if(sol.size() < poblacion.size()){
 					sol.add(poblacion.get(i).clone());
 				}
 			}
 			i++;
 		}
 		//Si ha quedado algun hueco por error con los decimales,
 		//lo llenamos con el mejor de la poblacion
 		while(sol.size() < poblacion.size()){
 			sol.add(poblacion.get(0).clone());
 		}
		return sol;
	}

}
