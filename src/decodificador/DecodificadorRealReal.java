package decodificador;

import fenotipo.FenotipoReal;
import genotipo.GenotipoReal;

public class DecodificadorRealReal extends DecodificadorEslabon {

	@Override
	protected void decodifica(Object genotipoE, Object fenotipoE)
	{
		if (genotipoE instanceof GenotipoReal && fenotipoE instanceof FenotipoReal)
		{
            FenotipoReal fenotipo = (FenotipoReal) fenotipoE;
            GenotipoReal genotipo = (GenotipoReal) genotipoE;
            
            for(int i = 0; i < genotipo.getNumGenes(); i++)
            {
            	fenotipo.setFenotipoDelGen(i, genotipo.getValorGen(i));      
            }
		}
		else
			(new DecodificadorFinal()).decodifica(genotipoE, fenotipoE);

	}

}
