package com.example.client;

import Client.Client;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Client client = new Client();
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
    private String[] sexChoice = {"male", "female"};

    @Override
    public void initialize (URL arg0, ResourceBundle arg1)
    {
        sex.getItems().addAll(sexChoice);
    }

    public void addTable() throws IOException {
        numeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("NumeStudent"));
        prenumeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("PrenumeStudent"));
        medieCol.setCellValueFactory(new PropertyValueFactory<Student, String>("MedieStudent"));
        sexCol.setCellValueFactory(new PropertyValueFactory<Student, String>("SexStudent"));
        cameraCol.setCellValueFactory(new PropertyValueFactory<Student, String>("IdCaminStudent"));
        prefCol.setCellValueFactory(new PropertyValueFactory<Student, String>("CameraPrefStudent"));
        natCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Nationalitate"));

        List<String> mesaj = new ArrayList<>();
        mesaj = List.of(client.sendMessage("showStudent," + idGetter.getText()).split(","));

        Student student = new Student(mesaj.get(0),mesaj.get(1),mesaj.get(2),mesaj.get(3),mesaj.get(4),mesaj.get(5),mesaj.get(6));
        ObservableList<Student> students = studTable.getItems();
        students.add(student);
        studTable.setItems(students);
    }

    public class Student
    {
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

        public String getIdCaminStudent() {
            return idCaminStudent;
        }

        public String getCameraPrefStudent() {
            return cameraPrefStudent;
        }

        public String getNationalitate() {
            return nationalitate;
        }

        String numeStudent;
        String prenumeStudent;
        String sexStudent;
        String medieStudent;
        String idCaminStudent;
        String cameraPrefStudent;
        String nationalitate;

        public void setNumeStudent(String numeStudent) {
            this.numeStudent = numeStudent;
        }

        public void setPrenumeStudent(String prenumeStudent) {
            this.prenumeStudent = prenumeStudent;
        }

        public void setSexStudent(String sexStudent) {
            this.sexStudent = sexStudent;
        }

        public void setMedieStudent(String medieStudent) {
            this.medieStudent = medieStudent;
        }

        public void setIdCaminStudent(String idCaminStudent) {
            this.idCaminStudent = idCaminStudent;
        }

        public void setCameraPrefStudent(String cameraPrefStudent) {
            this.cameraPrefStudent = cameraPrefStudent;
        }

        public void setNationalitate(String nationalitate) {
            this.nationalitate = nationalitate;
        }

        Student(String numeStudent, String prenumeStudent, String sexStudent, String medieStudent, String idCaminStudent, String cameraPrefStudent, String nationalitate)
        {
            this.numeStudent = numeStudent;
            this.prenumeStudent = prenumeStudent;
            this.cameraPrefStudent = cameraPrefStudent;
            this.idCaminStudent = idCaminStudent;
            this.nationalitate = nationalitate;
            this.medieStudent = medieStudent;
            this.sexStudent = sexStudent;
        }
    }
    public void sendMessage(String message) throws IOException {
        client.sendMessage(message);
    }

    public void addStudents() throws IOException {
        sendMessage("addStudents");
    }

    public void removeAllStudents() throws IOException {
        sendMessage("removeAllStudents");
    }

    public void distributeStudents() throws IOException {
        sendMessage("distributeStudents");
    }

    public void addStudent() throws IOException {
        String message = "addStudent," + nume.getText() + "," + prenume.getText() + "," + sex.getValue() + "," + nationalitate.getText() + "," + medie.getText();
        client.sendMessage(message);
    }

    public void addCamin() throws IOException {
        String message = "addCamin," + numeCamin.getText();
        client.sendMessage(message);
    }

    public void addCamera() throws IOException {
        String message = "addCamera," + idCaminRef.getText() + "," + capacitate.getText();
        client.sendMessage(message);
    }

    public void removeById () throws IOException {
        String message = "removeStudent," + removeById.getText();
        client.sendMessage(message);
    }
    public void switchScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene5(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene5.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene6(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene6.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(studTable);
    }

    public void switchScene7(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene7.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene8(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene8.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene9(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene9.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene10(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene10.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene11(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene11.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}