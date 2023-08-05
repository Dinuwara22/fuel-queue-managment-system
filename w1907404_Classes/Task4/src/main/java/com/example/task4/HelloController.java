package com.example.task4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public AnchorPane root;

    public void btnViewQueuesOnAction(ActionEvent actionEvent) throws IOException { //click for view all queues
        Parent parent = FXMLLoader.load(this.getClass().getResource("ViewQueues.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("View Queues");
        stage.centerOnScreen();
    }


    public void btnWaitingQueueOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("WaitingQueue.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Waiting Queues");
        stage.centerOnScreen();
    }


    public void btnSearchingOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("Search.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Search");
        stage.centerOnScreen();
    }
}