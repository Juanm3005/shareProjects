package ui;


import customExceptions.InvalidSerialNumberException;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Controller;

public class Executable {

    public static Controller control;
    public static Scanner input;

    public static void main(String[] args) {
        intiallizer();
        control.loadReportsFromFile(); //carga los reportes desde el archivo
        menu();

    }

    public static void menu() {
        try {
            System.out.println("Welcome to the Electronic Equipment Reporting Management System \n");

            boolean seguir = true; //valida que el usuario quiera seguir digitando opciones
            while (seguir == true) { //valida que el usuario quiera seguir digitando opciones

                int opcion = -1;
            

                while (opcion < 0 || opcion > 3) { //valida que la opcion digitada exista dentro de las opciones
                    System.out.println("what do you want to do today\n"
                                + "1. record reports \n"
                                + "2. consult reports \n"
                                + "3. generate inform \n"
                                + "0. exit \n");

                    opcion = input.nextInt();  //guarda la opcion digitada por el usuario
                }
                    
                switch (opcion) {
                    case 1:

                        int reportType = -1;
                    
                        while (reportType < 1 || reportType > 2) { //valida que el tipo de reporte digitado exista dentro de las opciones

                            System.out.println("Enter the type of report you want to record\n"
                                    + "1. Software \n"
                                    + "2. Hardware \n");

                            reportType = input.nextInt(); //guarda el tipo de reporte digitado por el usuario
                        }
                    

                        if (reportType == 1) {
                            recordSoftwareReports();
                        } else {
                            recordHardwareReports();
                        }
                        break;

                    case 2:
                        int consultType= 0 ;

                        while (consultType < 1 || consultType > 3) {
                            System.out.println("how do you want to consult the reports?\n"
                                    + "1. By ID \n"
                                    + "2. By severity level \n"
                                    + "3. By date \n");

                        
                            consultType = input.nextInt();

                            if (consultType == 1) {
                                consultReportsById();
                            } else if (consultType == 2) {
                                consultReportsByLevel();
                            } else if (consultType == 3) {
                                consultReportsByDate();
                            } else {
                                System.out.println("Enter one of the menu options\n");
                            }
                        }

                            break;

                    case 3:
                        System.out.println("Enter the type of report you want to generate\n"
                                + "1. Software \n"
                                + "2. Hardware \n");

                        int reportType2 = input.nextInt();
                        if (reportType2 == 1) {
                            System.out.println(control.saveSoftwareReportsToFile());
                        } else {
                            System.out.println(control.saveHardwareReportsToFile());
                        }

                        break;

                    case 0:
                        System.out.println("you wanna save the reports? (yes/no)");
                        input.nextLine();
                        String temp = input.nextLine();
                        if (temp.equalsIgnoreCase("yes")) {
                            System.out.println(control.saveAllReports());
                        } else {
                            System.out.println("the reports were not saved");
                        }

                        seguir = false; //valida que el usuario no quiera seguir digitando opciones
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric value.");

        }catch (InvalidSerialNumberException e) {
            System.out.println("error, the serial number is not valid (it must be a positive number)");

        }catch (NumberFormatException e){
            System.out.println("Error! You must enter a valid integer (no letters or symbols).");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }

    public static void consultReportsByDate() {
        System.out.println(control.newestDateOldestDate());
        System.out.println("Enter the date you want to consult \n");
        System.out.println("day: ");
        input.nextLine();
        String day = input.nextLine();
        System.out.println("month: ");
        String month = input.nextLine();
        System.out.println("year: ");
        String year = input.nextLine();
        String date = year + "-" + month + "-" + day;
       
        System.out.println(control.consultReportsByDate(date));

    }

    public static void consultReportsByLevel() {
        try {
            System.out.println("Enter the level of severity you want to consult: \n"
                    + "1. Low \n"
                    + "2. Half \n"
                    + "3. High");
            int level = input.nextInt();

            System.out.println(control.consultReportsByLevel(level));
        } catch (InputMismatchException e) {
            System.out.println("Error: The entered digit is not valid.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void consultReportsById() {
        System.out.println(control.getAllReports());

        System.out.println("Enter the ID of the report you want to consult: ");
        input.nextLine();
        String id = input.nextLine();

       ;
       
        System.out.println(control.consultReportsById(id));
    }

    public static void recordHardwareReports() throws InvalidSerialNumberException, InputMismatchException {
        
            System.out.println("Enter the device ID: ");
            input.nextLine();

            String id = input.nextLine();

           

            System.out.println("\nEnter the description of the problem: ");
            String description = input.nextLine();

            int level = 0;
            while (3 < level || level < 1) {
                System.out.println("\n Select the level of severity: \n"
                        + "1. Low \n"
                        + "2. Half \n"
                        + "3. High");

                level = input.nextInt();
            }

            System.out.println("\nEnter the component type");
            input.nextLine();
            String componentType = input.nextLine();

            System.out.println("\nEnter the serial number");
            String serialNumber = input.nextLine();

            int intSerialNumber = Integer.parseInt(serialNumber);
            if (intSerialNumber < 0) {
                throw new InvalidSerialNumberException();
            }

            System.out.println("\nHas the component been changed? (yes/no)");
            String temp = input.nextLine();

            boolean changed = false;
            if (temp.equalsIgnoreCase("yes")) {
                changed = true;
            }

            System.out.println(control.createHardwareReport(id, description, level, serialNumber, changed, componentType));
      
    }

    public static void recordSoftwareReports()  {

      
            System.out.println("Enter the device ID: ");

            input.nextLine();

            String id = input.nextLine();

            System.out.println("\nEnter the description of the problem: ");
            String description = input.nextLine();

            int level = 0;
            while (3 < level || level < 1) {
                System.out.println("\n Select the level of severity: \n"
                        + "1. Low \n"
                        + "2. Half \n"
                        + "3. High");

                level = input.nextInt();
            }

            System.out.println("\nEnter the operating system");
            input.nextLine();
            String oS = input.nextLine();

            System.out.println("\nEnter the software name");
            String softwareName = input.nextLine();

            System.out.println("\nEnter the software version");
            String sV = input.nextLine();

            System.out.println(control.createSoftwareReport(oS, softwareName, sV, description, level, id));
        

    }

    public static void intiallizer() {
        control = new Controller(); //instancia el controlador
        input = new Scanner(System.in); //instancia el scanner
    }
}
