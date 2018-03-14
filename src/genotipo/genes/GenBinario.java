package genotipo.genes;

import java.util.ArrayList;

public class GenBinario
{
    ArrayList<Boolean> gen; // Los genes del cromosoma
    private int longitud;

    /**
     * Constructora a partir de la longitud
     *
     * @param longitud: número de genes que debe tener el cromosoma
     */
    public GenBinario(int longitud)
    {
        gen = new ArrayList<Boolean>(longitud);
        this.longitud = longitud;
    }

    /**
     * Inicializa aleatoriamente el cromosoma
     */
    public void inicializacionAleatoria()
    {
        for(int i = 0; i < longitud; i++)
        {
            if (Math.random() <= 0.5)
                gen.add(true);
            else
                gen.add(false);
        }
    }

    /**
     * Obtiene los genes del cromosoma
     *
     * @return genes: los genes del cromosoma
     */
    public ArrayList<Boolean> getGen()
    {
        return gen;
    }

    /**
     * Calcula la longitud que debe tener el cromosoma para poder almacenar un real
     *
     * @param min valor mínimo que puede tomar el real
     * @param max valor máximo que puede tomar el real
     * @param precision precisión que debe tener el real
     * @return longitud que debe tener el cromosoma para poder almacenar el real
     */
    public static int calculaLongitud(final Double min, final Double max, final Double precision)
    {
        return (int) Math.ceil(Math.log(1 + (max - min) / precision) / Math.log(2));
    }

    /**
     * Convierte genes a un int
     *
     * @param genes: los genes
     * @return resultado: el int equivalente
     */
    public static int genAInt(ArrayList<Boolean> gen)
    {
        int resultado = 0;

        for(int i = 0; i < gen.size(); i++)
            if(gen.get(gen.size() - i - 1))
                resultado += Math.ceil(Math.pow(2, i));

        return resultado;
    }

    /**
     * Obtiene el número de genes
     *
     * @return número d egenes
     */
    public int getTamGen()
    {
        return gen.size();
    }
    public void setGen(ArrayList<Boolean> gen) {
    	this.gen = gen;
    }
}
