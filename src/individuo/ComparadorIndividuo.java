package individuo;

import java.util.Comparator;

public class ComparadorIndividuo<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>> implements
Comparator<Individuo<Genotipo, Fenotipo, Fitness>>{

	@Override
	public int compare(Individuo<Genotipo, Fenotipo, Fitness> individuo1, Individuo<Genotipo, Fenotipo, Fitness> individuo2)
	{
		return -individuo1.compara(individuo2);
	}

}
