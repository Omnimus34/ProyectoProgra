package model;

import java.time.LocalDate;

public class Planilla {
    private int idPlanilla;
    private int idEmpleado;
    private String mesPagado;
    private double salarioBruto;
    private double descuentoIGSS;
    private int horasExtras;
    private double salarioLiquido;
    private LocalDate fechaPago;

    // Constructor vacío
    public Planilla() {
    }

    // Constructor completo
    public Planilla(int idPlanilla, int idEmpleado, String mesPagado, double salarioBruto, double descuentoIGSS,
                    int horasExtras, double salarioLiquido, LocalDate fechaPago) {
        this.idPlanilla = idPlanilla;
        this.idEmpleado = idEmpleado;
        this.mesPagado = mesPagado;
        this.salarioBruto = salarioBruto;
        this.descuentoIGSS = descuentoIGSS;
        this.horasExtras = horasExtras;
        this.salarioLiquido = salarioLiquido;
        this.fechaPago = fechaPago;
    }

    // Getters y Setters
    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getMesPagado() {
        return mesPagado;
    }

    public void setMesPagado(String mesPagado) {
        this.mesPagado = mesPagado;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getDescuentoIGSS() {
        return descuentoIGSS;
    }

    public void setDescuentoIGSS(double descuentoIGSS) {
        this.descuentoIGSS = descuentoIGSS;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Planilla{" +
                "idPlanilla=" + idPlanilla +
                ", idEmpleado=" + idEmpleado +
                ", mesPagado='" + mesPagado + '\'' +
                ", salarioBruto=" + salarioBruto +
                ", descuentoIGSS=" + descuentoIGSS +
                ", horasExtras=" + horasExtras +
                ", salarioLiquido=" + salarioLiquido +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
