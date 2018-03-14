package presentacion;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class VistaImp extends Vista {

	private  PanelInfo panelInfo;
	private  JFrame frame;
	private  Plot2DPanel plot ;
	
	public VistaImp(){
		frame = new JFrame();
		frame.setTitle("Programación Evolutiva");
		frame.setSize(800, 650);
		panelInfo = new PanelInfo(frame);
		frame.setLayout(new BorderLayout());
		frame.add(panelInfo, BorderLayout.WEST);
		grafica();
		frame.setVisible(true);
	}
	private void grafica() {

		// Para crear un panel
		plot = new Plot2DPanel();
		//plot.setBackground(new java.awt.Color(187, 212, 238));
		// Para añadir la leyenda abajo
		plot.addLegend("SOUTH");
		// Para añadir lineas
		
		// Tambien se puede hacer con una matriz
		//plot.addLinePlot("my plot", x, y);
		// Para eliminar las herramientas que salen arriba
		plot.plotToolBar.removeAll();
		// Lo añado al jframe
		frame.add(plot, BorderLayout.CENTER);
	}
	@Override
	public void repintaGrafica(double[] x_generaciones, double[] y_mejorPoblacion,
			double[] y_mejorAbsoluto, JFrame jf) {

		plot.removeAllPlots();
		plot.addLegend("SOUTH");
		plot.addLinePlot("Mejor Absoluto", x_generaciones, y_mejorAbsoluto);
		plot.addLinePlot("Mejor de la generacion", x_generaciones, y_mejorPoblacion);

	}
	
}
