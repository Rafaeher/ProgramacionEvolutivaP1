package mutacion;

import configuracion.Mutacion_enum;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;

public class
FactoriaMutacionImp<GenotipoFMI extends Genotipo, FenotipoFMI extends Fenotipo, FitnessFMI extends Fitness>
extends FactoriaMutacion<GenotipoFMI, FenotipoFMI, FitnessFMI>
{

    @SuppressWarnings("unchecked")
	@Override
    public Mutacion<GenotipoFMI, FenotipoFMI, FitnessFMI> getMutacion(Mutacion_enum tipo)
    {
        switch(tipo)
        {
            case Normal: return (Mutacion<GenotipoFMI, FenotipoFMI, FitnessFMI>) new MutacionEstandarBinario<FenotipoFMI, FitnessFMI>();
            case Normal_Real: return (Mutacion<GenotipoFMI, FenotipoFMI, FitnessFMI>) new MutacionEstandarReal<FenotipoFMI, FitnessFMI>();
            default: return null;
        }
    }
}
