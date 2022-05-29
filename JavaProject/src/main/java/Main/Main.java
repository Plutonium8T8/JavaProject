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

        ///citire din csv studenti
        try {
            /*RepositoryStudent studentRepository = new RepositoryStudent();
            CSVReader reader = null;
            reader = new CSVReader(new FileReader("src/main/java/studenti.csv"));
            String[] nextline;
            int contor = 0;
            while ((nextline = reader.readNext()) != null) {
                transaction.begin();
                contor = 0;
                StudentEntity student = new StudentEntity();
                for (String token : nextline) {
                    contor++;
                    if (contor == 1) {
                        student.setNume(token);
                    } else if (contor == 2) {
                        student.setPrenume(token);
                    } else if (contor == 3) {
                        student.setMedie(Float.valueOf(token));
                    } else if (contor == 4) {
                        student.setSex(token);
                    } else if (contor == 5) {
                        student.setNationalitate(token);
                    }
                }
                studentRepository.save(student);
                transaction.commit();

                
            }*/
        }finally{
            if (transaction.isActive()) {
                transaction.rollback();
            }
            DBAccess.closeConnection();
        }
    }
}
