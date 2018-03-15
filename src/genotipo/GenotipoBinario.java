package genotipo;

import genotipo.genes.GenBinario;

import java.util.ArrayList;

public class GenotipoBinario
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
	 * Obtiene el n�mero de cromosomas que tiene el genotipo
	 *
	 * @return el n�mero de cromsomas que tiene el genotipo
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
	
	/**
	 * Devuelve el tama�o del gen i - �simo
	 * 
	 * @indice
	 * @return
	 */
	public int getTamGen(int indice)
	{
		return genes.get(indice).getTamGen();
	}
	
	/**
	 * Devuelve el bit i - �simo del gen j - �simo
	 * 
	 * @param indiceGen
	 * @param indiceBit
	 * @return
	 */
	public Boolean getBitDeGen(int indiceGen, int indiceBit)
	{
		return genes.get(indiceGen).getBit(indiceBit);
	}
	
	/**
	 * Actualiza el valor del bit i - �simo del gen j - �simo
	 * 
	 * @param indiceGen
	 * @param indiceBit
	 * @param valor
	 */
	public void setBitDeGen(int indiceGen, int indiceBit, boolean valor)
	{
		genes.get(indiceGen).setBit(indiceBit, valor);
	}

}
