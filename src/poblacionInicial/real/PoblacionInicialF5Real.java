package poblacionInicial.real;

import java.util.ArrayList;

import configuracion.Configuracion;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGenReal;
import fitness.FitnessReal;
import genotipo.GenotipoReal;
import genotipo.genes.GenReal;
import individuo.Individuo;
import poblacionInicial.PoblacionInicial;

public class PoblacionInicialF5Real implements PoblacionInicial {

	@Override
	public ArrayList<Individuo<?, ?, ?>> getPoblacionInicial(Configuracion config)
	{
		ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessReal>> poblacion= new ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessReal>>();
		
		double minimoGen = 0;
		double maximoGen = Math.PI;
		
		for(int i = 0; i < config.getTamano_poblacion(); i++)
		{
			ArrayList<GenReal> genes = new ArrayList<GenReal>();
			ArrayList<FenotipoGenReal> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGenReal>();
			FenotipoReal fenotipo = new FenotipoReal();
			
			for(int j = 0; j < config.getParametrosf5(); j++)
			{
				GenReal gen = new GenReal(minimoGen, maximoGen);
				gen.inicializaValorAleatoriamente(config.getPrecision());
				genes.add(gen);
				FenotipoGenReal fenotipoDelGen = new FenotipoGenReal(minimoGen, maximoGen, config.getPrecision());
				fenotiposDeTodosLosGenes.add(fenotipoDelGen);
			
			}
			
			GenotipoReal genotipo = new GenotipoReal(genes);	
			fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
				
			Decodificador.decodifica(genotipo, fenotipo);
			Individuo<GenotipoReal, FenotipoReal, FitnessReal> individuo = new Individuo<GenotipoReal, FenotipoReal, FitnessReal>(genotipo);
			individuo.setFenotipo(fenotipo);
			poblacion.add(individuo);
		}
		
		
		return (ArrayList<Individuo<?, ?, ?>>) ((ArrayList<?>) poblacion);
	}

}
