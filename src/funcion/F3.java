package funcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import individuo.Individuo;

public class F3 <Genotipo> extends Funcion<Genotipo, FenotipoReal, Double> {	

	private boolean maximizar = true;
	
	public F3(ArrayList<Individuo<Genotipo, FenotipoReal, Double>> poblacion, Configuracion configuracion) {
		super(poblacion, configuracion);
	}

	@Override
	public void algEvalua(ArrayList<Individuo<Genotipo, FenotipoReal, Double>> poblacion) {
		Individuo<Genotipo, FenotipoReal, Double> individuo_evaluar = null;
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
	
	private Double funcion3(double parametro1, double parametro2) {
		double parte1 = 21.5;
		double parte2 = parametro1 * Math.sin(4 * Math.PI * parametro1);
		double parte3 = parametro2 * Math.sin(20 * Math.PI * parametro2);
		return parte1 + parte2 + parte3;
	}

	@Override
	public Object calculaLosMejoresDeLaPoblacion(ArrayList<Individuo<Genotipo, FenotipoReal, Double>> poblacion, int tam) {

		TreeMap<Double,Individuo> treeParaOrdenar = new TreeMap<Double,Individuo>(java.util.Collections.reverseOrder());
		for(int i = 0; i < poblacion.size(); i++){
			treeParaOrdenar.put(poblacion.get(i).getFitness(), poblacion.get(i));
		}
		ArrayList<Individuo> solucion = new ArrayList<Individuo>();
		
		int i = 0;
		Iterator it = treeParaOrdenar.entrySet().iterator();
		while (it.hasNext() && i < tam) {
			Map.Entry e = (Map.Entry) it.next();
			Individuo<Genotipo, FenotipoReal, Double> copia = (Individuo<Genotipo, FenotipoReal, Double>) e.getValue();
			Individuo<Genotipo, FenotipoReal, Double> individuoseleccionado = new Individuo<Genotipo, FenotipoReal, Double>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
			solucion.add(individuoseleccionado);
		}
		return solucion;
	}

	@Override
	public boolean getMaximizar()
	{
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
