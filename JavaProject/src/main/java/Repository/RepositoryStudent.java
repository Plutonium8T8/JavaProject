package Repository;

import DataBaseAccess.DBAccess;
import Entity.CameraEntity;
import Entity.CaminEntity;
import Entity.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepositoryStudent implements Repository<StudentEntity>{

    private EntityManager em = DBAccess.getInstance();

    @Override
    public int count() {
        Query countQuery = em.createNativeQuery("SELECT count(*) FROM Student");
        Object result = countQuery.getSingleResult();
        return ((BigInteger) result).intValue();
    }

    @Override
    public void deleteAll() {
        Query deleteQuery = em.createNativeQuery("DELETE from Student");
        deleteQuery.executeUpdate();
    }

    @Override
    public void deleteById(int id) {
        Query deleteQuery = em.createNativeQuery("DELETE from Student where id = ?1");
        deleteQuery.setParameter(1,id);
        deleteQuery.executeUpdate();
    }

    @Override
    public boolean existsById(int id) {
        Query existsQuery = em.createNativeQuery("select count(*) FROM Student WHERE id = ?1");
        existsQuery.setParameter(1,id);
        Object result = existsQuery.getSingleResult();
        if ( ((BigInteger) result).intValue() == 0){
            return false;
        }
        return true;
    }
    @Override
    public Iterable<StudentEntity> findAll() {
        ArrayList<StudentEntity> resultList = new ArrayList<>();
        Query findQuery = em.createNativeQuery("SELECT * FROM Student");

        List<Object> result = (List<Object>) findQuery.getResultList();
        Iterator itr = result.iterator();

        while (itr.hasNext()) {
            StudentEntity student = new StudentEntity();
            Object[] obj = (Object[]) itr.next();
            Integer id = Integer.parseInt(String.valueOf(obj[0]));
            Integer idCamin = Integer.parseInt(String.valueOf(obj[1]));
            student.setId(id);
            student.setIdCamera(idCamin);
            resultList.add(student);
        }
        return resultList;
    }

    @Override
    public StudentEntity findById(int queryId) {
        Query existsQuery = em.createNativeQuery("select id FROM Student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        Object result = existsQuery.getSingleResult();
        Integer id = ((BigInteger) result).intValue();

        existsQuery = em.createNativeQuery("select id_camin FROM Student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        result = existsQuery.getSingleResult();
        Integer id_camin = ((BigInteger) result).intValue();

        StudentEntity returnStudentEntity = new StudentEntity();
        returnStudentEntity.setId(id);

        return returnStudentEntity;
    }

    @Override
    public void save(StudentEntity student) {
        RepositoryCamera repositoryCamera = new RepositoryCamera();
        CameraEntity camera = student.getReferencedContinent();
        if (camera != null) {
            repositoryCamera.save(camera);
        }
        em.persist(camera);
    }
}
