package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import individuo.Individuo;

public abstract class PoblacionInicialF3 implements PoblacionInicial {

	@Override
	public ArrayList<Individuo<?, ?, ?>> getPoblacionInicial(Configuracion config)
	{
		return getPoblacion(config);
	}

	protected abstract ArrayList<Individuo<?, ?, ?>> getPoblacion(Configuracion config);

	
}
