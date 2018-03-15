package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGenReal;
import fitness.FitnessReal;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;
import poblacionInicial.PoblacionInicialF1Binario;
import poblacionInicial.PoblacionInicialF3Binario;

public class FactoriaPrimeraPoblacionBinario
{

	public  ArrayList<Individuo<?, ?, ?>> getPrimeraPoblacion(Configuracion c)
	{
		switch(c.getProblema())
		{
			case 1: return (new PoblacionInicialF1Binario()).getPoblacionInicial(c);
			case 2: return (new PoblacionInicialF2Binario()).getPoblacionInicial(c);
			//case 2: return generaPrimeraPoblacionAleatoriaF2(c);
			case 3: return (new PoblacionInicialF3Binario()).getPoblacionInicial(c);
			//case 4: return generaPrimeraPoblacionAleatoriaF4(c);
			/*case Varios_puntos: return new VariosPuntos<Genotipo,Fenotipo,Fitness>();
			case Uniforme: return new Uniforme<Genotipo,Fenotipo,Fitness>();*/
			default: return null;
		}
	}
	
	
	
}
