package mutacion;

import java.util.Random;

import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.GenotipoBinario;

public class MutacionEstandarBinario<FenotipoMEB extends Fenotipo, FitnessMEB extends Fitness> implements Mutacion<GenotipoBinario, FenotipoMEB, FitnessMEB>
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
						genotipo.setBitDeGen(i, j, false);
					else
						genotipo.setBitDeGen(i, j, true);
				}
			}
		}
		
	}
}
