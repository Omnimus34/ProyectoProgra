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

    public static void imprimirHistorialSalarios(List<Empleado> empleados, List<Planilla> planillas) {
        System.out.println("\n=== REPORTE DE EMPLEADOS Y SU HISTORIAL DE SALARIOS ===");
        System.out.println("Total empleados: " + empleados.size());
        System.out.println("Total planillas: " + planillas.size());
        System.out.println();

        for (Empleado e : empleados) {
            System.out.println("Empleado: " + e.getNombre() + " " + e.getApellido() + " (ID: " + e.getIdEmpleado() + ")");
            boolean tienePlanillas = false;
            for (Planilla p : planillas) {
                if (p.getIdEmpleado() == e.getIdEmpleado()) {
                    System.out.println("  Mes: " + p.getMesPagado() + " | Salario Líquido: " + p.getSalarioLiquido());
                    tienePlanillas = true;
                }
            }
            if (!tienePlanillas) {
                System.out.println("  Sin historial de salarios.");
            }
            System.out.println();
        }
    }
}
