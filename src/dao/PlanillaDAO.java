package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Planilla;

public class PlanillaDAO {

    // Datos de conexión
     private final String URL = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3779319";
    private final String USER = "sql3779319";
    private final String PASSWORD = "rWcND2pFSf";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Agregar una nueva planilla
    public void agregarPlanilla(Planilla p) {
        String sql = "INSERT INTO planillas (id_empleado, mes_pagado, salario_bruto, descuento_igss, horas_extras, salario_liquido, fecha_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdEmpleado());
            ps.setString(2, p.getMesPagado());
            ps.setDouble(3, p.getSalarioBruto());
            ps.setDouble(4, p.getDescuentoIGSS());
            ps.setDouble(5, p.getHorasExtras());
            ps.setDouble(6, p.getSalarioLiquido());
            ps.setDate(7, Date.valueOf(p.getFechaPago()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar planilla por ID
    public Planilla buscarPlanillaPorId(int id) {
        String sql = "SELECT * FROM planillas WHERE id_planilla = ?";
        Planilla p = null;
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Planilla(
                    rs.getInt("id_planilla"),
                    rs.getInt("id_empleado"),
                    rs.getString("mes_pagado"),
                    rs.getDouble("salario_bruto"),
                    rs.getDouble("descuento_igss"),
                    rs.getDouble("horas_extras"),
                    rs.getDouble("salario_liquido"),
                    rs.getDate("fecha_pago").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    // Actualizar una planilla
    public void actualizarPlanilla(Planilla p) {
        String sql = "UPDATE planillas SET id_empleado = ?, mes_pagado = ?, salario_bruto = ?, descuento_igss = ?, horas_extras = ?, salario_liquido = ?, fecha_pago = ? WHERE id_planilla = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdEmpleado());
            ps.setString(2, p.getMesPagado());
            ps.setDouble(3, p.getSalarioBruto());
            ps.setDouble(4, p.getDescuentoIGSS());
            ps.setDouble(5, p.getHorasExtras());
            ps.setDouble(6, p.getSalarioLiquido());
            ps.setDate(7, Date.valueOf(p.getFechaPago()));
            ps.setInt(8, p.getIdPlanilla());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar planilla
    public void eliminarPlanilla(int id) {
        String sql = "DELETE FROM planillas WHERE id_planilla = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todas las planillas
    public List<Planilla> listarPlanillas() {
        List<Planilla> lista = new ArrayList<>();
        String sql = "SELECT * FROM planillas";
        try (Connection conn = conectar(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Planilla p = new Planilla(
                    rs.getInt("id_planilla"),
                    rs.getInt("id_empleado"),
                    rs.getString("mes_pagado"),
                    rs.getDouble("salario_bruto"),
                    rs.getDouble("descuento_igss"),
                    rs.getDouble("horas_extras"),
                    rs.getDouble("salario_liquido"),
                    rs.getDate("fecha_pago").toLocalDate()
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Cambia el nombre del método para coincidir con Main.java
    public void insertarPlanilla(Planilla p) {
        agregarPlanilla(p);
    }

    public List<Planilla> obtenerPlanillas() {
        return listarPlanillas();
    }
}
