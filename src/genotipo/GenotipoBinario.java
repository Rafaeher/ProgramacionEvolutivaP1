package genotipo;

import genotipo.genes.GenBinario;

import java.util.ArrayList;

public class GenotipoBinario implements Genotipo
{

    ArrayList<GenBinario> genes;

	/**
	 * Obtiene los cromosomas
	 *
	 * @return cromosomas: los cromosomas
	 */
    public GenotipoBinario(ArrayList<GenBinario> genes) {
    	this.genes = genes;
    }
	public ArrayList<GenBinario> getGenes()
	{
		return genes;
	}

	/**
	 * Obtiene el número de cromosomas que tiene el genotipo
	 *
	 * @return el número de cromsomas que tiene el genotipo
	 */
	public int getNumGenes()
	{
		return genes.size();
	}

	public GenBinario getGen(int indice)
	{
		return genes.get(indice);
	}
	public void setGen(int indice, GenBinario gen){
		genes.add(indice, gen);
	}

	/**
	 * Asigna un nuevo valor a los cromosomas;
	 *
	 * @param cromosomasE: cromosomas de entrada
	 */
	public void setGenes(ArrayList<GenBinario> genesE)
	{
		genes = genesE;
	}
	

	@Override
	public Genotipo clone()
	{	
		@SuppressWarnings("unchecked")
		ArrayList<GenBinario> genesClone = (ArrayList<GenBinario>) genes.clone();
		return new GenotipoBinario(genesClone);
	}

}
