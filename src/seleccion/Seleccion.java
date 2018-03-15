package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import genotipo.Genotipo;
import individuo.Individuo;

public interface Seleccion<GenotipoS extends Genotipo, FenotipoS extends Fenotipo, Fitness extends Comparable<Fitness>>
{

	//Si la funcion es de maximizar el boolean sera true
	public ArrayList<Individuo<GenotipoS, FenotipoS, Fitness>> Selecciona(ArrayList<Individuo<GenotipoS, FenotipoS, Fitness>> poblacion,
			Configuracion c, boolean maximizar);
	
}
