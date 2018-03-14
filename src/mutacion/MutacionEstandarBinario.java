package mutacion;

import java.util.Random;

import genotipo.GenotipoBinario;

public class MutacionEstandarBinario<Genotipo,Fenotipo,Fitness> implements Mutacion<GenotipoBinario,Fenotipo,Fitness>
{
	
	@Override
	public void muta(GenotipoBinario genotipo, double prob_mutacion)
	{

		Random r = new Random();
		double random = r.nextDouble();
		if (random < prob_mutacion)
		{	

			int i = Math.abs(r.nextInt()) % genotipo.getNumGenes();
			int j = Math.abs(r.nextInt()) % genotipo.getGen(i).getGen().size();

			if(genotipo.getGen(i).getGen().get(j))
				genotipo.getGen(i).getGen().set(j, false);
			else
				genotipo.getGen(i).getGen().set(j, true);
			
		}
		
	}
}
