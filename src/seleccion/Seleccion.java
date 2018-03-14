package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import genotipo.Genotipo;
import individuo.Individuo;

public interface Seleccion<GenotipoS extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{

	//Si la funcion es de maximizar el boolean sera true
	public ArrayList<Individuo<GenotipoS, Fenotipo, Fitness>> Selecciona(ArrayList<Individuo<GenotipoS, Fenotipo, Fitness>> poblacion,
			Configuracion c, boolean maximizar);
	
}
