package persistencia;

import modelos.Empleado;
import modelos.Planilla;

public class PlanillaDAO {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public void guardarPlanilla(Planilla planilla) {
        for (Empleado empleado : planilla.getEmpleados()) {
            empleadoDAO.guardarEmpleado(empleado);
        }
    }
}
