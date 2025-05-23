package ui;

import customExceptions.InvalidFormatVersionException;
import customExceptions.InvalidSerialNumberException;
import java.time.format.DateTimeParseException;
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

        System.out.println("Welcome to the Electronic Equipment Reporting Management System \n");

        boolean seguir = true; //valida que el usuario quiera seguir digitando opciones
        while (seguir == true) { //valida que el usuario quiera seguir digitando opciones

            int opcion = -1;

            while (opcion < 0 || opcion > 3) { //valida que la opcion digitada exista dentro de las opciones
                try {
                    System.out.println("what do you want to do today\n"
                            + "1. record reports \n"
                            + "2. consult reports \n"
                            + "3. generate inform \n"
                            + "0. exit \n");

                    opcion = input.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Enter a number (not letters or symbol).");
                    input.nextLine();
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }

            }

            switch (opcion) {
                case 1:

                    int reportType = -1;

                    while (reportType < 1 || reportType > 2) {
                        try {
                            System.out.println("Enter the type of report you want to record\n"
                                    + "1. Software \n"
                                    + "2. Hardware \n");

                            reportType = input.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Enter a number (not letters).");
                            input.nextLine();
                        } catch (Exception e) {
                            System.out.println("An unexpected error occurred: " + e.getMessage());
                        }

                    }

                    if (reportType == 1) {
                        recordSoftwareReports();
                    } else {
                        recordHardwareReports();
                    }
                    break;

                case 2:
                    int consultType = 0;

                    while (consultType < 1 || consultType > 3) {
                        try {
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
                        } catch (InputMismatchException e) {
                            System.out.println("Enter a number (not letters).");
                            input.nextLine();
                        } catch (Exception e) {
                            System.out.println("An unexpected error occurred: " + e.getMessage());
                        }

                    }

                    break;

                case 3:
                    int reportType2 = -1;
                    while (reportType2 < 1 || reportType2 > 2) {
                        try {
                            System.out.println("Enter the type of report you want to generate\n"
                                    + "1. Software \n"
                                    + "2. Hardware \n");

                            reportType2 = input.nextInt();
                            if (reportType2 == 1) {
                                System.out.println(control.saveSoftwareReportsToFile());
                            } else if (reportType2 == 2) {
                                System.out.println(control.saveHardwareReportsToFile());
                            } else {
                                System.out.println("Enter one of the menu options\n");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Enter a number (not letters).");
                            input.nextLine();
                        } catch (Exception e) {
                            System.out.println("An unexpected error occurred: " + e.getMessage());
                        }
                    }
                    break;

                case 0:
                    input.nextLine();
                    while (true) {
                        System.out.println("you wanna save the reports? (yes/no)");

                        String temp = input.nextLine();
                        if (temp.equalsIgnoreCase("yes")) {
                            System.out.println(control.saveAllReports());
                            break;
                        } else if (temp.equalsIgnoreCase("no")) {
                            System.out.println("the reports were not saved");
                            break;
                        } else {
                            System.out.println("\nEnter a valid option (yes/no)");
                        }
                    }
                    System.out.println("Thanks for using the system");
                    seguir = false;
                    break;
            }
        }

    }

    public static void consultReportsByDate() {
        input.nextLine();
        String date = "";
        boolean valid = false;
        while (!valid) {
            System.out.println(control.newestDateOldestDate());
            System.out.println("Enter the date you want to consult \n");
            System.out.println("day: ");

            String day = input.nextLine();
            System.out.println("month: ");
            String month = input.nextLine();
            System.out.println("year: ");
            String year = input.nextLine();
            date = year + "-" + month + "-" + day;
            try {
                System.out.println(control.consultReportsByDate(date));
                valid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.\n");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

    }

    public static void consultReportsByLevel() {
        int level = 0;
        while (level < 1 || level > 3) {
            try {
                System.out.println("Enter the level of severity you want to consult: \n"
                        + "1. Low \n"
                        + "2. Half \n"
                        + "3. High");
                level = input.nextInt();
                if (level < 1 || level > 3) {
                    System.out.println("\nEnter a number between 1 and 3.\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("\na number (not letters or symbols).\n");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

        }
        System.out.println(control.consultReportsByLevel(level));
    }

    public static void consultReportsById() {
        System.out.println(control.getAllReports());

        System.out.println("Enter the ID of the report you want to consult: ");
        input.nextLine();
        String id = input.nextLine();

        System.out.println(control.consultReportsById(id));
    }

    public static void recordHardwareReports() throws InvalidSerialNumberException {

        System.out.println("Enter the device ID: ");
        input.nextLine();

        String id = input.nextLine();

        System.out.println("\nEnter the description of the problem: ");
        String description = input.nextLine();

        int level = 0;
        while (3 < level || level < 1) {
            try {
                System.out.println("\nSelect the level of severity: \n"
                        + "1. Low \n"
                        + "2. Half \n"
                        + "3. High");

                level = input.nextInt();
                if (level < 1 || level > 3) {
                    System.out.println("\nEnter a number between 1 and 3.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number for select a severity level (not letters).");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

        }

        System.out.println("\nEnter the component type");
        input.nextLine();
        String componentType = input.nextLine();
        boolean valid = false;
        String serialNumber = "";
        while (!valid) {
            System.out.println("\nEnter the serial number");
            serialNumber = input.nextLine();
            try {
                int intSerialNumber = Integer.parseInt(serialNumber);
                if (intSerialNumber < 0) {
                    throw new InvalidSerialNumberException();
                }
                valid = true;
            } catch (InvalidSerialNumberException e) {
                System.out.println("error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error! the serial number must be a number");
            }
        }
        String temp = "";
        boolean changed = false;
        while (!temp.equalsIgnoreCase("yes") && !temp.equalsIgnoreCase("no")) {

            System.out.println("\nHas the component been changed? (yes/no)");
            temp = input.nextLine();

            changed = false;
            if (temp.equalsIgnoreCase("yes")) {
                changed = true;
            } else if (temp.equalsIgnoreCase("no")) {
                changed = false;
            } else {
                System.out.println("\nEnter a valid option (yes/no)");
            }

        }

        System.out.println(control.createHardwareReport(id, description, level, serialNumber, changed, componentType) + "\n");

    }

    public static void recordSoftwareReports() {

        System.out.println("Enter the device ID: ");

        input.nextLine();

        String id = input.nextLine();

        System.out.println("\nEnter the description of the problem: ");
        String description = input.nextLine();

        int level = 0;
        while (3 < level || level < 1) {
            try {
                System.out.println("\nSelect the level of severity: \n"
                        + "1. Low \n"
                        + "2. Half \n"
                        + "3. High");

                level = input.nextInt();

                if (level < 1 || level > 3) {
                    System.out.println("\nEnter a number between 1 and 3.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number (not letters or symbols).");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

        }

        System.out.println("\nEnter the operating system");
        input.nextLine();
        String oS = input.nextLine();

        System.out.println("\nEnter the software name");
        String softwareName = input.nextLine();

        boolean valid = false;
        String sV = "";
        while (!valid) {
            System.out.println("\nEnter the software version");
            sV = input.nextLine();
            try {
                valid = control.validVersion(sV);
            } catch (InvalidFormatVersionException e) {
                System.out.println("Error! The version format is invalid. It should be in the format X.X.X");
            }
        }

        System.out.println(control.createSoftwareReport(oS, softwareName, sV, description, level, id));

    }

    public static void intiallizer() {
        control = new Controller(); //instancia el controlador
        input = new Scanner(System.in); //instancia el scanner
    }
}
