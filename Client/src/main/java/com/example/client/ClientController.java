package com.example.client;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Client client = new Client();

    String returnSex;
    String returnNume;
    String retrunPrenume;
    String returnNationalitate;
    String returnMedie;

    @FXML
    private ChoiceBox<String> sex = new ChoiceBox<String>();

    @FXML
    private TextField nume;

    @FXML
    private TextField prenume;

    @FXML
    private TextField nationalitate;

    @FXML
    private TextField medie;


    private String[] sexChoice = {"male", "female"};

    @Override
    public void initialize (URL arg0, ResourceBundle arg1)
    {
        sex.getItems().addAll(sexChoice);
    }

    public void sendMessage(String message)
    {
        client.sendMessage(message);
    }

    public void addStudents()
    {
        sendMessage("addStudents");
    }

    public void removeAllStudents()
    {
        sendMessage("removeAllStudents");
    }

    public void distributeStudents()
    {
        sendMessage("distributeStudents");
    }

    public void addStudent()
    {
        String message = nume.getText() + "," + prenume.getText() + "," + sex.getValue() + "," + nationalitate.getText() + "," + medie.getText();
        System.out.println(message);
        client.sendMessage("addStudent");
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