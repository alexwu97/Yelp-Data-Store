package ca.ece.ubc.cpen221.mp5.models;

public abstract class Category {
    protected String name;
    protected Integer review_count;
    protected String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReview_count(Integer review_count) {
        this.review_count = review_count;
    }
}
