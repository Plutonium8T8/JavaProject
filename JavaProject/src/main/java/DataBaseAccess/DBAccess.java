package DataBaseAccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Entity.CameraEntity;
import Entity.CaminEntity;
import Entity.StudentEntity;

public class DBAccess {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static EntityManager em = emf.createEntityManager();
    public static EntityManager getInstance(){
        return em;
    }

    public static void closeConnection(){
        em.close();
        emf.close();
    }
}

