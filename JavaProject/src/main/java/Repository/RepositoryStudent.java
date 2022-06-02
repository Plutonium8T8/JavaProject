package Repository;

import DataBaseAccess.DBAccess;
import Entity.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepositoryStudent implements Repository<StudentEntity>{

    private final EntityManager em = DBAccess.getInstance();

    private final RepositoryCamera localCameraRepository = new RepositoryCamera();

    //returneaza numarul de studenti
    @Override
    public int count() {
        Query countQuery = em.createNativeQuery("SELECT count(*) FROM student");
        Object result = countQuery.getSingleResult();
        return ((BigInteger) result).intValue();
    }

    //sterge toti studentii
    @Override
    public void deleteAll() {
        Query deleteQuery = em.createNativeQuery("DELETE from student");
        deleteQuery.executeUpdate();
    }

    //sterge un student dupa IDul lui
    @Override
    public void deleteById(int id) {
        Query deleteQuery = em.createNativeQuery("DELETE from student where id = ?1");
        deleteQuery.setParameter(1,id);
        deleteQuery.executeUpdate();
    }

    //returneaza daca exista un student cu IDul respectiv
    @Override
    public boolean existsById(int id) {
        Query existsQuery = em.createNativeQuery("select count(*) FROM student WHERE id = ?1");
        existsQuery.setParameter(1,id);
        Object result = existsQuery.getSingleResult();
        return (Integer) result != 0;
    }

    //returneaza o lista cu toti studentii din baza de date
    @Override
    public List<StudentEntity> findAll() {
        ArrayList<StudentEntity> resultList = new ArrayList<>();
        Query findQuery = em.createNativeQuery("SELECT * FROM student");

        List<Object> result = (List<Object>) findQuery.getResultList();

        for (Object o : result) {
            StudentEntity student = new StudentEntity();
            Object[] obj = (Object[]) o;
            Integer id = Integer.parseInt(String.valueOf(obj[0]));
            if (obj[1] != null) {
                student.setReferencedCamera(localCameraRepository.findById(Integer.parseInt(String.valueOf(obj[1]))));
                student.setIdCamera(student.getReferencedCamera().getId());
            }
            String nume = String.valueOf(obj[2]);
            String sex = String.valueOf(obj[3]);
            String nationalitate = String.valueOf(obj[4]);
            Float medie = Float.valueOf(String.valueOf(obj[5]));
            String prenume = String.valueOf(obj[6]);
            Integer cameraPref = Integer.parseInt(String.valueOf(obj[7]));
            student.setId(id);
            student.setNume(nume);
            student.setPrenume(prenume);
            student.setSex(sex);
            student.setNationalitate(nationalitate);
            student.setMedie(medie);
            student.setCameraPref(cameraPref);
            resultList.add(student);
        }
        return resultList;
    }

    //returneaza o lista cu toti studentii care au sexul feminin din baza de date
    public List<StudentEntity> findAllF() {
        ArrayList<StudentEntity> resultList = new ArrayList<>();
        Query findQuery = em.createNativeQuery("SELECT * FROM student where sex = \'female\'");

        List<Object> result = (List<Object>) findQuery.getResultList();

        for (Object o : result) {
            StudentEntity student = new StudentEntity();
            Object[] obj = (Object[]) o;
            Integer id = Integer.parseInt(String.valueOf(obj[0]));
            if (obj[1] != null) {
                student.setReferencedCamera(localCameraRepository.findById(Integer.parseInt(String.valueOf(obj[1]))));
                student.setIdCamera(student.getReferencedCamera().getId());
            }
            String nume = String.valueOf(obj[2]);
            String sex = String.valueOf(obj[3]);
            String nationalitate = String.valueOf(obj[4]);
            Float medie = Float.valueOf(String.valueOf(obj[5]));
            String prenume = String.valueOf(obj[6]);
            Integer cameraPref = Integer.parseInt(String.valueOf(obj[7]));
            student.setId(id);
            student.setNume(nume);
            student.setPrenume(prenume);
            student.setSex(sex);
            student.setNationalitate(nationalitate);
            student.setMedie(medie);
            student.setCameraPref(cameraPref);
            resultList.add(student);
        }
        return resultList;
    }

    //returneaza o lista cu toti studentii care au sexul masculin din baza de date
    public List<StudentEntity> findAllM() {
        ArrayList<StudentEntity> resultList = new ArrayList<>();
        Query findQuery = em.createNativeQuery("SELECT * FROM student where sex = \'male\'");

        List<Object> result = (List<Object>) findQuery.getResultList();

        for (Object o : result) {
            StudentEntity student = new StudentEntity();
            Object[] obj = (Object[]) o;
            Integer id = Integer.parseInt(String.valueOf(obj[0]));
            if (obj[1] != null) {
                student.setReferencedCamera(localCameraRepository.findById(Integer.parseInt(String.valueOf(obj[1]))));
                student.setIdCamera(student.getReferencedCamera().getId());
            }
            String nume = String.valueOf(obj[2]);
            String sex = String.valueOf(obj[3]);
            String nationalitate = String.valueOf(obj[4]);
            Float medie = Float.valueOf(String.valueOf(obj[5]));
            String prenume = String.valueOf(obj[6]);
            Integer cameraPref = Integer.parseInt(String.valueOf(obj[7]));
            student.setId(id);
            student.setNume(nume);
            student.setPrenume(prenume);
            student.setSex(sex);
            student.setNationalitate(nationalitate);
            student.setMedie(medie);
            student.setCameraPref(cameraPref);
            resultList.add(student);
        }
        return resultList;
    }

    //returneaza un student cu IDul respectiv
    @Override
    public StudentEntity findById(int queryId) {
        StudentEntity returnStudentEntity = new StudentEntity();
        Integer idCamera;
        Query existsQuery;
        Object result;

        existsQuery = em.createNativeQuery("select id_camera FROM student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        result = existsQuery.getSingleResult();
        if (result != null) {
            idCamera = ((Number) result).intValue();
            returnStudentEntity.setReferencedCamera(localCameraRepository.findById(idCamera));
            returnStudentEntity.setIdCamera(idCamera);
        }

        existsQuery = em.createNativeQuery("select nume FROM student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        result = existsQuery.getSingleResult();
        String nume = result.toString();

        existsQuery = em.createNativeQuery("select prenume FROM student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        result = existsQuery.getSingleResult();
        String prenume = result.toString();

        existsQuery = em.createNativeQuery("select sex FROM student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        result = existsQuery.getSingleResult();
        String sex = result.toString();

        existsQuery = em.createNativeQuery("select nationalitate FROM student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        result = existsQuery.getSingleResult();
        String nationalitate = result.toString();

        existsQuery = em.createNativeQuery("select medie FROM student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        result = existsQuery.getSingleResult();
        Float medie = (Float) result;

        existsQuery = em.createNativeQuery("select camera_pref FROM student WHERE id = ?1");
        existsQuery.setParameter(1, queryId);
        result = existsQuery.getSingleResult();
        Integer cameraPref = (Integer) result;

        returnStudentEntity.setMedie(medie);
        returnStudentEntity.setNationalitate(nationalitate);
        returnStudentEntity.setNume(nume);
        returnStudentEntity.setSex(sex);
        returnStudentEntity.setPrenume(prenume);
        returnStudentEntity.setMedie(medie);
        returnStudentEntity.setCameraPref(cameraPref);

        return returnStudentEntity;
    }

    //salveaza studentul
    @Override
    public void save(StudentEntity student) {
            em.persist(student);
    }
}
