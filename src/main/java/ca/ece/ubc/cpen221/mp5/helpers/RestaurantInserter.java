package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.models.Location;
import ca.ece.ubc.cpen221.mp5.models.Restaurant;

public class RestaurantInserter extends DataInserter{
    Restaurant restaurant;

    @Override
    protected String convertToJSON() {
        return NO_HTML_ESCAPE_GSON.toJson(restaurant);
    }

    @Override
    protected void insertToTable() {
        database.getRestaurantTable().put(restaurant.getBusiness_id(), restaurant);
    }

    @Override
    protected void autoFillAttributes() {
        restaurant.autofillFields();
    }

    @Override
    protected void setModelID(String newID) {
        restaurant.setBusiness_id(newID);
    }

    @Override
    protected boolean validateModelStructure() {
        return restaurant.isObjectValidatedForInsert();
    }

    @Override
    protected boolean constructModelFromJSON(String query) {
        try{
            restaurant = GSON.fromJson(query, Restaurant.class);
            restaurant.setLocation(GSON.fromJson(query, Location.class));
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
