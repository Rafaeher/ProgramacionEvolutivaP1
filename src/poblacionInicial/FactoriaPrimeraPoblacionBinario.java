package poblacionInicial;

import java.util.ArrayList;

import configuracion.Configuracion;
import individuo.Individuo;
import poblacionInicial.PoblacionInicialF1Binario;
import poblacionInicial.PoblacionInicialF3Binario;

public class FactoriaPrimeraPoblacionBinario
{

	public  ArrayList<Individuo<?, ?, ?>> getPrimeraPoblacion(Configuracion c)
	{
		switch(c.getProblema())
		{
			case 1: return (new PoblacionInicialF1Binario()).getPoblacionInicial(c);
			case 2: return (new PoblacionInicialF2Binario()).getPoblacionInicial(c);
			case 3: return (new PoblacionInicialF3Binario()).getPoblacionInicial(c);
			case 4: return (new PoblacionInicialF4Binario()).getPoblacionInicial(c);
			case 5: return (new PoblacionInicialF5Binario()).getPoblacionInicial(c);
			default: return null;
		}
	}
	
	
	
}
