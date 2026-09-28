public class Motocicleta extends Vehiculo{
    private static final int CILIDRAJE = 200;
    private static final  double CARGO_ADICIONAL = 70.0;

    private int cilindraje;

    public Motocicleta(String placa, String marca, String modelo, double tarifas, int cilindraje){
        super(placa, marca, modelo, tarifas);
        if(cilindraje <= 0){
            throw new IllegalArgumentException("El cilindraje debe de ser mayor que 0.");
        }

        this.cilindraje = cilindraje;
    }

    public int getcilindraje(){
        return cilindraje;
    }

    @Override
    public double calcularcostodeAlquiler(int dias){
        double costo = getcilindraje() * dias;
        if(cilindraje > CILIDRAJE){
            costo = costo + CARGO_ADICIONAL;
        }
        return costo;
    }

    @Override
    public String obtenerdetalles(){
        return "cilindraje:" + cilindraje;
    }

    @Override
    public String getcategoria(){
        return "Motocicleta";
    }
    
}