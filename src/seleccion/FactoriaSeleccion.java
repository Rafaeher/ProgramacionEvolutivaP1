package seleccion;

import configuracion.Seleccion_enum;
import genotipo.Genotipo;

public class FactoriaSeleccion<GenotipoFS extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	
	public Seleccion<GenotipoFS,Fenotipo,Fitness> getSeleccion(Seleccion_enum seleccion)
    {
    	switch(seleccion)
		{
			case Ruleta: return new Ruleta<GenotipoFS,Fenotipo,Fitness>();
			case Estocastica: return new Estocastica<GenotipoFS,Fenotipo,Fitness>(); 
			case Torneo_Deterministico: return new TorneoDeterministico<GenotipoFS,Fenotipo,Fitness>(); 
			case Torneo_Probabilistico: return new TorneoProbabilistico<GenotipoFS,Fenotipo,Fitness>(); 
			default: return null;
		}
    }
}
