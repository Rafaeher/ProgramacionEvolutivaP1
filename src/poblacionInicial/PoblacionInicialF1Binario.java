package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGenReal;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;

public class PoblacionInicialF1Binario extends PoblacionInicialF1 {

	@SuppressWarnings("unchecked")
	@Override
	protected ArrayList<Individuo<?, ?, ?>> getPoblacion(Configuracion config)
	{
		ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>> poblacion = new ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>>();
		
		//int num_genes_por_cromosoma = 1;
		double minimo = 0;
		double maximo = 32.0;
		int longitud = GenBinario.calculaLongitud(minimo, maximo, config.getPrecision());
		for(int i = 0; i < config.getTamano_poblacion(); i++) {
			GenBinario gen = new GenBinario(longitud);
			gen.inicializacionAleatoria();
			ArrayList<GenBinario> genes = new ArrayList<GenBinario> ();
			genes.add(gen);
			GenotipoBinario genotipo = new GenotipoBinario(genes);
			FenotipoReal fenotipo = new FenotipoReal();
			FenotipoGenReal fenotipoDelGen = new FenotipoGenReal(minimo,maximo,config.getPrecision());
			ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGenReal>();
			fenotiposDeTodosLosGenes.add(fenotipoDelGen);
			fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
			//Se obtiene un array con todos los fenotipos de TODOS los genes
			Decodificador.decodifica(genotipo, fenotipo);

			fenotipo.getCaracteristicas().get(0).setFenotipodelgen(fenotipo.get(0).getFenotipodelgen());
			Individuo<GenotipoBinario,FenotipoReal,Double> individuo = new Individuo<GenotipoBinario,FenotipoReal,Double>(genotipo);
			individuo.setFenotipo(fenotipo);
			poblacion.add(individuo);
		}
		
		return (ArrayList<Individuo<?, ?, ?>>) ((ArrayList<?>) poblacion);
	}

}
