package rnr.home.solr.bean;

import lombok.Data;

import java.util.Date;

public @Data class SearchBean {
    private String stationName;
    private int totalDocks;
    private double latitude;
    private double longitude;
    private int availableBikes;
    private Date lastCommunicationTime;
}
