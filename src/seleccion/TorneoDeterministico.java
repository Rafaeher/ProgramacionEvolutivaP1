package seleccion;

import java.util.ArrayList;
import java.util.Random;
import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import individuo.Individuo;

public class TorneoDeterministico<GenotipoTD extends Genotipo, FenotipoTD extends Fenotipo, FitnessS extends Fitness>
		implements Seleccion<GenotipoTD, FenotipoTD, FitnessS>
{
	
	@Override
	public ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessS>>
	Selecciona(ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessS>> poblacion,
			Configuracion c, boolean maximizar)
	{
		ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessS>> poblacionfinal = new ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessS>>();
		Random r = new Random();
		Individuo<GenotipoTD, FenotipoTD, FitnessS> copia = null;
		while (poblacionfinal.size() < poblacion.size()) {
			int aux = r.nextInt(c.getTamano_poblacion());
			
			copia = poblacion.get(aux);
			Individuo<GenotipoTD, FenotipoTD, FitnessS> individuo1 = 
					new Individuo<GenotipoTD, FenotipoTD, FitnessS>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
			copia = poblacion.get(r.nextInt(c.getTamano_poblacion()));
			Individuo<GenotipoTD, FenotipoTD, FitnessS> individuo2 = 
					new Individuo<GenotipoTD, FenotipoTD, FitnessS>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
			copia = poblacion.get(r.nextInt(c.getTamano_poblacion()));
			Individuo<GenotipoTD, FenotipoTD, FitnessS> individuo3 = 
					new Individuo<GenotipoTD, FenotipoTD, FitnessS>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
			ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessS>> individuosEnElTorneo = new ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessS>>();
			individuosEnElTorneo.add(individuo1);
			individuosEnElTorneo.add(individuo2);
			individuosEnElTorneo.add(individuo3);
			if(maximizar) poblacionfinal.add(algMaximizar(individuosEnElTorneo));
			else poblacionfinal.add(algMinimizar(individuosEnElTorneo));
		}
		
		
		return poblacionfinal;
	}
	
	private Individuo<GenotipoTD, FenotipoTD, FitnessS> algMaximizar(ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessS>> torneo){
		Individuo<GenotipoTD, FenotipoTD, FitnessS> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if(torneo.get(i).compara(individuoMejor) > 0){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}
	private Individuo<GenotipoTD, FenotipoTD, FitnessS> algMinimizar(ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessS>> torneo){
		Individuo<GenotipoTD, FenotipoTD, FitnessS> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if(torneo.get(i).compara(individuoMejor) < 0){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}

}
package seleccion;

import java.util.ArrayList;
import java.util.Random;
import configuracion.Configuracion;
import fenotipo.Fenotipo;
import genotipo.Genotipo;
import individuo.Individuo;

public class TorneoDeterministico<GenotipoTD extends Genotipo, FenotipoTD extends Fenotipo, Fitness extends Comparable<Fitness>>
		implements Seleccion<GenotipoTD, FenotipoTD, Fitness>
{
	
	@Override
	public ArrayList<Individuo<GenotipoTD, FenotipoTD, Fitness>>
	Selecciona(ArrayList<Individuo<GenotipoTD, FenotipoTD, Fitness>> poblacion,
			Configuracion c, boolean maximizar)
	{
		ArrayList<Individuo<GenotipoTD, FenotipoTD, Fitness>> poblacionfinal = new ArrayList<Individuo<GenotipoTD, FenotipoTD, Fitness>>();
		Random r = new Random();
		
		while (poblacionfinal.size() < poblacion.size()) {
			
			Individuo<GenotipoTD, FenotipoTD, Fitness> individuo1 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			Individuo<GenotipoTD, FenotipoTD, Fitness> individuo2 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			Individuo<GenotipoTD, FenotipoTD, Fitness> individuo3 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			
			ArrayList<Individuo<GenotipoTD, FenotipoTD, Fitness>> individuosEnElTorneo = new ArrayList<Individuo<GenotipoTD, FenotipoTD, Fitness>>();
			individuosEnElTorneo.add(individuo1);
			individuosEnElTorneo.add(individuo2);
			individuosEnElTorneo.add(individuo3);
			if(maximizar) poblacionfinal.add(algMaximizar(individuosEnElTorneo));
			else poblacionfinal.add(algMinimizar(individuosEnElTorneo));
		}
		
		
		return poblacionfinal;
	}
	
	private Individuo<GenotipoTD, FenotipoTD, Fitness> algMaximizar(ArrayList<Individuo<GenotipoTD, FenotipoTD, Fitness>> torneo){
		Individuo<GenotipoTD, FenotipoTD, Fitness> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if((Double)torneo.get(i).getFitness() > (Double)individuoMejor.getFitness()){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}
	private Individuo<GenotipoTD, FenotipoTD, Fitness> algMinimizar(ArrayList<Individuo<GenotipoTD, FenotipoTD, Fitness>> torneo){
		Individuo<GenotipoTD, FenotipoTD, Fitness> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if((Double)torneo.get(i).getFitness() < (Double)individuoMejor.getFitness()){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}

}
