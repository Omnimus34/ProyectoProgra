package servicios;

import BD.PlanillaBD;
import java.util.Scanner;
import model.Empleado;
import model.Planilla;
import BD.EmpleadoBD;
import java.util.List;

public class PlanillaService {
    
    Scanner Input = new Scanner(System.in);
    PlanillaBD PlaBD = new PlanillaBD();
    EmpleadoBD EmpBD = new EmpleadoBD();
    
    public void IngresarPlanilla()
    {
        System.out.println("Ingrese cantidad de planillas");
        int N = Input.nextInt();
        for(int i =1; i <= N; i++)
        {
           System.out.println("Ingrese ID del Empleado");
            int idBuscar = Input.nextInt();
        
            Empleado Emp = EmpBD.BuscarEmpleadoId(idBuscar);
            
            if(Emp != null)
            {
            Planilla Pla = new Planilla();
            Pla.setIdEmpleado(Emp.getIdEmpleado());
            
            System.out.println("Mes Pagado: ");
            Input.nextLine();
            Pla.setMesPagado(Input.nextLine());
            System.out.println("Salario: ");
            Pla.setSalario(Input.nextDouble());
            System.out.println("Descuento IGSS: ");
            Pla.setDescuentoIgss(Input.nextDouble());
            System.out.println("Horas Extra: ");
            Pla.setHorasExtra(Input.nextInt());
            
            double igss = Pla.getSalario() * 0.0483;
            double PagoHoraExtra = (Pla.getSalario()/160) * 1.25 * Pla.getHorasExtra();
            double salarioLiquido = Pla.getSalario() - igss + PagoHoraExtra;
            
            Pla.setSalarioLiquido(salarioLiquido);
            
            int idGenerado = PlaBD.EmpleadoPlanilla(Pla);
                if(idGenerado != -1)
                {
                    System.out.println("Guardado Exitoso");
                    System.out.println("ID desigando: "+ idGenerado);
                }
                else
                {
                    System.out.println("Error con la Base de Datos");
                }
                PlaBD.EmpleadoPlanilla(Pla);
            }
            else
            {
                System.out.println("Empleado no encontrado");
            }
        } 
    }
    
    public void BuscarPlanillaId()
    {
        System.out.println("Ingrese ID del Empleado");
        int idBuscar = Input.nextInt();
        
        Planilla Pla = PlaBD.BuscarIdPlanilla(idBuscar);
        
        if(Pla != null)
        {
            Empleado Emp = EmpBD.BuscarEmpleadoId(Pla.getIdEmpleado());
            
            System.out.println("\n======= DATOS DE LA PLANILLA =======");
            System.out.println("ID: " + Pla.getIdEmpleado());
            System.out.println("Nombre: " + Emp.getNombre() + " " + Emp.getApellido());
            System.out.println("Mes Pagado: " + Pla.getMesPagado());
            System.out.println("Salario: " + Pla.getSalario());
            System.out.println("Igss: " + Pla.getDescuentoIgss());
            System.out.println("Horas Extra: " + Pla.getHorasExtra());
            System.out.println("Salario Liquido: "+ Pla.getSalarioLiquido());
            System.out.println("==================================");
        }
        else
        {
            System.out.println("No se encontró planilla con ese ID.");
        }
    }
    
    public void MostrarReportePlanillas() 
    {
    List<Planilla> lista = PlaBD.Reportes();

    System.out.println("\n================ REPORTE DE PLANILLAS ================");
    System.out.printf("%-5s %-15s %-10s %-12s %-10s %-10s %-15s%n",
        "ID", "Empleado", "Mes", "Salario", "IGSS", "Extras", "Salario Neto");
    System.out.println("--------------------------------------------------------------");

    for (Planilla p : lista) {
        Empleado emp = EmpBD.BuscarEmpleadoId(p.getIdEmpleado());
        String nombre = (emp != null) ? emp.getNombre() + " " + emp.getApellido() : "No encontrado";

        System.out.printf("%-5d %-15s %-10s Q%-11.2f Q%-9.2f %-10d Q%-14.2f%n",
            p.getIdEmpleado(),
            nombre,
            p.getMesPagado(),
            p.getSalario(),
            p.getDescuentoIgss(),
            p.getHorasExtra(),
            p.getSalarioLiquido());
    }

    System.out.println("===============================================================");
}

}
