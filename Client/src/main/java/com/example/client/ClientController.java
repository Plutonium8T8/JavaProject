package com.example.client;

import Client.Client;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Client client = new Client();
    @FXML
    private ChoiceBox<String> sex = new ChoiceBox<>();

    @FXML
    private TextField nume;

    @FXML
    private TextField prenume;
    @FXML
    private TextField nationalitate;

    @FXML
    private TextField medie;

    @FXML
    private TextField numeCamin;

    @FXML
    private TextField capacitate;

    @FXML
    private TextField idCaminRef;

    @FXML
    private TextField removeById;

    @FXML
    private TextField idGetter;

    @FXML
    private TableView<Student> studTable;

    @FXML
    private TableColumn<Student, String> idCol;
    @FXML
    private TableColumn<Student, String> numeCol;

    @FXML
    private TableColumn<Student, String> prenumeCol;

    @FXML
    private TableColumn<Student, String> medieCol;

    @FXML
    private TableColumn<Student, String> sexCol;

    @FXML
    private TableColumn<Student, String> cameraCol;

    @FXML
    private TableColumn<Student, String> prefCol;

    @FXML
    private TableColumn<Student, String> natCol;
    private final String[] sexChoice = {"male", "female"};

    private ObservableList<Student> students;

    @Override
    public void initialize (URL arg0, ResourceBundle arg1)
    {
        sex.getItems().addAll(sexChoice);
    }

    public void showStudent() throws IOException {
        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("idStudent"));
        numeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("NumeStudent"));
        prenumeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("PrenumeStudent"));
        medieCol.setCellValueFactory(new PropertyValueFactory<Student, String>("MedieStudent"));
        sexCol.setCellValueFactory(new PropertyValueFactory<Student, String>("SexStudent"));
        cameraCol.setCellValueFactory(new PropertyValueFactory<Student, String>("IdCaminStudent"));
        prefCol.setCellValueFactory(new PropertyValueFactory<Student, String>("CameraPrefStudent"));
        natCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Nationalitate"));

        List<String> mesaj;
        mesaj = List.of(client.sendMessage("showStudent," + idGetter.getText()).split(","));

        Student student = new Student(mesaj.get(0),mesaj.get(1),mesaj.get(2),mesaj.get(3),mesaj.get(4),mesaj.get(5),mesaj.get(6),mesaj.get(7));
        students = studTable.getItems();
        students.add(student);
        studTable.setItems(students);
    }

    public void showStudents() throws IOException {
        ObservableList<Student> students;

        studTable.getItems().clear();
        studTable.refresh();

        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("idStudent"));
        numeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("NumeStudent"));
        prenumeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("PrenumeStudent"));
        medieCol.setCellValueFactory(new PropertyValueFactory<Student, String>("MedieStudent"));
        sexCol.setCellValueFactory(new PropertyValueFactory<Student, String>("SexStudent"));
        cameraCol.setCellValueFactory(new PropertyValueFactory<Student, String>("IdCaminStudent"));
        prefCol.setCellValueFactory(new PropertyValueFactory<Student, String>("CameraPrefStudent"));
        natCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Nationalitate"));

        List<String> mesaj;
        mesaj = List.of(client.sendMessage("showStudents").split(";"));

        for (String indexString : mesaj) {
            List<String> splitIndexString = List.of(indexString.split(","));
            Student student = new Student(splitIndexString.get(0), splitIndexString.get(1), splitIndexString.get(2), splitIndexString.get(3), splitIndexString.get(4), splitIndexString.get(5), splitIndexString.get(6), splitIndexString.get(7));

            students = studTable.getItems();
            students.add(student);
            studTable.setItems(students);
        }
    }

    public static class Student
    {
        String numeStudent;
        String prenumeStudent;
        String sexStudent;
        String medieStudent;
        String idCaminStudent;
        String cameraPrefStudent;
        String nationalitate;
        String idStudent;

        @Override
        public String toString() {
            return "Student{" +
                    "numeStudent='" + numeStudent + '\'' +
                    ", prenumeStudent='" + prenumeStudent + '\'' +
                    ", sexStudent='" + sexStudent + '\'' +
                    ", medieStudent='" + medieStudent + '\'' +
                    ", idCaminStudent='" + idCaminStudent + '\'' +
                    ", cameraPrefStudent='" + cameraPrefStudent + '\'' +
                    ", nationalitate='" + nationalitate + '\'' +
                    ", idStudent='" + idStudent + '\'' +
                    '}';
        }

        public String getNumeStudent() {
            return numeStudent;
        }

        public String getPrenumeStudent() {
            return prenumeStudent;
        }

        public String getSexStudent() {
            return sexStudent;
        }

        public String getMedieStudent() {
            return medieStudent;
        }

        public String getIdCaminStudent() { return idCaminStudent;}

        public String getCameraPrefStudent() {
            return cameraPrefStudent;
        }

        public String getNationalitate() {
            return nationalitate;
        }
        public String getIdStudent() {
            return idStudent;
        }
        public void setIdStudent(String idStudent) {
            this.idStudent = idStudent;
        }
        public void setNumeStudent(String numeStudent) {
            this.numeStudent = numeStudent;
        }
        public void setPrenumeStudent(String prenumeStudent) {
            this.prenumeStudent = prenumeStudent;
        }
        public void setSexStudent(String sexStudent) { this.sexStudent = sexStudent;}
        public void setMedieStudent(String medieStudent) {this.medieStudent = medieStudent;}
        public void setIdCaminStudent(String idCaminStudent) {
            this.idCaminStudent = idCaminStudent;
        }
        public void setCameraPrefStudent(String cameraPrefStudent) {
            this.cameraPrefStudent = cameraPrefStudent;
        }
        public void setNationalitate(String nationalitate) {
            this.nationalitate = nationalitate;
        }

        Student(String idStudent, String numeStudent, String prenumeStudent, String sexStudent, String medieStudent, String idCaminStudent, String cameraPrefStudent, String nationalitate)
        {
            this.idStudent = idStudent;
            this.numeStudent = numeStudent;
            this.prenumeStudent = prenumeStudent;
            this.cameraPrefStudent = cameraPrefStudent;
            this.idCaminStudent = idCaminStudent;
            this.nationalitate = nationalitate;
            this.medieStudent = medieStudent;
            this.sexStudent = sexStudent;
        }
    }
    //salveaza mesajul
    public void sendMessage(String message) throws IOException {
        client.sendMessage(message);
    }

    //trimite comanda stopServer catre server
    public void stopServer() throws IOException {
        client.sendMessage("stopServer");
    }

    public void saveToJSON() throws IOException {
        //ObjectMapper objectMapper = new ObjectMapper();

        /*List <Student> test = new ArrayList<>();
        for (Student str : students){
            test.add(str);
            System.out.println(str);
        }*/
        //System.out.println(studTable);
       /* String arrayToJson = objectMapper.writeValueAsString(test);
        TypeReference<List<Student>> mapType = new TypeReference<List<Student>>() {};
        List<Student> jsonToPersonList = objectMapper.readValue(arrayToJson, mapType);
        objectMapper.writeValue(
                new File("./studenti.json"),
                jsonToPersonList);*/
        //System.out.println(test);
    }
    //trimite comanda addStudents catre server
    public void addStudents() throws IOException {
        sendMessage("addStudents");
    }

    //trimite comanda removeAllStudents catre server
    public void removeAllStudents() throws IOException {
        sendMessage("removeAllStudents");
    }

    //trimite comanda distributeStudents catre server
    public void distributeStudents() throws IOException {
        sendMessage("distributeStudents");
    }

    //trimite comanda addStudent catre server impreuna cu informatiile legate de student
    public void addStudent() throws IOException {
        String message = "addStudent," + nume.getText() + "," + prenume.getText() + "," + sex.getValue() + "," + nationalitate.getText() + "," + medie.getText() + "," + idCaminRef.getText();
        client.sendMessage(message);
    }

    //trimite comanda addCamin catre server impreuna cu informatiile despre camin
    public void addCamin() throws IOException {
        String message = "addCamin," + numeCamin.getText();
        client.sendMessage(message);
    }

    //trimite comanda addCamera catre server impreuna cu informatiile legate de camera
    public void addCamera() throws IOException {
        String message = "addCamera," + idCaminRef.getText() + "," + capacitate.getText();
        client.sendMessage(message);
    }

    //trimite comanda removeById catre server impreuna cu IDul studentului
    public void removeById () throws IOException {
        String message = "removeStudent," + removeById.getText();
        client.sendMessage(message);
    }
    //schimbare la scena1
    public void switchScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //schimbare la scena2
    public void switchScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //schimbare la scena4
    public void switchScene4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //schimbare la scena6
    public void switchScene6(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene6.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //System.out.println(studTable);
    }

    //schimbare la scena7
    public void switchScene7(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene7.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //schimbare la scena9
    public void switchScene9(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene9.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //schimbare la scena10
    public void switchScene10(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene10.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //schimbare la scena11
    public void switchScene11(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene11.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}