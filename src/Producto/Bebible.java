package Producto;

import Interfaz.Impuesto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Producto.Bebible")
public class Bebible extends Producto implements Impuesto {

    private boolean tieneGas;
    private boolean tieneAlcohol;
    private double mililitros;

    public Bebible() {
        // Constructor vacío necesario para la deserialización de Jackson
    }
    public Bebible(String nombre, String distribuidor, String marca, double precio, int stock, boolean tieneGas, boolean tieneAlcohol, double mililitros) {
        super(nombre, distribuidor, marca, precio, stock);
        this.tieneGas = tieneGas;
        this.tieneAlcohol = tieneAlcohol;
        this.mililitros = mililitros;
    }

    public boolean isTieneGas() {
        return tieneGas;
    }

    public void setTieneGas(boolean tieneGas) {
        this.tieneGas = tieneGas;
    }

    public boolean isTieneAlcohol() {
        return tieneAlcohol;
    }

    public void setTieneAlcohol(boolean tieneAlcohol) {
        this.tieneAlcohol = tieneAlcohol;
    }


    public double getMililitros() {
        return mililitros;
    }

    public void setMililitros(double mililitros) {
        this.mililitros = mililitros;
    }

    @Override
    public String toString() {
        return "" +super.toString()+
                "\nTIENE GAS: " + tieneGas +
                "\nTIENE ALCOHOL: " + tieneAlcohol +
                "\nMILILITROS: " + mililitros;
    }

    public double calcularImpuesto() {
        double impuesto = 0;

        if (tieneAlcohol) { //SI TIENE ALCOHOL SE APLICA PRECIO
            impuesto = (impuestoBebida + 0.13);
        }
        else {
            impuesto = impuestoBebida;
        }
        return impuesto;
    }
}

