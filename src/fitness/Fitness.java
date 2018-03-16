package fitness;

public interface Fitness extends Comparable<Fitness>
{
	public Fitness clone();
	
	public double getValorReal();
}
