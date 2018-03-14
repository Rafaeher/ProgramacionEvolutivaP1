package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import configuracion.Genotipo_enum;
import configuracion.Reproduccion_enum;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGen;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;
import poblacionInicial.PoblacionInicialF1Binario;
import poblacionInicial.PoblacionInicialF3Binario;
import reproduccion.Reproduccion;

public class FactoriaPrimeraPoblacionBinario
{

	public  ArrayList<Individuo<?, ?, ?>> getPrimeraPoblacion(Configuracion c)
	{
		switch(c.getProblema())
		{
			case 1: return (new PoblacionInicialF1Binario()).getPoblacionInicial(c);
			//case 2: return generaPrimeraPoblacionAleatoriaF2(c);
			case 3: return (new PoblacionInicialF3Binario()).getPoblacionInicial(c);
			//case 4: return generaPrimeraPoblacionAleatoriaF4(c);
			/*case Varios_puntos: return new VariosPuntos<Genotipo,Fenotipo,Fitness>();
			case Uniforme: return new Uniforme<Genotipo,Fenotipo,Fitness>();*/
			default: return null;
		}
	}
	
	
	private ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>> generaPrimeraPoblacionAleatoriaF2 ( Configuracion c) {
		ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>> poblacion= new ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>>();
		//int num_genes_por_cromosoma = 1;
		double minimo = -512;
		double maximo = 512;
		int longitud = GenBinario.calculaLongitud(minimo, maximo, c.getPrecision());
		for(int i = 0; i < c.getTamano_poblacion(); i++) {
			GenBinario gen1 = new GenBinario(longitud);
			GenBinario gen2 = new GenBinario(longitud);
			gen1.inicializacionAleatoria();
			gen2.inicializacionAleatoria();
			ArrayList<GenBinario> genes = new ArrayList<GenBinario> ();
			genes.add(gen1);
			genes.add(gen2);
			GenotipoBinario genotipo = new GenotipoBinario(genes);
			//---
			FenotipoReal fenotipo = new FenotipoReal();
			FenotipoGen fenotipoDelGen1 = new FenotipoGen(minimo,maximo,c.getPrecision());
			FenotipoGen fenotipoDelGen2 = new FenotipoGen(minimo,maximo,c.getPrecision());
			ArrayList<FenotipoGen> fenotiposDeTodosLosGenes = new ArrayList<FenotipoGen>();
			fenotiposDeTodosLosGenes.add(fenotipoDelGen1);
			fenotiposDeTodosLosGenes.add(fenotipoDelGen2);
			fenotipo.setCaracteristicas(fenotiposDeTodosLosGenes);
			ArrayList<Double> fenotipo_del_individuo_i = (ArrayList<Double>)Decodificador.decodifica(genotipo, fenotipo);
			fenotipo.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_del_individuo_i.get(0));
			fenotipo.getCaracteristicas().get(1).setFenotipodelgen(fenotipo_del_individuo_i.get(1));
			//----
			Individuo<GenotipoBinario,FenotipoReal,Double> individuo = new Individuo<GenotipoBinario,FenotipoReal,Double>(genotipo);
			individuo.setFenotipo(fenotipo);
			poblacion.add(individuo);
			
			
			
		}
		return poblacion;
	}
	
	
	
	private ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>> generaPrimeraPoblacionAleatoriaF4 ( Configuracion c) {
		ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>> poblacion= new ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>>();
		//int num_genes_por_cromosoma = 1;
		double minimoGen1 = -10;
		double maximoGen1 = 10;
		int longitudGen1 = GenBinario.calculaLongitud(minimoGen1, maximoGen1, c.getPrecision());
		int longitudGen2 = GenBinario.calculaLongitud(minimoGen1, maximoGen1, c.getPrecision());
		for(int i = 0; i < c.getTamano_poblacion(); i++) {
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
			FenotipoGen fenotipoDelGen1 = new FenotipoGen(minimoGen1,maximoGen1,c.getPrecision());
			FenotipoGen fenotipoDelGen2 = new FenotipoGen(minimoGen1,maximoGen1,c.getPrecision());
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
		return poblacion;
	}
	
	
	
}
