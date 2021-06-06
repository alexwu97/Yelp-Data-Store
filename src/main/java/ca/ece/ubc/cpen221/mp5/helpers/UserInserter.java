package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.models.User;

public class UserInserter extends DataInserter{
    User user;

    @Override
    protected String convertToJSON() {
        return NO_HTML_ESCAPE_GSON.toJson(user);
    }

    @Override
    protected void insertToTable() {
        database.getUserTable().put(user.getUser_id(), user);
    }

    @Override
    protected void autoFillAttributes() {
        user.autofillFields();
    }

    @Override
    protected void setModelID(String newID) {
        user.setUser_id(newID);
    }

    @Override
    protected boolean validateModelStructure() {
        return user.isObjectValidatedForInsert();
    }

    @Override
    protected boolean constructModelFromJSON(String query) {
        try{
            user = GSON.fromJson(query, User.class);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
