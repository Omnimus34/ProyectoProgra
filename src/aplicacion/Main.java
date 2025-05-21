package aplicacion;

import modelos.Empleado;
import modelos.Planilla;
import persistencia.PlanillaDAO;


public class Main {
    public static void main(String[] args) {
        Planilla planilla = new Planilla();

        // Crear empleados desde consola
        for (int i = 0; i < 2; i++) {
            Empleado empleado = Empleado.crearEmpleadoDesdeConsola();
            planilla.agregarEmpleado(empleado);
        }

        // Mostrar y guardar planilla
        planilla.mostrarEmpleados();

        PlanillaDAO planillaDAO = new PlanillaDAO();
        planillaDAO.guardarPlanilla(planilla);
    }
}
