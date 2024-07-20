package enums;

public enum GreenUnitStatus {
    IN_FIGHT("In Fight"),
    IN_PETROL("In Petrol"),
    PERISHED("Perished");

    private final String displayName;

    GreenUnitStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
