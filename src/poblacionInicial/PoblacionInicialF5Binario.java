package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import fitness.FitnessReal;
import genotipo.GenotipoBinario;
import individuo.Individuo;

public class PoblacionInicialF5Binario extends PoblacionInicialF5
{

	@Override
	protected ArrayList<Individuo<?, ?, ?>> getPoblacion(Configuracion config)
	{
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>> poblacion= new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>>();
		
		
		return (ArrayList<Individuo<?, ?, ?>>) ((ArrayList<?>) poblacion);
	}

}
