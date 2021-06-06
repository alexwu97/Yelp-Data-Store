package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.services.MP5DbImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MP5DbImplTest {
    MP5DbImpl db = MP5DbImpl.getInstance();
    String getRestaurantQuery = "ZMqhKMjtdqVZLw11ja3ANg";
    String addRestaurantQuery = "{\"name\": \"resA\", \"open\": false, \"latitude\": 1.23456, \"longitude\": 2.34567}";

    @Test
    void selectRestaurantValidID(){
        String expectedResult = "{\"url\":\"http://www.yelp.com/biz/crepes-a-go-go-berkeley-3\",\"photo_url\":\"http://s3-media3.ak.yelpcdn.com/bphoto/HjAUFQwPfkUXB6IYssCSag/ms.jpg\",\"price\":1,\"schools\":[\"University of California at Berkeley\"],\"stars\":4.0,\"categories\":[\"Creperies\",\"Restaurants\"],\"business_id\":\"ZMqhKMjtdqVZLw11ja3ANg\",\"location\":{\"longitude\":-122.25917,\"latitude\":37.86792,\"full_address\":\"2334 Telegraph Ave\\nTelegraph Ave\\nBerkeley, CA 94704\",\"city\":\"Berkeley\",\"state\":\"CA\",\"neighborhoods\":[\"Telegraph Ave\",\"UC Campus Area\"]},\"open\":true,\"name\":\"Crepes A-Go Go\",\"review_count\":158,\"type\":\"business\"}";
        Assertions.assertEquals(db.selectRestaurant(getRestaurantQuery), expectedResult);
    }

    @Test
    void selectRestaurantIncorrectQuery(){
        getRestaurantQuery = "{" + getRestaurantQuery + "}";
        Assertions.assertEquals(db.selectRestaurant(getRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_ID);
        getRestaurantQuery = "randomText";
        Assertions.assertEquals(db.selectRestaurant(getRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_ID);
        getRestaurantQuery = null;
        Assertions.assertEquals(db.selectRestaurant(getRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_ID);
        getRestaurantQuery = "";
        Assertions.assertEquals(db.selectRestaurant(getRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_ID);
    }

    @Test
    void insertRestaurantValidQuery(){
        Assertions.assertEquals(db.insertRestaurant(addRestaurantQuery), "{\"url\":\"http://www.yelp.com/biz/resA\",\"photo_url\":\"http://s3-media1.ak.yelpcdn.com/bphoto/resA/ms.jpg\",\"price\":1,\"schools\":[],\"stars\":5.0,\"categories\":[],\"business_id\":\"restaurant0\",\"location\":{\"longitude\":2.34567,\"latitude\":1.23456},\"open\":false,\"name\":\"resA\",\"review_count\":0,\"type\":\"business\"}");
    }

    @Test
    void insertRestaurantInvalidQuery(){
        addRestaurantQuery = "randomText";
        Assertions.assertEquals(db.insertRestaurant(addRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_STRING);
        addRestaurantQuery = "";
        Assertions.assertEquals(db.insertRestaurant(addRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_STRING);
        addRestaurantQuery = null;
        Assertions.assertEquals(db.insertRestaurant(addRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_STRING);
    }

    @Test
    void insertRestaurantIncorrectParameters(){
        addRestaurantQuery = "{\"name\": \"resA\", \"url\": \"http://www.yelp.com/biz/panchos-berkeley\", \"open\": false, \"latitude\": 1.23456, \"longitude\": 2.34567}";
        Assertions.assertEquals(db.insertRestaurant(addRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_STRING);
        addRestaurantQuery = "{\"name\": \"resA\", \"type\": \"review\", \"open\": false, \"latitude\": 1.23456, \"longitude\": 2.34567}";
        Assertions.assertEquals(db.insertRestaurant(addRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_STRING);
        addRestaurantQuery = "{\"name\": \"resA\", \"stars\": 0, \"open\": false, \"latitude\": 1.23456, \"longitude\": 2.34567}";
        Assertions.assertEquals(db.insertRestaurant(addRestaurantQuery), MP5DbImpl.INVALID_RESTAURANT_STRING);
    }
}
