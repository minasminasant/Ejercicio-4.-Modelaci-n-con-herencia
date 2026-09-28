import java.util.Locale;
import java.util.Scanner;

public class Main{
    private static Scanner teclado = new Scanner(System.in);
    private static Flota flota = new Flota();

    public static void main(String[] args) {
        cargarDatosIniciales();

        boolean continuar = true;
        while(continuar){
            menu();
            int opcion = leerEntero("Elije una de las opciones:");

            switch(opcion){
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    consultarFlota();
                    break;
                case 3:
                    cotizaralquiler();
                    break;
                case 4:
                    confirmarAlquiler();
                    break;
                case 5:
                    registrarDevolucion();
                    break;
                case 6:
                    mostrarReporte();
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Gracias por usar nuestros servicio");
                    break;
                default:
                System.out.println("Opcion no valida");    
            }
            System.out.println();   
        }
        teclado.close();
    }

    private static void cargarDatosIniciales(){
        flota.registrarVehiculo(new Automovil("PO909KJG","Toyota","Rav4", 250.0,5,true));
        flota.registrarVehiculo(new Automovil("PO379OYT","Toyota","corrolla", 250.0,5,false));

        flota.registrarVehiculo(new CamionetaCarga("C098POV","Isuzu","NPR", 200.0,1.5));
        flota.registrarVehiculo(new CamionetaCarga("C748HVK","Hino","300", 300.0,2));

        flota.registrarVehiculo(new Motocicleta("M849HKY","Honda","navi", 100.0,100));
        flota.registrarVehiculo(new Motocicleta("M214GFP","Yamaha","FZ", 150.0,150));
    }

    private static void menu(){
        System.out.println("-----RentaMovil------");
        System.out.println("1. Registrar vehiculo");
        System.out.println("2. Consultar flota");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. confirmar alquiler");
        System.out.println("5. registrar devolucion");
        System.out.println("6. reporte generar");
        System.out.println("7. salir");
    }

