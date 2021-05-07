package com.xyl.domain;

import java.io.Serializable;

public class FindAllBulidingBean implements Serializable {
    private Integer buildingId;
    private String buildingName;

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
