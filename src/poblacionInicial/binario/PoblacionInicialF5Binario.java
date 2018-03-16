package poblacionInicial.binario;

import java.util.ArrayList;

import configuracion.Configuracion;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGenReal;
import fitness.FitnessReal;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;
import poblacionInicial.PoblacionInicial;

public class PoblacionInicialF5Binario implements PoblacionInicial {

	@Override
	public ArrayList<Individuo<?, ?, ?>> getPoblacionInicial(Configuracion config)
	{
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>> poblacion= new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>>();
		
		double minimoGen = 0;
		double maximoGen = Math.PI;
		int longitudGen = GenBinario.calculaLongitud(minimoGen, maximoGen, config.getPrecision());
		
		for(int i = 0; i < config.getTamano_poblacion(); i++)
		{
			ArrayList<GenBinario> genes = new ArrayList<GenBinario> ();
			ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGenReal>();
			FenotipoReal fenotipo = new FenotipoReal();
			
			for(int j = 0; j < config.getParametrosf5(); j++)
			{
				GenBinario gen = new GenBinario(longitudGen);
				gen.inicializacionAleatoria();
				genes.add(gen);
				FenotipoGenReal fenotipoDelGen = new FenotipoGenReal(minimoGen, maximoGen, config.getPrecision());
				fenotiposDeTodosLosGenes.add(fenotipoDelGen);
			
			}
			
			GenotipoBinario genotipo = new GenotipoBinario(genes);	
			fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
				
			Decodificador.decodifica(genotipo, fenotipo);
			Individuo<GenotipoBinario,FenotipoReal, FitnessReal> individuo = new Individuo<GenotipoBinario,FenotipoReal, FitnessReal>(genotipo);
			individuo.setFenotipo(fenotipo);
			poblacion.add(individuo);
		}
		
		
		return (ArrayList<Individuo<?, ?, ?>>) ((ArrayList<?>) poblacion);
	}

}
