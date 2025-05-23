package model;

public class SoftwareReport extends Report {

    private String sistemaOperativo;
    private String softwareName;
    private String softwareVersion;

    public SoftwareReport(String sistemaOperativo, String softwareName, String softwareVersion, String descripcion, String id, LevelSeverity levelSeverity) {
        super(descripcion, id, levelSeverity);
        this.sistemaOperativo = sistemaOperativo;
        this.softwareName = softwareName;
        this.softwareVersion = softwareVersion;
    }

    @Override

    public String formatToFile() {
        return "ID: " + getId() + ", Descripcion: " + getDesscripcion() + ", Sistema Operativo: " + sistemaOperativo + ", Software Name: " + softwareName + ", Software Version: " + softwareVersion + ", Level Severity: " + getLevelSeverity() + ", Date: " + getDate();
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

}
