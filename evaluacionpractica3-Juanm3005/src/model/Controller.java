package model;

import customExceptions.InvalidId;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Controller {

    public Controller() {
        reports = new ArrayList<>();
    }

    public String consultReportsByDate(String date) {
        String mensaje = "";
        for (Report report : reports) {
            if (report.getDate().equals(date)) {
                mensaje += report.formatToFile() + "\n";
            }
        }

        if (mensaje.equals("")) {
            mensaje = "There are no reports with that date";
        } else {
            mensaje = "Reports with the date " + date + ":\n" + mensaje;
        }
        return mensaje;
    }

    public String consultReportsByLevel(int level) {
        String mensaje = "";
        LevelSeverity severity;
        if (level == 1) {
            severity = LevelSeverity.LOW;
        } else if (level == 2) {
            severity = LevelSeverity.HALF;
        } else {
            severity = LevelSeverity.HIGH;
        }
        for (Report report : reports) {
            if (report.getLevelSeverity() == severity) {
                mensaje += report.formatToFile() + "\n";
            }
        }

        if (mensaje.equals("")) {
            mensaje = "There are no reports with that level of severity.";
        } else {
            mensaje = "Reports with the level of severity " + severity + ":\n" + mensaje;
        }
        return mensaje;
    }

    public String consultReportsById(String id) {
        String mensaje = "";
        for (Report report : reports) {
            if (report.getId().equals(id)) {
                mensaje += report.formatToFile() + "\n";
            }
        }

        if (mensaje.equals("")) {
            mensaje = "There are no reports with that id";
        } else {
            mensaje = "Reports with the id " + id + ":\n" + mensaje;
        }

        return mensaje;
    }

    public String getHardwareReports() {
        String mensaje = "";
        for (Report report : reports) {
            if (report instanceof HardwareReport) {
                mensaje += report.getId() + " - " + report.getLevelSeverity() + "\n";
            }

        }

        if (mensaje.equals("")) {
            mensaje = "No Hardware Reports";
        } else {
            mensaje = "Hardware Reports:\n" + mensaje;
        }

        return mensaje;
    }

    public String getSoftwareReports() {
        String mensaje = "";
        for (Report report : reports) {
            if (report instanceof SoftwareReport) {
                mensaje += report.getId() + " - " + report.getLevelSeverity() + "\n";
            }

        }

        if (mensaje.equals("")) {
            mensaje = "No software reports";
        } else {
            mensaje = "Software reports:\n" + mensaje;
        }

        return mensaje;
    }

    public String saveAllReports() {
        String name = "data\\databaseReports.dat";

        File dataBase = new File(name);

        try {
            dataBase.createNewFile();
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(dataBase));

            for (Report p : reports) {
                writer.writeObject(p);
            }
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            return "File not found";

        } catch (IOException e) {
            return "Error, the file path is not valid";

        }

        return "";
    }

    public String loadReportsFromFile() {

        File dataBase = new File("data\\databaseReports.dat");

        try {

            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(dataBase));

            Report temp = null;

            boolean flag = false;

            while (!flag) {

                temp = (Report) reader.readObject();

                if (temp != null) {
                    reports.add(temp);
                } else {
                    flag = true;
                }

            }

            reader.close();

        } catch (ClassNotFoundException e) {
            return "Error de clase";
        } catch (EOFException e) {
            return "Loading successful";
        } catch (FileNotFoundException e) {
            return "File not found";
        } catch (IOException e) {
            return "Error, the file path is not valid";
        }

        return "";

    }

    private ArrayList<Report> reports;

    public LevelSeverity getLevelSeverity(int level) {
        switch (level) {
            case 1:
                return LevelSeverity.LOW;
            case 2:
                return LevelSeverity.HALF;
            case 3:
                return LevelSeverity.HIGH;

        }
        return null;
    }

    public String createHardwareReport(String id, String description, int levelSeverity, String serialNumber, boolean changed, String hardwareType) {

        try {
            HardwareReport hardwareReport = new HardwareReport(serialNumber, changed, hardwareType, description, id, getLevelSeverity(levelSeverity));
            reports.add(hardwareReport);
            return "Hardware report created successfully";
        } catch (Exception e) {
            return "Error creating hardware report" + e + e.getMessage();
        }

    }

    public String createSoftwareReport(String sistemaOperativo, String softwareName, String softwareVersion, String description, int levelSeverity, String id) throws InvalidId {
        int idInt = Integer.parseInt(id);

        if (idInt < 0) {
            throw new InvalidId();
        }

        try {
            SoftwareReport softwareReport = new SoftwareReport(sistemaOperativo, softwareName, softwareVersion, description, id, getLevelSeverity(levelSeverity));
            reports.add(softwareReport);
            return "Report of successfully created software";
        } catch (Exception e) {
            return "Error creating software report" + e + e.getMessage();
        }

    }

    public String saveSoftwareReportsToFile() {
        String name = "data\\reports\\Reporte_Software_";

        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");

        name = name + now.format(dateFormatter) + "_" + time.format(timeFormatter) + ".txt";
        File dataBase = new File(name);

        try {
            dataBase.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataBase));

            for (Report p : reports) {
                if (p instanceof SoftwareReport) {
                    writer.write(p.formatToFile() + "\n");
                }
            }
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            return "File not found";

        } catch (IOException e) {
            return "Error, the file path is not valid";

        }

        return "";
    }

    public String saveHardwareReportsToFile() {
        String name = "data\\reports\\Reporte_Hardware_";

        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");

        name = name + now.format(dateFormatter) + "_" + time.format(timeFormatter) + ".txt";
        File dataBase = new File(name);

        try {
            dataBase.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataBase));

            for (Report p : reports) {
                if (p instanceof HardwareReport) {
                    writer.write(p.formatToFile() + "\n");
                }
            }
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            return "file not found";

        } catch (IOException e) {
            return "Error, The file path is not valid";

        }

        return "";
    }

}
