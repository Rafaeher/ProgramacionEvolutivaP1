package funcion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import genotipo.Genotipo;
import individuo.Individuo;

public class F5 <GenotipoF5 extends Genotipo> extends Funcion<GenotipoF5, FenotipoReal, Double>{

	private boolean maximizar = false;
	
	public F5(ArrayList<Individuo<GenotipoF5, FenotipoReal, Double>> poblacion, Configuracion configuracion) {
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<GenotipoF5, FenotipoReal, Double>> poblacion) {
		Individuo<GenotipoF5, FenotipoReal, Double> individuo_evaluar = null;
		for(int i = 0; i < poblacion.size(); i++)
		{
			individuo_evaluar = poblacion.get(i);
			ArrayList<Double> parametros = new ArrayList<Double>();
			for(int j = 0; j < individuo_evaluar.getFenotipo().getCaracteristicas().size(); j++) {
				
				//parametros.add(individuo_evaluar.getFenotipo().getCaracteristicas().get(j));
				
			}
			individuo_evaluar.setFitness( funcion5(parametros));
		}
		//return individuo_evaluar.getFitness();
		
	}

	private Double funcion5(ArrayList<Double> parametros) {
		double sumatorio = 0;
		for(int i = 1; i <= parametros.size(); ++i) {
			double parte1 = Math.sin(parametros.get(i));
			double parte2 = Math.pow(Math.sin((i + 1) * Math.pow(parametros.get(i), Math.PI)), 20);
			sumatorio -= parte1 * parte2;
		}
		return sumatorio;
	}

	@Override
	public boolean getMaximizar() {
		// TODO Auto-generated method stub
		return maximizar;
	}

	@Override
	public ArrayList<Individuo<GenotipoF5, FenotipoReal, Double>> colocaLaelite(ArrayList<Individuo<GenotipoF5, FenotipoReal, Double>> poblacion,
			ArrayList<Individuo<GenotipoF5, FenotipoReal, Double>> elite) {
		// TODO Auto-generated method stub
		return null;
	}

}
