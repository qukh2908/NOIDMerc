package com.example.noidmerchant.Adapter;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private String tendm,nameID;

    public Category(String tendm, String nameID) {
        this.tendm = tendm;

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





    public String getNameID(String key) {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }
        public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("tendm", tendm);

        return result;
    }
}
