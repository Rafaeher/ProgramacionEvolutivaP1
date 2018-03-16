package mutacion;

import configuracion.Mutacion_enum;

public class FactoriaMutacionImp extends FactoriaMutacion
{

    @Override
    public Mutacion getMutacion(Mutacion_enum tipo)
    {
        switch(tipo)
        {
            case Normal: return new MutacionEstandarBinario();
            case Normal_Real: return new MutacionEstandarReal();
            default: return null;
        }
    }
}
