package servicios;

import dao.PlanillaDAO;
import model.Planilla;

import java.util.List;

public class PlanillaService {
    private final PlanillaDAO planillaDAO;

    public PlanillaService() {
        this.planillaDAO = new PlanillaDAO();
    }

    public void crearPlanilla(Planilla planilla) {
        // Validaciones si quieres
        planillaDAO.insertarPlanilla(planilla);
    }

    public List<Planilla> listarPlanillas() {
        return planillaDAO.obtenerPlanillas();
    }

    public Planilla buscarPlanillaPorId(int id) {
        return planillaDAO.buscarPlanillaPorId(id);
    }

    public void actualizarPlanilla(Planilla planilla) {
        planillaDAO.actualizarPlanilla(planilla);
    }

    public void eliminarPlanilla(int id) {
        planillaDAO.eliminarPlanilla(id);
    }
}
