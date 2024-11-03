package Shutdown;

import java.util.ArrayList;
import Memoria.Memoria;
import Producto.*;
public class ShutdownHook extends Thread {

    private final Memoria memoria;
    private final ArrayList<Producto> productos;

    public ShutdownHook(Memoria memoria, ArrayList<Producto> productos) {
        this.memoria = memoria;
        this.productos = productos;
    }

    @Override
    public void run() {
        System.out.println("Ejecutando Shutdown Hook...");
        try {
            memoria.guardarArchivo(productos); // Guardar el archivo al cerrar inesperadamente
        } catch (Exception e) {
            System.out.println("Error. Cierre inesperado.");;
        }
    }
}