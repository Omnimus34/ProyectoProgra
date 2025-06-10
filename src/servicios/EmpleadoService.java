package servicios;

import BD.EmpleadoBD;
import java.util.Scanner;
import model.Empleado;

public class EmpleadoService {
    
    Scanner Input = new Scanner(System.in);
    EmpleadoBD EmpBD = new EmpleadoBD();
    
    /* En este metodo simplemente estamos ingresando nuevos empleados */
    public void IngresarEmpleado(){
        System.out.println("Ingrese cantidad de empleados nuevos");
        int N = Input.nextInt();
        Input.nextLine(); // <-- Limpia el salto de línea pendiente

        for(int i =1; i <= N; i++)
        {
            Empleado Emp = new Empleado();

            System.out.println("Nombre: ");
            Emp.setNombre(Input.nextLine());
            System.out.println("Apellido: ");
            Emp.setApellido(Input.nextLine());
            System.out.println("Direccion: ");
            Emp.setDireccion(Input.nextLine());
            System.out.println("Email: ");
            Emp.setEmail(Input.nextLine());
            System.out.println("Telefono");
            Emp.setTelefono(Input.nextInt());
            Input.nextLine(); 

            int idGenerado = EmpBD.NuevoEmpleado(Emp);
            if(idGenerado != -1)
            {
                System.out.println("Guardado Exitoso");
                System.out.println("ID desigando: "+ idGenerado);
            }
            else
            {
                System.out.println("Error con la Base de Datos");
            }
        } 
    }
    
    /* En este metodo ingreamos un ID y nos retorna la informacion del empleado*/
    public void BuscarEmpleadoId()
    {
        System.out.println("Ingrese ID del Empleado");
        int idBuscar = Input.nextInt();
        
        Empleado Emp = EmpBD.BuscarEmpleadoId(idBuscar);
        
        if(Emp != null)
        {
            System.out.println("\n======= DATOS DEL EMPLEADO =======");
            System.out.println("ID: " + Emp.getIdEmpleado());
            System.out.println("Nombre: " + Emp.getNombre() + " " + Emp.getApellido());
            System.out.println("Dirección: " + Emp.getDireccion());
            System.out.println("Correo: " + Emp.getEmail());
            System.out.println("Teléfono: " + Emp.getTelefono());
            System.out.println("==================================");
        }
        else
        {
            System.out.println("Empleado no encontrado");
        }
        
    }

    /* En este metodo simplemente buscamos un ID y cambiamos los datos requeridos*/
    public void ActualizarEmpleado() 
    {
        System.out.println("Ingrese ID del Empleado");
        int idActualizar = Input.nextInt();
        Input.nextLine(); 

        Empleado Emp = EmpBD.BuscarEmpleadoId(idActualizar);

        if (Emp != null) {
            System.out.println("Dejar en blanco los campos que no desea actualizar");

            System.out.println("Nombre actual: " + Emp.getNombre());
            System.out.print("Nuevo nombre: ");
            String nuevoNombre = Input.nextLine();
            if (!nuevoNombre.isBlank()) Emp.setNombre(nuevoNombre);

            System.out.println("Apellido actual: " + Emp.getApellido());
            System.out.print("Nuevo apellido: ");
            String nuevoApellido = Input.nextLine();
            if (!nuevoApellido.isBlank()) Emp.setApellido(nuevoApellido);

            System.out.println("Dirección actual: " + Emp.getDireccion());
            System.out.print("Nueva dirección: ");
            String nuevaDireccion = Input.nextLine();
            if (!nuevaDireccion.isBlank()) Emp.setDireccion(nuevaDireccion);

            System.out.println("Email actual: " + Emp.getEmail());
            System.out.print("Nuevo email: ");
            String nuevoEmail = Input.nextLine();
            if (!nuevoEmail.isBlank()) Emp.setEmail(nuevoEmail);

            System.out.println("Teléfono actual: " + Emp.getTelefono());
            System.out.print("Nuevo teléfono: ");
            String nuevoTel = Input.nextLine();
            if (!nuevoTel.isBlank()) Emp.setTelefono(Integer.parseInt(nuevoTel));

            EmpBD.ActualizarEmpleadoBD(Emp);

            System.out.println("Actualización exitosa.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    /* En este metodod ingremos un ID y este borra la informacion relacionada*/
    public void EliminarEmpleado()
    {
        System.out.println("Ingrese ID del Empleado");
        int idEliminar = Input.nextInt();
        
        boolean eliminar = EmpBD.EliminarEmpleado(idEliminar);

        if (eliminar)
        {
            System.out.println("Se elimino con exito");
        }
        else
        {
             System.out.println("Empleado no encontrado o error al eliminar");
        }
    }
}
