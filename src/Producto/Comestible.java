package Producto;

import Interfaz.Impuesto;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;

@JsonTypeName("Producto.Comestible")
public class Comestible extends Producto implements Impuesto {
    private boolean esDulce;
    private double pesoGramos;
    private Categoria categoria;

    public Comestible() {
        // Constructor vacío necesario para la deserialización de Jackson
    }
    public Comestible(String nombre , String distribuidor, String marca, double precio, int stock, boolean esDulce, double pesoGramos, Categoria categoria) {
        super(nombre, distribuidor, marca, precio, stock);
        this.esDulce = esDulce;
        this.pesoGramos = pesoGramos;
        this.categoria = categoria;
    }

    public boolean isEsDulce() {
        return esDulce;
    }

    public void setEsDulce(boolean esDulce) {
        this.esDulce = esDulce;
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public Categoria getCatego() {
        return categoria;
    }

    public void setCatego(Categoria catego) {
        this.categoria = catego;
    }


    @Override
    public String toString() {
        return "" +super.toString()
                +"\nES DULCE: " + esDulce+
                "\nGRAMOS: " + pesoGramos +
                "\nCATEGORIA: " + categoria;
    }

    public double calcularImpuesto() {
        double impuesto = 0;

        if (pesoGramos > 1000) { //SI PESA MAS DE 1 KG SE APLICA IMPUESTO
            impuesto = (impuestoComestible + 0.02);
        }
        else {
            impuesto = impuestoComestible;
        }
        return impuesto;
    }
}
