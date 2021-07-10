package ca.ece.ubc.cpen221.mp5.services;

import ca.ece.ubc.cpen221.mp5.helpers.*;
import ca.ece.ubc.cpen221.mp5.models.*;
import ca.ece.ubc.cpen221.mp5.enums.GeneratorType;
import com.google.gson.Gson;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class MP5DbImpl implements MP5Db<Restaurant> {
    private static MP5DbImpl yelpDB = new MP5DbImpl("data/restaurants.json", "data/users.json", "data/reviews.json");
    private Map<String, Restaurant> restaurantTable;
    private Map<String, User> userTable;
    private Map<String, Review> reviewTable;
    IDGenerator restaurantIDGenerator;
    IDGenerator userIDGenerator;
    IDGenerator reviewIDGenerator;
    private Object restaurantLock = new Object();
    private Object userLock = new Object();
    private Object reviewLock = new Object();

    private Gson gson = new Gson();

    public final static String INVALID_REQUEST = "ERR: INVALID_REQUEST";
    public final static String INVALID_RESTAURANT_ID = "ERR: INVALID_RESTAURANT_ID";
    public final static String INVALID_RESTAURANT_STRING = "ERR: INVALID_RESTAURANT_STRING";
    public final static String INVALID_USER_STRING = "ERR: INVALID_USER_STRING";
    public final static String INVALID_USER_ID = "ERR: INVALID_USER_ID";
    public final static String INVALID_REVIEW_STRING = "ERR: INVALID_REVIEW_STRING";
    public final static String INVALID_REVIEW_ID = "ERR: INVALID_REVIEW_ID";

    private MP5DbImpl(String restaurantList, String userList, String reviewList){
        restaurantTable = new HashMap<>();
        userTable = new HashMap<>();
        reviewTable = new HashMap<>();

        restaurantIDGenerator = new IDGenerator(GeneratorType.RESTAURANT);
        userIDGenerator = new IDGenerator(GeneratorType.USER);
        reviewIDGenerator = new IDGenerator(GeneratorType.REVIEW);

        readJSONFileAndInsertToRestaurantTable(restaurantList);
        readJSONFileAndInsertToUserTable(userList);
        readJSONFileAndInsertToReviewTable(reviewList);
    }

    public static MP5DbImpl getInstance(){
        return yelpDB;
    }

    private void readJSONFileAndInsertToRestaurantTable(String restaurantList){
        RestaurantInitializer restaurantInitializer = new RestaurantInitializer();
        try{
            restaurantInitializer.initialize(restaurantList, restaurantTable);
        }catch(Exception e) {
            System.out.println("cannot insert a restaurant from json");
            e.printStackTrace();
        }
    }

    private void readJSONFileAndInsertToUserTable(String userList){
        UserInitializer userInitializer = new UserInitializer();
        try{
            userInitializer.initialize(userList, userTable);
        }catch(Exception e) {
            System.out.println("cannot insert a user from json");
            e.printStackTrace();
        }
    }

    private void readJSONFileAndInsertToReviewTable(String reviewList){
        ReviewInitializer reviewInitializer = new ReviewInitializer();
        try{
            reviewInitializer.initialize(reviewList, reviewTable);
        }catch(Exception e) {
            System.out.println("cannot insert a review from json");
            e.printStackTrace();
        }
    }

    public String selectRestaurant(String query){
        synchronized (restaurantLock){
            return getRestaurant(query);
        }
    }

    public String deleteRestaurant(String query){
        synchronized (restaurantLock){
            String result = getRestaurant(query);
            restaurantTable.remove(query);
            return result;
        }
    }

    public String getRestaurant(String query){
        if(query == null || query.length() == 0 || !restaurantTable.containsKey(query)){
            return INVALID_RESTAURANT_ID;
        }
        Restaurant foundedRestaurant = restaurantTable.get(query);
        return gson.toJson(foundedRestaurant);
    }

    public String selectUser(String query){
        synchronized (userLock){
            return getUser(query);
        }
    }

    public String deleteUser(String query){
        synchronized (userLock){
            String result = getUser(query);
            userTable.remove(query);
            return result;
        }
    }

    public String getUser(String query){
        if(query == null || query.length() == 0 || !userTable.containsKey(query)){
            return INVALID_USER_ID;
        }
        User foundedUser = userTable.get(query);
        return gson.toJson(foundedUser);
    }

    public String selectReview(String query){
        synchronized (reviewLock){
            return getReview(query);
        }
    }

    public String deleteReview(String query){
        synchronized (restaurantLock){
            String result = getReview(query);
            reviewTable.remove(query);
            return result;
        }
    }

    public String getReview(String query){
        if(query == null || query.length() == 0 || !reviewTable.containsKey(query)){
            return INVALID_REVIEW_ID;
        }
        Review foundedReview = reviewTable.get(query);
        return gson.toJson(foundedReview);
    }

    public String insertRestaurant(String query){
        synchronized (restaurantLock){
            RestaurantInserter restaurantInserter = new RestaurantInserter();
            String result = restaurantInserter.insert(query, restaurantIDGenerator);
            return StringUtils.isBlank(result) ? INVALID_RESTAURANT_STRING : result;
        }
    }

    public String insertUser(String query){
        synchronized (userLock){
            DataInserter userInserter = new UserInserter();
            String result = userInserter.insert(query, userIDGenerator);
            return StringUtils.isBlank(result) ? INVALID_USER_STRING : result;
        }
    }

    public String insertReview(String query){
        synchronized (reviewLock){
            ReviewInserter reviewInserter = new ReviewInserter();
            String result = reviewInserter.insert(query, reviewIDGenerator);
            return StringUtils.isBlank(result) ? INVALID_REVIEW_STRING : result;
        }
    }

    public String predictStars(String query){
        if(query == null || query.length() == 0){
            return INVALID_REQUEST;
        }

        String[] queryArr = query.split(" ", 2);
        if(queryArr.length <= 1){
            return INVALID_REQUEST;
        }
        String predictorUser = queryArr[0];
        String predictingRestaurant = queryArr[1];
        ToDoubleBiFunction<MP5Db<Restaurant>, String> func = getPredictorFunction(predictorUser);
        return String.valueOf(func.applyAsDouble(this, predictingRestaurant));
    }

    @Override
    public ToDoubleBiFunction<MP5Db<Restaurant>, String> getPredictorFunction(String user) {
        List<Double> userReviewStarList = new ArrayList<Double>();
        List<Integer> restaurantPriceList = new ArrayList<Integer>();
        for(Review review : reviewTable.values()){
            if(review.getUser_id().equals(user)){
                userReviewStarList.add(review.getStars());
                String reviewedBusinessId = review.getBusiness_id();
                if(!restaurantTable.containsKey(reviewedBusinessId)){
                    throw new IllegalArgumentException();
                }
                restaurantPriceList.add(restaurantTable.get(reviewedBusinessId).getPrice());
            }
        }
        LeastSquareRegressor<Integer, Double> lsRegressorModel = new LeastSquareRegressor<>();
        lsRegressorModel.generateRegressionModel(restaurantPriceList, userReviewStarList);

        return (db, restaurantID) -> {
            double predictedPrice = ((MP5DbImpl) db).getRestaurantTable().get(restaurantID).getPrice();
            return Math.max(1.0, Math.min(5.0, lsRegressorModel.predictOutput(predictedPrice)));
        };
    }

    //GETTERS

    public Map<String, Restaurant> getRestaurantTable(){
        return this.restaurantTable;
    }

    public Map<String, User> getUserTable() {
        return userTable;
    }

    public Map<String, Review> getReviewTable() {
        return reviewTable;
    }
}
