package UI;

import Exceptions.ErrorDeCaracterException;
import Producto.*;
import Contenedores.*;
import Memoria.Memoria;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private Inventario<Producto> inventario;

    public Menu(Inventario<Producto> inventario) {
        this.inventario = inventario;
    }

    Scanner scanner = new Scanner(System.in);
    String nombre;
    int op = 0;

    Memoria memoria = new Memoria();

    public void abrirMenu() throws IOException {
        boolean loop = true;
        System.out.println("Bienvenidos a su gestion de kiosco ");

        while (loop) {
            op = 0;
            System.out.println("\n-Menu Principal-");
            System.out.println("1. Mostrar productos");
            System.out.println("2. Agregar productos");
            System.out.println("3. Eliminar productos");
            System.out.println("4. Modificar datos de un producto");
            System.out.println("5. Visualizar precio con impuestos");
            System.out.println("0. Salir y guardar cambios");
            try {
                op = Integer.parseInt(scanner.nextLine());
                loop = menuPpal(loop);
            } catch (NumberFormatException e) {
                System.out.println("Opcion no valida, intente de nuevo.");
            }
        }
    }

    private boolean menuPpal(boolean loop) throws ErrorDeCaracterException, IOException {
        switch (op) {
            case 1 -> menuMostrarProductos();

            case 2 -> menuAgregarProducto(); //AGREGA UN PRODUCTO TENIENDO TODO EN CUENTA

            case 3 -> menuEliminarProducto(); //ELIMINA UN PRODUCTO POR NOMBRE

            case 4 -> menuModificarProducto(); // ABRE MENU, OFRECE OPCIONES DE MODIFICAR PRODUCTO

            case 5 ->
                    inventario.aplicarImpuesto(); //MUESTRA LISTA DE PRECIOS CON RESPECTIVOS IMPUESTOS *SOLO MUESTRA NO APLICA CAMBIO*
            case 6 -> {
                double div = 6/0;
            }

            case 0 -> {
                System.out.println("Hasta luego");
                memoria.guardarArchivo(inventario.getListaProductos());
                System.exit(0);
            }

            default -> System.out.println("Ingrese una opcion valida");
        }
        return loop;
    }

    public void menuMostrarProductos() {
        boolean loop = true;
        int limite = 0;
        while (loop) {
            System.out.println("\n-MOSTRAR PRODUCTOS-");
            System.out.println("1. Mostrar todos los productos");
            System.out.println("2. Filtrar por MARCA");
            System.out.println("3. Filtrar por tipo");
            System.out.println("4. Mostrar productos con bajo stock");
            System.out.println("5. Volver al menu principal");

            try {
                op = Integer.parseInt(scanner.nextLine());
                loop = subMenuMostrar(loop);
            } catch (Exception e) {
                System.out.println("Entrada no valida, intente de nuevo.");
            }
        }
    }

    private boolean subMenuMostrar(boolean loop) {
        int limite;
        switch (op) {
            case 1 -> inventario.mostrarProductos();

            case 2 -> {
                System.out.println("Nombre de la MARCA que esta buscando: ");
                nombre = scanner.nextLine();
                if (inventario.filtrarPorMarca(nombre).isEmpty()) {
                    System.out.println("No se encontro LA marca");
                } else {
                    System.out.println(inventario.filtrarPorMarca(nombre));
                }
            }

            case 3 -> {
                System.out.println("Ingrese que producto desea ver: (Comestible / Bebible / Otro)");
                nombre = scanner.nextLine();
                Class<?> universal = null;

                if (nombre.equalsIgnoreCase("bebible")) {
                    universal = Bebible.class;
                } else if (nombre.equalsIgnoreCase("comestible")) {
                    universal = Comestible.class;

                } else if (nombre.equalsIgnoreCase("otro")) {
                    universal = Otro.class;
                } else {
                    System.out.println("INGRESE UNA CLASE VALIDA");
                }
                System.out.println(inventario.filtrarPorTipo(universal));
            }

            case 4 -> {
                System.out.println("Ingrese el límite de stock bajo que desea ver:");
                limite = scanner.nextInt();
                scanner.nextLine();
                if (inventario.obtenerProductosConBajoStock(limite).isEmpty()) {
                    System.out.println("No hay productos con ese limite de stock");
                } else {
                    System.out.println(inventario.obtenerProductosConBajoStock(limite));
                }

            }

            case 5 -> loop = false;


            default -> System.out.println("Ingrese una opcion valida");

        }
        return loop;
    }


    private void menuAgregarProducto() {

        System.out.println("\n-AGREGAR PRODUCTO-");

        int tipo = 0;
        while (true) {
            System.out.print("Ingrese el tipo de producto (1. Bebible, 2. Comestible, 3. Otro): ");
            try {
                tipo = Integer.parseInt(scanner.nextLine());
                if (tipo >= 1 && tipo <= 3) break;
                else System.out.println("Tipo de producto no válido. Intente nuevamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida, intente nuevamente.");
            }
        }

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el distribuidor: ");
        String distribuidor = scanner.nextLine();
        System.out.print("Ingrese la marca: ");
        String marca = scanner.nextLine();

        double precio = 0;
        while (true) {
            System.out.print("Ingrese el precio: ");
            try {
                precio = Double.parseDouble(scanner.nextLine());

                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida, intente nuevamente.");
            }
        }

        int stock = 0;
        while (true) {
            System.out.print("Ingrese el stock: ");
            try {
                stock = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida, intente nuevamente.");
            }
        }

        Producto producto = null;
        switch (tipo) {
            case 1:
                boolean tieneGas = false, tieneAlcohol = false;
                double mililitros = 0;

                while (true) {
                    System.out.print("Tiene gas (true/false): ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                        tieneGas = Boolean.parseBoolean(input);
                        break;
                    } else {
                        System.out.println("Entrada no valida, intente nuevamente.");
                    }
                }
                while (true) {
                    System.out.print("Tiene alcohol (true/false): ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                        tieneAlcohol = Boolean.parseBoolean(input);
                        break;
                    } else {
                        System.out.println("Entrada no valida, intente nuevamente.");
                    }
                }
                while (true) {
                    System.out.print("Ingrese los mililitros: ");
                    try {
                        mililitros = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no valida, intente nuevamente.");
                    }
                }
                producto = new Bebible(nombre, distribuidor, marca, precio, stock, tieneGas, tieneAlcohol, mililitros);
                break;
            case 2:
                boolean esDulce2 = false;
                double pesoGramos = 0;
                String categoriaStr = "";
                Categoria categoria = null;

                while (true) {
                    System.out.print("Es dulce (true/false): ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                        esDulce2 = Boolean.parseBoolean(input);
                        break;
                    } else {
                        System.out.println("Entrada no valida, intente nuevamente.");
                    }
                }
                while (true) {
                    System.out.print("Ingrese el peso en gramos: ");
                    try {
                        pesoGramos = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no valida, intente nuevamente.");
                    }
                }
                while (true) {
                    System.out.print("Ingrese la categoria (Chocolate/Helados/Cereales/Golosinas/Galletitas): ");
                    categoriaStr = scanner.nextLine();
                    try {
                        categoria = Categoria.valueOf(categoriaStr);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Categoria no valida, las categorias validas son: Chocolate, Helados, Cereales, Golosinas, Galletitas.");
                    }
                }
                producto = new Comestible(nombre, distribuidor, marca, precio, stock, esDulce2, pesoGramos, categoria);
                break;
            case 3:
                System.out.print("Ingrese el detalle: ");
                String detalle = scanner.nextLine();
                producto = new Otro(nombre, distribuidor, marca, precio, stock, detalle);
                break;
            default:
                System.out.println("Tipo de producto no válido.");
                break;
        }

        if (producto != null) {
            inventario.agregarProducto(producto);
            System.out.println("Producto agregado correctamente.");
        } else {
            System.out.println("Error al agregar el producto.");
        }
    }


    private void menuEliminarProducto() {

        System.out.println("\n-ELIMINAR PRODUCTO-");

        System.out.print("Ingrese el nombre del producto que desea eliminar: ");
        String nombreProductoEliminar = scanner.nextLine();

        // Buscar el producto por nombre en el inventario
        Producto productoAEliminar = inventario.buscarPorNombre(nombreProductoEliminar);

        if (productoAEliminar != null) {
            System.out.println("Producto encontrado:");
            System.out.println(productoAEliminar);

            // Confirmacion de eliminacion
            System.out.print("¿Está seguro que desea eliminar este producto? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                boolean eliminado = inventario.eliminarProducto(nombreProductoEliminar);

                if (eliminado) {
                    System.out.println("Producto eliminado correctamente.");
                } else {
                    System.out.println("Error al intentar eliminar el producto.");
                }
            } else {
                System.out.println("Eliminacion cancelada.");
            }
        } else {
            System.out.println("No se encontro ningún producto con el nombre ingresado.");
        }
    }

    private void menuModificarProducto() {

        System.out.println("\n-MODIFICAR PRODUCTO-");

        System.out.print("Ingrese el nombre del producto que desea modificar: ");
        nombre = scanner.nextLine();

        Producto producto = inventario.buscarPorNombre(nombre);

        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
            System.out.println("¿Que desea modificar?");
            System.out.println("1. Stock");
            System.out.println("2. Precio en PESOS");
            System.out.println("3. Precio por PORCENTAJE");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Tiene que ingresar solo numero, intente de nuevo");
            }

            switch (op) {
                case 1:
                    System.out.print("STOCK ANTERIOR: " + producto.getStock() + " Ingrese el nuevo stock: ");

                    int nuevoStock =-1;
                    while (nuevoStock <=0) {
                        try {
                            nuevoStock = Integer.parseInt(scanner.nextLine());
                            if (nuevoStock <= 0) {
                                System.out.println("El stock tiene que ser mayor a 0, intente de nuevo");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ingreso mal un dato, intente de nuevo.");
                        }
                    }

                    if (inventario.modificarStockProducto(nombre, nuevoStock)) {
                        System.out.println("Stock modificado");
                        }
                    break;

                case 2:
                    System.out.print("Ingrese el nuevo precio: ");
                    double nuevoPrecio=-1;
                    while (nuevoPrecio<= 0) {
                        try {
                            nuevoPrecio = Double.parseDouble(scanner.nextLine());
                            if (nuevoPrecio <= 0) {
                                System.out.println("No puede poner un precio de 0, intente de nuevo");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ingreso mal un dato, intente de nuevo.");
                        }
                    }

                    if (inventario.modificarPrecioProducto(nombre, nuevoPrecio)) {
                        System.out.println("Se modifico el precio");
                    }
                    break;

                case 3:
                    System.out.println("Ingrese el porcentaje en el que quiere aumentar los precios: ");
                    double porcentaje=-1;
                    while (porcentaje <=0) {
                        try {
                            porcentaje = Double.parseDouble(scanner.nextLine());
                            if (porcentaje<=0) {
                                System.out.println("El porcentaje debe ser mayor que 0, intente de nuevo");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ingreso mal un dato, intente de nuevo.");
                        }
                    }
                    double nuevoPrecioConPorcentaje = ((porcentaje * producto.getPrecio()) / 100 + producto.getPrecio());
                    if (inventario.modificarPrecioProducto(nombre, nuevoPrecioConPorcentaje)) {
                        System.out.println("Se actualizo el precio");
                    }
                    break;

                default:
                    System.out.println("");
                    break;
            }
        } else {
            System.out.println("No existe tal producto.");
        }
    }
}




