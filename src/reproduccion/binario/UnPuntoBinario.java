package reproduccion.binario;

import java.util.ArrayList;
import java.util.Random;

import configuracion.Configuracion;
import decodificador.Decodificador;
import individuo.Individuo;
import reproduccion.Reproduccion;
import genotipo.GenotipoBinario;
import genotipo.genes.GenBinario;
import fenotipo.FenotipoReal;


public class UnPuntoBinario<Genotipo, Fenotipo, Fitness extends Comparable<Fitness>> implements Reproduccion<GenotipoBinario, FenotipoReal, Fitness>
{
	private ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> poblacion;
    @Override
    public ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>
    reproduce(ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> p, Configuracion c)
    {
        //ArrayList<Individuo<GenotipoBinario,FenotipoReal,Fitness>> publacion_solucion = new  ArrayList<Individuo<GenotipoBinario,FenotipoReal,Fitness>>(poblacion.size());
        this.poblacion = p;

        //Los individuos solo tienen un gen
        if(poblacion.get(0).getGenotipo().getNumGenes() == 1) {
        	//Los individuos solo tienen un gen
        	Random r = new Random();
        	int i = 0;
        	while(i+1 < poblacion.size()){
        		double random = r.nextDouble();
        		if( random <= c.getCruceporcentaje()) {
        			if(poblacion.get(i) != null && poblacion.get(i+1) != null) {
        				//Para comprobar que no nos salimos del array
        				ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> array_con_2_individuos = 
                				reproduce_un_gen(poblacion.get(i), poblacion.get(i+1));


        				Individuo<GenotipoBinario, FenotipoReal, Fitness> ind1 =array_con_2_individuos.get(0);
        				Individuo<GenotipoBinario, FenotipoReal, Fitness> ind2 =array_con_2_individuos.get(1);


                		poblacion.set(i, ind1);
                		poblacion.set(i+1, ind2);
        				
        			}
        			
        		}
        		
        		i = i +1;
        	}
        }
        else{{
        	//Los individuos solo tienen un gen
        	Random r = new Random();
        	for(int i = 0; i < poblacion.size() - 1; i = i + 2 ) {
        		double random = r.nextDouble();
        		if( random <= c.getCruceporcentaje()) {
        			if(poblacion.get(i) != null && poblacion.get(i+1) != null) {
        				//Para comprobar que no nos salimos del array
        				ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> array_con_2_individuos = 
                				reproduce_un_gen(poblacion.get(i), poblacion.get(i+1));

            				System.out.println("no tenia que entrar aqui");

            				poblacion.add(i,array_con_2_individuos.get(0));
                			poblacion.add(i +1,array_con_2_individuos.get(1));

        				
        			}
        			
        		}
        		
        	}
        }
        	
        }
        return poblacion;
    }
    
    private ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> reproduce_un_gen(Individuo<GenotipoBinario,FenotipoReal,Fitness> individuo1,
    		Individuo<GenotipoBinario,FenotipoReal,Fitness> individuo2) {
    	Random r = new Random();
    	int tamano_gen = individuo1.getGenotipo().getGenes().get(0).getTamGen();
    	int random = r.nextInt(tamano_gen);
    	for( int i = random; i < tamano_gen; i++ ) {
    		boolean aux = individuo1.getGenotipo().getGenes().get(0).getGen().get(i);
    		individuo1.getGenotipo().getGenes().get(0).getGen().set(i, 
    				individuo2.getGenotipo().getGenes().get(0).getGen().get(i));
   
    		individuo2.getGenotipo().getGenes().get(0).getGen().set(i, aux);
    		
    	}
    	//Actualizamos los fenotipos de los individuos
		ArrayList<Double> fenotipo_double_del_individuo_1 = (ArrayList<Double>)Decodificador.decodifica(individuo1.getGenotipo(), individuo1.getFenotipo());
		ArrayList<Double> fenotipo_double_del_individuo_2 = (ArrayList<Double>)Decodificador.decodifica(individuo2.getGenotipo(), individuo2.getFenotipo());

		FenotipoReal fenotipo_individuo1 = individuo1.getFenotipo();
		FenotipoReal fenotipo_individuo2 = individuo2.getFenotipo();
		
		//fenotipo_individuo1.setCaracteristicas(fenotipo_double_del_individuo_1);
		//fenotipo_individuo2.setCaracteristicas(fenotipo_double_del_individuo_2);
		
		fenotipo_individuo1.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_double_del_individuo_1.get(0));
		fenotipo_individuo2.getCaracteristicas().get(0).setFenotipodelgen(fenotipo_double_del_individuo_2.get(0));

		individuo1.setFenotipo(fenotipo_individuo1);
		individuo2.setFenotipo(fenotipo_individuo2);
		
    	ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>();
    	sol.add(individuo1);
    	sol.add(individuo2);
    	
    	return sol;
    }
    private ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> reproduce_varios_genes(Individuo<GenotipoBinario,FenotipoReal,Fitness> individuo1,
    		Individuo<GenotipoBinario,FenotipoReal,Fitness> individuo2) {
    	Random r = new Random();
    	
    	int num_genes = individuo1.getGenotipo().getNumGenes();
    	int random = r.nextInt(num_genes);
    	for( int i = random; i < num_genes; i++ ) {
    		GenBinario aux = individuo1.getGenotipo().getGenes().get(i);
    		individuo1.getGenotipo().setGen(i, individuo2.getGenotipo().getGen(i));
    		individuo1.getGenotipo().setGen(i, aux);
    		
    	}
    	//Actualizamos los fenotipos de los individuos
		ArrayList<Double> fenotipo_double_del_individuo_1 = (ArrayList<Double>)Decodificador.decodifica(individuo1.getGenotipo(), individuo1.getFenotipo());
		ArrayList<Double> fenotipo_double_del_individuo_2 = (ArrayList<Double>)Decodificador.decodifica(individuo2.getGenotipo(), individuo2.getFenotipo());

		FenotipoReal fenotipo_individuo1 = individuo1.getFenotipo();
		FenotipoReal fenotipo_individuo2 = individuo2.getFenotipo();
		
		for(int i = 0; i < fenotipo_individuo1.getCaracteristicas().size();i++){
			fenotipo_individuo1.getCaracteristicas().get(i).setFenotipodelgen(fenotipo_double_del_individuo_1.get(i));
			fenotipo_individuo2.getCaracteristicas().get(i).setFenotipodelgen(fenotipo_double_del_individuo_2.get(i));
		}
		
		
		
		
		individuo1.setFenotipo(fenotipo_individuo1);
		individuo2.setFenotipo(fenotipo_individuo2);

		ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>> sol = new ArrayList<Individuo<GenotipoBinario, FenotipoReal, Fitness>>();
		sol.add(individuo1);
		sol.add(individuo2);

		return sol;
	}

}

