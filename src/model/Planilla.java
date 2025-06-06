package model;

import java.util.Date;


public class Planilla {
    
    int ID;
    int IdEmpleado;
    String MesPagado;
    double Salario;
    double DescuentoIgss;
    int HorasExtra;
    double SalarioLiquido;

    public Planilla() {
    }

    public Planilla(int ID, int IdEmpleado, String MesPagado, double Salario, double DescuentoIgss, int HorasExtra, double SalarioLiquido) {
        this.ID = ID;
        this.IdEmpleado = IdEmpleado;
        this.MesPagado = MesPagado;
        this.Salario = Salario;
        this.DescuentoIgss = DescuentoIgss;
        this.HorasExtra = HorasExtra;
        this.SalarioLiquido = SalarioLiquido;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public String getMesPagado() {
        return MesPagado;
    }

    public void setMesPagado(String MesPagado) {
        this.MesPagado = MesPagado;
    }

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double Salario) {
        this.Salario = Salario;
    }

    public double getDescuentoIgss() {
        return DescuentoIgss;
    }

    public void setDescuentoIgss(double DescuentoIgss) {
        this.DescuentoIgss = DescuentoIgss;
    }

    public int getHorasExtra() {
        return HorasExtra;
    }

    public void setHorasExtra(int HorasExtra) {
        this.HorasExtra = HorasExtra;
    }

    public double getSalarioLiquido() {
        return SalarioLiquido;
    }

    public void setSalarioLiquido(double SalarioLiquido) {
        this.SalarioLiquido = SalarioLiquido;
    }

}
