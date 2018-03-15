package funcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import genotipo.Genotipo;
import individuo.Individuo;

public class F2<GenotipoF2 extends Genotipo> extends Funcion<GenotipoF2, FenotipoReal, Double> {
	
	private boolean maximizar = false;
	
	public F2(ArrayList<Individuo<GenotipoF2, FenotipoReal, Double>> poblacion, Configuracion configuracion) {
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<GenotipoF2, FenotipoReal, Double>> poblacion) {
		Individuo<GenotipoF2, FenotipoReal, Double> individuo_evaluar = null;
		for(int i = 0; i < poblacion.size(); i++)
		{
			individuo_evaluar = poblacion.get(i);
			FenotipoReal fenotipo =(FenotipoReal)individuo_evaluar.getFenotipo();
			double parametro1 = (Double)fenotipo.getCaracteristicas().get(0).getFenotipodelgen();
			double parametro2 = (Double)fenotipo.getCaracteristicas().get(1).getFenotipodelgen();
			individuo_evaluar.setFitness( funcion2(parametro1, parametro2));
		}
	}

	private Double funcion2(double parametro1, double parametro2) {
		double parte1 = (parametro2 + 47);
		double parte2 = Math.sin(Math.sqrt(Math.abs(parametro2 + (parametro1 / 2) + 47)));
		double parte3 = Math.sin(Math.sqrt(Math.abs(parametro1 - (parametro2 + 47))));
		return -parte1 * parte2 - parametro1 * parte3;
	}

	@Override
	public boolean getMaximizar() {
		// TODO Auto-generated method stub
		return maximizar;
	}

	@Override
	public ArrayList<Individuo<GenotipoF2, FenotipoReal, Double>> colocaLaelite(ArrayList<Individuo<GenotipoF2, FenotipoReal, Double>> poblacion,
			ArrayList<Individuo<GenotipoF2, FenotipoReal, Double>> elite) {
		ArrayList<Individuo<GenotipoF2, FenotipoReal, Double>> solucion = new ArrayList<Individuo<GenotipoF2, FenotipoReal, Double>>(
				poblacion);
		/*
		 * Double guarda el fitness integer guarda la posicion en el array El
		 * mapa guarda la poblacion, el 0 es el peor de la poblacion para
		 * sustituirlo por la elite
		 */

		TreeMap<Double, Integer> treeParaOrdenar = new TreeMap<Double, Integer>(java.util.Collections.reverseOrder());
		for (int i = 0; i < poblacion.size(); i++) {
			treeParaOrdenar.put(poblacion.get(i).getFitness(), i);
		}

		int i = 0;
		Iterator it = treeParaOrdenar.entrySet().iterator();
		while (it.hasNext() && i < elite.size()) {
			Map.Entry e = (Map.Entry) it.next();
			Individuo<GenotipoF2, FenotipoReal, Double> copia = elite.get(i);
			Individuo<GenotipoF2, FenotipoReal, Double> individuoseleccionado = new Individuo<GenotipoF2, FenotipoReal, Double>(
					copia.getGenotipo(), copia.getFenotipo(), copia.getFitness());
			solucion.set((int) e.getValue(), individuoseleccionado);

			i = i + 1;
		}
		return solucion;
	}

}
