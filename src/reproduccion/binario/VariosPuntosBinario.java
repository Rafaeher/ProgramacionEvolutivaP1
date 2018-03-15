package reproduccion.binario;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fenotipo.FenotipoReal;
import fitness.Fitness;
import genotipo.GenotipoBinario;
import individuo.Individuo;
import reproduccion.Reproduccion;

public class VariosPuntosBinario<FenotipoVPB extends Fenotipo, FitnessVPB extends Fitness> implements Reproduccion<GenotipoBinario, FenotipoReal, FitnessVPB>
{

    @Override
    public ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessVPB>>
    reproduce(ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessVPB>> poblacion, Configuracion c)
    {
    	
    	
        return null;
    }
}
