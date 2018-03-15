package reproduccion;

import configuracion.Reproduccion_enum;
import reproduccion.binario.UnPuntoBinario;
import reproduccion.binario.UniformeBinario;
import reproduccion.binario.VariosPuntosBinario;

public class FactoriaReproduccion<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	
	@SuppressWarnings("unchecked")
	public Reproduccion<Genotipo,Fenotipo,Fitness> getReproduccion(Reproduccion_enum reproduccion)
	{
		switch(reproduccion)
		{
			case Un_punto: return (Reproduccion<Genotipo, Fenotipo, Fitness>) new UnPuntoBinario<Fenotipo, Fitness>();
			case Varios_puntos: return (Reproduccion<Genotipo, Fenotipo, Fitness>) new VariosPuntosBinario<Fenotipo, Fitness>();
			case Uniforme: return (Reproduccion<Genotipo, Fenotipo, Fitness>) new UniformeBinario<Fenotipo,Fitness>();
			default: return null;
		}
	}
	
}
