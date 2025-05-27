package servicios;

import dao.EmpleadoDAO;
import model.Empleado;

import java.util.List;

public class EmpleadoService {
    private final EmpleadoDAO empleadoDAO;

    public EmpleadoService() {
        this.empleadoDAO = new EmpleadoDAO();
    }

    public void crearEmpleado(Empleado emp) {
        // Aquí puedes agregar validaciones si quieres
        empleadoDAO.insertarEmpleado(emp);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoDAO.obtenerEmpleados();
    }

    public Empleado buscarEmpleadoPorId(int id) {
        return empleadoDAO.buscarEmpleadoPorId(id);
    }

    public void actualizarEmpleado(Empleado emp) {
        empleadoDAO.actualizarEmpleado(emp);
    }

    public void eliminarEmpleado(int id) {
        empleadoDAO.eliminarEmpleado(id);
    }
}
