package presentacion;

import javax.swing.JFrame;

public abstract class Vista {

	private static Vista instance;
	
	public static Vista getVista(){
		if(instance == null){
			instance = new VistaImp();
		}
		return instance;
	}
	public abstract void repintaGrafica(double[] x_generaciones, double[] y_mejorPoblacion,
			double[] y_mejorAbsoluto, JFrame jf);
	
}
