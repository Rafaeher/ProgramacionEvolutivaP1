package reproduccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import individuo.Individuo;

public interface Reproduccion<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	ArrayList<Individuo<Genotipo, Fenotipo, Fitness>>
	reproduce(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion, Configuracion c);
}
