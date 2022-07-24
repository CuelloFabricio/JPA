package libreria.persistencia;

import libreria.entidades.Autor;

public class AutorDao extends Dao {

    public void ingresarAutor(Autor l) throws Exception {
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
                System.err.println("Error en ingresarAutor()");
                throw e;
            }
        }
    }

    public Autor buscarId(Integer id) throws Exception {
        try {
            return em.find(Autor.class, id);
        } catch (Exception e) {
            System.err.println("Error en buscarId()");
            throw e;
        }
    }

    public void darBaja(Autor l) throws Exception {
        try {
            if (l == null) {
                throw new Exception("Necesita un autor");
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

    public Autor buscarNombre(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Nesecita un Nombre");
            }
            return (Autor) em.createQuery("SELECT DISTINCT a FROM Autor a WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void editarAutor(Autor l) throws Exception {
        try {
            if (l == null) {
                throw new Exception("Necesita un Autor");
            }
            em.getTransaction().begin();
            em.merge(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error en editarAutor()");
            throw e;
        }
    }
}
