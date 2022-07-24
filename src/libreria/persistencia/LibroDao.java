package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

public class LibroDao extends Dao {

    public void ingresarLibro(Libro l) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error rollback");
            } finally {
                System.err.println("Error en ingresarLibro()");
                throw e;
            }
        }
    }

    public Libro buscarId(Integer id) throws Exception {
        try {
            return em.find(Libro.class, id);
        } catch (Exception e) {
            System.err.println("Error en buscarId()");
            throw e;
        }
    }

    public void darBaja(Libro l) throws Exception {
        try {
            if (l == null) {
                throw new Exception("Necesita un libro");
            }
            l.setAlta(false);
            em.getTransaction().begin();
            em.remove(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error en darBaja()");
            throw e;
        }
    }

    public void editarLibro(Libro l) throws Exception {
        try {
            if (l == null) {
                throw new Exception("Necesita un libro");
            }
            em.getTransaction().begin();
            em.merge(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error en editarLibro()");
            throw e;
        }
    }

    public Libro buscarIsbn(Long isbn) throws Exception {
        try {
            if (isbn == null) {
                throw new Exception("Necesita un isbn");
            }
            return (Libro) em.createQuery("SELECT DISTINCT l FROM Libro l WHERE l.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Libro buscarTitulo(String titulo) throws Exception {
        try {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Nesecita un Titulo");
            }
            return (Libro) em.createQuery("SELECT DISTINCT a FROM Libro a WHERE a.titulo = :titulo").setParameter("titulo", titulo).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Libro> buscarNombreAutor(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Nesecita un Nombre");
            }
            List<Libro> ll = em.createQuery("SELECT l FROM Libro l INNER JOIN l.autor a WHERE a.nombre = :nombre").setParameter("nombre", nombre).getResultList();
            return ll;
        } catch (Exception e) {
            System.err.println("Error en buscarNombreAutor()");
            throw e;
        }
    }

    public List<Libro> buscarNombreEditorial(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Nesecita un Nombre");
            }
            List<Libro> ll = em.createQuery("SELECT l FROM Libro l INNER JOIN l.editorial e WHERE e.nombre = :nombre").setParameter("nombre", nombre).getResultList();
            return ll;
        } catch (Exception e) {
            System.err.println("Error en buscarNombreEditorial()");
            throw e;
        }
    }

    public List<Libro> buscarTodos() throws Exception {
        try {
            List<Libro> ll = em.createQuery("SELECT l FROM Libro l").getResultList();
            return ll;
        } catch (Exception e) {
            System.err.println("Error en buscarTodo()");
            throw e;
        }
    }
}
