package aplicacion;

import modelos.Empleado;
import modelos.Planilla;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Planilla planilla = new Planilla();

        System.out.print("¿Cuántos empleados desea agregar? ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nIngresando datos del empleado #" + (i + 1));
            Empleado nuevoEmpleado = Empleado.crearEmpleadoDesdeConsola();
            planilla.agregarEmpleado(nuevoEmpleado);
        }

        planilla.mostrarEmpleados();
        scanner.close();
    }
}
