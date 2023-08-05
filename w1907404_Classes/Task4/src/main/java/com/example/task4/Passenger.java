package com.example.task4;

public class Passenger {
    private String firstName;
    private String secondName;
    private String fullName;
    private String vehicleNumber;
    private double noOfLiterRequired;

    public Passenger() {
        this.fullName = "Empty";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getNoOfLiterRequired() {
        return noOfLiterRequired;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setNoOfLiterRequired(double noOfLiterRequired) {
        this.noOfLiterRequired = noOfLiterRequired;
    }
}
