package decodificador;

public class DecodificadorInicial extends DecodificadorEslabon
{
    @Override
    protected void decodifica(Object genotipo, Object fenotipo)
    {
        new DecodificadorBinarioReal().decodifica(genotipo, fenotipo);
    }
}
