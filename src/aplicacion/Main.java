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
            System.out.println("1. CRUD Empleados");
            System.out.println("2. CRUD Planillas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    menuEmpleados(empleadoService, scanner);
                    break;
                case 2:
                    menuPlanillas(planillaService, scanner);
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }

    private static void menuEmpleados(EmpleadoService empleadoService, Scanner scanner) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- CRUD Empleados ---");
            System.out.println("1. Insertar empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Buscar empleado por ID");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int op = leerEntero(scanner);

            switch (op) {
                case 1:
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
                        break;
                    }
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    if (!Utilidades.esTelefonoValido(telefono)) {
                        System.out.println("Teléfono inválido.");
                        break;
                    }
                    System.out.print("Fecha de contratación (YYYY-MM-DD): ");
                    String fechaStr = scanner.nextLine();
                    LocalDate fecha = Utilidades.parseFecha(fechaStr);
                    if (fecha == null) {
                        System.out.println("Fecha inválida.");
                        break;
                    }
                    System.out.print("Salario base: ");
                    double salario = leerDouble(scanner);
                    System.out.print("Estado: ");
                    String estado = scanner.nextLine();
                    Empleado emp = new Empleado(0, nombre, apellido, direccion, correo, telefono, fecha, salario, estado);
                    empleadoService.crearEmpleado(emp);
                    break;
                case 2:
                    List<Empleado> empleados = empleadoService.listarEmpleados();
                    Reportes.imprimirEmpleados(empleados);
                    break;
                case 3:
                    System.out.print("ID del empleado: ");
                    int idBuscar = leerEntero(scanner);
                    Empleado encontrado = empleadoService.buscarEmpleadoPorId(idBuscar);
                    System.out.println(encontrado != null ? encontrado : "No encontrado.");
                    break;
                case 4:
                    System.out.print("ID del empleado a actualizar: ");
                    int idActualizar = leerEntero(scanner);
                    Empleado actualizar = empleadoService.buscarEmpleadoPorId(idActualizar);
                    if (actualizar != null) {
                        System.out.print("Nuevo correo: ");
                        String nuevoCorreo = scanner.nextLine();
                        if (!Utilidades.esCorreoValido(nuevoCorreo)) {
                            System.out.println("Correo inválido.");
                            break;
                        }
                        actualizar.setCorreo(nuevoCorreo);
                        empleadoService.actualizarEmpleado(actualizar);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("ID del empleado a eliminar: ");
                    int idEliminar = leerEntero(scanner);
                    empleadoService.eliminarEmpleado(idEliminar);
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuPlanillas(PlanillaService planillaService, Scanner scanner) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- CRUD Planillas ---");
            System.out.println("1. Insertar planilla");
            System.out.println("2. Listar planillas");
            System.out.println("3. Buscar planilla por ID");
            System.out.println("4. Actualizar planilla");
            System.out.println("5. Eliminar planilla");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int op = leerEntero(scanner);

            switch (op) {
                case 1:
                    System.out.print("ID Empleado: ");
                    int idEmp = leerEntero(scanner);
                    System.out.print("Mes pagado: ");
                    String mes = scanner.nextLine();
                    System.out.print("Salario bruto: ");
                    double bruto = leerDouble(scanner);
                    System.out.print("Descuento IGSS: ");
                    double igss = leerDouble(scanner);
                    System.out.print("Horas extras: ");
                    double horas = leerDouble(scanner);
                    System.out.print("Salario líquido: ");
                    double liquido = leerDouble(scanner);
                    System.out.print("Fecha de pago (YYYY-MM-DD): ");
                    String fechaPagoStr = scanner.nextLine();
                    LocalDate fechaPago = Utilidades.parseFecha(fechaPagoStr);
                    if (fechaPago == null) {
                        System.out.println("Fecha inválida.");
                        break;
                    }
                    Planilla planilla = new Planilla(0, idEmp, mes, bruto, igss, horas, liquido, fechaPago);
                    planillaService.crearPlanilla(planilla);
                    break;
                case 2:
                    List<Planilla> planillas = planillaService.listarPlanillas();
                    Reportes.imprimirPlanillas(planillas);
                    break;
                case 3:
                    System.out.print("ID de la planilla: ");
                    int idBuscar = leerEntero(scanner);
                    Planilla encontrada = planillaService.buscarPlanillaPorId(idBuscar);
                    System.out.println(encontrada != null ? encontrada : "No encontrada.");
                    break;
                case 4:
                    System.out.print("ID de la planilla a actualizar: ");
                    int idActualizar = leerEntero(scanner);
                    Planilla actualizar = planillaService.buscarPlanillaPorId(idActualizar);
                    if (actualizar != null) {
                        System.out.print("Nuevas horas extras: ");
                        actualizar.setHorasExtras(leerDouble(scanner));
                        planillaService.actualizarPlanilla(actualizar);
                    } else {
                        System.out.println("Planilla no encontrada.");
                    }
                    break;
                case 5:
                    System.out.print("ID de la planilla a eliminar: ");
                    int idEliminar = leerEntero(scanner);
                    planillaService.eliminarPlanilla(idEliminar);
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
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
