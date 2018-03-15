package seleccion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import individuo.Individuo;

public class Truncamiento<GenotipoT extends Genotipo, FenotipoT extends Fenotipo, FitnessT extends Fitness>
		implements Seleccion<GenotipoT, FenotipoT, FitnessT>
{
	private boolean maximizar;


	@Override
	public ArrayList<Individuo<GenotipoT, FenotipoT, FitnessT>> Selecciona(
			ArrayList<Individuo<GenotipoT, FenotipoT, FitnessT>> poblacion, Configuracion c, boolean maximizar) {
		this.maximizar = maximizar;
		return null;
	}

}
