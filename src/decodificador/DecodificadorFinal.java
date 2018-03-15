package decodificador;

public class DecodificadorFinal extends DecodificadorEslabon
{
    @Override
    protected void decodifica(Object genotipo, Object fenotipo)
    {
        throw new IllegalArgumentException("No existe un decodificador del genotipo al fenotipo indicados.");
    }
}
