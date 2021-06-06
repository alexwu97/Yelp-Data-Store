package ca.ece.ubc.cpen221.mp5;

import ca.ece.ubc.cpen221.mp5.services.MP5DbImpl;

public class YelpDBController {
    private MP5DbImpl yelpDB;


    public YelpDBController(){
        this.yelpDB = MP5DbImpl.getInstance();
    }

    public String handleRequest(String request){
        String[] requestArr = request.split(" ", 2);
        String response = yelpDB.INVALID_REQUEST;
        if(requestArr.length <= 1){
            return response;
        }
        String query = requestArr[0];
        String requestData = requestArr[1];

        switch(query){
            case "GETRESTAURANT":
                response = getRestaurant(requestData);
                break;
            case "ADDUSER":
                response = addUser(requestData);
                break;
            case "ADDRESTAURANT":
                response = addRestaurant(requestData);
                break;
            case "ADDREVIEW":
                response = addReview(requestData);
                break;
            case "PREDICT":
                response = predictStars(requestData);
                break;
            default:
                response = yelpDB.INVALID_REQUEST;
        }
        return response;
    }

    public String getRestaurant(String data){
        return yelpDB.selectRestaurant(data);
    }

    public String addUser(String data){
        return yelpDB.insertUser(data);
    }

    public String addRestaurant(String data){
        return yelpDB.insertRestaurant(data);
    }

    public String addReview(String data){
        return yelpDB.insertReview(data);
    }

    public String predictStars(String data){
        return yelpDB.predictStars(data);
    }
}
