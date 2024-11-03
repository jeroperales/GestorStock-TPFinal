import Contenedores.Inventario;
import Producto.*;
import Shutdown.ShutdownHook;
import UI.Menu;
import Memoria.Memoria;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Memoria memoria = new Memoria();
        ArrayList<Producto> listaProductos = null;
        try {
            listaProductos = memoria.vaciarArchivo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook(memoria, listaProductos)));

        try {
            Inventario<Producto> inventario = new Inventario<Producto>(listaProductos);
            Menu menu = new Menu(inventario);
            menu.abrirMenu();
            memoria.guardarArchivo(inventario.getListaProductos());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}