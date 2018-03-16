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

public class F2<GenotipoF2 extends Genotipo> extends Funcion<GenotipoF2, FenotipoReal, FitnessReal> {
	
	private boolean maximizar = false;
	
	public F2(ArrayList<Individuo<GenotipoF2, FenotipoReal, FitnessReal>> poblacion, Configuracion configuracion) {
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<GenotipoF2, FenotipoReal, FitnessReal>> poblacion) {
		Individuo<GenotipoF2, FenotipoReal, FitnessReal> individuo_evaluar = null;
		for(int i = 0; i < poblacion.size(); i++)
		{
			individuo_evaluar = poblacion.get(i);
			FenotipoReal fenotipo =(FenotipoReal)individuo_evaluar.getFenotipo();
			double parametro1 = (Double)fenotipo.getCaracteristicas().get(0).getFenotipodelgen();
			double parametro2 = (Double)fenotipo.getCaracteristicas().get(1).getFenotipodelgen();
			individuo_evaluar.setFitness(funcion2(parametro1, parametro2));
		}
	}

	private FitnessReal funcion2(double parametro1, double parametro2) {
		double parte1 = (parametro2 + 47);
		double parte2 = Math.sin(Math.sqrt(Math.abs(parametro2 + (parametro1 / 2) + 47)));
		double parte3 = Math.sin(Math.sqrt(Math.abs(parametro1 - (parametro2 + 47))));
		return new FitnessReal(-parte1 * parte2 - parametro1 * parte3);
	}

	@Override
	public boolean getMaximizar() {
		// TODO Auto-generated method stub
		return maximizar;
	}

}
