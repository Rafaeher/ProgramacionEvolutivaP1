package genotipo.genes;

import java.util.ArrayList;
import java.util.Random;

public class GenReal implements Cloneable
{
    ArrayList<Double> codigo; // El código del gen
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
     * Obtiene el código del gen
     *
     * @return codigo: el código del gen
     */
    public ArrayList<Double> getCodigo()
    {
        return (ArrayList<Double>) codigo.clone();
    }

    /**
     * Convierte un código binario a double
     *
     * @param codigo: el código del gen
     * @return resultado: el double equivalente
     */
    public static int genADouble(ArrayList<Double> codigo)
    {
        int resultado = 0;



        return resultado;
    }

    /**
     * Obtiene el tamaño del código
     *
     * @return tamaño del código
     */
    public int getTamGen()
    {
        return codigo.size();
    }
    
    /**
     * Actualiza el valor del código del gen
     * 
     * @param codigo: el código del gen
     */
    public void setCodigo(ArrayList<Double> codigo)
    {
    	this.codigo = codigo;
    }
    
    /**
     * Obtiene el valor i - ésimo
     * 
     * @param indiceValor: el índice
     * @return el valor i - ésimo
     */
    public double getValor(int indiceValor)
    {
    	return codigo.get(indiceValor);
    }
    
    /**
     * Actualiza el valor i - ésimo
     * 
     * @param indiceValor: el índice
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
