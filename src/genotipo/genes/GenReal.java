package genotipo.genes;

import java.util.ArrayList;
import java.util.Random;

public class GenReal implements Cloneable
{
    ArrayList<Double> codigo; // El c�digo del gen
    private int longitud;

    /**
     * Constructora a partir de la longitud
     *
     * @param longitud: numero de bits que debe tener el gen
     */
    public GenReal(int longitud)
    {
        codigo = new ArrayList<Double>(longitud);
        this.longitud = longitud;
    }
    
    /**
     * Inicializa aleatoriamente el gen
     */
    public void inicializacionAleatoria()
    {
    	Random r = new Random();
    	
        for(int i = 0; i < longitud; i++)
        {
            codigo.add(r.nextDouble());
        }
    }

    /**
     * Obtiene el c�digo del gen
     *
     * @return codigo: el c�digo del gen
     */
    public ArrayList<Double> getCodigo()
    {
        return (ArrayList<Double>) codigo.clone();
    }

    /**
     * Convierte un c�digo binario a double
     *
     * @param codigo: el c�digo del gen
     * @return resultado: el double equivalente
     */
    public static int genADouble(ArrayList<Double> codigo)
    {
        int resultado = 0;



        return resultado;
    }

    /**
     * Obtiene el tama�o del c�digo
     *
     * @return tama�o del c�digo
     */
    public int getTamGen()
    {
        return codigo.size();
    }
    
    /**
     * Actualiza el valor del c�digo del gen
     * 
     * @param codigo: el c�digo del gen
     */
    public void setCodigo(ArrayList<Double> codigo)
    {
    	this.codigo = codigo;
    }
    
    /**
     * Obtiene el valor i - �simo
     * 
     * @param indiceValor: el �ndice
     * @return el valor i - �simo
     */
    public double getValor(int indiceValor)
    {
    	return codigo.get(indiceValor);
    }
    
    /**
     * Actualiza el valor i - �simo
     * 
     * @param indiceValor: el �ndice
     * @param valor: el valor con el que se quiere actualizar
     */
    public void setBit(int indiceValor, double valor)
    {
    	codigo.set(indiceValor, valor);
    }
    
    @Override
    public Object clone()
    {
    	GenReal clon = new GenReal(longitud);
    	clon.codigo = (ArrayList<Double>) codigo.clone();
    	
    	return clon;
    }
}
