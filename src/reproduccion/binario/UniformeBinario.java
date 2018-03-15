package reproduccion.binario;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import fenotipo.Fenotipo;
import fitness.Fitness;
import individuo.Individuo;
import reproduccion.Reproduccion;
import genotipo.GenotipoBinario;

public class UniformeBinario<FenotipoUB extends Fenotipo, FitnessUB extends Fitness> implements Reproduccion<GenotipoBinario, FenotipoUB, FitnessUB>
{

	private static double P_CRUCE_GEN = 0.5; 
	
	@Override
	public ArrayList<Individuo<GenotipoBinario, FenotipoUB, FitnessUB>>
	reproduce(
			ArrayList<Individuo<GenotipoBinario, FenotipoUB, FitnessUB>> poblacion,
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
						
			int indGen = rand.nextInt(numGenes);
			int indBit;
			try
			{

			for(indBit = 0; indBit < genotipo1.getTamGen(indGen); indBit++)
			{
				if (rand.nextDouble() < P_CRUCE_GEN)
				{
					Boolean aux = genotipo1.getBitDeGen(indGen, indBit);
					genotipo1.setBitDeGen(indGen, indBit, genotipo2.getBitDeGen(indGen, indBit));
					genotipo2.setBitDeGen(indGen, indBit, aux);
				}
			}
			}
			catch(Exception e)
			{
				System.out.println("hi");
			}
		}
		
		return poblacion;
	}
	
	

}
