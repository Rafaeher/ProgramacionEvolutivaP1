package fenotipo.caracteristica;

public class FenotipoGenReal {
	 private double min; // El valor mínimo que puede tomar una característica
	 private double max; // El valor máximo que puede tomar una característica
	 private double precision; // La precisión que deben tener las características
	 private double fenotipodelgen;
	 
	 public FenotipoGenReal(double min, double max, double precision){
		 this.min = min;
		 this.max = max;
		 this.precision = precision;
	 }
	    public double getMax()
	    {
	        return max;
	    }

	    /**
	     * Obtiene el valor mínimo que puede tomar una característica
	     *
	     * @return min: el valor mínimo que puede tomar una característica
	     */
	    public double getMin()
	    {
	        return min;
	    }

	    /**
	     * Obtiene la precisión que deben tener las características
	     *
	     * @return precision: la precisión que deben tener las características
	     */
	    public double getPrecision()
	    {
	        return precision;
	    }
		public double getFenotipodelgen() {
			return fenotipodelgen;
		}
		public void setFenotipodelgen(double fenotipodelgen)
		{
			this.fenotipodelgen = fenotipodelgen;
		}
	
		@Override
		public Object clone()
		{
			FenotipoGenReal clon = new FenotipoGenReal(min, max, precision);
			clon.fenotipodelgen = fenotipodelgen;
			
			return clon;
		}
}
