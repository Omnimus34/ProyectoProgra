package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;

public class EmpleadoDAO {

    // Datos de conexión
    private final String URL = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3779319";
    private final String USER = "sql3779319";
    private final String PASSWORD = "rWcND2pFSf";
    

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Agregar un nuevo empleado
    public void agregarEmpleado(Empleado emp) {
        String sql = "INSERT INTO empleados (nombre, apellido, direccion, correo, telefono, fecha_contratacion, salario_base, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());
            ps.setString(3, emp.getDireccion());
            ps.setString(4, emp.getCorreo());
            ps.setString(5, emp.getTelefono());
            ps.setDate(6, Date.valueOf(emp.getFechaContratacion()));
            ps.setDouble(7, emp.getSalarioBase());
            ps.setString(8, emp.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar empleado por ID
    public Empleado buscarEmpleadoPorId(int id) {
        String sql = "SELECT * FROM empleados WHERE id_empleado = ?";
        Empleado emp = null;
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getDate("fecha_contratacion").toLocalDate(),
                    rs.getDouble("salario_base"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    // Actualizar empleado
    public void actualizarEmpleado(Empleado emp) {
        String sql = "UPDATE empleados SET nombre = ?, apellido = ?, direccion = ?, correo = ?, telefono = ?, fecha_contratacion = ?, salario_base = ?, estado = ? WHERE id_empleado = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());
            ps.setString(3, emp.getDireccion());
            ps.setString(4, emp.getCorreo());
            ps.setString(5, emp.getTelefono());
            ps.setDate(6, Date.valueOf(emp.getFechaContratacion()));
            ps.setDouble(7, emp.getSalarioBase());
            ps.setString(8, emp.getEstado());
            ps.setInt(9, emp.getIdEmpleado());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar empleado por ID
    public void eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleados WHERE id_empleado = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos los empleados
    public List<Empleado> listarEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection conn = conectar(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Empleado emp = new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getDate("fecha_contratacion").toLocalDate(),
                    rs.getDouble("salario_base"),
                    rs.getString("estado")
                );
                lista.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertarEmpleado(Empleado emp) {
        agregarEmpleado(emp);
    }

    public List<Empleado> obtenerEmpleados() {
        return listarEmpleados();
    }
}
