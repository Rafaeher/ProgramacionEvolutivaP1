package reproduccion;

import configuracion.Reproduccion_enum;
import fenotipo.Fenotipo;
import genotipo.Genotipo;
import reproduccion.binario.UnPuntoBinario;
import reproduccion.binario.UniformeBinario;
import reproduccion.binario.VariosPuntosBinario;

public class FactoriaReproduccion<GenotipoFR extends Genotipo, FenotipoFR extends Fenotipo, Fitness extends Comparable<Fitness>>
{
	
	@SuppressWarnings("unchecked")
	public Reproduccion<GenotipoFR,FenotipoFR,Fitness> getReproduccion(Reproduccion_enum reproduccion)
	{
		switch(reproduccion)
		{
			case Un_punto: return (Reproduccion<GenotipoFR, FenotipoFR, Fitness>) new UnPuntoBinario<FenotipoFR, Fitness>();
			case Varios_puntos: return (Reproduccion<GenotipoFR, FenotipoFR, Fitness>) new VariosPuntosBinario<FenotipoFR, Fitness>();
			case Uniforme: return (Reproduccion<GenotipoFR, FenotipoFR, Fitness>) new UniformeBinario<FenotipoFR,Fitness>();
			default: return null;
		}
	}
	
}
