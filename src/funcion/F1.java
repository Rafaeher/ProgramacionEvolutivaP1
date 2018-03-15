package funcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import fenotipo.caracteristica.FenotipoGen;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import individuo.Individuo;

public class F1<Genotipo> extends Funcion<Genotipo, FenotipoReal, Double> {

	private boolean maximizar = true;

	public F1(ArrayList<Individuo<Genotipo, FenotipoReal, Double>> poblacion, Configuracion configuracion)

	{
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<Genotipo, FenotipoReal, Double>> poblacion) {

		Individuo<Genotipo, FenotipoReal, Double> individuo_evaluar = null;
		for (int i = 0; i < poblacion.size(); i++) {
			individuo_evaluar = poblacion.get(i);
			FenotipoReal fenotipo = individuo_evaluar.getFenotipo();
			double parametro1 = (Double) fenotipo.getCaracteristicas().get(0).getFenotipodelgen();
			individuo_evaluar.setFitness(funcion1(parametro1));
		}

		// return individuo_evaluar.getFitness();
	}

	/**
	 * funcion que tiene los calculos propios de la funcion
	 * 
	 * @param fenotipo:
	 *            fenotipo del individuo que queremos evaluar
	 * @return resultado de la funcion
	 */
	private Double funcion1(double parametro) {
		double parte1 = 20;
		double parte2 = Math.E;
		double parte3 = 20 * Math.pow(Math.E, -0.2 * Math.abs(parametro));
		double parte4 = Math.pow(Math.E, Math.cos(2 * Math.PI * parametro));
		return parte1 + parte2 - parte3 - parte4;
	}

	@Override
	public boolean getMaximizar() {
		return maximizar;
	}

	@Override
	public Object colocaLaelite(ArrayList<Individuo<Genotipo, FenotipoReal, Double>> poblacion,
			ArrayList<Individuo<Genotipo, FenotipoReal, Double>> elite) {
		ArrayList<Individuo<Genotipo, FenotipoReal, Double>> solucion = new ArrayList<Individuo<Genotipo, FenotipoReal, Double>>(
				poblacion);
		/*
		 * Double guarda el fitness integer guarda la posicion en el array El
		 * mapa guarda la poblacion, el 0 es el peor de la poblacion para
		 * sustituirlo por la elite
		 */

		TreeMap<Double, Integer> treeParaOrdenar = new TreeMap<Double, Integer>();
		for (int i = 0; i < poblacion.size(); i++) {
			treeParaOrdenar.put(poblacion.get(i).getFitness(), i);
		}

		int i = 0;
		Iterator it = treeParaOrdenar.entrySet().iterator();
		while (it.hasNext() && i < elite.size()) {
			Map.Entry e = (Map.Entry) it.next();
			Individuo<Genotipo, FenotipoReal, Double> copia = elite.get(i);
			Individuo<Genotipo, FenotipoReal, Double> individuoseleccionado = new Individuo<Genotipo, FenotipoReal, Double>(
					copia.getGenotipo(), copia.getFenotipo(), copia.getFitness());
			solucion.set((int) e.getValue(), individuoseleccionado);

			i = i + 1;
		}
		return solucion;
	}

}
