package individuo;

import java.util.Comparator;

import fenotipo.Fenotipo;
import genotipo.Genotipo;

public class ComparadorIndividuo
<GenotipoCI extends Genotipo, FenotipoCI extends Fenotipo, Fitness extends Comparable<Fitness>>
implements
Comparator<Individuo<GenotipoCI, FenotipoCI, Fitness>>{

	private boolean maximizar;
	
	public ComparadorIndividuo(boolean maximizarE)
	{
		maximizar = maximizarE;
	}
	
	@Override
	public int compare(Individuo<GenotipoCI, FenotipoCI, Fitness> individuo1, Individuo<GenotipoCI, FenotipoCI, Fitness> individuo2)
	{
		return -individuo1.compara(individuo2);
	}

}
