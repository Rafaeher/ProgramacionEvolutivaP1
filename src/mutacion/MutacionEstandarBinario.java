package mutacion;

import java.util.Random;

import genotipo.GenotipoBinario;

public class MutacionEstandarBinario<Fenotipo,Fitness> implements Mutacion<GenotipoBinario,Fenotipo,Fitness>
{
	
	@Override
	public void muta(GenotipoBinario genotipo, double prob_mutacion)
	{
/*
		Random r = new Random();
		double random = r.nextDouble();
		if (random < prob_mutacion)
		{	

			int i = Math.abs(r.nextInt()) % genotipo.getNumGenes();
			int j = Math.abs(r.nextInt()) % genotipo.getGen(i).getCodigo().size();

			if(genotipo.getGen(i).getCodigo().get(j))
				genotipo.getGen(i).getCodigo().set(j, false);
			else
				genotipo.getGen(i).getCodigo().set(j, true);
			
		}
		*/
		
		for(int i = 0; i < genotipo.getNumGenes(); i++){
			for(int j = 0; j < genotipo.getGen(i).getTamGen();j++){
				Random r = new Random();
				double random = r.nextDouble();
				if (random < prob_mutacion){
					if(genotipo.getGen(i).getCodigo().get(j))
						genotipo.getGen(i).getCodigo().set(j, false);
					else
						genotipo.getGen(i).getCodigo().set(j, true);
				}
			}
		}
		
	}
}
