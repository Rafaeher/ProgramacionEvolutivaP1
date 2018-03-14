package decodificador;

public class DecodificadorInicial extends DecodificadorEslabon
{
    @Override
    protected Object decodifica(Object genotipo, Object fenotipo)
    {
        return (new DecodificadorBinarioReal().decodifica(genotipo, fenotipo));
    }
}
