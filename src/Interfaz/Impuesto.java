package Interfaz;

public interface Impuesto {
     static final double impuestoBebida= 0.06;
     static final double impuestoComestible= 0.08;
     static final double impuestoOtro = 0.03;

     //METODO QUE SE USARA EN CADA CLASE
    public double calcularImpuesto();

}
