package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import DataBaseAccess.DBAccess;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager1 = DBAccess.getInstance();
        EntityTransaction transaction1 = entityManager1.getTransaction();

            DBAccess.closeConnection();
    }
}
