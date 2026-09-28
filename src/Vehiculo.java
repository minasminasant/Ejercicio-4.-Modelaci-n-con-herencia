import java.util.Locale;

public abstract class Vehiculo{
    private String placa;
    private String marca;
    private String modelo;
    private double tarifas;
    private boolean disponible;

    public Vehiculo(String placa, String marca, String modelo, double tarifas){
        if(placa == null || placa.trim().isEmpty()){
            throw new IllegalArgumentException("La placa no puede estar vacia.");
        }
        if(tarifas <= 0){
            throw new IllegalArgumentException("La tarifa tiene que ser mayor que 0.");
        }

        this.placa = placa.trim();
        this.marca = marca;
        this.modelo = modelo;
        this.tarifas = tarifas;
        this.disponible = true;
    }

    public String getplaca(){
        return placa;
    }

    public String getmarca(){
        return marca;
    }

    public String getmodelo(){
        return modelo;
    }

    public double gettarifas(){
        return tarifas;
    }

    public boolean isdisponible(){
        return disponible;
    }

    public void setdisponible(boolean disponible){
        this.disponible = disponible;
    }

    public abstract double calcularcostodeAlquiler(int dias);

    public abstract String obtenerdetalles();

    public abstract String getcategoria();

    public String obtenerInformacion(){
        String estado = disponible ? "disponible" : "Alquilado";
        return String.format(Locale.US, "Placa: | Marca: | Modelo: | Categoria: | Tarifa Diaria: | Estado:", placa, marca, modelo, getcategoria(), tarifas, estado);
    }

    
}