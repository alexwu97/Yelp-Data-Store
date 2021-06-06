package ca.ece.ubc.cpen221.mp5.helpers;

import ca.ece.ubc.cpen221.mp5.models.Review;

public class ReviewInserter extends DataInserter {
    Review review;

    @Override
    protected String convertToJSON() {
        return NO_HTML_ESCAPE_GSON.toJson(review);
    }

    @Override
    protected void insertToTable() {
        database.getReviewTable().put(review.getReview_id(), review);
    }

    @Override
    protected void autoFillAttributes() {
        review.autofillFields();
    }

    @Override
    protected void setModelID(String newID) {
        review.setReview_id(newID);
    }

    @Override
    protected boolean validateModelStructure() {
        return review.isObjectValidatedForInsert() && database.getUserTable().containsKey(review.getUser_id())
                && database.getRestaurantTable().containsKey(review.getBusiness_id());
    }

    @Override
    protected boolean constructModelFromJSON(String query) {
        try{
            review = GSON.fromJson(query, Review.class);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
