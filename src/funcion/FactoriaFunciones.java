package funcion;

import java.util.ArrayList;

import configuracion.Configuracion;
import fenotipo.*;
import fitness.*;
import genotipo.Genotipo;
import individuo.Individuo;

public class FactoriaFunciones <GenotipoFF extends Genotipo, FenotipoFF extends Fenotipo, FitnessFF extends Fitness> {

	@SuppressWarnings("unchecked")
	public Funcion<GenotipoFF,FenotipoFF,FitnessFF> getSeleccion(int f,
			ArrayList<Individuo<GenotipoFF,FenotipoFF, FitnessFF>> poblacion,
			Configuracion config)
    {
		
    	switch(f)
		{
			case 1:
				ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>> poblAux1 = (ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>>) ((ArrayList<?>) poblacion);
				return (Funcion<GenotipoFF, FenotipoFF, FitnessFF>) new F1<GenotipoFF>(poblAux1, config);
			case 2:
				ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>> poblAux2 = (ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>>) ((ArrayList<?>) poblacion);
				return (Funcion<GenotipoFF, FenotipoFF, FitnessFF>) new F2<GenotipoFF>(poblAux2, config);
			case 3:
				ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>> poblAux3 = (ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>>) ((ArrayList<?>) poblacion);
				return (Funcion<GenotipoFF, FenotipoFF, FitnessFF>) new F3<GenotipoFF>(poblAux3, config);
			case 4:
				ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>> poblAux4 = (ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>>) ((ArrayList<?>) poblacion);
				return (Funcion<GenotipoFF, FenotipoFF, FitnessFF>) new F4<GenotipoFF>(poblAux4, config);
			case 5:
				ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>> poblAux5 = (ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>>) ((ArrayList<?>) poblacion);
				return (Funcion<GenotipoFF, FenotipoFF, FitnessFF>) new F5<GenotipoFF>(poblAux5, config);
			//6 es la F5 en real
			case 6:
				ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>> poblAux6 = (ArrayList<Individuo<GenotipoFF, FenotipoReal, FitnessReal>>) ((ArrayList<?>) poblacion);
				return (Funcion<GenotipoFF, FenotipoFF, FitnessFF>) new F5<GenotipoFF>(poblAux6, config);
			default: return null;
		}
    }
}