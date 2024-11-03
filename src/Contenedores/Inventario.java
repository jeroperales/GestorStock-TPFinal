package Contenedores;

import Producto.*;

import java.util.*;

public class Inventario<T extends Producto> {
    private ArrayList<T> listaProductos = new ArrayList<>();

    //CONSTRUCTORES
    public Inventario(ArrayList<T> listaProductos) {

        this.listaProductos = listaProductos;
    }


    public void  mostrarProductos(){
        for(T producto: this.listaProductos){
            System.out.println(producto);
        }
    }
    public void agregarProducto(T producto){
        listaProductos.add(producto);
    }

    public boolean eliminarProducto(String nombre) { //RECORRE LA LISTA, SI ENCUENTRA EL MISMO NOMBRE SIN IMPORTAR MAYUSCULAS LO ELIMINA
        Iterator<T> iteradorProductos = listaProductos.iterator(); //SIRVE DE INDICE
        while (iteradorProductos.hasNext()) { //MIENTRAS TENGA SIGUIENTE
            T producto = iteradorProductos.next();
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                iteradorProductos.remove();
                return true; // Se encontro y elimino el producto
            }
        }
        return false; //NO SE ENCUENTRA EL PRODUCTO
    }



    public Producto buscarPorNombre(String nombre){
        for(T producto: listaProductos){
            if(producto.getNombre().equalsIgnoreCase(nombre)){
                return producto;
            }
        }
        return null;
    }

    public ArrayList<T> filtrarPorMarca(String marca){ //RECIBE UN NOMBRE Y PASA A LISTA TODOS LOS PRODUCTOS DE UNA MARCA
        ArrayList<T> filtrados = new ArrayList();
        for(T producto: listaProductos){
            if(producto.getMarca().equalsIgnoreCase(marca)){
                filtrados.add(producto);
            }
        }
        return filtrados;
    }


    public ArrayList<T> filtrarPorTipo(Class<?> tipo) {
        ArrayList<T> filtrados = new ArrayList<>();
        for (T producto : listaProductos) {
            if (tipo.isInstance(producto)) {
                filtrados.add(producto);
            }
        }
        return filtrados;
    }




    public boolean modificarPrecioProducto(String nombre, double nuevoPrecio) {
        Producto producto=buscarPorNombre(nombre);
        if(producto !=null) {
            producto.setPrecio(nuevoPrecio);
            return true;
        }
        return false;
    }

    public boolean modificarStockProducto(String nombre, int nuevoStock)
    {
        Producto producto=buscarPorNombre(nombre);
        if (producto !=null) {
            producto.setStock(nuevoStock);
            return true;
        }
        return false;
    }


    //RECIBE NUMERO Y DEVUELVE LISTA CON PRODUCTOS CON MENOR CANTIDAD QUE ESO
    public ArrayList<T> obtenerProductosConBajoStock(int limite) throws IllegalArgumentException {
        if (limite < 0) {
            throw new IllegalArgumentException("El límite de stock debe ser un valor positivo.");
        }

        ArrayList<T> productosConBajoStock = new ArrayList<>();
        for (T producto : listaProductos) {
            if (producto.getStock() < limite) {
                productosConBajoStock.add(producto);
            }
        }

        return productosConBajoStock;
    }

public void aplicarImpuesto(){
        double precioFinal = 0;
    System.out.println("---PRECIOS SI SE APLICAN LOS IMPUESTOS ---");
        for (T producto : listaProductos) {
            precioFinal = ((producto.calcularImpuesto() * producto.getPrecio()) + producto.getPrecio());
            System.out.println("Producto: "+producto.getNombre() + ","+" Precio con impuesto: " + precioFinal + "\n");
        }
}




    //GETTERS AND SETTERS
    public ArrayList<T> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<T> listaProductos) {
        this.listaProductos = listaProductos;
    }

}
