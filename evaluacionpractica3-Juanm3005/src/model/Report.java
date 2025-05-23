package model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Report implements Serializable {

    private String id;
    private String descripcion;
    private LevelSeverity levelSeverity;
    private LocalDate date;

    public Report(String descripcion, String id, LevelSeverity levelSeverity) {
        this.date = LocalDate.now();
        this.descripcion = descripcion;
        this.id = id;
        this.levelSeverity = levelSeverity;
    }

    public abstract String formatToFile();
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesscripcion() {
        return descripcion;
    }

    public void setDesscripcion(String desscripcion) {
        this.descripcion = desscripcion;
    }

    public LevelSeverity getLevelSeverity() {
        return levelSeverity;
    }

    public void setLevelSeverity(LevelSeverity levelSeverity) {
        this.levelSeverity = levelSeverity;
    }

    public LocalDate getDate() {
        
        return date;
    }

}
