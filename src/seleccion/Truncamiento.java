package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import genotipo.Genotipo;
import individuo.Individuo;

public class Truncamiento<GenotipoT extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
		implements Seleccion<GenotipoT, Fenotipo, Fitness>
{
	private boolean maximizar;


	@Override
	public ArrayList<Individuo<GenotipoT, Fenotipo, Fitness>> Selecciona(
			ArrayList<Individuo<GenotipoT, Fenotipo, Fitness>> poblacion, Configuracion c, boolean maximizar) {
		this.maximizar = maximizar;
		return null;
	}

}
