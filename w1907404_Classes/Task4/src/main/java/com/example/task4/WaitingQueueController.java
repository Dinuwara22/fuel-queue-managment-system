package com.example.task4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WaitingQueueController extends Main{
    public TableView<Circula> tblWaitingQueue;
    public AnchorPane root;

    public void initialize(){
        connectDetails();
    }

    public void connectDetails(){
        ObservableList<Circula> passengerList = tblWaitingQueue.getItems();


        int front1 = front;
        for(int i=0; i < noOfItems; i++){
            if(front1 == maxSize){
                front1 = 0;
            }
            switch (front1) {
                case 0 -> passengerList.add(circulas[0]);
                case 1 -> passengerList.add(circulas[1]);
                case 2 -> passengerList.add(circulas[2]);
                case 3 -> passengerList.add(circulas[3]);
            }
            front1++;
        }
        tblWaitingQueue.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblWaitingQueue.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("secondName"));
        tblWaitingQueue.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        tblWaitingQueue.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("noOfLiterRequired"));

    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }
}
