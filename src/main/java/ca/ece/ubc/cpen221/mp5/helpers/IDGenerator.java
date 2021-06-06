package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.enums.GeneratorType;

public class IDGenerator {
    private int idNumber = 0;
    private GeneratorType generatorType;

    public IDGenerator(GeneratorType generatorType){
        this.generatorType = generatorType;
    }

    private void incrementID(){
        this.idNumber++;
    }

    public String generateNewID(){
        return generatorType.getTypeString() + idNumber++;
    }
}
