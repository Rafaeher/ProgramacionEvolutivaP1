package decodificador;


public class Decodificador
{
    /**
     * Decodifica el genotipo en el fenotipo apropiado
     *
     * @param genotipo el genotipo a decodificar
     * @param fenotipo el fenotipo decodificado
     */
    public static void decodifica(Object genotipo, Object fenotipo)
    {
        new DecodificadorInicial().decodifica(genotipo, fenotipo);
    }
}
