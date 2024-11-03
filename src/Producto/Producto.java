package Producto;

import Interfaz.Impuesto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = Comestible.class, name = "Producto.Comestible"),
        @JsonSubTypes.Type(value = Bebible.class, name = "Producto.Bebible"),
        @JsonSubTypes.Type(value = Otro.class, name = "Producto.Otro"),
})
public class Producto implements Impuesto {
    private String nombre;
    private String distribuidor;
    private String marca;
    private double precio;
    private  int stock;

    public Producto(String nombre, String distribuidor, String marca, double precio, int stock) {
        this.nombre = nombre;
        this.distribuidor = distribuidor;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto() {
        // Constructor vacío necesario para la deserialización de Jackson
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Double.compare(precio, producto.precio) == 0 && stock == producto.stock && Objects.equals(nombre, producto.nombre) && Objects.equals(distribuidor, producto.distribuidor) && Objects.equals(marca, producto.marca);
    }

    @Override
    public String toString() {
        return "\n---------------------" +
                "\nNOMBRE: " + nombre +
                "\nDISTRIBUIDOR: " + distribuidor +
                "\nMARCA: " + marca +
                "\nPRECIO: " + precio +
                "\nSTOCK: " + stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, distribuidor, marca, precio, stock);
    }

    @Override
    public double calcularImpuesto() {
        return 0;
    }
}
