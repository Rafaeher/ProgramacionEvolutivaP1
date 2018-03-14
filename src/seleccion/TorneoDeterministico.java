package seleccion;

import java.util.ArrayList;
import java.util.Random;
import configuracion.Configuracion;
import genotipo.Genotipo;
import individuo.Individuo;

public class TorneoDeterministico<GenotipoTD extends Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
		implements Seleccion<GenotipoTD, Fenotipo, Fitness>
{
	
	@Override
	public ArrayList<Individuo<GenotipoTD, Fenotipo, Fitness>>
	Selecciona(ArrayList<Individuo<GenotipoTD, Fenotipo, Fitness>> poblacion,
			Configuracion c, boolean maximizar)
	{
		ArrayList<Individuo<GenotipoTD, Fenotipo, Fitness>> poblacionfinal = new ArrayList<Individuo<GenotipoTD, Fenotipo, Fitness>>();
		Random r = new Random();

		/*
		while (poblacionfinal.size() < poblacion.size()) {
			
			Individuo<Genotipo, Fenotipo, Fitness> individuo1 = 
					poblacion.get(r.nextInt(c.getTamano_poblacion()));
			Individuo<Genotipo, Fenotipo, Fitness> individuo2 = 
					poblacion.get(r.nextInt(c.getTamano_poblacion()));
			Individuo<Genotipo, Fenotipo, Fitness> individuo3 = 
					poblacion.get(r.nextInt(c.getTamano_poblacion()));
					
			ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> individuosEnElTorneo = new ArrayList<Individuo<Genotipo, Fenotipo, Fitness>>();
			individuosEnElTorneo.add(individuo1);
			individuosEnElTorneo.add(individuo2);
			individuosEnElTorneo.add(individuo3);
			if(maximizar) poblacionfinal.add(algMaximizar(individuosEnElTorneo));
			else poblacionfinal.add(algMinimizar(individuosEnElTorneo));
		}*/
		
		
		return poblacionfinal;
	}
	
	private Individuo<GenotipoTD, Fenotipo, Fitness> algMaximizar(ArrayList<Individuo<GenotipoTD, Fenotipo, Fitness>> torneo){
		Individuo<GenotipoTD, Fenotipo, Fitness> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if((Double)torneo.get(i).getFitness() > (Double)individuoMejor.getFitness()){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}
	private Individuo<GenotipoTD, Fenotipo, Fitness> algMinimizar(ArrayList<Individuo<GenotipoTD, Fenotipo, Fitness>> torneo){
		Individuo<GenotipoTD, Fenotipo, Fitness> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if((Double)torneo.get(i).getFitness() < (Double)individuoMejor.getFitness()){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}

}
