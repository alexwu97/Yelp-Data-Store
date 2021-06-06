package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.models.Review;

import java.util.Map;

public class ReviewInitializer extends TableInitializer<Review>{
    Review review;

    @Override
    protected void parseFile(String jsonString) {
        review = gson.fromJson(jsonString, Review.class);
    }

    @Override
    protected void insertDataToTable(Map<String, Review> reviewTable) {
        reviewTable.put(review.getReview_id(), review);
    }
}
