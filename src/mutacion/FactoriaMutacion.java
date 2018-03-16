package mutacion;

import configuracion.Mutacion_enum;
import fitness.Fitness;

public class FactoriaMutacion<Genotipo, Fenotipo, FitnessFM extends Fitness>
{
	public Mutacion<Genotipo, Fenotipo, FitnessFM> getMutacion(Mutacion_enum tipo)
    {
        switch(tipo)
        {
            case Normal: return (Mutacion<Genotipo, Fenotipo, FitnessFM>) new MutacionEstandarBinario<Fenotipo,FitnessFM>();
            case Normal_Real: return (Mutacion<Genotipo, Fenotipo, FitnessFM>) new MutacionEstandarReal<Fenotipo,FitnessFM>();
            default: return null;
        }
    }
}
