package BD;

import java.sql.*;
import model.Empleado;

public class EmpleadoBD {
 
    /*Estos son los datos que utiliza el driver, para conectarse a la BD, estos datos ps van a variar*/
    private final String URL = "jdbc:mysql://localhost:3306/sys";
    private final String USER = "root";
    private final String PASSWORD = "77954004";

    /* Aqui simplemente le estamos diciendo que queremos conectarnos a la BD  con los datos de arriba y que si los datos son correctos por se conecta*/
    public Connection Con() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /* Aqui tomamos los datos del empleado y los insertamos en la base de datos */
    public int NuevoEmpleado(Empleado Emp) {
        String sql = "INSERT INTO empleados (nombre, apellido, direccion, correo, telefono) VALUES (?, ?, ?, ?, ?) ";
        try(Connection conn = Con(); 
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, Emp.getNombre());
            ps.setString(2, Emp.getApellido());
            ps.setString(3, Emp.getDireccion());
            ps.setString(4, Emp.getEmail());
            ps.setInt(5, Emp.getTelefono());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                return rs.getInt(1);
            }
        }
        catch (SQLException e) 
        {
        System.out.println(" Error al guardar datos: " + e.getMessage());
        e.printStackTrace(); 
        }
        return -1;
    }
    
    /* Este metodo busca un empleado por su ID, si lo encuentra devuelve un objeto Empleado, si no lo encuentra devuelve null */
    public Empleado BuscarEmpleadoId(int id)
    {
        String sql = "SELECT * FROM empleados WHERE id_empleado = ?";
        Empleado Emp = null;
        try(Connection conn = Con(); 
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Emp  = new Empleado(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getInt("telefono")
                );
            }
        }
        catch (SQLException e)
        {
                e.printStackTrace();
        }
        return Emp;
    }
    /* Este metodo actualiza un empleado en la base de datos */
    public void ActualizarEmpleadoBD(Empleado Emp)
    {
        String sql = "UPDATE empleados SET nombre= ?, apellido = ?, direccion = ?, correo = ?, telefono = ? WHERE id_empleado = ?";
        try(Connection conn = Con();
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, Emp.getNombre());
            ps.setString(2, Emp.getApellido());
            ps.setString(3, Emp.getDireccion());
            ps.setString(4, Emp.getEmail());
            ps.setInt(5, Emp.getTelefono()); 
            ps.setInt(6, Emp.getIdEmpleado());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    /*Este metodo elimina un empleado de la base de datos */
    public boolean EliminarEmpleado(int id)
    {
        String sql = "DELETE  FROM empleados WHERE id_empleado = ?";
        try(Connection conn = Con();
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
    