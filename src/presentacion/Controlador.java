package presentacion;

import java.util.ArrayList;

import javax.swing.JFrame;

import configuracion.Configuracion;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import funcion.F1;
import funcion.FactoriaFunciones;
import funcion.Funcion;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;
import poblacionInicial.FactoriaPrimeraPoblacionBinario;

public class Controlador {
	
	
	public void execute(Configuracion c, JFrame j) {
		
		if(c.getProblema() > 0 && c.getProblema() <= 5)
		{
			FactoriaPrimeraPoblacionBinario factoriaPrimeraPoblacion = new FactoriaPrimeraPoblacionBinario();
			ArrayList<?> poblacion = factoriaPrimeraPoblacion.getPrimeraPoblacion(c);
			@SuppressWarnings("unchecked")
			ArrayList<Individuo<GenotipoBinario, FenotipoReal, Double>> pob = (ArrayList<Individuo<GenotipoBinario, FenotipoReal, Double>>) poblacion;
			FactoriaFunciones<GenotipoBinario,FenotipoReal,Double> factoriaFunciones = new FactoriaFunciones<GenotipoBinario,FenotipoReal,Double>();
			Funcion<GenotipoBinario, FenotipoReal, Double> funcion = factoriaFunciones.getSeleccion(c.getProblema(), pob, c);
			funcion.algoritmoGenetico();
			Vista.getVista().repintaGrafica(funcion.getGeneraciones(), funcion.getmejoriteracion(), funcion.gety_mejor_total(),funcion.getMedia(),j);
		
		}
		
		
	}
	
	
	
}
