package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import individuo.Individuo;

public abstract class PoblacionInicialF1 implements PoblacionInicial
{

	@Override
	public ArrayList<Individuo<?, ?, ?>> getPoblacionInicial(Configuracion config)
	{
		return getPoblacion(config);
	}
	
	protected abstract ArrayList<Individuo<?, ?, ?>> getPoblacion(Configuracion config);

}
