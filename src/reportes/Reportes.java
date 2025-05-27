package reportes;

import model.Empleado;
import model.Planilla;

import java.util.List;

public class Reportes {
    public static void imprimirEmpleados(List<Empleado> empleados) {
        System.out.println("=== REPORTE DE EMPLEADOS ===");
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    public static void imprimirPlanillas(List<Planilla> planillas) {
        System.out.println("=== REPORTE DE PLANILLAS ===");
        for (Planilla p : planillas) {
            System.out.println(p);
        }
    }
}
