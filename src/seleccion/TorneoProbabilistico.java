package seleccion;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import genotipo.Genotipo;
import individuo.Individuo;

public class TorneoProbabilistico <GenotipoTP extends Genotipo, FenotipoTP extends Fenotipo, Fitness extends Comparable<Fitness>>
implements Seleccion<GenotipoTP, FenotipoTP, Fitness>{

	private double prob = 0.68;

	@Override
	public ArrayList<Individuo<GenotipoTP, FenotipoTP, Fitness>>
	Selecciona(ArrayList<Individuo<GenotipoTP, FenotipoTP, Fitness>> poblacion,
			Configuracion c, boolean maximizar)
	{
		ArrayList<Individuo<GenotipoTP, FenotipoTP, Fitness>> poblacionfinal = new ArrayList<Individuo<GenotipoTP, FenotipoTP, Fitness>>();
		Random r = new Random();

		while (poblacionfinal.size() < poblacion.size()) {
			
			Individuo<GenotipoTP, FenotipoTP, Fitness> individuo1 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			Individuo<GenotipoTP, FenotipoTP, Fitness> individuo2 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			Individuo<GenotipoTP, FenotipoTP, Fitness> individuo3 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();

			ArrayList<Individuo<GenotipoTP, FenotipoTP, Fitness>> individuosEnElTorneo = new ArrayList<Individuo<GenotipoTP, FenotipoTP, Fitness>>();
			individuosEnElTorneo.add(individuo1);
			individuosEnElTorneo.add(individuo2);
			individuosEnElTorneo.add(individuo3);
			if(maximizar) poblacionfinal.add(algMaximizar(individuosEnElTorneo));
			else poblacionfinal.add(algMinimizar(individuosEnElTorneo));
		}
		
		
		return poblacionfinal;
	}
	
	private Individuo<GenotipoTP, FenotipoTP, Fitness> algMaximizar(ArrayList<Individuo<GenotipoTP, FenotipoTP, Fitness>> torneo){
		Individuo<GenotipoTP, FenotipoTP, Fitness> individuoMejor = torneo.get(0);
		Individuo<GenotipoTP, FenotipoTP, Fitness> individuoPeor = torneo.get(0);
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
	private Individuo<GenotipoTP, FenotipoTP, Fitness> algMinimizar(ArrayList<Individuo<GenotipoTP, FenotipoTP, Fitness>> torneo){
		Individuo<GenotipoTP, FenotipoTP, Fitness> individuoMejor = torneo.get(0);
		Individuo<GenotipoTP, FenotipoTP, Fitness> individuoPeor = torneo.get(0);
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
