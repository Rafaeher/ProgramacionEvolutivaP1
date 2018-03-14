package mutacion;

import java.util.ArrayList;

import individuo.Individuo;

public interface Mutacion<Genotipo, Fenotipo, Fitness>
{
	/**
	 * Muta un genotio
	 */
	void muta(Genotipo genotipo,double prob_mutacion);
}
