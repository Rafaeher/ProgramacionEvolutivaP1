package dao;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;

public class DAO_Ngramas
{
	private final static String RUTA = "ngrams/";
	
	/**
	 * Obtiene un HashMap que contiene los n -gramas y sus frecuencias relativas, con n dado.
	 * 
	 * @param n
	 */
   public HashMap<String, Double> lectura(int n)
   {
      File archivo = null;
      FileReader fileReader = null;
      BufferedReader bufferedReader = null;
      HashMap<String, Double> resultado = new HashMap<String, Double>();
      HashMap<String, BigDecimal> frecuencias = new HashMap<String, BigDecimal>();
      BigDecimal total = new BigDecimal(0);
      
      try
      {
         archivo = new File (RUTA + obtenerNombreFichero(n));
         fileReader = new FileReader (archivo);
         bufferedReader = new BufferedReader(fileReader);

         String linea;
         while((linea=bufferedReader.readLine()) != null)
         {
            String[] separacion = linea.split(" ");
            String ngrama = separacion[0];
            BigDecimal frecuencia = new BigDecimal(separacion[1], new MathContext(5));
                        
            frecuencias.put(ngrama, frecuencia);
            total = total.add(frecuencia);
         }
         
         
         for(String ngrama : frecuencias.keySet()) // Para cada n - grama, añade al resultado el n - grama con su frecuencia relativa
         {
        	 Double frecuenciaRelativa = (frecuencias.get(ngrama).divide(total)).doubleValue();
        	 resultado.put(ngrama, frecuenciaRelativa);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {                    
            if( null != fileReader )
            {   
               fileReader.close();     
            }                  
         }
         catch (Exception e2)
         { 
            e2.printStackTrace();
         }
      }
      
      return resultado;
   }
   
   /**
    * Obtiene el nombre del fichero de los n - gramas, con n dado
    * 
    * @param n
    * @return
    */
   private static String obtenerNombreFichero(int n)
   {
	   switch(n)
	   {
	   case 1: return "monograms.txt";
	   case 2: return "bigrams.txt";
	   case 3: return "trigrams.txt";
	   case 4: return "quadgrams.txt";
	   case 5: return "quintgrams.txt";
	   default: return "words.txt";
	   }
   }
   
   public static void main(String[] args)
   {
	   DAO_Ngramas dao = new DAO_Ngramas();
	   
	   HashMap<String, Double> ngramas = dao.lectura(1);
	   
	   for(String ngrama : ngramas.keySet())
	   {
		   System.out.println(ngrama + "    " + ngramas.get(ngrama).toString());
	   }
   }
}
