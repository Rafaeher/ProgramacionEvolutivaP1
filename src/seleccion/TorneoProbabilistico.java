package seleccion;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import genotipo.Genotipo;
import individuo.Individuo;

public class TorneoProbabilistico <GenotipoTP extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
implements Seleccion<GenotipoTP, Fenotipo, Fitness>{

	private double prob = 0.68;

	@Override
	public ArrayList<Individuo<GenotipoTP, Fenotipo, Fitness>>
	Selecciona(ArrayList<Individuo<GenotipoTP, Fenotipo, Fitness>> poblacion,
			Configuracion c, boolean maximizar)
	{
		ArrayList<Individuo<GenotipoTP, Fenotipo, Fitness>> poblacionfinal = new ArrayList<Individuo<GenotipoTP, Fenotipo, Fitness>>();
		Random r = new Random();

		while (poblacionfinal.size() < poblacion.size()) {
			Individuo<GenotipoTP, Fenotipo, Fitness> individuo1 = 
					poblacion.get(r.nextInt(c.getTamano_poblacion()));
			Individuo<GenotipoTP, Fenotipo, Fitness> individuo2 = 
					poblacion.get(r.nextInt(c.getTamano_poblacion()));
			Individuo<GenotipoTP, Fenotipo, Fitness> individuo3 = 
					poblacion.get(r.nextInt(c.getTamano_poblacion()));
			ArrayList<Individuo<GenotipoTP, Fenotipo, Fitness>> individuosEnElTorneo = new ArrayList<Individuo<GenotipoTP, Fenotipo, Fitness>>();
			individuosEnElTorneo.add(individuo1);
			individuosEnElTorneo.add(individuo2);
			individuosEnElTorneo.add(individuo3);
			if(maximizar) poblacionfinal.add(algMaximizar(individuosEnElTorneo));
			else poblacionfinal.add(algMinimizar(individuosEnElTorneo));
		}
		
		
		return poblacionfinal;
	}
	
	private Individuo<GenotipoTP, Fenotipo, Fitness> algMaximizar(ArrayList<Individuo<GenotipoTP, Fenotipo, Fitness>> torneo){
		Individuo<GenotipoTP, Fenotipo, Fitness> individuoMejor = torneo.get(0);
		Individuo<GenotipoTP, Fenotipo, Fitness> individuoPeor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if((Double)torneo.get(i).getFitness() > (Double)individuoMejor.getFitness()){
				individuoMejor = torneo.get(i);
			}
			if((Double)torneo.get(i).getFitness() < (Double)individuoPeor.getFitness()){
				individuoPeor = torneo.get(i);
			}
		}
		Random r = new Random();
		if(r.nextDouble() < prob){
			return individuoMejor;
		}
		else return individuoPeor;
	}
	private Individuo<GenotipoTP, Fenotipo, Fitness> algMinimizar(ArrayList<Individuo<GenotipoTP, Fenotipo, Fitness>> torneo){
		Individuo<GenotipoTP, Fenotipo, Fitness> individuoMejor = torneo.get(0);
		Individuo<GenotipoTP, Fenotipo, Fitness> individuoPeor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if((Double)torneo.get(i).getFitness() < (Double)individuoMejor.getFitness()){
				individuoMejor = torneo.get(i);
			}
			if((Double)torneo.get(i).getFitness() > (Double)individuoPeor.getFitness()){
				individuoPeor = torneo.get(i);
			}
		}
		Random r = new Random();
		if(r.nextDouble() < prob){
			return individuoMejor;
		}
		else return individuoPeor;
	}

}
