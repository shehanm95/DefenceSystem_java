package enums;

public enum JustifiedLabelAlignment {
    Top_Left("text-align: left; vertical-align: top;"),;

    private final String displayName;

    JustifiedLabelAlignment(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
    
}


