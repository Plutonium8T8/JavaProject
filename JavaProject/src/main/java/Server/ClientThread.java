package Server;

import DataBaseAccess.DBAccess;
import Entity.CameraEntity;
import Entity.StudentEntity;
import Repository.RepositoryCamera;
import Repository.RepositoryCamin;
import Repository.RepositoryStudent;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class ClientThread extends Thread {
    private Socket socket = null ;
    RepositoryStudent studentRepository = new RepositoryStudent();

    RepositoryCamera cameraRepository = new RepositoryCamera();
    public ClientThread (Socket socket) { this.socket = socket ; }

    private void loadDB(){
        EntityManager entityManager = DBAccess.getInstance();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            CSVReader reader = null;
            reader = new CSVReader(new FileReader("src/main/java/studenti.csv"));
            String[] nextline;
            while ((nextline = reader.readNext()) != null) {
                transaction.begin();
                int contor = 0;                                     ///contorizare camp
                StudentEntity student = new StudentEntity();
                for (String token : nextline) {                 ///citire din csv
                    contor++;
                    if (contor == 1) {                          ///camp: nume
                        student.setNume(token);
                    } else if (contor == 2) {                   ///camp: prenume
                        student.setPrenume(token);
                    } else if (contor == 3) {                   ///camp: medie
                        student.setMedie(Float.valueOf(token));
                    } else if (contor == 4) {                   ///camp: sex
                        student.setSex(token);
                    } else if (contor == 5) {                   ///camp: nationalitate
                        student.setNationalitate(token);
                    }
                }
                studentRepository.save(student);
                transaction.commit();
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            if (transaction.isActive()) {
                transaction.rollback();
            }
            DBAccess.closeConnection();
        }
    }

    private void distribution(){
        List <StudentEntity> males = new ArrayList<>();
        List <StudentEntity> females = new ArrayList<>();

        EntityManager entityManager = DBAccess.getInstance();
        EntityTransaction transaction = entityManager.getTransaction();

        males = studentRepository.findAllM();
        females = studentRepository.findAllF();

        List <StudentEntity> sortedmales = males.stream()
                .sorted(Comparator.comparing(StudentEntity::getMedie))
                .collect(Collectors.toList());

        List <StudentEntity> sortedfemales = females.stream()
                .sorted(Comparator.comparing(StudentEntity::getMedie))
                .collect(Collectors.toList());

        Collections.reverse(sortedmales);
        Collections.reverse(sortedfemales);
        List <CameraEntity> camera = new ArrayList<>();
        camera = cameraRepository.findAll();
        System.out.println(camera.size());
        int rand;   //rand=1 randul baietilor la distribuire, rand =2 randul fetelor la distribuire
        if (sortedmales.get(1).getMedie() > sortedfemales.get(1).getMedie())
            rand=1;
        else rand=2;

        for (CameraEntity str : camera ){
            System.out.println(str);
            if (rand == 1){
                int counter = 0;
                for (StudentEntity str2 : sortedmales){
                    if (str.getCapacitate() > 0){
                        counter++;
                        transaction.begin();
                        System.out.println(str2);
                        StudentEntity student;
                        student = studentRepository.findById(str2.getId());
                        student.setReferencedCamera(str);
                        student.setIdCamera(student.getReferencedCamera().getId());
                        studentRepository.deleteById(str2.getId());
                        transaction.commit();
                        transaction.begin();
                        studentRepository.save(student);
                        str.setCapacitate(str.getCapacitate()-1);
                        transaction.commit();
                    }else{
                        break;
                    }
                }
                for (int i=1;i<=counter;i++) {
                    System.out.println("+++");
                    System.out.println(sortedmales.remove(1));
                }
                if(!sortedmales.equals(Collections.emptyList()))
                {
                    if (sortedmales.get(1).getMedie() > sortedfemales.get(1).getMedie())
                        rand=1;
                    else rand=2;
                }
            }
            else{
                int counter =0;
                for (StudentEntity str2 : sortedfemales){
                    if (str.getCapacitate() > 0){
                        counter++;
                        transaction.begin();
                        StudentEntity student;
                        student = studentRepository.findById(str2.getId());
                        student.setReferencedCamera(str);
                        student.setIdCamera(student.getReferencedCamera().getId());
                        studentRepository.deleteById(str2.getId());
                        transaction.commit();
                        transaction.begin();
                        studentRepository.save(student);
                        str.setCapacitate(str.getCapacitate()-1);
                        transaction.commit();
                    }else{
                        break;
                    }
                }
                System.out.println(sortedfemales.get(1));
                System.out.println(sortedfemales.size());
                System.out.println(str.getCapacitate());
                for (int i=1;i<=counter;i++) {
                    System.out.println("+++");
                    System.out.println(sortedfemales.remove(1));
                }
                System.out.println(sortedfemales.get(1));
                if (sortedmales.get(1).getMedie() > sortedfemales.get(1).getMedie())
                    rand=1;
                else rand=2;
            }
            System.out.println(rand);
        }

        for (StudentEntity str2 : sortedmales) {
            transaction.begin();
            StudentEntity student;
            student = studentRepository.findById(str2.getId());
            student.setReferencedCamera(null);
            student.setIdCamera(null);
            studentRepository.deleteById(str2.getId());
            transaction.commit();
            transaction.begin();
            studentRepository.save(student);
            transaction.commit();
        }

        for (StudentEntity str2 : sortedfemales) {
            transaction.begin();
            StudentEntity student;
            student = studentRepository.findById(str2.getId());
            student.setReferencedCamera(null);
            student.setIdCamera(null);
            studentRepository.deleteById(str2.getId());
            transaction.commit();
            transaction.begin();
            studentRepository.save(student);
            transaction.commit();
        }
    }
    public void run () {
        try {
            while (true){
                //studentRepository.deleteAll();
                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                //loadDB();
                //distribution();
                String request = in.readLine();
                // Send the response to the oputput stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                if(request == "removeAllStudents") studentRepository.deleteAll();
                if(request == "addStudents") loadDB();
                if(request == "distributeStudents") distribution();
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) { System.err.println (e); }
        }
    }
}
