import java.util.Locale;

public class CamionetaCarga extends Vehiculo{
    private static final double RECARGO_TONELADA_POR_DIA = 100.0;
    private double capacidadmaximadetoneladas;

    public CamionetaCarga(String placa, String marca, String modelo, double tarifas,double capacidadmaximadetoneladas){
        super(placa, marca, modelo, tarifas);
        if(capacidadmaximadetoneladas <= 0){
            throw new IllegalArgumentException("La capacidad maxima debe de ser mayor que 0");
        }

        this.capacidadmaximadetoneladas = capacidadmaximadetoneladas;
    }

    public double getcapacidadmaximadetoneladas(){
        return capacidadmaximadetoneladas;
    }

    @Override
    public double calcularcostodeAlquiler(int dias){
        double costoBase = gettarifas() * dias;
        double recargo = RECARGO_TONELADA_POR_DIA * capacidadmaximadetoneladas * dias;
        return costoBase + recargo;
    }

    @Override
    public String obtenerdetalles(){
        return String.format(Locale.US, "Capacidad maxima: toneladas", capacidadmaximadetoneladas);
    }

    @Override
    public String getcategoria(){
        return "camioneta de carga";
    }
}