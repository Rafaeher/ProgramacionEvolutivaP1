package genotipo;

import java.util.ArrayList;

import genotipo.genes.GenBinario;
import genotipo.genes.GenReal;

public class GenotipoReal implements Genotipo
{
    ArrayList<GenReal> genes;

    public GenotipoReal() {
    	genes = new  ArrayList<Double>();
    }
    
    /**
     * Constructora a partir de los genes
     * 
     * @param genes
     */
    public GenotipoReal(ArrayList<Double> genes)
    {
    	this.genes = genes;
    }
    
	public ArrayList<Double> getGenes()
	{
		ArrayList<Double> genesCopia = new ArrayList<Double>();
		
		for(int i = 0; i < genes.size(); i++)
		{
			genesCopia.add(genes.get(i));
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

	public Double getGen(int indice)
	{
		return genes.get(indice);
	}
	
	public void setGen(int indice, Double gen){
		genes.set(indice, gen);
	}

	/**
	 * Asigna un nuevo valor a los genes;
	 *
	 * @param cromosomasE: genes de entrada
	 */
	public void setGenes(ArrayList<Double> genesE)
	{
		genes = genesE;
	}
	
	@Override
	public GenotipoReal clone()
	{
		GenotipoReal clon = new GenotipoReal();
		for(int i = 0; i < genes.size(); i++)
		{
			clon.genes.add(i, genes.get(i));
		}
		
		return clon;
	}
}
