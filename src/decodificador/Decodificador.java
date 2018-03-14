package decodificador;


public class Decodificador
{
    /**
     * Decodifica el genotipo en el fenotipo apropiado
     *
     * @param genotipo el genotipo a decodificar
     * @param fenotipo el fenotipo decodificado
     */
    public static Object decodifica(Object genotipo, Object fenotipo)
    {
        return (new DecodificadorInicial()).decodifica(genotipo, fenotipo);
    }
}
