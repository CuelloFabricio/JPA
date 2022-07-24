package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDao;

public class ServicioAutor {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    AutorDao dao = new AutorDao();

    public Autor crearAutor(String nombre) throws Exception {
        try {
            Autor a = new Autor();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Nesecita un Nombre");
            }
            if (comprobarNombre(nombre)) {
                throw new Exception("Autor en Existencia");
            }
            a.setNombre(nombre);
            dao.ingresarAutor(a);
            return a;
        } catch (Exception e) {
            System.err.println("Error en crearAutor()");
            throw e;
        }
    }

    public void mostrarAutor(Autor a) throws Exception {
        try {
            if (a == null) {
                throw new Exception("Necesita un Autor");
            }
            System.out.println(a.toString());
        } catch (Exception e) {
            System.err.println("Error en mostrarAutor()");
            throw e;
        }
    }

    public Autor encontrarId() throws Exception {
        try {
            System.out.print("Numero Id del Autor:");
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

    public void darBaja(Autor e) throws Exception {
        try {
            if (e == null) {
                throw new Exception("Necesita una Autor");
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

    public void cambiarAutor(Autor e) throws Exception {
        try {
            if (e == null) {
                throw new Exception("Necesita un Autor");
            }
            System.out.print("Nombre Cambiado:");
            String nombre = sc.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Necesita un Nombre");
            }
            e.setNombre(nombre);
            dao.editarAutor(e);
        } catch (Exception ex) {
            System.err.println("Error en cambiarAutor()");
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
