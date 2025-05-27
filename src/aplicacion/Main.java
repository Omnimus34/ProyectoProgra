package aplicacion;
import dao.EmpleadoDAO;
import dao.PlanillaDAO;
import model.Empleado;
import model.Planilla;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        PlanillaDAO planillaDAO = new PlanillaDAO();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. CRUD Empleados");
            System.out.println("2. CRUD Planillas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    menuEmpleados(empleadoDAO, scanner);
                    break;
                case 2:
                    menuPlanillas(planillaDAO, scanner);
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

    private static void menuEmpleados(EmpleadoDAO empleadoDAO, Scanner scanner) {
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
            int op = scanner.nextInt();
            scanner.nextLine();

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
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Fecha de contratación (YYYY-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(scanner.nextLine());
                    System.out.print("Salario base: ");
                    double salario = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Estado: ");
                    String estado = scanner.nextLine();
                    Empleado emp = new Empleado(0, nombre, apellido, direccion, correo, telefono, fecha, salario, estado);
                    empleadoDAO.insertarEmpleado(emp);
                    break;
                case 2:
                    List<Empleado> empleados = empleadoDAO.obtenerEmpleados();
                    empleados.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("ID del empleado: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();
                    Empleado encontrado = empleadoDAO.buscarEmpleadoPorId(idBuscar);
                    System.out.println(encontrado != null ? encontrado : "No encontrado.");
                    break;
                case 4:
                    System.out.print("ID del empleado a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    Empleado actualizar = empleadoDAO.buscarEmpleadoPorId(idActualizar);
                    if (actualizar != null) {
                        System.out.print("Nuevo correo: ");
                        actualizar.setCorreo(scanner.nextLine());
                        empleadoDAO.actualizarEmpleado(actualizar);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("ID del empleado a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();
                    empleadoDAO.eliminarEmpleado(idEliminar);
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuPlanillas(PlanillaDAO planillaDAO, Scanner scanner) {
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
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.print("ID Empleado: ");
                    int idEmp = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Mes pagado: ");
                    String mes = scanner.nextLine();
                    System.out.print("Salario bruto: ");
                    double bruto = scanner.nextDouble();
                    System.out.print("Descuento IGSS: ");
                    double igss = scanner.nextDouble();
                    System.out.print("Horas extras: ");
                    double horas = scanner.nextDouble();
                    System.out.print("Salario líquido: ");
                    double liquido = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Fecha de pago (YYYY-MM-DD): ");
                    LocalDate fechaPago = LocalDate.parse(scanner.nextLine());
                    Planilla planilla = new Planilla(0, idEmp, mes, bruto, igss, horas, liquido, fechaPago);
                    planillaDAO.insertarPlanilla(planilla);
                    break;
                case 2:
                    List<Planilla> planillas = planillaDAO.obtenerPlanillas();
                    planillas.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("ID de la planilla: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();
                    Planilla encontrada = planillaDAO.buscarPlanillaPorId(idBuscar);
                    System.out.println(encontrada != null ? encontrada : "No encontrada.");
                    break;
                case 4:
                    System.out.print("ID de la planilla a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    Planilla actualizar = planillaDAO.buscarPlanillaPorId(idActualizar);
                    if (actualizar != null) {
                        System.out.print("Nuevas horas extras: ");
                        actualizar.setHorasExtras(scanner.nextDouble());
                        scanner.nextLine();
                        planillaDAO.actualizarPlanilla(actualizar);
                    } else {
                        System.out.println("Planilla no encontrada.");
                    }
                    break;
                case 5:
                    System.out.print("ID de la planilla a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();
                    planillaDAO.eliminarPlanilla(idEliminar);
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
