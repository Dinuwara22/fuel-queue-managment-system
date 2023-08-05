import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static FuelQueue[] fuelQueues = new FuelQueue[5];//create reference
    static Scanner input = new Scanner(System.in);
    static double fuelVolume = 6600;

    public static void main(String[] args) {

        createObjects();
        mainOption();
    }

    public static void mainOption(){
        boolean flag = true;
        while(flag){
            menu();
            //the user select what they want.
            String option = askSomeQuestionStr("Enter Option Above Menu: ");
            //checking what the user wanted.
            switch (option) {
                case "100", "VFQ" -> viewFuelQueue();
                case "101", "VEQ" -> viewEmptyFuelQueue();
                case "102", "ACQ" -> addPassengers();
                case "103", "RCQ" -> remove();
                case "104", "PCQ" -> removeServeCustomer();
                case "105", "VCS" -> sort();
                case "106", "SPD" -> storeData();
                case "107", "LPD" -> loadData();
                case "108", "STK" -> fuelStock();
                case "109", "AFS" -> addFuelStock();
                case "110", "IFQ" -> displayIncome();
                case "999", "EXT" -> flag = exit();
                default -> System.out.println("Something Wrong!!! :( \nPlease Enter Valid Code...");
            }
        }
    }

    public static void menu(){
        System.out.println();
        System.out.printf("%88s","---MENU---");
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
                        "110 or IFQ: Income of each Fuel queue.",
                        "999 or EXT: Exit the Program.",
                }
        };
        for(int i=0; i < menu[0].length; i++){ //Display Main Menu.
            System.out.printf("%28s","");
            for (String[] strings : menu) {
                System.out.printf("%-65s", strings[i]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String askSomeQuestionStr(String ask){
        System.out.print(ask);
        String code = input.nextLine();
        return code.toUpperCase();   //convert user input to uppercase.
    }

    public static void viewFuelQueue(){

        System.out.printf("%92s","View all Fuel Queues");
        System.out.println("\n");
        System.out.printf("%14s","");
        System.out.printf("%-33s%-33s%-33s%-33s%-31s","--Queue1--","--Queue2--","--Queue3--","--Queue4--","--Queue5--");
        System.out.println();

        //checking all queues are 1st position empty.
        if(fuelQueues[0].passengers[0].getFullName().equals("Empty") && fuelQueues[1].passengers[0].getFullName().equals("Empty") && fuelQueues[2].passengers[0].getFullName().equals("Empty") && fuelQueues[3].passengers[0].getFullName().equals("Empty") && fuelQueues[4].passengers[0].getFullName().equals("Empty")){ //change
            for(int i=0; i < fuelQueues[0].passengers.length;i++){
                System.out.printf("%14s","");
                for(int j=0; j < fuelQueues.length;j++){
                    //If all position are Empty then display all empty.
                    System.out.printf("[%d]%-30s",(i+1),fuelQueues[j].passengers[i].getFullName());
                }
                System.out.println();
            }
            System.out.println();
            System.out.printf("%98s","Number of NonEmpty place : 0");
            System.out.println();

        }else {
            for(int i=0; i < fuelQueues[0].passengers.length;i++){
                System.out.printf("%14s","");
                for(int j=0; j < fuelQueues.length; j++){
                    if(fuelQueues[j].passengers[i].getFullName().equals("Empty")){
                        //If all positions are not Empty then Empty position is display null("").
                        System.out.printf("[%d]%-30s",(i+1),"");
                    }else{
                        System.out.printf("[%d]%-30s",(i+1),fuelQueues[j].passengers[i].getFullName());
                    }
                }
                System.out.println();
            }
            System.out.println();
            //checking how many NonEmpty place are there.
            int count=0;
            for(int i=0; i< fuelQueues.length;i++){
                for(int j=0; j<fuelQueues[i].passengers.length;j++){
                    if(!fuelQueues[i].passengers[j].getFullName().equals("Empty")) count++;
                }
            }
            System.out.printf("%99s %d","Number of NonEmpty place : ",count);    // methana hadnna thiyenava
            System.out.println();
        }
    }

    public static void viewEmptyFuelQueue(){
        System.out.printf("%92s","View Empty Place");
        System.out.println("\n");
        System.out.printf("%14s","");
        System.out.printf("%-33s%-33s%-33s%-33s%-31s","--Queue1--","--Queue2--","--Queue3--","--Queue4--","--Queue5--");
        System.out.println();
        for(int i=0; i < fuelQueues[0].passengers.length; i++){
            System.out.printf("%14s","");
            for(int j=0; j < fuelQueues.length; j++){
                //Checking which positions are Empty.
                if(fuelQueues[j].passengers[i].getFullName().equals("Empty")){
                    System.out.printf("[%d]%-30s",(i+1),fuelQueues[j].passengers[i].getFullName());
                }else{
                    System.out.printf("[%d]%-30s",(i+1),"");
                }
            }
            System.out.println();
        }
        System.out.println();
        //checking how many Empty position are there.
        int count=0;
        for(int i=0; i<fuelQueues.length;i++){
            for(int j=0; j<fuelQueues[i].passengers.length;j++){
                if(fuelQueues[i].passengers[j].getFullName().equals("Empty")) {
                    count++;
                }
            }
        }
        System.out.printf("%94s %d","Number of Empty place : ",count);
        System.out.println();
    }

    public static void createObjects(){
        for(int i=0; i < fuelQueues.length; i++){
            fuelQueues[i] = new FuelQueue();
        }
    }

    public static boolean add(){
        //check all positions are empty
        if(fuelQueues[0].passengers[5].getFullName().equals("Empty") || fuelQueues[1].passengers[5].getFullName().equals("Empty") || fuelQueues[2].passengers[5].getFullName().equals("Empty") || fuelQueues[3].passengers[5].getFullName().equals("Empty") || fuelQueues[4].passengers[5].getFullName().equals("Empty")){
            for(int i=0; i < fuelQueues[0].passengers.length;i++){
                for(int j=0; j < fuelQueues.length; j++){
                    if(fuelQueues[j].passengers[i].getFullName().equals("Empty")){
                        fuelQueues[j].addPassenger(i);
                        String message = fuelQueues[j].passengers[i].getFullName() + " added to the queue " + (j+1) + " successfully";
                        for (int k = 0; k < message.length(); k++) System.out.print("-");
                        System.out.println("\n" + message);
                        int count=0;
                        for(int k=0; k < fuelQueues[j].passengers.length;k++){
                            if(!fuelQueues[j].passengers[k].getFullName().equals("Empty")){
                                count++;
                            }
                        }
                        System.out.println("Now Queue " + (j + 1) + ":  " + count + "/6");
                        for (int k = 0; k < message.length(); k++) System.out.print("-");
                        System.out.println();
                        return true;
                    }
                }
            }
            return true;
        }else {
            System.out.println("All Queues are full...");
            return false;
        }
    }

    public static void addPassengers(){
        boolean flag2 = true;
        while(flag2){
            flag2 = add();
            if(flag2){
                flag2 = switchCase("Do you want repeat 'YES' or 'NO': ");
            }else {
                return;
            }
        }
    }

    public static void message(int queueIndex,int positionIndex,String sent){

        String message = fuelQueues[queueIndex].passengers[positionIndex].getFullName() + sent + (queueIndex+1) + " successfully.";
        for(int i=0; i <message.length();i++) System.out.print("-");
        System.out.println("\n" + message);
        for(int i=0; i <message.length();i++) System.out.print("-");
        System.out.println();
    }

    public static void removeServeCustomer() {

        boolean flag = true;
        while (flag) {
            int queueIndex = 0;
            if ((fuelVolume - fuelQueues[queueIndex].passengers[0].getNoOfLiterRequired()) > 500) { // Deduct from the fuel stock and check if it can be served
                if(fuelQueues[0].passengers[0].getFullName().equals("Empty") && fuelQueues[1].passengers[0].getFullName().equals("Empty") && fuelQueues[2].passengers[0].getFullName().equals("Empty") && fuelQueues[3].passengers[0].getFullName().equals("Empty") && fuelQueues[4].passengers[0].getFullName().equals("Empty")){
                    System.out.println("All Queues are Empty.Therefore You Can Not Serve...");
                    return;
                }else {
                    try {
                        Scanner scan = new Scanner(System.in);
                        System.out.print("\nEnter Queue : ");
                        int queue = scan.nextInt();
                        queueIndex = queue - 1;
                        if (queueIndex < 0 || queueIndex > 4) {
                            System.out.println("There are only 5 pumps....\n1 to 5");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("You have to go to main menu...\nPlease next time type Integer that point!");
                        break;
                    }

                    if(!fuelQueues[queueIndex].passengers[0].getFullName().equals("Empty")){
                        fuelQueues[queueIndex].setPrice(); // After serving, the price is added
                        fuelVolume -= fuelQueues[queueIndex].passengers[0].getNoOfLiterRequired(); // The fuel stock is reduced
                        message(queueIndex,0," serve and remove from the Queue ");
                        fuelQueues[queueIndex].removeServePassenger(); //remove Passenger
                    }else{
                        System.out.println("This position already Empty...!\nYou Can Try Another Queue.");
                    }
                }
            } else {
                //After reducing the amount required by the user from the stock, if it is less than 500, this will be executed
                System.out.println("Sorry....\nWe can't serve now.Because our stock reaches 500liters.");
                return;
            }
            flag = switchCase("\nDo you want again serve for Customer ?\nPlease Enter 'YES' or 'NO' : ");
        }
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

    public static void remove(){
        boolean flag = true;
        int queueIndex = 0;
        int positionIndex = 0;
        while(flag){
            //checking that place is already Empty.
            if(fuelQueues[0].passengers[0].getFullName().equals("Empty") && fuelQueues[1].passengers[0].getFullName().equals("Empty") && fuelQueues[2].passengers[0].getFullName().equals("Empty") && fuelQueues[3].passengers[0].getFullName().equals("Empty") && fuelQueues[4].passengers[0].getFullName().equals("Empty")){
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
                    if(queueIndex < 0 || queueIndex > 4){
                        System.out.println("There are only 5 pumps....\n1 to 5");
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

                if(!fuelQueues[queueIndex].passengers[positionIndex ].getFullName().equals("Empty")){
                    //Decoration
                    System.out.println();
                    message(queueIndex,positionIndex," remove from the Queue ");
                    fuelQueues[queueIndex].removePassenger(positionIndex);
                }else{
                    System.out.println("This position already Empty...! \nYou Can Try Another Place...");
                }
            }
            //If user like to add another data set.
            flag = switchCase("\nDo you want again remove\nPlease Enter 'YES' or 'NO' : ");
        }
    }

    public static void displayIncome(){
        int queueIndex = 0;
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("\nEnter Queue : ");
            int queue = scan.nextInt();
            queueIndex = queue - 1;
            if (queueIndex < 0 || queueIndex > 4) {
                System.out.println("There are only 5 pumps....\n1 to 5");
                return;
            }
        } catch (Exception e) {
            System.out.println("You have to go to main menu...\nPlease next time type Integer that point!");
            return;
        }
        double price = fuelQueues[queueIndex].getPrice();
        System.out.println("Queue " + (queueIndex+1) + " currently Price: "+ price);
    }

    public static void sort(){
        //create 1D array for store all currently customer names.
        String[] name = new String[30];
        for(int i=0; i < fuelQueues.length; i++){
            for(int j=0; j < fuelQueues[i].passengers.length; j++){
                name[(i*fuelQueues[i].passengers.length)+j] = fuelQueues[i].passengers[j].getFullName();
            }
        }
        System.out.println();
        //bubble sort algorithm.
        for(int i=0; i < name.length; i++){
            for(int j=1; j < name.length-i;j++){
                //      ---THIS MY ALGORITHM....
                int minLength = Math.min(name[j-1].length(),name[j].length()); //check what is minimum length
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

        System.out.printf("%102s","Customers Sorted in alphabetical order");
        System.out.println("\n");
        //Display name array
        int count = 0;
        for(int i=0; i < name.length; i++){
            if(!name[i].equals("Empty")){
                count++;
                System.out.printf("%73d.%s",count,name[i]+"\n");
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


            for(int i=0; i < fuelQueues.length; i++){
                for(int j=0; j < fuelQueues[i].passengers.length; j++){
                    dataWriter.write(fuelQueues[i].passengers[j].getFullName()+"\n");
                    dataWriter.write(fuelQueues[i].passengers[j].getFirstName()+"\n");
                    dataWriter.write(fuelQueues[i].passengers[j].getSecondName()+"\n");
                    dataWriter.write(fuelQueues[i].passengers[j].getVehicleNumber()+"\n");
                }
            }

            for(int i=0; i < fuelQueues.length; i++){
                for(int j=0; j < fuelQueues[i].passengers.length; j++){
                    dataWriter.write(fuelQueues[i].passengers[j].getNoOfLiterRequired()+"\n");
                }
            }

            dataWriter.write(fuelVolume+"\n");

            for(int i=0; i < fuelQueues.length;i++){
                dataWriter.write(fuelQueues[i].getPrice()+"\n");
            }
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

            for (int i = 0; i < fuelQueues.length; i++) {
                for (int j = 0; j < fuelQueues[i].passengers.length; j++) {
                    fuelQueues[i].passengers[j].setFullName(dataScanner.nextLine());
                    fuelQueues[i].passengers[j].setFirstName(dataScanner.nextLine());
                    fuelQueues[i].passengers[j].setSecondName(dataScanner.nextLine());
                    fuelQueues[i].passengers[j].setVehicleNumber(dataScanner.nextLine());
                }
            }

            for(int i=0; i < fuelQueues.length; i++){
                for(int j=0; j < fuelQueues[i].passengers.length; j++){
                    fuelQueues[i].passengers[j].setNoOfLiterRequired(dataScanner.nextDouble());
                }
            }

            fuelVolume = dataScanner.nextDouble();

            for(int i=0; i < fuelQueues.length;i++){
                fuelQueues[i].setPrice(dataScanner.nextDouble());
            }
            System.out.println("Task Successfully... :)");
        }catch (FileNotFoundException e){
            System.out.println("An Error Occurred.");
        }catch (NoSuchElementException e){
            System.out.println("Please store the data...");
        }
    }

    public static void fuelStock(){
        System.out.println("\nCurrently Fuel Stock : " + fuelVolume);
        System.out.println("But we will give you only up to 500Liters...");
    }

    public static int askSomeQuestionInt(String ask){
        System.out.print(ask);
        int code = input.nextInt();
        //fix error
        input.nextLine();
        return code;
    }

    public static void addFuelStock(){
        while(true){
            try{
                Scanner scn = new Scanner(System.in);
                System.out.print("\nHow many Fuel you add : ");
                double addFuelVolume = scn.nextDouble();
                scn.nextLine();
                fuelVolume += addFuelVolume;
                System.out.println("Successfully added.... :)");
                break;
            }catch (Exception e){
                System.out.println("Please Enter Number...");
            }
        }
    }

    public static boolean exit(){
        System.out.println("Thank you using our program... :)");
        return false;
    }
}


//Dinuwara 3:43AM 8/5/2022