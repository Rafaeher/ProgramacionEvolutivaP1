package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import individuo.Individuo;

public interface Seleccion<GenotipoS extends Genotipo, FenotipoS extends Fenotipo, FitnessS extends Fitness>
{

	//Si la funcion es de maximizar el boolean sera true
	public ArrayList<Individuo<GenotipoS, FenotipoS, FitnessS>> Selecciona(ArrayList<Individuo<GenotipoS, FenotipoS, FitnessS>> poblacion,
			Configuracion c, boolean maximizar);
	
}
