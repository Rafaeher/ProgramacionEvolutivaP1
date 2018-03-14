package reproduccion;

import configuracion.Reproduccion_enum;
import genotipo.Genotipo;
import reproduccion.binario.UnPuntoBinario;
import reproduccion.binario.VariosPuntosBinario;

public class FactoriaReproduccion<GenotipoFR extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	
	public Reproduccion<GenotipoFR,Fenotipo,Fitness> getReproduccion(Reproduccion_enum reproduccion)
	{
		switch(reproduccion)
		{
			case Un_punto: return (Reproduccion<GenotipoFR, Fenotipo, Fitness>) new UnPuntoBinario();
			case Varios_puntos: return (Reproduccion<GenotipoFR, Fenotipo, Fitness>) new VariosPuntosBinario<GenotipoFR,Fenotipo,Fitness>();
			//case Uniforme: return new Uniforme<Genotipo,Fenotipo,Fitness>();
			default: return null;
		}
	}
	
}
