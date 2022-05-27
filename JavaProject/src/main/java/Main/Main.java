package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import DataBaseAccess.DBAccess;
import Entity.CameraEntity;
import Entity.CaminEntity;
import Entity.StudentEntity;
import Repository.RepositoryCamera;
import Repository.RepositoryCamin;
import Repository.RepositoryStudent;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
        EntityManager entityManager = DBAccess.getInstance();
        EntityTransaction transaction = entityManager.getTransaction();

        /*CSVReader reader = null;
        reader = new CSVReader(new FileReader("src/main/java/studenti.csv"));
        String [] nextline;
        int contor=0;
        String nume=null,prenume=null,sex=null,nationalitate=null;
        float medie=0;
        while ((nextline = reader.readNext())!= null){
            contor=0;
            StudentEntity student = new;
            for (String token : nextline){
                contor = contor + 1;

            }
        }*/
        try {
            transaction.begin();

            RepositoryCamin caminRepository = new RepositoryCamin();
            RepositoryCamera cameraRepository = new RepositoryCamera();
            RepositoryStudent studentRepository = new RepositoryStudent();
/*
            caminRepository.deleteAll();
            cameraRepository.deleteAll();
*/
            /*
            CaminEntity newCamin = new CaminEntity();
            newCamin.setNume("C43");
            caminRepository.save(newCamin);

/**/
            CameraEntity newCamera = new CameraEntity();
            newCamera.setReferencedCamin(caminRepository.findById(24));
            newCamera.setIdCamin(newCamera.getReferencedCamin().getId());
            newCamera.setCapacitate(100);
            cameraRepository.save(newCamera);

            /*
            StudentEntity newStudent = new StudentEntity();
            newStudent.setNume("TestNume");
            newStudent.setPrenume("TestPrenume");
            newStudent.setIdCamera(cameraRepository.findById(12).getId());
            studentRepository.save(newStudent);

             */

            transaction.commit();
        }finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            DBAccess.closeConnection();
        }
    }
}
