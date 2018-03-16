package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import individuo.Individuo;

public interface PoblacionInicial
{
	public ArrayList<Individuo<?, ?, ?>> getPoblacionInicial(Configuracion config);
}
