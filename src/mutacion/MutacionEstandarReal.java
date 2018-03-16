package mutacion;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import genotipo.GenotipoReal;

public class MutacionEstandarReal<Fenotipo, Fitness> implements Mutacion<GenotipoReal, Fenotipo, Fitness> {

	@Override
	public void muta(GenotipoReal genotipo, double prob_mutacion) {
		for (int i = 0; i < genotipo.getNumGenes(); i++) {
			Random r = new Random();
			double random = r.nextDouble();
			if (random < prob_mutacion) {

			}
		}

	}

}
