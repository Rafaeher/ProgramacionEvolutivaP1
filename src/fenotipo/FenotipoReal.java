package fenotipo;

import java.util.ArrayList;

import fenotipo.caracteristica.FenotipoGen;

public class FenotipoReal
{
    ArrayList<FenotipoGen> caracteristicas; // Las características del fenotipo



    /**
     * Constructora
     *
     * @param minE: el valor mínimo que puede tomar una característica
     * @param maxE: el valor máximo que puede tomar una característica
     * @param precisionE: la precisión que deben tener las características
     */


    /**
     * Constructora a partir del número de características
     *
     * @param n: número de características
     * @param minE: el valor mínimo que puede tomar una característica
     * @param maxE: el valor máximo que puede tomar una característica
     * @param precisionE: la precisión que deben tener las características
     */
    public FenotipoReal()
    {
        caracteristicas = new ArrayList<FenotipoGen>();
        
    }

    /**
     * Inicializa los min, max y precision
     *
     * @param minE: el valor mínimo que puede tomar una característica
     * @param maxE: el valor máximo que puede tomar una característica
     * @param precisionE: la precisión que deben tener las características
     */
  

    /**
     * Obtiene las características del fenotipo
     *
     * @return caracteristicas: las características del fenotipo
     */
    public ArrayList<FenotipoGen> getCaracteristicas()
    {
        return caracteristicas;
    }

    /**
     * Asigna un nuevo valor a las características del fenotipo
     *
     * @param caracteristicasE: las características de entrada
     */
    public void setCaracteristicas(ArrayList<FenotipoGen> caracteristicasE)
    {
        caracteristicas = caracteristicasE;
    }

    /**
     * Obtiene el valor máximo que puede tomar una característica
     *
     * @return max: el valor máximo que puede tomar una característica
     */

}
