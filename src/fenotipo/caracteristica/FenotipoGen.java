package fenotipo.caracteristica;

public class FenotipoGen {
	 private Double min; // El valor mínimo que puede tomar una característica
	 private Double max; // El valor máximo que puede tomar una característica
	 private Double precision; // La precisión que deben tener las características
	 private Double fenotipodelgen;
	 
	 public FenotipoGen(Double min, Double max, Double precision){
		 this.min = min;
		 this.max = max;
		 this.precision = precision;
	 }
	    public Double getMax()
	    {
	        return max;
	    }

	    /**
	     * Obtiene el valor mínimo que puede tomar una característica
	     *
	     * @return min: el valor mínimo que puede tomar una característica
	     */
	    public Double getMin()
	    {
	        return min;
	    }

	    /**
	     * Obtiene la precisión que deben tener las características
	     *
	     * @return precision: la precisión que deben tener las características
	     */
	    public Double getPrecision()
	    {
	        return precision;
	    }
		public Double getFenotipodelgen() {
			return fenotipodelgen;
		}
		public void setFenotipodelgen(Double fenotipodelgen) {
			this.fenotipodelgen = fenotipodelgen;
		}
	
	
}
