package decodificador;

import fenotipo.FenotipoReal;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;

import java.util.ArrayList;

public class DecodificadorBinarioReal extends DecodificadorEslabon
{
    @Override
    protected Object decodifica(Object genotipoE, Object fenotipoE)
    {
    	ArrayList<Double> solucion = new ArrayList<Double>();
        if (genotipoE instanceof GenotipoBinario && fenotipoE instanceof FenotipoReal)
        {
            FenotipoReal fenotipo = (FenotipoReal) fenotipoE;
            GenotipoBinario genotipo = (GenotipoBinario) genotipoE;
            ArrayList<Double> caracteristicas = new ArrayList<>();


            for(int i = 0; i < genotipo.getNumGenes(); i++)
            {
                int genesInt = GenBinario.genAInt(genotipo.getGen(i).getGen());
                int numGenes = genotipo.getGen(i).getTamGen();
                
                solucion.add(fenotipo.getCaracteristicas().get(i).getMin() + genesInt * (fenotipo.getCaracteristicas().get(i).getMax() - fenotipo.getCaracteristicas().get(i).getMin()) / (Math.pow(2, numGenes) - 1));
            }

            return solucion;
        }
        else
            return (new DecodificadorFinal()).decodifica(genotipoE, fenotipoE);

    }
}
