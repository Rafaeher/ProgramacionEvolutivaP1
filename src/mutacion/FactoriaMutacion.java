package mutacion;

import configuracion.Mutacion_enum;

public class FactoriaMutacion<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	public Mutacion<Genotipo, Fenotipo, Fitness> getMutacion(Mutacion_enum tipo)
    {
        switch(tipo)
        {
            case Normal: return (Mutacion<Genotipo, Fenotipo, Fitness>) new MutacionEstandarBinario<Genotipo,Fenotipo,Fitness>();
            default: return null;
        }
    }
}
