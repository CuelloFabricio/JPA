package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDao;

public class ServicioLibro {

    LibroDao dao = new LibroDao();
    ServicioAutor sa = new ServicioAutor();
    ServicioEditorial se = new ServicioEditorial();
    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public Libro crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Autor a, Editorial e) throws Exception {
        try {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Necesita un Titulo");
            }
            if (ejemplares == null) {
                throw new Exception("Necesita ejemplares");
            }
            if (anio == null) {
                throw new Exception("Necesita un Año");
            }
            if (isbn == null) {
                throw new Exception("Necesita un Isbn");
            }
            if (a == null) {
                throw new Exception("Necesita un Autor");
            }
            if (e == null) {
                throw new Exception("Necesita una Editorial");
            }
            if (dao.buscarIsbn(isbn) != null) {
                throw new Exception("La isbn esta asociada a otro libro");
            }
            if (dao.buscarTitulo(titulo) != null) {
                throw new Exception("El titulo esta asociado a otro libro");
            }
            Libro l = new Libro(isbn, titulo, anio, ejemplares, a, e);
            dao.ingresarLibro(l);
            return l;
        } catch (Exception ex) {
            System.err.println("Error en crearLibro()");
            throw ex;
        }
    }

    public Libro encontrarId() throws Exception {
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

    public void darBaja(Libro e) throws Exception {
        try {
            if (e == null) {
                throw new Exception("Necesita un Libro");
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

    public void cambiarTitulo(Libro l) throws Exception {
        try {
            if (l == null) {
                throw new Exception("Nesecita un libro");
            }
            if (dao.buscarTitulo(l.getTitulo()) == null) {
                throw new Exception("El libro no existe");
            }
            System.out.print("Titulo Cambiado:");
            String nombre = sc.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Nesecita un titulo");
            }
            l.setTitulo(nombre);
            dao.editarLibro(l);
        } catch (Exception e) {
            System.err.println("Error en cambiarTitulo");
            throw e;
        }
    }

    public void mostrarLibro(Libro a) throws Exception {
        try {
            if (a == null) {
                throw new Exception("Necesita un Libro");
            }
            System.out.println(a.toString());
        } catch (Exception e) {
            System.err.println("Error en mostrarLibro()");
            throw e;
        }
    }

    public void mostrarLibrosEditorial(String nombre) throws Exception {
        try {
            if (se.comprobarNombre(nombre) == false) {
                throw new Exception("Necesita una editorial");
            }
            List<Libro> al = dao.buscarNombreEditorial(nombre);
            if (al == null || al.isEmpty()) {
                throw new Exception("Editorial sin Libros");
            }
            for (Libro libro : al) {
                mostrarLibro(libro);
            }
        } catch (Exception e) {
            System.err.println("Error en mostrarLibrosEditorial()");
            throw e;
        }
    }

    public void mostrarLibrosAutor(String nombre) throws Exception {
        try {
            if (sa.comprobarNombre(nombre) == false) {
                throw new Exception("Necesita un Autor");
            }
            List<Libro> al = dao.buscarNombreAutor(nombre);
            if (al == null || al.isEmpty()) {
                throw new Exception("Autor sin Libros");
            }
            for (Libro libro : al) {
                mostrarLibro(libro);
            }
        } catch (Exception e) {
            System.err.println("Error en mostrarLibrosAutor()");
            throw e;
        }
    }

    public void mostrarLibros() throws Exception {
        try {
            List<Libro> al = dao.buscarTodos();
            if (al == null || al.isEmpty()) {
                throw new Exception("Sin Libros");
            }
            for (Libro libro : al) {
                mostrarLibro(libro);
            }
        } catch (Exception e) {
            System.err.println("Error en mostrarTodo()");
            throw e;
        }
    }
}
