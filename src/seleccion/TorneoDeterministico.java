package seleccion;

import java.util.ArrayList;
import java.util.Random;
import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import individuo.Individuo;

public class TorneoDeterministico<GenotipoTD extends Genotipo, FenotipoTD extends Fenotipo, FitnessTD extends Fitness>
		implements Seleccion<GenotipoTD, FenotipoTD, FitnessTD>
{
	
	@Override
	public ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessTD>>
	Selecciona(ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessTD>> poblacion,
			Configuracion c, boolean maximizar)
	{
		ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessTD>> poblacionfinal = new ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessTD>>();
		Random r = new Random();
		
		while (poblacionfinal.size() < poblacion.size()) {
			
			Individuo<GenotipoTD, FenotipoTD, FitnessTD> individuo1 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			Individuo<GenotipoTD, FenotipoTD, FitnessTD> individuo2 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			Individuo<GenotipoTD, FenotipoTD, FitnessTD> individuo3 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			
			ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessTD>> individuosEnElTorneo = new ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessTD>>();
			individuosEnElTorneo.add(individuo1);
			individuosEnElTorneo.add(individuo2);
			individuosEnElTorneo.add(individuo3);
			if(maximizar) poblacionfinal.add(algMaximizar(individuosEnElTorneo));
			else poblacionfinal.add(algMinimizar(individuosEnElTorneo));
		}
		
		
		return poblacionfinal;
	}
	
	private Individuo<GenotipoTD, FenotipoTD, FitnessTD> algMaximizar(ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessTD>> torneo){
		Individuo<GenotipoTD, FenotipoTD, FitnessTD> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if(torneo.get(i).compara(individuoMejor) > 0){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}
	private Individuo<GenotipoTD, FenotipoTD, FitnessTD> algMinimizar(ArrayList<Individuo<GenotipoTD, FenotipoTD, FitnessTD>> torneo){
		Individuo<GenotipoTD, FenotipoTD, FitnessTD> individuoMejor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if(torneo.get(i).compara(individuoMejor) < 0){
				individuoMejor = torneo.get(i);
			}
		}
		return individuoMejor;
	}

}
