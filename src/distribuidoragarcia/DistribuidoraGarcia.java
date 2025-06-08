package distribuidoragarcia;

import java.util.Scanner;
import servicios.EmpleadoService;
import servicios.PlanillaService;

public class DistribuidoraGarcia {

    public static void main(String[] args) {
        
        int Op = 0;
        boolean Salir = false;
        Scanner Input = new Scanner(System.in);
        
        EmpleadoService  EmSe = new EmpleadoService();
        PlanillaService PlaSe = new PlanillaService();
        
        while (!Salir)
        {
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ingreso de datos personales del Empleado");
            System.out.println("2. Ingreso de la planilla");
            System.out.println("3. Búsqueda del Empleado por ID");
            System.out.println("4. Búsqueda de Planilla por ID");
            System.out.println("5. Actualizar datos del Empleado");
            System.out.println("6. Eliminar datos del Empleado");
            System.out.println("7. Reporte de empleados y su historial de salarios");
            System.out.println("8. Salir");
            
            System.out.println("Seleccione una opcion ");
            Op = Input.nextInt();
            
            switch(Op)
            {
                case 1: EmSe.IngresarEmpleado();
                break;
                case 2: PlaSe.IngresarPlanilla();
                break;
                case 3: EmSe.BuscarEmpleadoId();
                break;
                case 4: PlaSe.BuscarPlanillaId();
                break;
                case 5: EmSe.ActualizarEmpleado();
                break;
                case 6: EmSe.EliminarEmpleado();
                break;
                case 7: PlaSe.MostrarReportePlanillas();
                break;
                case 8: Salir = true;
                break;
                    
            }
        }
    }
}