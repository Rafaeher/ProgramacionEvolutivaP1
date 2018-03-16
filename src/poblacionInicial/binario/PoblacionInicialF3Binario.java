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

public class PoblacionInicialF3Binario implements PoblacionInicial
{

	@Override
	public ArrayList<Individuo<?, ?, ?>> getPoblacionInicial(Configuracion config)
	{
		ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>> poblacion= new ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>>();
		//int num_genes_por_cromosoma = 1;
		double minimoGen1 = -3;
		double maximoGen1 = 12.1;
		double minimoGen2 = 4.1;
		double maximoGen2 = 5.8;
		int longitudGen1 = GenBinario.calculaLongitud(minimoGen1, maximoGen1, config.getPrecision());
		int longitudGen2 = GenBinario.calculaLongitud(minimoGen2, maximoGen2, config.getPrecision());
		for(int i = 0; i < config.getTamano_poblacion(); i++) {
			GenBinario gen1 = new GenBinario(longitudGen1);
			GenBinario gen2 = new GenBinario(longitudGen2);
			gen1.inicializacionAleatoria();
			gen2.inicializacionAleatoria();
			ArrayList<GenBinario> genes = new ArrayList<GenBinario> ();
			genes.add(gen1);
			genes.add(gen2);
			GenotipoBinario genotipo = new GenotipoBinario(genes);
			//---
			FenotipoReal fenotipo = new FenotipoReal();
			FenotipoGenReal fenotipoDelGen1 = new FenotipoGenReal(minimoGen1,maximoGen1,config.getPrecision());
			FenotipoGenReal fenotipoDelGen2 = new FenotipoGenReal(minimoGen2,maximoGen2,config.getPrecision());
			ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGenReal>();
			fenotiposDeTodosLosGenes.add(fenotipoDelGen1);
			fenotiposDeTodosLosGenes.add(fenotipoDelGen2);
			fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
			Decodificador.decodifica(genotipo, fenotipo);
			fenotipo.getCaracteristicas().get(0).setFenotipodelgen(fenotipo.get(0).getFenotipodelgen());
			fenotipo.getCaracteristicas().get(1).setFenotipodelgen(fenotipo.get(1).getFenotipodelgen());
			//---
			Individuo<GenotipoBinario,FenotipoReal, FitnessReal> individuo = new Individuo<GenotipoBinario,FenotipoReal, FitnessReal>(genotipo);
			individuo.setFenotipo(fenotipo);
			poblacion.add(individuo);
		}
		return (ArrayList<Individuo<?, ?, ?>>) ((ArrayList<?>) poblacion);
	}

}
