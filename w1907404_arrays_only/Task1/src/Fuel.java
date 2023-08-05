//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1907404    IIT ID: 20211523
//Date: 10/07/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Fuel {
    //create 2D array for add name.
    static String[][] queues = new String[3][6];
    static Scanner input = new Scanner(System.in);
    static int fuelVolume = 6600;

    public static void main(String[] args) {
        // Must be added Empty name in first.
        addEmpty();
        mainOption();
    }
    //                                          ----METHODS---

    public static void mainOption(){
        boolean flag = true;
        while(flag){
            menu();
            //The user select what they want.
            String option = askSomeQuestionStr("Enter option from the above Menu : ");
            //Checking what the user wants.
            switch (option) {
                case "100", "VFQ" -> viewFuelQueue();
                case "101", "VEQ" -> viewEmptyQueue();
                case "102", "ACQ" -> add();
                case "103", "RCQ" -> remove();
                case "104", "PCQ" -> removeServeCustomer();
                case "105", "VCS" -> sort();
                case "106", "SPD" -> storeData();
                case "107", "LPD" -> loadData();
                case "108", "STK" -> fuelStock();
                case "109", "AFS" -> addFuelStock();
                case "999", "EXT" -> flag = exit();
                default -> System.out.println("Something Wrong!!! :( \nPlease Enter Valid Code...");
            }
        }
    }

    public static boolean exit(){
        System.out.println("Thank you using our program... :)");
        return false;
    }

    public static void addEmpty(){
        for(int i=0; i < queues.length; i++){
            for(int j=0; j < queues[i].length; j++){
                queues[i][j] = "Empty";
            }
        }
    }

    public static void menu(){
        System.out.println();
        System.out.printf("%70s","---MENU---");
        System.out.println("\n");
        String[][] menu = {
                {
                        "100 or VFQ: View all Fuel Queues.",
                        "101 or VEQ: View all Empty Queues.",
                        "102 or ACQ: Add customer to a Queue.",
                        "103 or RCQ: Remove a customer from a Queue.",
                        "104 or PCQ: Remove a served customer.",
                        "105 or VCS: View Customers Sorted in alphabetical order."
                },
                {
                        "106 or SPD: Store Program Data into file.",
                        "107 or LPD: Load Program Data from file.",
                        "108 or STK: View Remaining Fuel Stock.",
                        "109 or AFS: Add Fuel Stock.",
                        "999 or EXT: Exit the Program.",
                        "\n"}
        };
        //Display Main Menu.
        for(int i=0; i < menu[0].length; i++){
            System.out.printf("%9s","");
            for(int j=0; j < menu.length; j++){
                System.out.printf("%-65s",menu[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String askSomeQuestionStr(String ask){
        System.out.print(ask);
        String code = input.nextLine();
        //convert user input to uppercase.
        return code.toUpperCase();
    }

    public static boolean switchCase(String ask){
        //get 'YES' or 'NO'
        String select = askSomeQuestionStr(ask);
        boolean flag;
        switch (select) {
            case "YES" -> flag = true;
            case "NO" -> {
                flag = false;
                System.out.println("Now you have to go to Main Menu.....");
            }
            default -> {
                System.out.println("Please Enter YES or NO !!!\nNow you have to go to Main Menu....");
                flag = false;
            }
        }
        return flag;
    }

    public static void add(){

        int queueIndex = 0;
        boolean flag = true;
        while(flag){
            //check fuel stock greater than 500liter.Because Customers will not be added when it is less than 500Liters.
            if(fuelVolume >500) {
                if(!queues[0][5].equals("Empty") && !queues[1][5].equals("Empty") && !queues[2][5].equals("Empty")){
                    System.out.println("All Queues Are full...");
                    return;
                }else {
                    //Exception handling
                    try {
                        Scanner scan = new Scanner(System.in);
                        System.out.print("\nEnter Queue : ");
                        int queue = scan.nextInt();
                        queueIndex = queue - 1;
                    }catch (Exception e){
                        System.out.println("You have to go to main menu...\nPlease next time type Integer that point!");
                        break;
                    }
                    //check queue is greater than 3
                    if(queueIndex  < 0 || queueIndex >2){
                        System.out.println("There are only 3 pumps....\n1 to 3");
                        break;
                    }
                    //check that queue last position is Empty.
                    if (!queues[queueIndex][5].equals("Empty")) {
                        System.out.println("Queue " + (queueIndex + 1) + " is Already full.But You Can Check Another Queue...");
                    } else {
                        //get Name from user.
                        String name = askSomeQuestionStr("Enter Name \t: ");
                        System.out.println();
                        //Searching first Empty which position.
                        for (int i = 0; i < queues[queueIndex].length; i++) {
                            if (queues[queueIndex][i].equals("Empty")) {
                                queues[queueIndex][i] = name;
                                break;
                            }
                        }
                        //                          ---Decoration(Line 149 to Line 159)---
                        String message = name + " added to the queue " + (queueIndex + 1) + " successfully";
                        for (int i = 0; i < message.length(); i++) System.out.print("-");
                        System.out.println("\n" + message);
                        //check currently customer each queues.
                        int count = 0;
                        for (int i = 0; i < queues[queueIndex].length; i++)
                            if (!queues[queueIndex][i].equals("Empty")) count++;
                        System.out.println("Now Queue " + (queueIndex + 1) + ":  " + count + "/6");
                        for (int i = 0; i < message.length(); i++) System.out.print("-");
                        System.out.println();
                    }
                }
            }else{
                System.out.println("WARNING...!\nYou can't add.Because stock reaches 500Liters :( ");
                break;
            }
            flag = switchCase("\nDo you want again add?\nPlease enter 'YES' or 'NO' : ");
        }
    }

    public static void viewFuelQueue(){

        System.out.printf("%75s","View all Fuel Queues");
        System.out.println("\n");
        System.out.printf("%37s","");
        System.out.printf("%-23s%-23s%-24s","--Queue1--","--Queue2--","--Queue3--");
        System.out.println();
        //checking all queues are 1st position empty.
        if(queues[0][0].equals("Empty")&&queues[1][0].equals("Empty")&&queues[2][0].equals("Empty")){
            for(int i=0; i < queues[0].length;i++){
                System.out.printf("%37s","");
                for(int j=0; j < queues.length;j++){
                    //If all position are Empty then display all empty.
                    System.out.printf("[%d]%-20s",(i+1),queues[j][i]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.printf("%65s","Number of NonEmpty place : 0");
            System.out.println();
        }else {
            for(int i=0; i < queues[0].length;i++){
                System.out.printf("%37s","");
                for(int j=0; j < queues.length; j++){
                    if(queues[j][i].equals("Empty")){
                        //If all positions are not Empty then Empty position is display null("").
                        System.out.printf("[%d]%-20s",(i+1),"");
                    }else{
                        System.out.printf("[%d]%-20s",(i+1),queues[j][i]);
                    }
                }
                System.out.println();
            }
            System.out.println();
            //checking how many NonEmpty place are there.
            int count=0;
            for(int i=0; i<queues.length;i++){
                for(int j=0; j<queues[i].length;j++){
                    if(!queues[i][j].equals("Empty")) count++;
                }
            }
            System.out.printf("%64s %d","Number of NonEmpty place : ",count);
            System.out.println();
        }
    }

    public static void viewEmptyQueue(){

        System.out.printf("%75s","View all Empty Queues");
        System.out.println("\n");
        System.out.printf("%37s","");
        System.out.printf("%-23s%-23s%-24s","--Queue1--","--Queue2--","--Queue3--");
        System.out.println();

        for(int i=0; i < queues[0].length; i++){
            System.out.printf("%37s","");
            for(int j=0; j < queues.length; j++){
                //Checking which positions are Empty.
                if(queues[j][i].equals("Empty")){
                    System.out.printf("[%d]%-20s",(i+1),queues[j][i]);
                }else{
                    System.out.printf("[%d]%-20s",(i+1),"");
                }
            }
            System.out.println();
        }

        System.out.println();
        //checking how many Empty position are there.
        int count=0;
        for(int i=0; i<queues.length;i++){
            for(int j=0; j<queues[i].length;j++){
                if(queues[i][j].equals("Empty")) count++;
            }
        }
        System.out.printf("%61s %d","Number of Empty place : ",count);
        System.out.println();
    }

    public static void remove(){
        boolean flag = true;
        int queueIndex = 0;
        int positionIndex = 0;
        while(flag){
            if(queues[0][0].equals("Empty") && queues[1][0].equals("Empty") && queues[2][0].equals("Empty")){
                System.out.println("All Queues are Empty.Therefore You Can Not Remove...");
                return;
            }else {
                //Exception Handling.
                try {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("\nEnter Queue : ");
                    int queue = scan.nextInt();
                    queueIndex = queue - 1;
                    //check queue valid.
                    if(queueIndex < 0 || queueIndex > 2){
                        System.out.println("There are only 3 pumps....\n1 to 3");
                        break;
                    }
                    System.out.print("Enter Position : ");
                    int position = scan.nextInt();
                    positionIndex = position - 1;
                    //check position is valid.
                    if(positionIndex < 0 || positionIndex >5){
                        System.out.println("There are only 6 positions....\n1 to 6");
                        break;
                    }
                }catch(Exception e){
                    System.out.println("You have to go to main menu...\nPlease next time type Integer that point!");
                    break;
                }

                //checking that place is already Empty.
                if(!queues[queueIndex][positionIndex].equals("Empty")){
                    //Decration
                    System.out.println();
                    message(queueIndex,positionIndex," remove from the Queue ");
                    if(positionIndex >= 0 && positionIndex <= 4){
                        for (int i = positionIndex; i < queues[queueIndex].length - 1; i++) {
                            queues[queueIndex][i] = queues[queueIndex][i + 1];
                        }
                        queues[queueIndex][5] = "Empty";
                    }else{
                        queues[queueIndex][positionIndex] = "Empty";
                    }
                }else{
                    System.out.println("This position already Empty...! \nYou Can Try Another Place...");
                }
            }
            //If user like to add another data set.
            flag = switchCase("\nDo you want again remove\nPlease Enter 'YES' or 'NO' : ");
        }
    }

    public static void removeServeCustomer() {

        boolean flag = true;
        while (flag) {
            int queueIndex = 0;

            //check stock greater than 500liters.
            if (fuelVolume > 500) {
                if(queues[0][0].equals("Empty") && queues[1][0].equals("Empty") && queues[2][0].equals("Empty")){
                    System.out.println("All Queues are Empty.Therefore You Can Not Serve...");
                    return;
                }else {
                    //Exception Handling.
                    try {
                        Scanner scan = new Scanner(System.in);
                        System.out.print("\nEnter Queue : ");
                        int queue = scan.nextInt();
                        queueIndex = queue - 1;
                        if (queueIndex < 0 || queueIndex > 2) {
                            System.out.println("There are only 3 pumps....\n1 to 3");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("You have to go to main menu...\nPlease next time type Integer that point!");
                        break;
                    }

                    //Check that place is Already Empty.
                    if (queues[queueIndex][0].equals("Empty")) {
                        System.out.println("This position already Empty...!\nYou Can Try Another Queue.");
                    } else {
                        fuelVolume = fuelVolume - 10;
                        message(queueIndex,0," serve and remove from the Queue ");
                        String serveCustomerName = queues[queueIndex][0];
                        for (int i = 0; i < queues[queueIndex].length - 1; i++) {
                            queues[queueIndex][i] = queues[queueIndex][i + 1];
                        }
                        queues[queueIndex][5] = "Empty";
                    }
                }

            } else {
                System.out.println("Sorry....\nWe can't serve now.Because our stock reaches 500liters.");
                return;
            }
            flag = switchCase("\nDo you want again serve for Customer ?\nPlease Enter 'YES' or 'NO' : ");
        }
    }

    public static void fuelStock(){
        System.out.println("\nCurrently Fuel Stock : " + fuelVolume);
        System.out.println("But we will give you only up to 500Liters...");
    }

    public static void addFuelStock(){
        int addFuelVolume = 0;
        while (true){
            try{
                Scanner scn = new Scanner(System.in);
                System.out.print("\nHow many Fuel you add : ");
                addFuelVolume = scn.nextInt();
                scn.nextLine();
                System.out.println("Successfully added... :)");
                break;
            }catch (Exception e){
                System.out.println("Please Enter Integer Type Data...");
            }
        }
        fuelVolume += addFuelVolume;
    }

    public static void sort(){
        //create 1D array for store all currently customer names.
        String[] name = new String[18];
        //add name from queues array(2D) to name array(1D)
        for(int i=0; i < queues.length; i++){
            for(int j=0; j < queues[i].length; j++){
                name[(i*queues[i].length)+j] = queues[i][j]+" ";
            }
        }
        System.out.println();

        //bubble sort algorithm.
        for(int i=0; i < name.length; i++){
            for(int j=1; j < name.length-i;j++){
                //      ---THIS MY ALGORITHM....
                //check what is minimum length
                int minLength = Math.min(name[j-1].length(),name[j].length());
                for(int k=0; k < minLength; k++){
                    if(name[j-1].charAt(k) > name[j].charAt(k)){
                        String swapName = name[j];
                        name[j] = name[j-1];
                        name[j-1] = swapName;
                        break;
                    } else if (name[j-1].charAt(k) == name[j].charAt(k)) {
                        continue;
                    }else {
                        break;
                    }
                }
            }
        }

        System.out.printf("%85s","Customers Sorted in alphabetical order");
        System.out.println("\n");
        //Display name array
        int count = 0;
        for(int i=0; i < name.length; i++){
            if(!name[i].equals("Empty ")){
                count++;
                System.out.printf("%62d.%s",count,name[i]+"\n");;
            }
        }
        System.out.println();
    }

    public static void storeData(){
        //File Handling
        try{
            File dataFile = new File("queuedata.txt");
            //create txt file and check there are already have this txt file.
            if(dataFile.createNewFile()){
                System.out.println("Created data File same location...");
            }else{
                System.out.println("Task Successfully...");
            }
            //store names to txt file.
            FileWriter dataWriter = new FileWriter("queuedata.txt");
            for(int i=0; i < queues.length; i++){
                for(int j=0; j < queues[i].length; j++){
                    dataWriter.write(queues[i][j]+"\n");
                }
            }
            //store fuel volume to file.
            dataWriter.write(fuelVolume+"\n");
            //close file.
            dataWriter.close();
        }catch(IOException e){
            System.out.println("An error occurred.");
        }
    }

    public static void loadData() {
        //File Handling.
        try {
            File data2File = new File("queuedata.txt");
            Scanner dataScanner = new Scanner(data2File);
            //Load to queue array(2D).
            for (int i = 0; i < queues.length; i++) {
                for (int j = 0; j < queues[i].length; j++) {
                    queues[i][j] = dataScanner.nextLine();
                }
            }
            //load fuel volume.
            fuelVolume = dataScanner.nextInt();
            //numberOfServeCustomers = dataScanner.nextInt();
            System.out.println("Task Successfully... :)");
        }catch (FileNotFoundException e){
            System.out.println("An Error Occurred.");
        }catch (NoSuchElementException e){
            System.out.println("Plesae store the data...");
        }
    }

    public static void message(int queueIndex,int positionIndex,String sent){

        String message = queues[queueIndex][positionIndex] + sent + (queueIndex+1) + " successfully.";
        for(int i=0; i <message.length();i++) System.out.print("-");
        System.out.println("\n" + message);
        for(int i=0; i <message.length();i++) System.out.print("-");
        System.out.println();
    }

}

// Dinuwara 3:31AM 8/7/2022
