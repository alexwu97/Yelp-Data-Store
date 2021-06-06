package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.helpers.Validator;
import ca.ece.ubc.cpen221.mp5.services.MP5DbImpl;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public abstract class TableInitializer<T extends Validator> {
    protected Gson gson = new Gson();

    public final void initialize(String filepath, Map<String, T> table) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String jsonString = scanner.nextLine();
            parseFile(jsonString);
            insertDataToTable(table);
        }
    }

    protected abstract void parseFile(String jsonString);

    protected abstract void insertDataToTable(Map<String, T> table);
}
