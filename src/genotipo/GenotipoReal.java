package genotipo;

import java.util.ArrayList;

import genotipo.genes.GenReal;

public class GenotipoReal implements Genotipo
{
    ArrayList<GenReal> genes;

    public GenotipoReal() {
    	genes = new  ArrayList<GenReal>();
    }
    
    /**
     * Constructora a partir de los genes
     * 
     * @param genes
     */
    public GenotipoReal(ArrayList<GenReal> genes)
    {
    	this.genes = genes;
    }
    
	public ArrayList<GenReal> getGenes()
	{
		ArrayList<GenReal> genesCopia = new ArrayList<GenReal>();
		
		for(int i = 0; i < genes.size(); i++)
		{
			genesCopia.add((GenReal) genes.get(i).clone());
		}
		
		return genesCopia;
	}

	/**
	 * Obtiene el número de genes que tiene el genotipo
	 *
	 * @return el número de genes que tiene el genotipo
	 */
	public int getNumGenes()
	{
		return genes.size();
	}

	public GenReal getGen(int indice)
	{
		return (GenReal) genes.get(indice).clone();
	}
	
	public double getMaxGen(int indice)
	{
		return genes.get(indice).getMaximo();
	}
	
	public double getMinGen(int indice)
	{
		return genes.get(indice).getMinimo();
	}
	
	public double getValorGen(int indice)
	{
		return genes.get(indice).getValor();
	}
	
	public void setGen(int indice, GenReal gen){
		genes.set(indice, gen);
	}

	/**
	 * Asigna un nuevo valor a los genes;
	 *
	 * @param cromosomasE: genes de entrada
	 */
	public void setGenes(ArrayList<GenReal> genesE)
	{
		genes = genesE;
	}
	
	@Override
	public GenotipoReal clone()
	{
		GenotipoReal clon = new GenotipoReal();
		for(int i = 0; i < genes.size(); i++)
		{
			clon.genes.add((GenReal) genes.get(i).clone());
		}
		
		return clon;
	}
}
