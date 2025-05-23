package aplicacion;
import dao.EmpleadoDAO;
import dao.PlanillaDAO;
import model.Empleado;
import model.Planilla;

import java.time.LocalDate;
import java.util.List;

public class Main {
    private final String URL = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3779319";

    public static void main(String[] args) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        PlanillaDAO planillaDAO = new PlanillaDAO();

        // ================================
        // 1. CRUD de EMPLEADOS
        // ================================
        System.out.println("==== EMPLEADOS ====");

        // Insertar nuevo empleado
        Empleado emp = new Empleado(0, "Juan", "Pérez", "Zona 1", "juan@email.com",
                "55551234", LocalDate.parse("2024-01-15"), 3500.0, "Activo");
        empleadoDAO.insertarEmpleado(emp);

        // Obtener todos los empleados
        List<Empleado> empleados = empleadoDAO.obtenerEmpleados();
        empleados.forEach(System.out::println);

        // Buscar empleado por ID
        Empleado empleadoEncontrado = empleadoDAO.buscarEmpleadoPorId(1);
        if (empleadoEncontrado != null) {
            System.out.println("Empleado encontrado: " + empleadoEncontrado);
        }

        // Actualizar empleado
        empleadoEncontrado.setCorreo("nuevo@email.com");
        empleadoDAO.actualizarEmpleado(empleadoEncontrado);

        // Eliminar empleado
        // empleadoDAO.eliminarEmpleado(1);  // Descomenta para probar

        // ================================
        // 2. CRUD de PLANILLAS
        // ================================
        System.out.println("\n==== PLANILLAS ====");

        // Insertar nueva planilla
        Planilla planilla = new Planilla(0, 1, "Mayo", 3500.0, 130.0,
                10.0, 3450.0, LocalDate.now());
        planillaDAO.insertarPlanilla(planilla);

        // Obtener todas las planillas
        List<Planilla> planillas = planillaDAO.obtenerPlanillas();
        planillas.forEach(System.out::println);

        // Buscar planilla por ID
        Planilla planillaEncontrada = planillaDAO.buscarPlanillaPorId(1);
        if (planillaEncontrada != null) {
            System.out.println("Planilla encontrada: " + planillaEncontrada);
        }

        // Actualizar planilla
        planillaEncontrada.setHorasExtras(15.0);
        planillaDAO.actualizarPlanilla(planillaEncontrada);

        // Eliminar planilla
        // planillaDAO.eliminarPlanilla(1);  // Descomenta para probar
    }
}
