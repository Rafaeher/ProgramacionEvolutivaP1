package mutacion;

import configuracion.Mutacion_enum;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;

public class FactoriaMutacion<GenotipoFM extends Genotipo, FenotipoFM extends Fenotipo, FitnessFM extends Fitness>
{
	@SuppressWarnings("unchecked")
	public Mutacion<GenotipoFM, FenotipoFM, FitnessFM> getMutacion(Mutacion_enum tipo)
    {
        switch(tipo)
        {
            case Normal: return (Mutacion<GenotipoFM, FenotipoFM, FitnessFM>) new MutacionEstandarBinario<FenotipoFM, FitnessFM>();
            case Normal_Real: return (Mutacion<GenotipoFM, FenotipoFM, FitnessFM>) new MutacionEstandarReal<FenotipoFM, FitnessFM>();
            default: return null;
        }
    }
}
