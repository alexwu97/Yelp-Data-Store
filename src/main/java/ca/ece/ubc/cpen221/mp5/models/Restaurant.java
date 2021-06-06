package ca.ece.ubc.cpen221.mp5.models;

import ca.ece.ubc.cpen221.mp5.helpers.Validator;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Business implements Validator {
    public static final String URL_RESOURCE = "http://www.yelp.com/biz/";
    public static final String PHOTO_URL_RESOURCE = "http://s3-media1.ak.yelpcdn.com/bphoto/";
    public static final String PHOTO_EXTENSION = "/ms.jpg";
    private String url;
    private String photo_url;
    private Integer price;
    private List<String> schools;
    private Double stars;
    private List<String> categories;

    public String getUrl(){
        return url;
    }

    public String getPhoto_url(){
        return photo_url;
    }

    public Integer getPrice(){
        return price;
    }

    public List<String> getSchools() {
        return schools;
    }

    public Double getStars() {
        return stars;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setPhoto_url(String photo_url){
        this.photo_url = photo_url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setSchools(List<String> schools) {
        this.schools = schools;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public boolean isObjectValidatedForInsert() {
        if(getBusiness_id() != null || getName() == null || getUrl() != null || isOpen() == null || getLocation() == null || !getLocation().isObjectValidatedForInsert()){
            return false;
        }

        if((getType() != null && !getType().equals("business")) || (getStars() != null && (getStars() < 1.0 || getStars() > 5.0)) || (getPrice() != null && (getPrice() < 1 || getPrice() > 3))){
            return false;
        }
        return true;
    }

    @Override
    public void autofillFields() {
        getLocation().autofillFields();
        if(getType() == null){
            setType("business");
        }
        if(getStars() == null){
            setStars(5.0D);
        }
        if(getPrice() == null){
            setPrice(1);
        }
        if(getReview_count() == null){
            setReview_count(0);
        }
        if(getCategories() == null){
            setCategories(new ArrayList<>());
        }
        if(getSchools() == null){
            setSchools(new ArrayList<>());
        }

        String urlEndpoint = formURLEndpoint();

        if(getUrl() == null){
            setUrl(URL_RESOURCE + urlEndpoint);
        }

        if(getPhoto_url() == null){
            StringBuilder fullURL = new StringBuilder();
            fullURL.append(PHOTO_URL_RESOURCE).append(urlEndpoint).append(PHOTO_EXTENSION);
            setPhoto_url(fullURL.toString());
        }
    }

    private String formURLEndpoint(){
        StringBuilder endpoint = new StringBuilder();
        endpoint.append(getName().trim());
        if(getLocation() != null && getLocation().getCity() != null){
            endpoint.append(' ').append(getLocation().getCity().trim());
        }
        for(int i = 0; i < endpoint.length(); i++){
            if(endpoint.charAt(i) == ' '){
                endpoint.setCharAt(i, '-');
            }
        }
        return endpoint.toString();
    }
}
