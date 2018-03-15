package reproduccion.binario;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import genotipo.GenotipoBinario;
import individuo.Individuo;
import reproduccion.Reproduccion;

public class VariosPuntosBinario<Fenotipo, Fitness extends Comparable<Fitness>> implements Reproduccion<GenotipoBinario, FenotipoReal, Fitness>
{

    @Override
    public ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>
    reproduce(ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> poblacion, Configuracion c)
    {
    	
    	
        return null;
    }
}
