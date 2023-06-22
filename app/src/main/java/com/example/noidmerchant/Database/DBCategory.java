package com.example.noidmerchant.Database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DBCategory implements Serializable {
    private String tendm, nameID;

    public DBCategory(String tendm, String nameID) {
        this.tendm = tendm;

        this.nameID = nameID;
    }

    public DBCategory() {
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
