package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDao;

public class ServicioEditorial {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    EditorialDao dao = new EditorialDao();

    public Editorial crearEditorial(String nombre) throws Exception {
        try {
            Editorial e = new Editorial();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Necesita un Nombre");
            }
            if (comprobarNombre(nombre)) {
                throw new Exception("Editorial en Existencia");
            }
            e.setNombre(nombre);
            dao.ingresarEditorial(e);
            return e;
        } catch (Exception ex) {
            System.err.println("Error en crearEditorial()");
            throw ex;
        }
    }

    public void mostrarEditorial(Editorial a) throws Exception {
        try {
            if (a == null) {
                throw new Exception("Necesita una Editorial");
            }
            System.out.println(a.toString());
        } catch (Exception e) {
            System.err.println("Error en mostrarEditorial()");
            throw e;
        }
    }

    public Editorial encontrarId() throws Exception {
        try {
            System.out.print("Numero Id de la Editorial:");
            Integer id = sc.nextInt();
            if (id == null) {
                throw new Exception("Necesita un Id");
            }
            if (dao.buscarId(id) == null) {
                throw new Exception("No existe tal editorial");
            }
            return dao.buscarId(id);
        } catch (Exception e) {
            System.err.println("Error en encontrarId()");
            throw e;
        }
    }

    public void darBaja(Editorial e) throws Exception {
        try {
            if (e == null) {
                throw new Exception("Necesita una Editorial");
            }

            dao.darBaja(e);

            if (e.getAlta()) {
                throw new Exception("No se pudo dar de baja");
            }
        } catch (Exception ex) {
            System.err.println("Error en darBaja()");
            throw ex;
        }
    }

    public void cambiarEditorial(Editorial e) throws Exception {
        try {
            if (e == null) {
                throw new Exception("Necesita una editorial");
            }
            System.out.print("Nombre Cambiado:");
            String nombre = sc.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Necesita un Nombre");
            }
            e.setNombre(nombre);
            dao.editarEditorial(e);
        } catch (Exception ex) {
            System.err.println("Error en cambiarEditorial()");
            throw ex;
        }
    }

    public Boolean comprobarNombre(String nombre) throws Exception {
        try {
            return dao.buscarNombre(nombre) != null;
        } catch (Exception e) {
            System.err.println("Error en comprobarNombre()");
            throw e;
        }
    }
}
