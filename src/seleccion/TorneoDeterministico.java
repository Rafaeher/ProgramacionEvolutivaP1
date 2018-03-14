package seleccion;

import java.util.ArrayList;
import java.util.Random;
import configuracion.Configuracion;
import individuo.Individuo;

public class TorneoDeterministico<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>>
		implements Seleccion<Genotipo, Fenotipo, Fitness>
{
	
	@Override
	public ArrayList<Individuo<Genotipo, Fenotipo, Fitness>>
	Selecciona(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacion,
			Configuracion c, boolean maximizar)
	{
		ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> poblacionfinal = new ArrayList<Individuo<Genotipo, Fenotipo, Fitness>>();
		Random r = new Random();
		Individuo<Genotipo, Fenotipo, Fitness> copia = null;
		while (poblacionfinal.size() < poblacion.size()) {
			int aux = r.nextInt(c.getTamano_poblacion());
			
			copia = poblacion.get(aux);
			Individuo<Genotipo, Fenotipo, Fitness> individuo1 = 
					new Individuo<Genotipo, Fenotipo, Fitness>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
			copia = poblacion.get(r.nextInt(c.getTamano_poblacion()));
			Individuo<Genotipo, Fenotipo, Fitness> individuo2 = 
					new Individuo<Genotipo, Fenotipo, Fitness>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
			copia = poblacion.get(r.nextInt(c.getTamano_poblacion()));
			Individuo<Genotipo, Fenotipo, Fitness> individuo3 = 
					new Individuo<Genotipo, Fenotipo, Fitness>(copia.getGenotipo(),copia.getFenotipo(),copia.getFitness());
			ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> individuosEnElTorneo = new ArrayList<Individuo<Genotipo, Fenotipo, Fitness>>();
			individuosEnElTorneo.add(individuo1);
			individuosEnElTorneo.add(individuo2);
			individuosEnElTorneo.add(individuo3);
			if(maximizar) poblacionfinal.add(algMaximizar(individuosEnElTorneo));
			else poblacionfinal.add(algMinimizar(individuosEnElTorneo));
		}
		
		
		return poblacionfinal;
	}
	
	private Individuo<Genotipo, Fenotipo, Fitness> algMaximizar(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> torneo){
		Individuo<Genotipo, Fenotipo, Fitness> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if((Double)torneo.get(i).getFitness() > (Double)individuoMejor.getFitness()){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}
	private Individuo<Genotipo, Fenotipo, Fitness> algMinimizar(ArrayList<Individuo<Genotipo, Fenotipo, Fitness>> torneo){
		Individuo<Genotipo, Fenotipo, Fitness> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if((Double)torneo.get(i).getFitness() < (Double)individuoMejor.getFitness()){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}

}
