package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class Dao {

    protected EntityManager em = Persistence.createEntityManagerFactory("libreriaPU").createEntityManager();

}
