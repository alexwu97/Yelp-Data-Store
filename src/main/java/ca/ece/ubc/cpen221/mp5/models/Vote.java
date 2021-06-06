package ca.ece.ubc.cpen221.mp5.models;

public class Vote {
    private Integer cool;
    private Integer useful;
    private Integer funny;

    public Vote(int cool, int useful, int funny){
        this.cool = cool;
        this.useful = useful;
        this.funny = funny;
    }

    public Integer getCool() {
        return cool;
    }

    public Integer getFunny() {
        return funny;
    }

    public Integer getUseful() {
        return useful;
    }

    public void setCool(Integer cool) {
        this.cool = cool;
    }

    public void setFunny(Integer funny) {
        this.funny = funny;
    }

    public void setUseful(Integer useful) {
        this.useful = useful;
    }
}