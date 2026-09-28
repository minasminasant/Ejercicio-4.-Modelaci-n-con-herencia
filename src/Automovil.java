public class Automovil extends Vehiculo{
    private  static final double RECARGO_POR_DIA = 50.0;

    private int numeroDePasajeros;
    private boolean TransmisionAutomatica;

    public Automovil(String placa, String marca, String modelo, double tarifas, int numeroDePasajeros, boolean TransmicionAutomatica){
        super(placa, marca, modelo, tarifas);
        if(numeroDePasajeros <= 0){
            throw  new IllegalArgumentException("El numero de pasajeros debe de ser mayor que 0.");
        }

        this.numeroDePasajeros = numeroDePasajeros;
        this.TransmisionAutomatica = TransmisionAutomatica;
    }

    public int getnumeroDePasajeros(){
        return numeroDePasajeros;
    }

    public boolean isTransmisionAutomatica(){
        return TransmisionAutomatica;
    }

    @Override
    public double calcularcostodeAlquiler(int dias){
        double costo = gettarifas() * dias;

        if(TransmisionAutomatica){
            costo = costo + (RECARGO_POR_DIA * dias);
        }
        return costo;
    }

    @Override
    public String obtenerdetalles(){
        String transmision = TransmisionAutomatica ? "Automatica" : "Manual";
        return "pasajero:" + numeroDePasajeros + "| Transmision:" + transmision;
    }


    @Override
    public String getcategoria(){
        return "Automovil";
    }
}