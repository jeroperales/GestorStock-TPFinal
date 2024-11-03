package Producto;

import Interfaz.Impuesto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Producto.Otro")
public class Otro extends Producto implements Impuesto {
    private String detalle;

    public Otro() {
        // Constructor vacío necesario para la deserialización de Jackson
    }
    public Otro(String nombre, String distribuidor, String marca, double precio, int stock, String detalle) {
        super(nombre, distribuidor, marca, precio, stock);
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


    @Override
    public String toString() {
        return "" + super.toString() +
                "\nDETALLE: " + detalle;
    }

    @Override
    public double calcularImpuesto() {
        double impuesto = 0;

        impuesto = impuestoOtro;

        return impuesto;
    }
}
