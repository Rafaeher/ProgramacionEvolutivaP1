package fenotipo;

import java.util.ArrayList;

import fenotipo.caracteristica.FenotipoGenReal;

public class FenotipoReal implements Fenotipo
{
    ArrayList<FenotipoGenReal> caracteristicas; // Las características del fenotipo


    /**
     * 
     */
    public FenotipoReal()
    {
        caracteristicas = new ArrayList<FenotipoGenReal>();
        
    }
    
    public FenotipoGenReal get(int indice)
    {
    	return (FenotipoGenReal) caracteristicas.get(indice).clone();
    }
  

    /**
     * Obtiene las características del fenotipo
     *
     * @return caracteristicas: las características del fenotipo
     */
    public ArrayList<FenotipoGenReal> getCaracteristicas()
    {
        return caracteristicas;
    }

    /**
     * Asigna un nuevo valor a las características del fenotipo
     *
     * @param caracteristicasE: las características de entrada
     */
    public void setCaracteristicas(ArrayList<FenotipoGenReal> caracteristicasE)
    {
        caracteristicas = caracteristicasE;
    }

    /**
     * 
     * @param indice: el �ndice de la caracter�stica
     * @param valor: el nuevo valor
     */
    public void setFenotipoDelGen(int indice, double valor)
    {
    	FenotipoGenReal fenotipoGen = caracteristicas.get(indice);
    	fenotipoGen.setFenotipodelgen(valor);
    }
    
    @Override
    public FenotipoReal clone()
    {
    	FenotipoReal clon = new FenotipoReal();
    	for(int i = 0; i < caracteristicas.size(); i++)
    		clon.caracteristicas.set(i, (FenotipoGenReal) caracteristicas.get(i).clone());
    	
    	return clon;
    }

}
