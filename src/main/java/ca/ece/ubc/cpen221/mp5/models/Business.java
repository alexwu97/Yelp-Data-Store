package ca.ece.ubc.cpen221.mp5.models;

public class Business extends Category {
    protected String business_id;
    protected Location location;
    protected Boolean open;

    public Boolean isOpen(){
        return open;
    }

    public String getBusiness_id(){
        return business_id;
    }

    public Location getLocation(){
        return location;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