    private static void registrarVehiculo(){
        System.out.println("-----Registrar vehiculo----");
        System.out.println("categoria: | automovil: | motocicleta: | camioneta de carga: |");
        int categoria = leerEntero("elije la categoria:");

        String placa = leerTextoNoVacio("placa:");
        String marca = leerTextoNoVacio("marca:");
        String modelo = leerTextoNoVacio("modelo:");
        double tarifa = leerDecimalPositivo("Tarifa diaria:");

        try {
            Vehiculo nuevo;

            if(categoria == 1){
                int pasajero = leerEnteroPositivo("numero de pasajeros:");
                boolean automatica = leerSiNo("transmision automatica? (s/n)");
                nuevo = new Automovil(placa, marca, modelo, tarifa, pasajero, automatica);
            }
            else if( categoria == 2){
                int cilindraje = leerEnteroPositivo("Cilindraje:");
                nuevo = new Motocicleta(placa, marca, modelo, tarifa, cilindraje);
            }
            else if( categoria == 3){
                double capacidad = leerDecimalPositivo("capacidad maxima:");
                nuevo = new CamionetaCarga(placa, marca, modelo, tarifa, capacidad);
            }
            else{
                System.out.println("Categoria no valida");
                return;
            }
            flota.registrarVehiculo(nuevo);
            System.out.println("Vehiculo registrado");
        } catch (IllegalArgumentException error) {
            System.out.println("No se pudo registrar el vehiculo" + error.getMessage());
        }
    }
    private static void consultarFlota(){
        System.out.println("----Flota registrada ----");
        if(flota.obtenerTodos().isEmpty()){
            System.out.println("No hay vehiculos registrados");
            return;
        }

        for(int i = 0; i < flota.obtenerTodos().size(); i++){
            Vehiculo v = flota.obtenerTodos().get(i);
            System.out.println(v.obtenerInformacion());
            System.out.println("" + v.obtenerdetalles());
        }
    }
    private static void cotizaralquiler(){
        System.out.println("---- cotizar alquiler----");
        String placa = leerTextoNoVacio("placa del vehiculo:");
        int dias = leerEnteroPositivo("cantidad de dias:");

        try {
            Vehiculo vehiculo = flota.buscarPorPlaca(placa);
            double costo = flota.cotizar(placa, dias);
            System.out.println(vehiculo.obtenerInformacion());
            System.out.println("" + vehiculo.obtenerdetalles());
            System.out.printf(Locale.US, "Costo total por dias:", dias, costo);
        } catch (IllegalArgumentException error) {
            System.out.println("Nose pudo cotizar:" + error.getMessage());
        }
    }
    private static void confirmarAlquiler(){
        System.out.println("--- confirmar alquiler---");
        String placa = leerTextoNoVacio("placa del vehiculo:");
        int dias = leerEnteroPositivo("cantidad de dias: ");

        try {
            double costo = flota.confirmarAlquiler(placa, dias);
            System.out.printf(Locale.US,"Alquiler confirmado. se cobrara:", costo);
        } catch (IllegalArgumentException error) {
            System.out.println("Nose pudo confirmar el alquiler" + error.getMessage());
        }
    }
    private static void registrarDevolucion(){
        System.out.println("----- Registrar devolucion----");
        String placa = leerTextoNoVacio("placa del vehiculo:");

        try {
            flota.registrarDevolucion(placa);
            System.out.println("Devolucion registrada");
        } catch (IllegalArgumentException error) {
            System.out.println("no se pudo registrar la devolucion:" + error.getMessage());
        }
    }
    private static void mostrarReporte(){
        System.out.println("----- reporte general-----");
        String[] categorias = {"Automovil", "Motocicleta", "Camioneta de carga"};

        int totalVehiculos = 0;
        int totalDisponibles = 0;
        int totalAlquilados = 0;

        for(int i = 0; i < categorias.length; i++){
            String categoria = categorias[i];
            int cantidad = flota.contarPorCategoria(categoria);
            int disponibles = flota.contarDisponiblesporcategoria(categoria);
            int alquilados = flota.contarAlquiladorporcategoria(categoria);

            System.out.println(categoria + ":" + cantidad + "registrados," + disponibles + "dosponibles," + alquilados + "alquilados.");

            totalVehiculos = totalVehiculos + cantidad;
            totalDisponibles = totalDisponibles + disponibles;
            totalAlquilados = totalAlquilados + alquilados;
        }

        System.out.println("Total de vehiculos:" + totalVehiculos + "|" + totalDisponibles + "dosponibles," + totalAlquilados + "alqilados" );
        System.out.printf(Locale.US,"Ingreosos acumulados por alquileres confir,ados:", flota.getingresosAcumulados());

    }

    private static int leerEntero(String mensaje){
        while (true) { 
         System.out.println(mensaje);
         String texto = teclado.nextLine();
         try {
             return Integer.parseInt(texto.trim());
         } catch (NumberFormatException error) {
            System.out.println("ingresar un numero entero");
         }
        }
    }

    private static int leerEnteroPositivo(String mensaje){
        while (true) { 
         int valor = leerEntero(mensaje);
         if(valor > 0){
            return valor;
         }   
         System.out.println("El valor debe de ser mas que 0");
        }
    }

    private static double leerDecimalPositivo(String mensaje){
        while (true) { 
         System.out.print(mensaje);
         String texto = teclado.nextLine();
         try {
             double valor = Double.parseDouble(texto.trim());
             if(valor > 0){
                return valor;
             }
         } catch (NumberFormatException error) {
            System.out.println("ingresar un numero valido");
         }   
        }
    }

    private static String leerTextoNoVacio(String mensaje){
        while (true) { 
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();
            if(!texto.isEmpty()){
                return texto;
            }   
            System.out.println("esta parte no puede estar vacia");
        }
    }

    private static boolean leerSiNo(String mensaje){
        while (true) { 
         System.out.print(mensaje);
         String texto = teclado.nextLine().trim().toLowerCase();
         if(texto.equals("s")){
            return true;
         }   
         else if(texto.equals("n")){
            return false;
         }
         System.out.println("responda con s o n ");
        }
    }

}