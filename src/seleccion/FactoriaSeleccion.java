package seleccion;

import configuracion.Seleccion_enum;
import fenotipo.Fenotipo;
import genotipo.Genotipo;

public class FactoriaSeleccion<GenotipoFS extends Genotipo, FenotipoFS extends Fenotipo, Fitness extends Comparable<Fitness>>
{
	
	public Seleccion<GenotipoFS,FenotipoFS,Fitness> getSeleccion(Seleccion_enum seleccion)
    {
    	switch(seleccion)
		{
			case Ruleta: return new Ruleta<GenotipoFS,FenotipoFS,Fitness>();
			case Estocastica: return new Estocastica<GenotipoFS,FenotipoFS,Fitness>(); 
			case Torneo_Deterministico: return new TorneoDeterministico<GenotipoFS,FenotipoFS,Fitness>(); 
			case Torneo_Probabilistico: return new TorneoProbabilistico<GenotipoFS,FenotipoFS,Fitness>(); 
			default: return null;
		}
    }
}
