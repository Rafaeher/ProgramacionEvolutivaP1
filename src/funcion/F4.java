package funcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import configuracion.Configuracion;
import fenotipo.FenotipoReal;
import individuo.Individuo;

public class F4 <Genotipo> extends Funcion<Genotipo, FenotipoReal, Double>{

	private boolean maximizar = false;
	
	public F4(ArrayList<Individuo<Genotipo, FenotipoReal, Double>> poblacion, Configuracion configuracion) {
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
			System.out.println("Sol f4 " + funcion4(parametro1, parametro2));
			individuo_evaluar.setFitness( funcion4(parametro1, parametro2));
		}
		//return individuo_evaluar.getFitness();
		
	}
	private Double funcion4(double parametro1, double parametro2) {
		return formula(parametro1) * formula(parametro2);
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
	public Object calculaLosMejoresDeLaPoblacion(ArrayList<Individuo<Genotipo, FenotipoReal, Double>> poblacion, int tam) {

		TreeMap<Double,Individuo> treeParaOrdenar = new TreeMap<Double,Individuo>();
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
		// TODO Auto-generated method stub
		return null;
	}

}
