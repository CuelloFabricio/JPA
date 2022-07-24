package libreria.persistencia;

import libreria.entidades.Editorial;

public class EditorialDao extends Dao {

    public void ingresarEditorial(Editorial l) throws Exception {
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
                System.err.println("Error en ingresarEditorial()");
                throw e;
            }
        }
    }

    public Editorial buscarId(Integer id) throws Exception {
        try {
            return em.find(Editorial.class, id);
        } catch (Exception e) {
            System.err.println("Error en buscarId");
            throw e;
        }
    }

    public void darBaja(Editorial l) throws Exception {
        try {
            if (l == null) {
                throw new Exception("Necesita una editorial");
            }
            em.getTransaction().begin();
            em.remove(l);
            em.getTransaction().commit();
            l.setAlta(false);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error en darBaja()");
            throw e;
        }

    }

    public void editarEditorial(Editorial l) throws Exception {
        try {
            if (l == null) {
                throw new Exception("Necesita una editorial");
            }
            em.getTransaction().begin();
            em.merge(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error en editarEditorial()");
            throw e;
        }
    }

    public Editorial buscarNombre(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Nesecita un Nombre");
            }
            return (Editorial) em.createQuery("SELECT a FROM Editorial a WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
