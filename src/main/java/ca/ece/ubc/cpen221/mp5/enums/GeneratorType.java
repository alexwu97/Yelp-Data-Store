package ca.ece.ubc.cpen221.mp5.enums;

public enum GeneratorType {
    RESTAURANT("restaurant"),
    USER("user"),
    REVIEW("review");

    private final String typeString;
    GeneratorType(String type){
        this.typeString = type;
    }

    public String getTypeString() {
        return typeString;
    }
}
