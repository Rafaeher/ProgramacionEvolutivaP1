package seleccion;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;
import individuo.Individuo;

public class TorneoProbabilistico <GenotipoTP extends Genotipo, FenotipoTP extends Fenotipo, FitnessTP extends Fitness>
implements Seleccion<GenotipoTP, FenotipoTP, FitnessTP>{

	private double prob = 0.68;

	@Override
	public ArrayList<Individuo<GenotipoTP, FenotipoTP, FitnessTP>>
	Selecciona(ArrayList<Individuo<GenotipoTP, FenotipoTP, FitnessTP>> poblacion,
			Configuracion c, boolean maximizar)
	{
		ArrayList<Individuo<GenotipoTP, FenotipoTP, FitnessTP>> poblacionfinal = new ArrayList<Individuo<GenotipoTP, FenotipoTP, FitnessTP>>();
		Random r = new Random();

		while (poblacionfinal.size() < poblacion.size()) {

			
			Individuo<GenotipoTP, FenotipoTP, FitnessTP> individuo1 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			Individuo<GenotipoTP, FenotipoTP, FitnessTP> individuo2 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();
			Individuo<GenotipoTP, FenotipoTP, FitnessTP> individuo3 = poblacion.get(r.nextInt(c.getTamano_poblacion())).clone();

			ArrayList<Individuo<GenotipoTP, FenotipoTP, FitnessTP>> individuosEnElTorneo = new ArrayList<Individuo<GenotipoTP, FenotipoTP, FitnessTP>>();

			individuosEnElTorneo.add(individuo1);
			individuosEnElTorneo.add(individuo2);
			individuosEnElTorneo.add(individuo3);
			if(maximizar) poblacionfinal.add(algMaximizar(individuosEnElTorneo));
			else poblacionfinal.add(algMinimizar(individuosEnElTorneo));
		}
		
		
		return poblacionfinal;
	}
	
	private Individuo<GenotipoTP, FenotipoTP, FitnessTP> algMaximizar(ArrayList<Individuo<GenotipoTP, FenotipoTP, FitnessTP>> torneo){
		Individuo<GenotipoTP, FenotipoTP, FitnessTP> individuoMejor = torneo.get(0);
		Individuo<GenotipoTP, FenotipoTP, FitnessTP> individuoPeor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if(torneo.get(i).compara(individuoMejor) > 0) // Si es mejor
			{
				individuoMejor = torneo.get(i);
			}
			if(torneo.get(i).compara(individuoPeor) < 0) // Si es peor
			{
				individuoPeor = torneo.get(i);
			}
		}
		Random r = new Random();
		if(r.nextDouble() < prob){
			return individuoMejor;
		}
		else return individuoPeor;
	}
	private Individuo<GenotipoTP, FenotipoTP, FitnessTP> algMinimizar(ArrayList<Individuo<GenotipoTP, FenotipoTP, FitnessTP>> torneo){
		Individuo<GenotipoTP, FenotipoTP, FitnessTP> individuoMejor = torneo.get(0);
		Individuo<GenotipoTP, FenotipoTP, FitnessTP> individuoPeor = torneo.get(0);
		for(int i = 0; i < torneo.size(); i++){
			if(torneo.get(i).compara(individuoMejor) < 0) // Si es peor
			{
				individuoMejor = torneo.get(i);
			}
			if(torneo.get(i).compara(individuoPeor) > 0) // Si es mejor
			{
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
