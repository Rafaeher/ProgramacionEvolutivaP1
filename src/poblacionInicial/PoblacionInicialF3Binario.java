package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGen;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;

public class PoblacionInicialF3Binario implements PoblacionInicial {

	@Override
	public ArrayList<Individuo<?, ?, ?>> getPoblacionInicial(Configuracion config)
	{
		ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>> poblacion= new ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>>();
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
			FenotipoGen fenotipoDelGen1 = new FenotipoGen(minimoGen1,maximoGen1,config.getPrecision());
			FenotipoGen fenotipoDelGen2 = new FenotipoGen(minimoGen2,maximoGen2,config.getPrecision());
			ArrayList<FenotipoGen> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGen>();
			fenotiposDeTodosLosGenes.add(fenotipoDelGen1);
			fenotiposDeTodosLosGenes.add(fenotipoDelGen2);
			fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
			ArrayList<Double> fenotipo_del_individuo_i = (ArrayList<Double>)Decodificador.decodifica(genotipo, fenotipo);
			fenotipo.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_del_individuo_i.get(0));
			fenotipo.getCaracteristicas().get(1).setFenotipodelgen(fenotipo_del_individuo_i.get(1));
			//---
			Individuo<GenotipoBinario,FenotipoReal,Double> individuo = new Individuo<GenotipoBinario,FenotipoReal,Double>(genotipo);
			individuo.setFenotipo(fenotipo);
			poblacion.add(individuo);
		}
		return (ArrayList<Individuo<?, ?, ?>>) ((ArrayList<?>) poblacion);
	}

}
