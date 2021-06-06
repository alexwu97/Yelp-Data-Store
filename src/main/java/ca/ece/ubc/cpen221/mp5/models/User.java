package ca.ece.ubc.cpen221.mp5.models;

import ca.ece.ubc.cpen221.mp5.helpers.Validator;

public class User implements Validator{
    public static final String URL_RESOURCE = "http://www.yelp.com/user_details?userid=";
    private String type;
    private String user_id;
    private String name;
    private Integer review_count;
    private Double average_stars;
    private Vote votes;
    private String url;

    public String getUser_id() {
        return user_id;
    }

    public String getType() {
        return type;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public Double getAverage_stars() {
        return average_stars;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Vote getVotes() {
        return votes;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReview_count(Integer review_count) {
        this.review_count = review_count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAverage_stars(Double average_stars) {
        this.average_stars = average_stars;
    }

    public void setVotes(Vote votes) {
        this.votes = votes;
    }

    public void setFields(String user_id){

    }

    @Override
    public boolean isObjectValidatedForInsert() {
        if(getName() == null || getUrl() != null || (getType() != null && !getType().equals("user"))){
            return false;
        }
        return true;
    }

    @Override
    public void autofillFields() {
        if(getType() == null){
            setType("user");
        }
        if(getUrl() == null) {
            setUrl(URL_RESOURCE + getUser_id());
        }
        if(getVotes() == null){
            setVotes(new Vote(0,0,0));
        }
        if(getReview_count() == null){
            setReview_count(0);
        }
        if(getAverage_stars() == null){
            setAverage_stars(0.0);
        }
    }
}