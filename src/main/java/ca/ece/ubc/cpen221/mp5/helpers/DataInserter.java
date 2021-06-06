package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.helpers.IDGenerator;
import ca.ece.ubc.cpen221.mp5.services.MP5DbImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.platform.commons.util.StringUtils;

public abstract class DataInserter {
    MP5DbImpl database;
    protected final static Gson GSON = new Gson();
    protected final static Gson NO_HTML_ESCAPE_GSON = new GsonBuilder().disableHtmlEscaping().create();

    public DataInserter(){
        database = MP5DbImpl.getInstance();
    }

    public final String insert(String query, IDGenerator idGenerator){
        if(isQueryExists(query) && constructModelFromJSON(query) && validateModelStructure()){
            setModelID(idGenerator.generateNewID());
            autoFillAttributes();
            insertToTable();
            return convertToJSON();
        }
        return null;
    }

    private boolean isQueryExists(String query) {
        return StringUtils.isNotBlank(query);
    }

    abstract protected String convertToJSON();

    abstract protected void insertToTable();

    abstract protected void autoFillAttributes();

    abstract protected void setModelID(String newID);

    abstract protected boolean validateModelStructure();

    abstract protected boolean constructModelFromJSON(String query);
}
