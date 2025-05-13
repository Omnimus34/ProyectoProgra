package modelos;

import java.util.ArrayList;
import java.util.List;

public class Planilla {
    private List<Empleado> empleados;

    public Planilla() {
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void mostrarEmpleados() {
        System.out.println("\nLista de empleados:");
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }
}
