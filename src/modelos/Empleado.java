package modelos;

import java.util.Scanner;

public class Empleado {
    private String nombre;
    private int edad;
    private double salario;

    public Empleado(String nombre, int edad, double salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
    }

    public static Empleado crearEmpleadoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese nombre del empleado: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese edad del empleado: ");
        int edad = scanner.nextInt();

        System.out.print("Ingrese salario del empleado: ");
        double salario = scanner.nextDouble();

        return new Empleado(nombre, edad, salario);
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public double getSalario() { return salario; }

    @Override
    public String toString() {
        return "Empleado{nombre='" + nombre + "', edad=" + edad + ", salario=" + salario + '}';
    }
}
