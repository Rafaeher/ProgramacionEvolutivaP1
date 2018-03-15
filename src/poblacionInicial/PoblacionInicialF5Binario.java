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

public class PoblacionInicialF5Binario extends PoblacionInicialF5
{

	@Override
	protected ArrayList<Individuo<?, ?, ?>> getPoblacion(Configuracion config)
	{
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>> poblacion= new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>>();
		
		double minimoGen = 0;
		double maximoGen = Math.PI;
		int longitudGen = GenBinario.calculaLongitud(minimoGen, maximoGen, config.getPrecision());
		
		for(int i = 0; i < config.getTamano_poblacion(); i++)
		{
			for(int j = 0; j < 7; j++)
			{
				GenBinario gen = new GenBinario(longitudGen);
				gen.inicializacionAleatoria();
				ArrayList<GenBinario> genes = new ArrayList<GenBinario> ();
				genes.add(gen);
				GenotipoBinario genotipo = new GenotipoBinario(genes);
				FenotipoReal fenotipo = new FenotipoReal();
				FenotipoGenReal fenotipoDelGen = new FenotipoGenReal(minimoGen, maximoGen,config.getPrecision());
				ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGenReal>();
				fenotiposDeTodosLosGenes.add(fenotipoDelGen);
				fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
				Decodificador.decodifica(genotipo, fenotipo);
				fenotipo.getCaracteristicas().get(i).setFenotipodelgen(fenotipo.get(i).getFenotipodelgen());
				
				Individuo<GenotipoBinario,FenotipoReal, FitnessReal> individuo = new Individuo<GenotipoBinario,FenotipoReal, FitnessReal>(genotipo);
				individuo.setFenotipo(fenotipo);
				poblacion.add(individuo);
			}
		}
		
		
		return (ArrayList<Individuo<?, ?, ?>>) ((ArrayList<?>) poblacion);
	}

}
