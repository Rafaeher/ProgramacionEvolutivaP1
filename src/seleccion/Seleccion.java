package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import individuo.Individuo;

public interface Seleccion<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{

	//Si la funcion es de maximizar el boolean sera true
	public ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> Selecciona(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion,
			Configuracion c, boolean maximizar);
	
}
