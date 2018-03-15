package reproduccion.binario;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import individuo.Individuo;
import reproduccion.Reproduccion;
import genotipo.GenotipoBinario;

public class UniformeBinario<Fenotipo, Fitness extends Comparable<Fitness>> implements Reproduccion<GenotipoBinario, Fenotipo, Fitness>
{

	private static double P_CRUCE_GEN = 0.5; 
	
	@Override
	public ArrayList<Individuo<GenotipoBinario, Fenotipo, Fitness>>
	reproduce(
			ArrayList<Individuo<GenotipoBinario, Fenotipo, Fitness>> poblacion,
			Configuracion config)
	{
		
		Random rand = new Random();
		
		if (rand.nextDouble() < config.getCruceporcentaje())
		{
			int indIndividuo1 = rand.nextInt(poblacion.size()),
					indIndividuo2 = rand.nextInt(poblacion.size());
			
			GenotipoBinario genotipo1 = (GenotipoBinario) poblacion.get(indIndividuo1).getGenotipo(),
					genotipo2 = (GenotipoBinario) poblacion.get(indIndividuo2).getGenotipo();
			
			int numGenes = genotipo1.getNumGenes();
						
			int indGen1 = rand.nextInt(numGenes), indGen2 = rand.nextInt(numGenes);
			
			for(int indBit = 0; indBit < genotipo1.getTamGen(); indBit++)
			{
				if (rand.nextDouble() < P_CRUCE_GEN)
				{
					Boolean aux = genotipo1.getBitDeGen(indGen1, indBit);
					genotipo1.setBitDeGen(indGen1, indBit, genotipo2.getBitDeGen(indGen2, indBit));
					genotipo2.setBitDeGen(indGen2, indBit, aux);
				}
			}
		}
		
		return poblacion;
	}
	
	

}
