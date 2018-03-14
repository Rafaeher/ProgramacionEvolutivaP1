package presentacion;

import java.util.ArrayList;

import javax.swing.JFrame;

import configuracion.Configuracion;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import funcion.F1;
import funcion.Funcion;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;
import poblacionInicial.FactoriaPrimeraPoblacionBinario;

public class Controlador {
	
	
	public void execute(Configuracion c, JFrame j) {
		
		//ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>> poblacion= new ArrayList<Individuo<GenotipoBinario,FenotipoReal,Double>>();
		/*GenBinario gen = new GenBinario(5);
		ArrayList<Boolean> prueba = new ArrayList<Boolean>();
		for(int j = 0; j < 10; j++) prueba.add(true);
		gen.setGen(prueba);
		ArrayList<GenBinario> genes = new ArrayList<GenBinario> ();
		genes.add(gen);
		GenotipoBinario genotipo = new GenotipoBinario(genes);
		
		for(int i= 0; i < c.getTamano_poblacion(); i++) {
			
			Individuo<GenotipoBinario,FenotipoReal,Double> individuo = new Individuo<GenotipoBinario,FenotipoReal,Double>(genotipo);
			FenotipoReal fenotipo = new FenotipoReal(1.1,1.1,1.1);
			ArrayList<Double> array = new ArrayList<Double>();
			array.add(2.1);
			fenotipo.setCaracteristicas(array);
			individuo.setFenotipo(fenotipo);
			poblacion.add(individuo);
		}*/
		if(c.getProblema() > 0 && c.getProblema() <= 5)
		{
			FactoriaPrimeraPoblacionBinario factoriaPrimeraPoblacion = new FactoriaPrimeraPoblacionBinario();
			ArrayList<?> poblacion = factoriaPrimeraPoblacion.getPrimeraPoblacion(c);
			@SuppressWarnings("unchecked")
			ArrayList<Individuo<GenotipoBinario, FenotipoReal, Double>> pob = (ArrayList<Individuo<GenotipoBinario, FenotipoReal, Double>>) poblacion;
			FactoriaFunciones<GenotipoBinario,FenotipoReal,Double> factoriaFunciones = new FactoriaFunciones<GenotipoBinario,FenotipoReal,Double>();
			Funcion<GenotipoBinario, FenotipoReal, Double> funcion = factoriaFunciones.getSeleccion(c.getProblema(), pob, c);
			funcion.algoritmoGenetico();
			Vista.getVista().repintaGrafica(funcion.getGeneraciones(), funcion.getmejoriteracion(), funcion.gety_mejor_total(),j);
		
		}
		
		
	}
	
	
	
}
