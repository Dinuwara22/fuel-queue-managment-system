package com.example.task4;

import java.util.Scanner;

public class FuelQueue {

    Scanner input = new Scanner(System.in);
    Passenger[] passengers = new Passenger[6];
    private double price = 0;


    public FuelQueue() {
        for(int i=0; i < passengers.length; i++){
            passengers[i] = new Passenger();
        }
    }

    void addPassenger(int i){

        System.out.print("Enter First Name: ");
        passengers[i].setFirstName(input.nextLine());

        System.out.print("Enter Second Name: ");
        passengers[i].setSecondName(input.nextLine());

        passengers[i].setFullName(passengers[i].getFirstName().toUpperCase() + " " + passengers[i].getSecondName().toUpperCase());

        System.out.print("Enter Vehicle Number: ");
        passengers[i].setVehicleNumber(input.nextLine());

        while(true){
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter require liters: ");
                passengers[i].setNoOfLiterRequired(input.nextDouble());
                input.nextLine();
                break;
            }catch (Exception e){
                System.out.println("Please Enter Valid Input...");
            }
        }
    }

    void removePassenger(int positionIndex){
        if(positionIndex >= 0 && positionIndex <= 4){
            for(int i=positionIndex; i < passengers.length - 1; i++){
                passengers[i] = passengers[i+1];
            }
            passengers[5] = new Passenger();
        }else {
            passengers[positionIndex] = new Passenger();
        }
    }

    void removeServePassenger(){
        if(!passengers[0].getFullName().equals("Empty")){ //Check that place is Already Empty.
            for(int i=0; i < passengers.length-1; i++){
                passengers[i] = passengers[i+1];
            }
            passengers[5] = new Passenger();
        }else {
            System.out.println("This queue is already empty.Therefore you can't serve!!!");
        }
    }

    void setPrice(){
        this.price += (passengers[0].getNoOfLiterRequired() * 430);
    }

    void setPrice(double oldPrice){
        this.price = oldPrice;
    }

    double getPrice(){
        return price;
    }

}