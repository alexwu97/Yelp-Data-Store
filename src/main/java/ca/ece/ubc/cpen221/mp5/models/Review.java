package ca.ece.ubc.cpen221.mp5.models;

import ca.ece.ubc.cpen221.mp5.helpers.Validator;

public class Review implements Validator {
    private String review_id;
    private String business_id;
    private String text;
    private String type;
    private String date;
    private String user_id;
    private Double stars;
    private Vote votes;

    public String getType() {
        return type;
    }

    public Double getStars() {
        return stars;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getDate() {
        return date;
    }

    public String getReview_id() {
        return review_id;
    }

    public String getText() {
        return text;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setVotes(Vote votes) {
        this.votes = votes;
    }

    public Vote getVotes() {
        return votes;
    }

    @Override
    public boolean isObjectValidatedForInsert() {
        if(getReview_id() != null || getBusiness_id() == null || getUser_id() == null || getDate() == null || getStars() == null){
            return false;
        }
        if(getStars() > 5.0 || getStars() < 1.0 || (getType() != null && !getType().equals("review"))){
            return false;
        }
        return true;
    }

    @Override
    public void autofillFields() {
        if(getType() == null){
            setType("review");
        }
        if(getVotes() == null){
            setVotes(new Vote(0,0,0));
        }
        if(getText() == null){
            setText("");
        }

    }
}


