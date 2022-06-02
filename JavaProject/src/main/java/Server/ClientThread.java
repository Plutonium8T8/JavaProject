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

    //repository cu entitati de tip student
    RepositoryStudent studentRepository = new RepositoryStudent();

    //repository cu entitati de tip camera
    RepositoryCamera cameraRepository = new RepositoryCamera();

    //repository cu entitati de tip camin
    RepositoryCamin caminRepository = new RepositoryCamin();

    //socket-ul thread-ului
    public ClientThread (Socket socket) { this.socket = socket ; }

    //sterge toti studentii din baza de date
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

    //citeste din fisier csv studenti si ii adauga in baza de date
    private void loadDB(){
        try {
            CSVReader reader;
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

    //algoritm de repartizare a studentilor in functie de medie,sex si preferinta camerei
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

        for (CameraEntity str : sortedcamera ){
            if(!sortedfemales.equals(Collections.emptyList()) && !sortedmales.equals(Collections.emptyList())) {
                List<Integer> indexMales = new ArrayList<>();
                int index = 0;
                while (indexMales.size() < str.getCapacitate() && index < sortedmales.size()) {
                    if (sortedmales.get(index).getCameraPref() == str.getId()) {
                        indexMales.add(index);
                    }
                    index++;
                }

                List<Integer> indexFemales = new ArrayList<>();
                index = 0;
                while (indexFemales.size() < str.getCapacitate() && index < sortedfemales.size()) {
                    if (sortedfemales.get(index).getCameraPref() == str.getId()) {
                        indexFemales.add(index);
                    }
                    index++;
                }

                int turn;

                if (indexMales.size() == 0) {
                    turn = 2;
                } else if (indexFemales.size() == 0) {
                    turn = 1;
                } else if (sortedmales.get(indexMales.get(0)).getMedie() > sortedfemales.get(indexFemales.get(0)).getMedie()) {
                    turn = 1;
                } else {
                    turn = 2;
                }

                if (turn == 1) {
                    for (int indexFor : indexMales) {
                        transaction.begin();
                        StudentEntity student;
                        student = studentRepository.findById(sortedmales.get(indexFor).getId());
                        student.setReferencedCamera(str);
                        student.setIdCamera(student.getReferencedCamera().getId());
                        studentRepository.deleteById(sortedmales.get(indexFor).getId());
                        transaction.commit();
                        transaction.begin();
                        studentRepository.save(student);
                        transaction.commit();

                        sortedmales.get(indexFor).setMedie(null);
                    }
                    if (str.getCapacitate() > indexMales.size()) {
                        for (StudentEntity stdM : sortedmales) {
                            if (str.getCapacitate() > indexMales.size()) {
                                transaction.begin();
                                StudentEntity student;
                                try {
                                    student = studentRepository.findById(stdM.getId());
                                    student.setReferencedCamera(str);
                                    student.setIdCamera(student.getReferencedCamera().getId());
                                    studentRepository.deleteById(stdM.getId());
                                    transaction.commit();
                                    transaction.begin();
                                    studentRepository.save(student);
                                    transaction.commit();
                                    indexMales.add(stdM.getId());
                                } catch (Exception e) {
                                    transaction.commit();
                                    continue;
                                }

                                stdM.setMedie(null);
                            }
                        }
                    }
                }

                if (turn == 2) {
                    for (int indexFor : indexFemales) {
                        transaction.begin();
                        StudentEntity student;
                        student = studentRepository.findById(sortedfemales.get(indexFor).getId());
                        student.setReferencedCamera(str);
                        student.setIdCamera(student.getReferencedCamera().getId());
                        studentRepository.deleteById(sortedfemales.get(indexFor).getId());
                        transaction.commit();
                        transaction.begin();
                        studentRepository.save(student);
                        transaction.commit();

                        sortedfemales.get(indexFor).setMedie(null);
                    }
                    if (str.getCapacitate() > indexFemales.size()) {
                        for (StudentEntity stdF : sortedfemales) {
                            if (str.getCapacitate() > indexFemales.size()) {
                                transaction.begin();
                                StudentEntity student;
                                try {
                                    student = studentRepository.findById(stdF.getId());
                                    student.setReferencedCamera(str);
                                    student.setIdCamera(student.getReferencedCamera().getId());
                                    studentRepository.deleteById(stdF.getId());
                                    transaction.commit();
                                    transaction.begin();
                                    studentRepository.save(student);
                                    transaction.commit();
                                    indexFemales.add(stdF.getId());
                                } catch (Exception e) {
                                    transaction.commit();
                                    continue;
                                }

                                stdF.setMedie(null);
                            }
                        }
                    }
                }
            }
        }

        for (StudentEntity std : sortedmales)
        {
            if (std.getMedie() != null) {
                transaction.begin();
                StudentEntity student;
                student = studentRepository.findById(std.getId());
                student.setReferencedCamera(null);
                student.setIdCamera(null);
                studentRepository.deleteById(std.getId());
                transaction.commit();
                transaction.begin();
                studentRepository.save(student);
                transaction.commit();
            }
        }

        for (StudentEntity std : sortedfemales)
        {
            if (std.getMedie() != null)
            {
                transaction.begin();
                StudentEntity student;
                student = studentRepository.findById(std.getId());
                student.setReferencedCamera(null);
                student.setIdCamera(null);
                studentRepository.deleteById(std.getId());
                transaction.commit();
                transaction.begin();
                studentRepository.save(student);
                transaction.commit();
            }
        }

        System.out.println("Distribution done!");
    }

    //pornirea thread-ului
    public void run () {
        try {
            boolean serverIsRunning = true;
            while (serverIsRunning) {

                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String request = in.readLine();

                // Send the response to the oputput stream: server → client
                if (request != null) {
                    //serverul primeste de la client comanda removeAllStudents, se apeleaza metoda deleteDBStuds()
                    if (request.equals("removeAllStudents"))
                    {
                        out.println("like");
                        out.flush();
                        deleteDBStuds();
                    }
                    //serverul primeste de la client comanda addStudents, se apeleaza metoda loadDB()
                    if (request.equals("addStudents"))
                    {
                        out.println("like");
                        out.flush();
                        loadDB();
                    }
                    //serverul primeste de la client comanda distributeStudents, se apeleaza metoda distribution()
                    if (request.equals("distributeStudents"))
                    {
                        out.println("like");
                        out.flush();
                        distribution();
                    }

                    String[] message = request.split(",");
                    //serverul primeste de la client comanda addStudent si informatii despre student
                    //serverul il adauga in baza de date
                    if (message[0].equals("addStudent")) {
                        StudentEntity newStudent = new StudentEntity();
                        transaction.begin();
                        newStudent.setNume(message[1]);
                        newStudent.setPrenume(message[2]);
                        newStudent.setSex(message[3]);
                        newStudent.setNationalitate(message[4]);
                        newStudent.setMedie(Float.valueOf(message[5]));
                        newStudent.setCameraPref(Integer.parseInt((message[6])));
                        studentRepository.save(newStudent);
                        transaction.commit();
                        out.println("like");
                        out.flush();
                    }

                    //serverul primeste de la client comanda removeStudent impreuna cu idul studentului
                    //serverul sterge studentul respectiv din baza de date
                    if (message[0].equals("removeStudent")) {
                        transaction.begin();
                        studentRepository.deleteById(Integer.parseInt(message[1]));
                        transaction.commit();
                        out.println("like");
                        out.flush();
                    }

                    //serverul primeste de la client comanda addCamin impreuna cu numele caminului
                    //serverul adauga caminul in baza de date
                    if (message[0].equals("addCamin")) {
                        CaminEntity newCamin = new CaminEntity();
                        transaction.begin();
                        newCamin.setNume(message[1]);
                        caminRepository.save(newCamin);
                        transaction.commit();
                        out.println("like");
                        out.flush();
                    }

                    //serverul primeste de la client comanda addCamera impreuna cu idul caminului de unde face parte camera
                    //serverul adauga camera in baza de date
                    if (message[0].equals("addCamera")) {
                        CameraEntity newCamera = new CameraEntity();
                        transaction.begin();
                        newCamera.setCapacitate(Integer.parseInt(message[2]));
                        newCamera.setReferencedCamin(caminRepository.findById(Integer.parseInt(message[1])));
                        newCamera.setIdCamin(newCamera.getReferencedCamin().getId());
                        cameraRepository.save(newCamera);
                        transaction.commit();
                        out.println("like");
                        out.flush();
                    }

                    //serverul primeste de la client comanda showStudents
                    //serverul trimite clientului toti studentii inregistrati in baza de date
                    if (request.equals("showStudents"))
                    {
                         List <StudentEntity>  students = studentRepository.findAll();
                         String mesaj = "";
                         for (StudentEntity str : students){
                             mesaj = mesaj + str.getId() + "," + str + ";";
                         }
                         out.println(mesaj);
                         out.flush();
                    }

                    //serverul primeste de la client comanda showStudent impreuna cu idul studentului
                    //serverul trimite clientului informatii despre studentul respectiv
                    if (message[0].equals("showStudent"))
                    {
                        StudentEntity student = studentRepository.findById(Integer.parseInt(message[1]));
                        String mesaj = message[1] + "," + student.toString();
                        out.println( mesaj);
                        out.flush();
                    }

                    //serverul primeste de la client comanda stopServer
                    //serverul se opreste
                    if (message[0].equals("stopServer"))
                    {
                        out.println("serverStopped");
                        out.flush();
                        serverIsRunning = false;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println (e);
            }
        }
        DBAccess.closeConnection();
    }
}

