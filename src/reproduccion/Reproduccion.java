package reproduccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import individuo.Individuo;

public interface Reproduccion<GenotipoR extends Genotipo, FenotipoR extends Fenotipo, FitnessR extends Fitness>
{
	ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>>
	reproduce(ArrayList<Individuo<GenotipoR, FenotipoR, FitnessR>> poblacion, Configuracion c);
}
