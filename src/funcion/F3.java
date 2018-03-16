package funcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import fitness.FitnessReal;
import genotipo.Genotipo;
import individuo.Individuo;

public class F3 <GenotipoF3 extends Genotipo> extends Funcion<GenotipoF3, FenotipoReal, FitnessReal>
{	

	private boolean maximizar = true;
	
	public F3(ArrayList<Individuo<GenotipoF3, FenotipoReal, FitnessReal>> poblacion, Configuracion configuracion) {
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<GenotipoF3, FenotipoReal, FitnessReal>> poblacion) {
		Individuo<GenotipoF3, FenotipoReal, FitnessReal> individuo_evaluar = null;
		for(int i = 0; i < poblacion.size(); i++)
		{
			individuo_evaluar = poblacion.get(i);
			FenotipoReal fenotipo =(FenotipoReal)individuo_evaluar.getFenotipo();
			double parametro1 = (Double)fenotipo.getCaracteristicas().get(0).getFenotipodelgen();
			double parametro2 = (Double)fenotipo.getCaracteristicas().get(1).getFenotipodelgen();
			individuo_evaluar.setFitness(funcion3(parametro1, parametro2));
		}
		//return individuo_evaluar.getFitness();
	}
	
	private FitnessReal funcion3(double parametro1, double parametro2) {
		double parte1 = 21.5;
		double parte2 = parametro1 * Math.sin(4 * Math.PI * parametro1);
		double parte3 = parametro2 * Math.sin(20 * Math.PI * parametro2);
		return new FitnessReal(parte1 + parte2 + parte3);
	}

	@Override
	public boolean getMaximizar()
	{
		return maximizar;
	}
}
