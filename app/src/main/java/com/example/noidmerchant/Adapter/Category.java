package com.example.noidmerchant.Adapter;

public class Category {
    private String tendm,pic,nameID;

    public Category(String tendm, String pic, String nameID) {
        this.tendm = tendm;
        this.pic = pic;
        this.nameID = nameID;
    }

    public Category() {
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNameID(String key) {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }
    //    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//
//        return result;
//    }
}
