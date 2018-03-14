package presentacion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import funcion.F1;
import funcion.F2;
import funcion.F3;
import funcion.F4;
import funcion.F5;
import funcion.Funcion;
import genotipo.Genotipo;
import genotipo.GenotipoBinario;
import individuo.Individuo;

public class FactoriaFunciones <GenotipoFF extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>> {

	public Funcion<GenotipoFF,Fenotipo,Fitness> getSeleccion(int f,
			ArrayList<Individuo<GenotipoFF,Fenotipo, Fitness>> poblacion,
			Configuracion c)
    {
    	switch(f)
		{
			case 1: return new F1(poblacion,c);
			case 2: return new F2(poblacion,c);
			case 3: return new F3(poblacion,c);
			case 4: return new F4(poblacion,c);
			case 5: return new F5(poblacion,c); 
			default: return null;
		}
    }
}