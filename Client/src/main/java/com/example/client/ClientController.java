package com.example.client;

import Client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ClientController {
    @FXML
    private Pane pane;

    @FXML
    private Button start;

    public void startClient(){
        Client client = new Client();
        client.run();

    }
}