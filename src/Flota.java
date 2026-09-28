import java.util.ArrayList;

public class Flota{
    private ArrayList<Vehiculo>vehiculos;
    private double ingresoAcumulado;

    public Flota(){
    this.vehiculos = new ArrayList<Vehiculo>();
    this.ingresoAcumulado = 0.0;
}

public void registrarVehiculo(Vehiculo vehiculo){
    if(buscarPorPlaca(vehiculo.getplaca()) != null){
        throw new IllegalArgumentException(" Ya existe un vehiculo registrado con la placa" + vehiculo.getplaca());
    }
    vehiculos.add(vehiculo);
}

public Vehiculo buscarPorPlaca(String placa){
    for(int i = 0; i < vehiculos.size(); i++){
        Vehiculo actual = vehiculos.get(i);
        if(actual.getplaca().equalsIgnoreCase(placa)){
            return actual;
        }
    }
    return null;
}

public ArrayList<Vehiculo> obtenerTodos(){
    return vehiculos;
}

public double cotizar(String placa, int dias){
    Vehiculo vehiculo = buscarPorPlaca(placa);
    if(vehiculo == null){
        throw new IllegalArgumentException("NO hay ningun vehiculo con la placa" + placa);
    }
    if(dias <= 0){
        throw new IllegalArgumentException("La cantidad de dias debe de ser mas que 0");
    }
    return vehiculo.calcularcostodeAlquiler(dias);
}

public double confirmarAlquiler(String placa, int dias){
    Vehiculo vehiculo = buscarPorPlaca(placa);
    if(vehiculo == null){
        throw new IllegalArgumentException("NO hay ningun vehiculo con la placa" + placa);
    }
    if(dias <= 0){
        throw new IllegalArgumentException("La cantidad de dias debe de ser mas que 0");
    }
    if(!vehiculo.isdisponible()){
        throw new IllegalArgumentException("El vehiculo con placa" + placa + "ya esta alquilado");
    }
    double costoTotal = vehiculo.calcularcostodeAlquiler(dias);
    vehiculo.setdisponible(false);
    ingresoAcumulado = ingresoAcumulado + costoTotal;
    return costoTotal;    
}

public void registrarDevolucion(String placa){
    Vehiculo vehiculo = buscarPorPlaca(placa);
    if(vehiculo == null){
        throw new IllegalArgumentException("NO hay ningun vehiculo con la placa" + placa);
    }
    if(vehiculo.isdisponible()){
        throw new IllegalArgumentException("El vehiculo con placa" + placa + "ya esta disponible");
    }
    vehiculo.setdisponible(true);
}

public double getingresosAcumulados(){
    return ingresoAcumulado;
}

public int contarPorCategoria(String categoria){
    int contador = 0;
    for(int i = 0; i < vehiculos.size(); i++){
        if(vehiculos.get(i).getcategoria().equals(categoria)){
            contador++;
        }
    }
    return contador;
}

public int contarDisponiblesporcategoria(String categoria){
    int contador = 0;
    for(int i = 0; i < vehiculos.size(); i++){
        Vehiculo v = vehiculos.get(i);
        if(v.getcategoria().equals(categoria) && v.isdisponible()){
            contador++;
        }
    }
    return contador;
}

public int contarAlquiladorporcategoria(String categoria){
    return contarPorCategoria(categoria) - contarDisponiblesporcategoria(categoria);
}

}



