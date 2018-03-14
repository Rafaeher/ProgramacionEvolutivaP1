package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import individuo.Individuo;

public class Truncamiento<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
		implements Seleccion<Genotipo, Fenotipo, Fitness>
{
	private boolean maximizar;


	@Override
	public ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> Selecciona(
			ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion, Configuracion c, boolean maximizar) {
		this.maximizar = maximizar;
		return null;
	}

}
