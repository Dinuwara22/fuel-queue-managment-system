package com.example.task4;

public class Circula {
    private String firstName;
    private String secondName;
    private String fullName;
    private String vehicleNumber;
    private double noOfLiterRequired;

    public Circula() {
        this.fullName = "Empty";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getNoOfLiterRequired() {
        return noOfLiterRequired;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setNoOfLiterRequired(double noOfLiterRequired) {
        this.noOfLiterRequired = noOfLiterRequired;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
