package funcion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import genotipo.Genotipo;
import individuo.Individuo;
import fitness.FitnessReal;

public class F4 <GenotipoF4 extends Genotipo> extends Funcion<GenotipoF4, FenotipoReal, FitnessReal>
{

	private boolean maximizar = false;
	
	public F4(ArrayList<Individuo<GenotipoF4, FenotipoReal, FitnessReal>> poblacion, Configuracion configuracion) {
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<GenotipoF4, FenotipoReal, FitnessReal>> poblacion) {
		
		Individuo<GenotipoF4, FenotipoReal, FitnessReal> individuo_evaluar = null;
		for(int i = 0; i < poblacion.size(); i++)
		{
			individuo_evaluar = poblacion.get(i);
			FenotipoReal fenotipo =(FenotipoReal)individuo_evaluar.getFenotipo();
			double parametro1 = (Double)fenotipo.getCaracteristicas().get(0).getFenotipodelgen();
			double parametro2 = (Double)fenotipo.getCaracteristicas().get(1).getFenotipodelgen();
			System.out.println("Sol f4 " + funcion4(parametro1, parametro2));
			individuo_evaluar.setFitness( funcion4(parametro1, parametro2));
		}
		//return individuo_evaluar.getFitness();
		
	}
	private FitnessReal funcion4(double parametro1, double parametro2) {
		return new FitnessReal(formula(parametro1) * formula(parametro2));
	}
	private double formula(double parametro) {
		double sumador = 0;
		for(int i = 1; i <= 5; ++i) {
			sumador += i * Math.cos((i + 1) * parametro + i);
		}
		System.out.println("Sol formula " + sumador);
		return sumador;
	}

	@Override
	public boolean getMaximizar()
	{
		return maximizar;
	}

}
