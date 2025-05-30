package aplicacion;
import servicios.EmpleadoService;
import servicios.PlanillaService;
import model.Empleado;
import model.Planilla;
import utilidades.Utilidades;
import reportes.Reportes;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoService empleadoService = new EmpleadoService();
        PlanillaService planillaService = new PlanillaService();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ingreso de datos personales del Empleado");
            System.out.println("2. Ingreso de la planilla");
            System.out.println("3. Búsqueda del Empleado por ID");
            System.out.println("4. Búsqueda de Planilla por ID");
            System.out.println("5. Actualizar datos del Empleado");
            System.out.println("6. Eliminar datos del Empleado");
            System.out.println("7. Reporte de empleados y su historial de salarios");
            System.out.println("8. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    ingresarEmpleado(empleadoService, scanner);
                    break;
                case 2:
                    ingresarPlanilla(planillaService, scanner);
                    break;
                case 3:
                    buscarEmpleado(empleadoService, scanner);
                    break;
                case 4:
                    buscarPlanilla(planillaService, scanner);
                    break;
                case 5:
                    actualizarEmpleado(empleadoService, scanner);
                    break;
                case 6:
                    eliminarEmpleado(empleadoService, scanner);
                    break;
                case 7:
                    reporteHistorial(empleadoService, planillaService);
                    break;
                 case 8:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }

    private static void ingresarEmpleado(EmpleadoService empleadoService, Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        if (!Utilidades.esCorreoValido(correo)) {
            System.out.println("Correo inválido.");
            return;
        }
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        if (!Utilidades.esTelefonoValido(telefono)) {
            System.out.println("Teléfono inválido.");
            return;
        }
        System.out.print("Fecha de contratación (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDate fecha = Utilidades.parseFecha(fechaStr);
        if (fecha == null) {
            System.out.println("Fecha inválida.");
            return;
        }
        System.out.print("Salario base: ");
        double salario = leerDouble(scanner);
        System.out.print("Estado (Activo/Inactivo): ");
        String estado = scanner.nextLine();
        if (!estado.equals("Activo") && !estado.equals("Inactivo")) {
            System.out.println("Estado inválido.");
            return;
        }
        Empleado emp = new Empleado(0, nombre, apellido, direccion, correo, telefono, fecha, salario, estado);
        empleadoService.crearEmpleado(emp);
        System.out.println("Empleado ingresado correctamente.");
    }

    private static void ingresarPlanilla(PlanillaService planillaService, Scanner scanner) {
        System.out.print("ID Empleado: ");
        int idEmp = leerEntero(scanner);
        System.out.print("Mes pagado (Enero, Febrero, ...): ");
        String mes = scanner.nextLine();
        String[] mesesValidos = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        boolean mesValido = false;
        for (String m : mesesValidos) {
            if (m.equalsIgnoreCase(mes)) {
                mesValido = true;
                mes = m; // Usa el formato correcto
                break;
            }
        }
        if (!mesValido) {
            System.out.println("Mes inválido.");
            return;
        }
        System.out.print("Horas extras: ");
        int horas = leerEntero(scanner);

        // El trigger de la BD calcula el resto, así que los pasas como 0 o null
        Planilla planilla = new Planilla(0, idEmp, mes, 0, 0, horas, 0, null);
        planillaService.crearPlanilla(planilla);
        System.out.println("Planilla ingresada correctamente.");
    }

    private static void buscarEmpleado(EmpleadoService empleadoService, Scanner scanner) {
        System.out.print("ID del empleado: ");
        int idBuscar = leerEntero(scanner);
        Empleado e = empleadoService.buscarEmpleadoPorId(idBuscar);
        if (e != null) {
            System.out.println("\n======= DATOS DEL EMPLEADO =======");
            System.out.println("ID: " + e.getIdEmpleado());
            System.out.println("Nombre: " + e.getNombre() + " " + e.getApellido());
            System.out.println("Dirección: " + e.getDireccion());
            System.out.println("Correo: " + e.getCorreo());
            System.out.println("Teléfono: " + e.getTelefono());
            System.out.println("Fecha de Contratación: " + e.getFechaContratacion());
            System.out.println("Salario Base: Q" + e.getSalarioBase());
            System.out.println("Estado: " + e.getEstado());
            System.out.println("==================================");
        } else {
            System.out.println("No encontrado.");
        }
    }

    private static void buscarPlanilla(PlanillaService planillaService, Scanner scanner) {
        System.out.print("ID de la planilla: ");
        int idBuscar = leerEntero(scanner);
        Planilla encontrada = planillaService.buscarPlanillaPorId(idBuscar);
        System.out.println(encontrada != null ? encontrada : "No encontrada.");
    }

    private static void actualizarEmpleado(EmpleadoService empleadoService, Scanner scanner) {
        System.out.print("ID del empleado a actualizar: ");
        int idActualizar = leerEntero(scanner);
        Empleado actualizar = empleadoService.buscarEmpleadoPorId(idActualizar);
        if (actualizar != null) {
            System.out.print("Nuevo correo: ");
            String nuevoCorreo = scanner.nextLine();
            if (!Utilidades.esCorreoValido(nuevoCorreo)) {
                System.out.println("Correo inválido.");
                return;
            }
            actualizar.setCorreo(nuevoCorreo);
            empleadoService.actualizarEmpleado(actualizar);
            System.out.println("Empleado actualizado correctamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void eliminarEmpleado(EmpleadoService empleadoService, Scanner scanner) {
        System.out.print("ID del empleado a eliminar: ");
        int idEliminar = leerEntero(scanner);
        empleadoService.eliminarEmpleado(idEliminar);
        System.out.println("Empleado eliminado correctamente (si existía).");
    }

    private static void reporteHistorial(EmpleadoService empleadoService, PlanillaService planillaService) {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        List<Planilla> planillas = planillaService.listarPlanillas();

        System.out.println("\n=== REPORTE DE EMPLEADOS Y SU HISTORIAL DE SALARIOS ===");
        System.out.println("Total empleados: " + empleados.size());
        System.out.println("Total planillas: " + planillas.size());
        System.out.println();

        for (Empleado e : empleados) {
            System.out.println("Empleado: " + e.getNombre() + " " + e.getApellido() + " (ID: " + e.getIdEmpleado() + ")");
            boolean tienePlanillas = false;
            for (Planilla p : planillas) {
                if (p.getIdEmpleado() == e.getIdEmpleado()) {
                    System.out.println("  Mes: " + p.getMesPagado() + " | Salario Líquido: " + p.getSalarioLiquido());
                    tienePlanillas = true;
                }
            }
            if (!tienePlanillas) {
                System.out.println("  Sin historial de salarios.");
            }
            System.out.println();
        }
    }

    // Métodos auxiliares para evitar errores de input
    private static int leerEntero(Scanner scanner) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private static double leerDouble(Scanner scanner) {
        while (true) {
            try {
                double valor = Double.parseDouble(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
