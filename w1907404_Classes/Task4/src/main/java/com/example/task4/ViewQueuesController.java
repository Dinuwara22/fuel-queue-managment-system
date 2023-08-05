package com.example.task4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewQueuesController extends Main{
    public TableView<Passenger> tblQueue1;
    public TableView<Passenger> tblQueue2;
    public TableView<Passenger> tblQueue5;
    public TableView<Passenger> tblQueue3;
    public TableView<Passenger> tblQueue4;
    public AnchorPane root;
    public Label incomeLabel1;
    public Label incomeLabel2;
    public Label incomeLabel3;
    public Label incomeLabel4;
    public Label incomeLabel5;


    public void initialize(){
        connectDetails(tblQueue1,0);
        connectDetails(tblQueue2,1);
        connectDetails(tblQueue3,2);
        connectDetails(tblQueue4,3);
        connectDetails(tblQueue5,4);

        income(incomeLabel1,0);
        income(incomeLabel2,1);
        income(incomeLabel3,2);
        income(incomeLabel4,3);
        income(incomeLabel5,4);

    }

    public void connectDetails(TableView<Passenger> tblQueue,int queueNumber){
        ObservableList<Passenger> passengerList = tblQueue.getItems();


        for(int i=0; i < fuelQueues[queueNumber].passengers.length; i++){
            if(!fuelQueues[queueNumber].passengers[i].getFullName().equals("Empty")){
                passengerList.add(fuelQueues[queueNumber].passengers[i]);
            }
        }

        tblQueue.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblQueue.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("secondName"));
        tblQueue.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        tblQueue.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("noOfLiterRequired"));

    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }

    public void income(Label income,int i){
        income.setText("Currently Income Queue "+(i+1)+ " : Rs " +fuelQueues[i].getPrice() +"/=");
    }
}
