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
        String sql = "INSERT INTO planillas (id_empleado, mes_pagado, horas_extras) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdEmpleado());
            ps.setString(2, p.getMesPagado());
            ps.setInt(3, p.getHorasExtras());
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
                    rs.getInt("horas_extras"), // <-- CAMBIA AQUÍ
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
        String sql = "UPDATE planillas SET horas_extras = ? WHERE id_planilla = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getHorasExtras());
            ps.setInt(2, p.getIdPlanilla());
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
                    rs.getInt("horas_extras"), // <-- CAMBIA AQUÍ
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
