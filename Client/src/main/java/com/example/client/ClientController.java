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

    public void startClient(){
        client.run();
    }

    public void switchScene1(ActionEvent event) throws IOException {
        startClient();
        root = FXMLLoader.load(getClass().getResource("client-scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        client.stop();
    }
}