package reproduccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import genotipo.Genotipo;
import individuo.Individuo;

public interface Reproduccion<GenotipoR extends Genotipo, FenotipoR extends Fenotipo, Fitness extends Comparable<Fitness>>
{
	ArrayList<Individuo<GenotipoR, FenotipoR, Fitness>>
	reproduce(ArrayList<Individuo<GenotipoR, FenotipoR, Fitness>> poblacion, Configuracion c);
}
