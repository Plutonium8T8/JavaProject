package Server;

import DataBaseAccess.DBAccess;
import Entity.StudentEntity;
import Repository.RepositoryStudent;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ClientThread extends Thread {
    private Socket socket = null ;
    RepositoryStudent studentRepository = new RepositoryStudent();
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
        males = (List<StudentEntity>) studentRepository.findAllM();

    }
    public void run () {
        try {
            while (true){
                //studentRepository.deleteAll();
                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                // Send the response to the oputput stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String raspuns = "Hello " + request + "!";
                out.println(raspuns);
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
