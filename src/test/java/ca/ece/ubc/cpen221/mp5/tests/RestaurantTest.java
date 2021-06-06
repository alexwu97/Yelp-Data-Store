package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.models.Location;
import ca.ece.ubc.cpen221.mp5.models.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

public class RestaurantTest {
    Restaurant validRestaurant = createValidRestaurant();
    Location mockLocation = Mockito.mock(Location.class);

    @Test
    void isObjectValidatedForInsertValid(){
        validRestaurant.setLocation(mockLocation);
        when(mockLocation.isObjectValidatedForInsert()).thenReturn(true);
        Assertions.assertTrue(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertNoName() {
        String name = validRestaurant.getName();
        validRestaurant.setName(null);

        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertWithURL(){
        validRestaurant.setUrl("http://www.yelp.com/biz/cafe-3-berkeley");
        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertNoOpen(){
        validRestaurant.setOpen(null);
        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertNoLocation(){
        validRestaurant.setLocation(null);
        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertTypeNotBusiness(){
        validRestaurant.setType("test");
        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertStarsOver5(){
        validRestaurant.setStars(10.0);
        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertStarsLessThan1(){
        validRestaurant.setStars(0.99);
        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertPriceLessThan1(){
        validRestaurant.setPrice(0);
        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertPriceOver3(){
        validRestaurant.setPrice(4);
        Assertions.assertFalse(validRestaurant.isObjectValidatedForInsert());
    }

    @Test
    void autoFillTest(){
        Restaurant emptyRestaurant = new Restaurant();
        emptyRestaurant.setName("testName");
        emptyRestaurant.setLocation(mockLocation);
        when(mockLocation.getCity()).thenReturn("testCity");

        emptyRestaurant.autofillFields();
        Assertions.assertEquals(emptyRestaurant.getType(), "business");
        Assertions.assertEquals(emptyRestaurant.getStars(), 5.0D);
        Assertions.assertEquals(emptyRestaurant.getPrice(), 1);
        Assertions.assertEquals(emptyRestaurant.getReview_count(), 0);
        Assertions.assertEquals(emptyRestaurant.getCategories(), new ArrayList<>());
        Assertions.assertEquals(emptyRestaurant.getSchools(), new ArrayList<>());
        Assertions.assertEquals(emptyRestaurant.getUrl(), Restaurant.URL_RESOURCE + "testName-testCity");
        Assertions.assertEquals(emptyRestaurant.getPhoto_url(), Restaurant.PHOTO_URL_RESOURCE + "testName-testCity" + Restaurant.PHOTO_EXTENSION);
    }

    private Restaurant createValidRestaurant(){
        Restaurant restaurantTestValid = new Restaurant();
        restaurantTestValid.setName("Cafe 3");
        restaurantTestValid.setBusiness_id("gclB3ED6uk6viWlolSb_uA");
        restaurantTestValid.setCategories(new ArrayList<>());
        restaurantTestValid.getCategories().add("Cafes");
        restaurantTestValid.getCategories().add("Restaurants");
        restaurantTestValid.setOpen(true);
        restaurantTestValid.setPrice(1);
        restaurantTestValid.setLocation(mockLocation);
        restaurantTestValid.setType("business");
        restaurantTestValid.setStars(2.0);
        restaurantTestValid.setReview_count(9);
        restaurantTestValid.setPhoto_url("http://s3-media1.ak.yelpcdn.com/bphoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg");
        restaurantTestValid.setSchools(new ArrayList<>());
        restaurantTestValid.getSchools().add("University of California at Berkeley");
        return restaurantTestValid;
    }
}
