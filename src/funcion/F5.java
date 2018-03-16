package funcion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import fitness.FitnessReal;
import genotipo.Genotipo;
import individuo.Individuo;

public class F5 <GenotipoF5 extends Genotipo> extends Funcion<GenotipoF5, FenotipoReal, FitnessReal>{

	private boolean maximizar = false;
	
	public F5(ArrayList<Individuo<GenotipoF5, FenotipoReal, FitnessReal>> poblacion, Configuracion configuracion) {
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<GenotipoF5, FenotipoReal, FitnessReal>> poblacion) {
		Individuo<GenotipoF5, FenotipoReal, FitnessReal> individuo_evaluar = null;
		for(int i = 0; i < poblacion.size(); i++)
		{
			individuo_evaluar = poblacion.get(i);
			ArrayList<Double> parametros = new ArrayList<Double>();
			for(int j = 0; j < individuo_evaluar.getFenotipo().getCaracteristicas().size(); j++) {
				
				parametros.add(individuo_evaluar.getFenotipo().getCaracteristicas().get(j).getFenotipodelgen());
				
			}
			individuo_evaluar.setFitness(funcion5(parametros));
		}
		//return individuo_evaluar.getFitness();
		
	}

	private FitnessReal funcion5(ArrayList<Double> parametros) {
		double sumatorio = 0.0;
		for(int i = 0; i < parametros.size(); ++i) {
			double parte1 = Math.sin(parametros.get(i));
			double parte2 = Math.pow(Math.sin((i + 1) * Math.pow(parametros.get(i), 2) / Math.PI), 20);
			sumatorio -= parte1 * parte2;
		}
		return new FitnessReal(sumatorio);
	}

	@Override
	public boolean getMaximizar()
	{
		return maximizar;
	}

}
