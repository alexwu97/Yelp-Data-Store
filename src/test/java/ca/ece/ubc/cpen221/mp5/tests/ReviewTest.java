package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.models.Review;
import ca.ece.ubc.cpen221.mp5.models.Vote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ReviewTest {
    Review validReview = createValidReview();
    Vote mockReview = Mockito.mock(Vote.class);

    @Test
    void isObjectValidatedForInsertValid(){
        Assertions.assertTrue(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertContainsReviewID(){
        validReview.setReview_id("revID2");
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertNoBusinessID(){
        validReview.setBusiness_id(null);
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertNoUserID(){
        validReview.setUser_id(null);
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertNoDate(){
        validReview.setDate(null);
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertNoStars(){
        validReview.setStars(null);
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertMoreThanFiveStars(){
        validReview.setStars(5.1);
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertZeroStars(){
        validReview.setStars(0.0);
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertIncorrectType(){
        validReview.setType("business");
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
        validReview.setType("user");
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
        validReview.setType("randomText");
        Assertions.assertFalse(validReview.isObjectValidatedForInsert());
    }

    @Test
    void isObjectValidatedForInsertReviewType(){
        validReview.setType("review");
        Assertions.assertTrue(validReview.isObjectValidatedForInsert());
    }

    @Test
    void autoFillFields(){
        Review emptyReview = new Review();
        emptyReview.autofillFields();
        Assertions.assertEquals(emptyReview.getType(), "review");
        Assertions.assertEquals(emptyReview.getVotes().getFunny(), 0);
        Assertions.assertEquals(emptyReview.getVotes().getCool(), 0);
        Assertions.assertEquals(emptyReview.getVotes().getUseful(), 0);
        Assertions.assertEquals(emptyReview.getText(), "");
    }

    private Review createValidReview(){
        Review review = new Review();
        review.setBusiness_id("busID1");
        review.setUser_id("userID1");
        review.setDate("Jan1");
        review.setStars(4.0);
        return review;
    }
}
