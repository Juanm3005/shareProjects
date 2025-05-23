package model;

public class HardwareReport extends Report {

    private String serialNumber;
    private boolean changed;
    private String hardwareType;

    public HardwareReport(String serialNumber, boolean changed, String hardwareType, String descripcion, String id, LevelSeverity levelSeverity) {
        super(descripcion, id, levelSeverity);
        this.serialNumber = serialNumber;
        this.changed = changed;
        this.hardwareType = hardwareType;
    }

    @Override

    public String formatToFile() {
        return "ID: " + getId() + ", Descripcion: " + getDesscripcion() + ", Serial Number: " + serialNumber + ", Changed: " + changed + ", Hardware Type: " + hardwareType + ", Level Severity: " + getLevelSeverity() + ", Date: " + getDate();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean getChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

}
