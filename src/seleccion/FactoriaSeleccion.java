package seleccion;

import configuracion.Seleccion_enum;

public class FactoriaSeleccion<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
{
	
	public Seleccion<Genotipo,Fenotipo,Fitness> getSeleccion(Seleccion_enum seleccion)
    {
    	switch(seleccion)
		{
			case Ruleta: return new Ruleta<Genotipo,Fenotipo,Fitness>();
			case Estocastica: return new Estocastica<Genotipo,Fenotipo,Fitness>(); 
			case Torneo_Deterministico: return new TorneoDeterministico<Genotipo,Fenotipo,Fitness>(); 
			case Torneo_Probabilistico: return new TorneoProbabilistico<Genotipo,Fenotipo,Fitness>(); 
			default: return null;
		}
    }
}
