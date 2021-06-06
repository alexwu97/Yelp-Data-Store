package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.models.User;

import java.util.Map;

public class UserInitializer extends TableInitializer<User> {
    User user;

    @Override
    protected void parseFile(String jsonString) {
        user = gson.fromJson(jsonString, User.class);
    }

    @Override
    protected void insertDataToTable(Map<String, User> userTable) {
        userTable.put(user.getUser_id(), user);
    }
}
