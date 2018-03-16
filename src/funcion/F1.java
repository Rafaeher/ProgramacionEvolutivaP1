package funcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGenReal;
import fitness.FitnessReal;
import genotipo.Genotipo;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;

public class F1<GenotipoF1 extends Genotipo> extends Funcion<GenotipoF1, FenotipoReal, FitnessReal> {

	private final static boolean maximizar = true;

	public F1(ArrayList<Individuo<GenotipoF1, FenotipoReal, FitnessReal>> poblacion, Configuracion configuracion)

	{
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<GenotipoF1, FenotipoReal, FitnessReal>> poblacion) {

		Individuo<GenotipoF1, FenotipoReal, FitnessReal> individuo_evaluar = null;
		for (int i = 0; i < poblacion.size(); i++) {
			individuo_evaluar = poblacion.get(i);
			FenotipoReal fenotipo = individuo_evaluar.getFenotipo();
			double parametro1 = (Double) fenotipo.getCaracteristicas().get(0).getFenotipodelgen();
			individuo_evaluar.setFitness(funcion1(parametro1));
		}

	}

	/**
	 * funcion que tiene los calculos propios de la funcion
	 * 
	 * @param fenotipo:
	 *            fenotipo del individuo que queremos evaluar
	 * @return resultado de la funcion
	 */
	private FitnessReal funcion1(double parametro) {
		double parte1 = 20;
		double parte2 = Math.E;
		double parte3 = 20 * Math.pow(Math.E, -0.2 * Math.abs(parametro));
		double parte4 = Math.pow(Math.E, Math.cos(2 * Math.PI * parametro));
		return new FitnessReal(parte1 + parte2 - parte3 - parte4);
	}

	@Override
	public boolean getMaximizar() {
		return maximizar;
	}
}
