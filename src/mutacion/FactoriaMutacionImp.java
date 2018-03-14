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
            default: return null;
        }
    }
}
