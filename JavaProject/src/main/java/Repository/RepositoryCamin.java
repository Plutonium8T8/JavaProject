package Repository;

import Entity.CaminEntity;
import Entity.CameraEntity;
import DataBaseAccess.DBAccess;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Integer;


public class RepositoryCamin implements Repository<CaminEntity> {
    private final EntityManager em = DBAccess.getInstance();

    //returneaza numarul de camine din baza de date
    @Override
    public int count() {
        Query countQuery = em.createNativeQuery("SELECT count(*) FROM camin");
        Object result = countQuery.getSingleResult();
        return ((BigInteger) result).intValue();
    }

    //sterge toate caminele din baza de date
    @Override
    public void deleteAll() {
        Query deleteQuery = em.createNativeQuery("DELETE from camin");
        deleteQuery.executeUpdate();
    }

    //sterge caminul cu IDul respectiv din baza de date
    @Override
    public void deleteById(int id) {
        Query deleteQuery = em.createNativeQuery("DELETE from camin where id = ?1");
        deleteQuery.setParameter(1,id);
        deleteQuery.executeUpdate();
    }

    //verifica daca caminul cu IDul respectiv exista in baza de date
    @Override
    public boolean existsById(int id) {
        Query existsQuery = em.createNativeQuery("select count(*) FROM camin WHERE id = ?1");
        existsQuery.setParameter(1,id);
        Object result = existsQuery.getSingleResult();
        return ((BigInteger) result).intValue() != 0;
    }

    //returneaza o lista cu toate caminele din baza de date
    @Override
    public Iterable<CaminEntity> findAll() {
        ArrayList<CaminEntity> resultList = new ArrayList<>();
        Query findQuery = em.createNativeQuery("SELECT * FROM camin");

        List<Object> result = (List<Object>) findQuery.getResultList();

        for (Object o : result) {
            CaminEntity camin = new CaminEntity();
            Object[] obj = (Object[]) o;
            Integer id = Integer.parseInt(String.valueOf(obj[0]));
            String numeCamin = String.valueOf(obj[1]);
            camin.setId(id);
            camin.setNume(numeCamin);
            resultList.add(camin);
        }
        return resultList;
    }

    //returneaza caminul cu IDul respectiv
    @Override
    public CaminEntity findById(int queryId) {
        Query findQuery = em.createNativeQuery("select id FROM camin WHERE id = ?1");
        findQuery.setParameter(1,queryId);
        Object result = findQuery.getSingleResult();
        Integer id = ((Number) result).intValue();

        findQuery = em.createNativeQuery("select nume FROM camin WHERE id = ?1");
        findQuery.setParameter(1,queryId);
        result = findQuery.getSingleResult();
        String numeCamin = String.valueOf(result);

        CaminEntity returnCaminEntityEntity = new CaminEntity();
        returnCaminEntityEntity.setId(id);
        returnCaminEntityEntity.setNume(numeCamin);

        return returnCaminEntityEntity;
    }

    //salveaza caminul
    @Override
    public void save(CaminEntity camin) {
        em.persist(camin);
    }

    //returneaza caminul cu numele respectiv
    public CameraEntity findByname(String name) {
        Query existsQuery = em.createNativeQuery("select id FROM camin WHERE name = ?1");
        existsQuery.setParameter(1,name);
        Object result = existsQuery.getSingleResult();
        Integer id = ((BigInteger) result).intValue();

        existsQuery = em.createNativeQuery("select id_camin FROM camin WHERE name = ?1");
        existsQuery.setParameter(1,name);
        result = existsQuery.getSingleResult();
        Integer id_camin = ((BigInteger) result).intValue();

        CameraEntity returnCameraEntity = new CameraEntity();
        returnCameraEntity.setId(id);
        returnCameraEntity.setIdCamin(id_camin);

        return returnCameraEntity;
    }

    //verifica daca exista caminul cu numele respectiv in baza de date
    public boolean existsByName(String name) {
        Query existsQuery = em.createNativeQuery("select count(*) FROM camin WHERE name = ?1");
        existsQuery.setParameter(1,name);
        Object result = existsQuery.getSingleResult();
        return ((BigInteger) result).intValue() != 0;
    }
}
