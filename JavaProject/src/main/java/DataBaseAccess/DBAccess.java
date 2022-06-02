package DataBaseAccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBAccess {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static EntityManager getInstance(){
        return entityManager;
    }

    public static void closeConnection(){
        entityManager.close();
        entityManagerFactory.close();
    }
}

