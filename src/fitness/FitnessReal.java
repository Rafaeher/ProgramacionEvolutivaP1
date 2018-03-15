package fitness;

public class FitnessReal implements Fitness
{
	Double valor;
	
	public FitnessReal(double valorE)
	{
		valor = valorE;
	}
	
	@Override
	public int compareTo(Fitness otro)
	{
		if (otro instanceof FitnessReal)
		{
			return valor.compareTo(((FitnessReal) otro).valor);
		}
		else
		{
			System.err.println("No se puede comparar " + getClass().getName() + " con " + otro.getClass().getName());
			return 2;
		}
	}

	@Override
	public Fitness clone()
	{
		return new FitnessReal(valor);
	}
	
	public double getValor()
	{
		return valor;
	}
}
