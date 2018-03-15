package decodificador;

import fenotipo.FenotipoReal;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;

import java.util.ArrayList;

public class DecodificadorBinarioReal extends DecodificadorEslabon
{
    @Override
    protected void decodifica(Object genotipoE, Object fenotipoE)
    {
        if (genotipoE instanceof GenotipoBinario && fenotipoE instanceof FenotipoReal)
        {
            FenotipoReal fenotipo = (FenotipoReal) fenotipoE;
            GenotipoBinario genotipo = (GenotipoBinario) genotipoE;
            ArrayList<Double> caracteristicas = new ArrayList<>();


            for(int i = 0; i < genotipo.getNumGenes(); i++)
            {
                int genesInt = GenBinario.genAInt(genotipo.getGen(i).getCodigo());
                int numGenes = genotipo.getGen(i).getTamGen();
               
               try{
            	   fenotipo.setFenotipoDelGen(i, fenotipo.getCaracteristicas().get(i).getMin() + genesInt * (fenotipo.getCaracteristicas().get(i).getMax() - fenotipo.getCaracteristicas().get(i).getMin()) / (Math.pow(2, numGenes) - 1));
               }
               catch(Exception e){
            	   System.out.println(e.getMessage());
               }
                
            }
        }
        else
            (new DecodificadorFinal()).decodifica(genotipoE, fenotipoE);

    }
}
