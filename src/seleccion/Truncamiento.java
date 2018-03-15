package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import genotipo.Genotipo;
import individuo.Individuo;

public class Truncamiento<GenotipoT extends Genotipo, FenotipoT extends Fenotipo, Fitness extends Comparable<Fitness>>
		implements Seleccion<GenotipoT, FenotipoT, Fitness>
{
	private boolean maximizar;


	@Override
	public ArrayList<Individuo<GenotipoT, FenotipoT, Fitness>> Selecciona(
			ArrayList<Individuo<GenotipoT, FenotipoT, Fitness>> poblacion, Configuracion c, boolean maximizar) {
		this.maximizar = maximizar;
		return null;
	}

}
