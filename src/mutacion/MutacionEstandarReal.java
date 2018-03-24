package mutacion;

import java.util.Random;

import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.GenotipoReal;
import genotipo.genes.GenReal;

public class MutacionEstandarReal<FenotipoMER extends Fenotipo, FitnessMER extends Fitness> implements Mutacion<GenotipoReal, FenotipoMER, FitnessMER> {

	@Override
	public void muta(GenotipoReal genotipo, double prob_mutacion) {
		for (int i = 0; i < genotipo.getNumGenes(); i++) {
			Random r = new Random();
			double random = r.nextDouble();
			if (random < prob_mutacion) {
				Double genotipoNuevo = r.nextDouble ()*(genotipo.getMaxGen(i) - genotipo.getMinGen(i))
						+ genotipo.getMinGen(i);
				GenReal gen = new GenReal();
				gen.setValor(genotipoNuevo);
				genotipo.setGen(i, gen);
			}
		}

	}

}
