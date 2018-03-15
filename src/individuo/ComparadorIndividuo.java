package individuo;

import java.util.Comparator;

import fenotipo.Fenotipo;
import fitness.Fitness;
import genotipo.Genotipo;

public class ComparadorIndividuo
<GenotipoCI extends Genotipo, FenotipoCI extends Fenotipo, FitnessCI extends Fitness>
implements
Comparator<Individuo<GenotipoCI, FenotipoCI, FitnessCI>>{

	private boolean maximizar;
	
	public ComparadorIndividuo(boolean maximizarE)
	{
		maximizar = maximizarE;
	}
	
	@Override
	public int compare(Individuo<GenotipoCI, FenotipoCI, FitnessCI> individuo1, Individuo<GenotipoCI, FenotipoCI, FitnessCI> individuo2)
	{
		if (maximizar)
		{
			return -individuo1.compara(individuo2);
		}
		else
		{
			return individuo1.compara(individuo2);
		}
	}

}
