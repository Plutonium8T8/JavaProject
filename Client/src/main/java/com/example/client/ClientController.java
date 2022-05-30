package com.example.client;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Client client = new Client();
    @FXML
    private Pane pane;

    @FXML
    private Button start;

    public void sendMessage(String message) {
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

    public void addStudent(String nume, String prenume, String sex, String medie, String nationalitate)
    {
        String message = nume + "," + prenume + "," + sex + "," + nationalitate + "," + medie;
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