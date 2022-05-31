package Server;

import DataBaseAccess.DBAccess;
import Entity.CameraEntity;
import Entity.CaminEntity;
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
import java.util.*;
import java.util.stream.Collectors;

class ClientThread extends Thread {
    private final Socket socket;

    EntityManager entityManager = DBAccess.getInstance();

    EntityTransaction transaction = entityManager.getTransaction();

    RepositoryStudent studentRepository = new RepositoryStudent();

    RepositoryCamera cameraRepository = new RepositoryCamera();

    RepositoryCamin caminRepository = new RepositoryCamin();

    public ClientThread (Socket socket) { this.socket = socket ; }

    private void deleteDBStuds(){
        try {
                transaction.begin();
                studentRepository.deleteAll();
                transaction.commit();

        } finally{
            if (transaction.isActive()) {
                transaction.rollback();

            }
        }
        System.out.println("Delete done!");
    }
    private void loadDB(){
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
                Random rand = new Random();
                List<CameraEntity> listaCamere = cameraRepository.findAll();
                student.setCameraPref(listaCamere.get(rand.nextInt(listaCamere.size())).getId());
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
        }
        System.out.println("Load done!");
    }

    private void distribution(){
        List <StudentEntity> males;
        List <StudentEntity> females;

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

        List <CameraEntity> camera;
        camera = cameraRepository.findAll();
        List <CameraEntity> sortedcamera = camera.stream()
                .sorted(Comparator.comparing(CameraEntity::getId))
                .collect(Collectors.toList());

        int rand;   //rand=1 randul baietilor la distribuire, rand =2 randul fetelor la distribuire
        if (sortedmales.get(0).getMedie() > sortedfemales.get(0).getMedie())
            rand=1;
        else rand=2;

        for (CameraEntity str : sortedcamera ){
            if (rand == 1){
                List <Integer> indexStud = new ArrayList<>();
                for (StudentEntity str2 : sortedmales){
                    if (str.getCapacitate() > 0 && str2.getCameraPref() == str.getId()){
                        indexStud.add(str2.getId());
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
                for (int i=1;i<=indexStud.size();i++) {
                    sortedmales.remove(0);
                }
                if(!sortedmales.equals(Collections.emptyList()))
                {
                    if (sortedmales.get(0).getMedie() > sortedfemales.get(0).getMedie()) {
                        rand = 1;
                    }
                    else {
                        rand = 2;
                    }
                }
            }
            else{
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
                for (int i = 1; i <= counter; i++) {
                    sortedfemales.remove(0);
                }
                if(!sortedfemales.equals(Collections.emptyList()))
                {
                    if (sortedmales.get(0).getMedie() > sortedfemales.get(0).getMedie()) {
                        rand = 1;
                    }
                    else {
                        rand = 2;
                    }
                }
            }
        }

        for (CameraEntity str : sortedcamera ){
            int counter = 0;
            if (rand == 1){
                for (StudentEntity str2 : sortedmales){
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
                for (int i=1;i<=counter;i++) {
                    sortedmales.remove(0);
                }
                if(!sortedmales.equals(Collections.emptyList()))
                {
                    if (sortedmales.get(0).getMedie() > sortedfemales.get(0).getMedie()) {
                        rand = 1;
                    }
                    else {
                        rand = 2;
                    }
                }
            }
            else{
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
                for (int i = 1; i <= counter; i++) {
                    sortedfemales.remove(0);
                }
                if(!sortedfemales.equals(Collections.emptyList()))
                {
                    if (sortedmales.get(0).getMedie() > sortedfemales.get(0).getMedie()) {
                        rand = 1;
                    }
                    else {
                        rand = 2;
                    }
                }
            }
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
        System.out.println("Distribution done!");
    }
    public void run () {
        try {
            boolean serverIsRunning = true;
            while (serverIsRunning) {

                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();

                // Send the response to the oputput stream: server → client
                if (request != null)
                    System.out.println(request);

                if (request != null) {
                    if (request.equals("removeAllStudents")) deleteDBStuds();

                    if (request.equals("addStudents")) loadDB();

                    if (request.equals("distributeStudents")) distribution();

                    String[] message = request.split(",");

                    if (message[0].equals("addStudent")) {
                        StudentEntity newStudent = new StudentEntity();
                        transaction.begin();
                        newStudent.setNume(message[1]);
                        newStudent.setPrenume(message[2]);
                        newStudent.setSex(message[3]);
                        newStudent.setNationalitate(message[4]);
                        newStudent.setMedie(Float.valueOf(message[5]));
                        studentRepository.save(newStudent);
                        transaction.commit();
                    }

                    if (message[0].equals("removeStudent")) {
                        transaction.begin();
                        studentRepository.deleteById(Integer.parseInt(message[1]));
                        transaction.commit();
                    }

                    if (message[0].equals("addCamin")) {
                        CaminEntity newCamin = new CaminEntity();
                        transaction.begin();
                        newCamin.setNume(message[1]);
                        caminRepository.save(newCamin);
                        transaction.commit();
                    }

                    if (message[0].equals("addCamera")) {
                        CameraEntity newCamera = new CameraEntity();
                        transaction.begin();
                        newCamera.setCapacitate(Integer.parseInt(message[2]));
                        newCamera.setReferencedCamin(caminRepository.findById(Integer.parseInt(message[1])));
                        newCamera.setIdCamin(newCamera.getReferencedCamin().getId());
                        cameraRepository.save(newCamera);
                        transaction.commit();
                    }

                    if (request.equals("getStudents"))
                    {
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        //out = studentRepository.toString();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println (e);
            }
        }
        DBAccess.closeConnection();
    }
}
