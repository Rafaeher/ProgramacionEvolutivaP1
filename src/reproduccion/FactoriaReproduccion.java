package reproduccion;

import configuracion.Reproduccion_enum;
import reproduccion.binario.UnPuntoBinario;
import reproduccion.binario.VariosPuntosBinario;

public class FactoriaReproduccion<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	
	public Reproduccion<Genotipo,Fenotipo,Fitness> getReproduccion(Reproduccion_enum reproduccion)
	{
		switch(reproduccion)
		{
			case Un_punto: return (Reproduccion<Genotipo, Fenotipo, Fitness>) new UnPuntoBinario<Genotipo,Fenotipo,Fitness>();
			case Varios_puntos: return (Reproduccion<Genotipo, Fenotipo, Fitness>) new VariosPuntosBinario<Genotipo,Fenotipo,Fitness>();
			//case Uniforme: return new Uniforme<Genotipo,Fenotipo,Fitness>();
			default: return null;
		}
	}
	
}
