package reproduccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import genotipo.Genotipo;
import individuo.Individuo;

public interface Reproduccion<GenotipoR extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>>
	reproduce(ArrayList<Individuo<GenotipoR, Fenotipo, Fitness>> poblacion, Configuracion c);
}
