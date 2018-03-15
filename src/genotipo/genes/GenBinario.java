package genotipo.genes;

import java.util.ArrayList;

public class GenBinario //implements Cloneable
{
    ArrayList<Boolean> codigo; // El código del gen
    private int longitud;

    /**
     * Constructora a partir de la longitud
     *
     * @param longitud: numero de bits que debe tener el gen
     */
    public GenBinario(int longitud)
    {
        codigo = new ArrayList<Boolean>(longitud);
        this.longitud = longitud;
    }
    
    /**
     * Inicializa aleatoriamente el gen
     */
    public void inicializacionAleatoria()
    {
        for(int i = 0; i < longitud; i++)
        {
            if (Math.random() <= 0.5)
                codigo.add(true);
            else
                codigo.add(false);
        }
    }

    /**
     * Obtiene el código del gen
     *
     * @return codigo: el código del gen
     */
    public ArrayList<Boolean> getCodigo()
    {
        return (ArrayList<Boolean>) codigo.clone();
    }

    /**
     * Calcula la longitud que debe tener el código del gen para poder almacenar un real
     *
     * @param min valor mínimo que puede tomar el real
     * @param max valor máximo que puede tomar el real
     * @param precision precisión que debe tener el real
     * @return longitud que debe tener el código del gen para poder almacenar el real
     */
    public static int calculaLongitud(final Double min, final Double max, final Double precision)
    {
        return (int) Math.ceil(Math.log(1 + (max - min) / precision) / Math.log(2));
    }

    /**
     * Convierte un código binario a int
     *
     * @param codigo: el código binario
     * @return resultado: el int equivalente
     */
    public static int genAInt(ArrayList<Boolean> codigo)
    {
        int resultado = 0;

        for(int i = 0; i < codigo.size(); i++)
            if(codigo.get(codigo.size() - i - 1))
                resultado += Math.ceil(Math.pow(2, i));

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
    public void setCodigo(ArrayList<Boolean> codigo)
    {
    	this.codigo = codigo;
    }
    
    /**
     * Obtiene el bit i - ésimo
     * 
     * @param indiceBit: el índice
     * @return el bit i - ésimo
     */
    public boolean getBit(int indiceBit)
    {
    	return codigo.get(indiceBit);
    }
    
    /**
     * Actualiza el bit i - ésimo
     * 
     * @param indiceBit: el índice
     * @param valor: el valor con el que se quiere actualizar
     */
    public void setBit(int indiceBit, boolean valor)
    {
    	codigo.set(indiceBit, valor);
    }
    
    @Override
    public Object clone()
    {
    	GenBinario clon = new GenBinario(longitud);
    	clon.codigo = (ArrayList<Boolean>) codigo.clone();
    	
    	return clon;
    }
}
