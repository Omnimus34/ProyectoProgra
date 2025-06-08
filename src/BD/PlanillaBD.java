package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Planilla;

public class PlanillaBD {

    /* Estos son los datos que utiliza el driver, para conectarse a la BD, estos datos ps van a variar */
    static String URL = "jdbc:mysql://localhost:3306/sys";
    static String USER = "root";
    static String PASSWORD = "77954004";

    private Connection Con() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    /* Este metodo inserta la informacion tomada en PlanillaService en la BD*/
    public int EmpleadoPlanilla(Planilla Pla)
    {
        String sql = "INSERT INTO planillas (id_empleado, mes_pagado, salario_bruto, descuento_igss, horas_extras, salario_liquido) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection conn = Con();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, Pla.getIdEmpleado());
            ps.setString(2, Pla.getMesPagado());
            ps.setDouble(3, Pla.getSalario());
            ps.setDouble(4, Pla.getDescuentoIgss());
            ps.setInt(5, Pla.getHorasExtra());
            ps.setDouble(6, Pla.getSalarioLiquido());
            int filas = ps.executeUpdate();
            
            if(filas >0)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                {
                    return rs.getInt(1);
                }
            }

        } 
        catch (SQLException e) 
        {
            System.out.println(" Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }
    
    /* Aqui mediante un ID buscamos la informacion que deseamos */
    public Planilla BuscarIdPlanilla(int id)
    {
        String sql = "SELECT * FROM planillas WHERE id_planilla = ?";
        Planilla Pla = null;
        try(Connection conn = Con();
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Pla = new Planilla(
                        rs.getInt("id_planilla"), 
                        rs.getInt("id_empleado"),
                        rs.getString("mes_pagado"),
                        rs.getDouble("salario_bruto"),
                        rs.getDouble("descuento_igss"),
                        rs.getInt("horas_extras"),
                        rs.getDouble("salario_liquido")
                );
            }
        }
        catch (SQLException e) 
        {
            System.out.println(" Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
        }
        return Pla;
    }   
    
    /* Este metodo genera un reporte de todas las planillas */
    public List<Planilla> Reportes() {
    List<Planilla> lista = new ArrayList<>();
    String sql = "SELECT * FROM planillas";
    
    try (Connection conn = Con();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            Planilla pla = new Planilla(
                rs.getInt("id_planilla"),
                rs.getInt("id_empleado"),
                rs.getString("mes_pagado"),
                rs.getDouble("salario_bruto"),
                rs.getDouble("descuento_igss"),
                rs.getInt("horas_extras"),
                rs.getDouble("salario_liquido")
            );
            lista.add(pla);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar planillas: " + e.getMessage());
    }
    return lista;
}
}
