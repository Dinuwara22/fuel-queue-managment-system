package com.example.task4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchController extends Main{
    public TableView<Passenger> searchPassengers;
    public TextField searchBar;
    public Button btnSearch;
    public AnchorPane root;

    public void initialize(){
        connectDetails();
    }

    public void connectDetails(){
        ObservableList<Passenger> passengerList = searchPassengers.getItems();

        for(int i=0; i < fuelQueues.length; i++){
            for(int j=0; j < fuelQueues[i].passengers.length; j++){
                if(!fuelQueues[i].passengers[j].getFullName().equals("Empty")){
                    passengerList.add(fuelQueues[i].passengers[j]);
                }
            }
        }
        searchPassengers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        searchPassengers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("secondName"));
        searchPassengers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        searchPassengers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("noOfLiterRequired"));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchName = searchBar.getText();
        searchBar.clear();
        searchPassengers.getItems().clear();
        btnSearch.setDisable(true);
        searchBar.setDisable(true);

        for(int i=0; i < fuelQueues.length; i++){
            for(int j=0; j < fuelQueues[i].passengers.length; j++){
                if(!fuelQueues[i].passengers[j].getFullName().equals("Empty")){
                    String vehicleNumber = fuelQueues[i].passengers[j].getVehicleNumber().toUpperCase();
                    if(fuelQueues[i].passengers[j].getFirstName().equals(searchName)){
                        addSearchPassenger(i,j);
                    }else if(fuelQueues[i].passengers[j].getSecondName().equals(searchName)){
                        addSearchPassenger(i,j);
                    } else if (fuelQueues[i].passengers[j].getFullName().equals(searchName.toUpperCase())) {
                        addSearchPassenger(i,j);
                    } else if (vehicleNumber.equals(searchName.toUpperCase())) {
                        addSearchPassenger(i,j);
                    }
                }
            }
        }
    }

    public void addSearchPassenger(int i, int j){
        ObservableList<Passenger> passengerList = searchPassengers.getItems();
        passengerList.add(fuelQueues[i].passengers[j]);
        searchPassengers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        searchPassengers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("secondName"));
        searchPassengers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        searchPassengers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("noOfLiterRequired"));
    }

    public void btnAgainOnAction(ActionEvent actionEvent) {
        searchPassengers.getItems().clear();
        btnSearch.setDisable(false);
        searchBar.setDisable(false);
        connectDetails();
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
