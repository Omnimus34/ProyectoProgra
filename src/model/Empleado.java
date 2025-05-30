package model;

import java.time.LocalDate;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String telefono;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private String estado;

    public Empleado() {}

    public Empleado(int idEmpleado, String nombre, String apellido, String direccion, String correo, String telefono,
                    LocalDate fechaContratacion, double salarioBase, String estado) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
        this.salarioBase = salarioBase;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return  "----------------------------------------\n" +
                "ID: " + idEmpleado + "\n" +
                "Nombre: " + nombre + " " + apellido + "\n" +
                "Dirección: " + direccion + "\n" +
                "Correo: " + correo + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Fecha de Contratación: " + fechaContratacion + "\n" +
                "Salario Base: Q" + salarioBase + "\n" +
                "Estado: " + estado + "\n" +
                "----------------------------------------";
    }
}

