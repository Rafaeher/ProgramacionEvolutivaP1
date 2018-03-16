package presentacion;

import java.util.ArrayList;

import javax.swing.JFrame;

import configuracion.Configuracion;
import decodificador.Decodificador;
import fenotipo.FenotipoReal;
import fitness.FitnessReal;
import funcion.F1;
import funcion.FactoriaFunciones;
import funcion.Funcion;
import genotipo.GenotipoBinario;
import genotipo.GenotipoReal;
import genotipo.genes.GenBinario;
import individuo.Individuo;
import poblacionInicial.FactoriaPrimeraPoblacionBinario;

public class Controlador {
	
	
	public void execute(Configuracion c)
	{
		
		if(c.getProblema() > 0 && c.getProblema() <= 5)
		{
			FactoriaPrimeraPoblacionBinario factoriaPrimeraPoblacion = new FactoriaPrimeraPoblacionBinario();
			ArrayList<?> poblacion = factoriaPrimeraPoblacion.getPrimeraPoblacion(c);
			@SuppressWarnings("unchecked")
			ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>> pob = (ArrayList<Individuo<GenotipoBinario, FenotipoReal, FitnessReal>>) poblacion;
			FactoriaFunciones<GenotipoBinario,FenotipoReal, FitnessReal> factoriaFunciones = new FactoriaFunciones<GenotipoBinario,FenotipoReal, FitnessReal>();
			Funcion<GenotipoBinario, FenotipoReal, FitnessReal> funcion = factoriaFunciones.getSeleccion(c.getProblema(), pob, c);
			funcion.algoritmoGenetico();
			Vista.getVista().repintaGrafica(funcion.getGeneraciones(), funcion.getmejoriteracion(), funcion.gety_mejor_total(),funcion.getMedia());
		
		}
		else{
			FactoriaPrimeraPoblacionBinario factoriaPrimeraPoblacion = new FactoriaPrimeraPoblacionBinario();
			ArrayList<?> poblacion = factoriaPrimeraPoblacion.getPrimeraPoblacion(c);
			@SuppressWarnings("unchecked")
			ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessReal>> pob = (ArrayList<Individuo<GenotipoReal, FenotipoReal, FitnessReal>>) poblacion;
			FactoriaFunciones<GenotipoReal,FenotipoReal, FitnessReal> factoriaFunciones = new FactoriaFunciones<GenotipoReal,FenotipoReal, FitnessReal>();
			Funcion<GenotipoReal, FenotipoReal, FitnessReal> funcion = factoriaFunciones.getSeleccion(c.getProblema(), pob, c);
			funcion.algoritmoGenetico();
			Vista.getVista().repintaGrafica(funcion.getGeneraciones(), funcion.getmejoriteracion(), funcion.gety_mejor_total(),funcion.getMedia());
		
		}
		
		
	}
	
	
	
}
