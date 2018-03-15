package reproduccion;

import configuracion.Reproduccion_enum;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import reproduccion.binario.UnPuntoBinario;
import reproduccion.binario.UniformeBinario;
import reproduccion.binario.VariosPuntosBinario;

public class FactoriaReproduccion<GenotipoFR extends Genotipo, FenotipoFR extends Fenotipo, FitnessFR extends Fitness>
{
	
	@SuppressWarnings("unchecked")
	public Reproduccion<GenotipoFR,FenotipoFR,FitnessFR> getReproduccion(Reproduccion_enum reproduccion)
	{
		switch(reproduccion)
		{
			case Un_punto: return (Reproduccion<GenotipoFR, FenotipoFR, FitnessFR>) new UnPuntoBinario<FenotipoFR, FitnessFR>();
			case Varios_puntos: return (Reproduccion<GenotipoFR, FenotipoFR, FitnessFR>) new VariosPuntosBinario<FenotipoFR, FitnessFR>();
			case Uniforme: return (Reproduccion<GenotipoFR, FenotipoFR, FitnessFR>) new UniformeBinario<FenotipoFR,FitnessFR>();
			default: return null;
		}
	}
	
}
