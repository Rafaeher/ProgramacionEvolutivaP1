package genotipo;

import java.util.ArrayList;
import java.util.Random;

public class GenotipoAlfabeto implements Genotipo
{
	private static final int NUMLETRAS = 26;
	private String codigo;
	
	/**
	 * Constructora por defecto
	 */
	public GenotipoAlfabeto()
	{
		codigo = "";
	}
	
	/**
	 * Inicializa aleatoriamente el gen
	 */
	public void inicializacionAleatoria()
	{
		final String caracteres = "abcdefghijklmnopqrstuvwxyz";
		ArrayList<Character> contenedor = new ArrayList<Character>(NUMLETRAS); 
		
		for(int i = 0; i < NUMLETRAS; i++)
		{
			contenedor.add(caracteres.charAt(i));
		}
		
		Random random = new Random();
		
		for(int i = 0; i < NUMLETRAS; i++)
		{
			int index = random.nextInt(contenedor.size());
			codigo += contenedor.get(index);
			contenedor.remove(index);
		}
	}
	
	/**
	 * Obtiene el código del gen
	 * 
	 * @return copia del código
	 */
	public String getCodigo()
	{
		String clon = codigo;
		return clon;
	}
	
	
    @Override
    public Genotipo clone()
    {
    	GenotipoAlfabeto clon = new GenotipoAlfabeto();
    	clon.codigo = codigo;
    	
    	return clon;
    } 

}
