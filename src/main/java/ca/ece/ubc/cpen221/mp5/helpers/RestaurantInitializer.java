package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.models.Location;
import ca.ece.ubc.cpen221.mp5.models.Restaurant;

import java.util.Map;

public class RestaurantInitializer extends TableInitializer<Restaurant>{
    Restaurant restaurant;

    @Override
    protected void parseFile(String jsonString) {
        restaurant = gson.fromJson(jsonString, Restaurant.class);
        restaurant.setLocation(gson.fromJson(jsonString, Location.class));
    }

    @Override
    protected void insertDataToTable(Map<String, Restaurant> restaurantTable) {
        restaurantTable.put(restaurant.getBusiness_id(), restaurant);
    }
}
