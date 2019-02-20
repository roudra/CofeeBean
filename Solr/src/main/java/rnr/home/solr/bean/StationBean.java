package rnr.home.solr.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Date;

@SolrDocument(solrCoreName = "CitiBike")
public @Data class StationBean{
    @JsonProperty("id")
    @Id
    @Field private long recordId;
    @Field private String stationName;
    @Field private int availableDocks;
    @Field private int totalDocks;
    @Field private double latitude;
    @Field private double longitude;
    @Field private String statusValue;
    @Field private int statusKey;
    @Field private int availableBikes;
    @Field private String stAddress1;
    @Field private String stAddress2;
    @Field private String city;
    @Field private String postalCode;
    @Field private String location;
    @Field private String altitude;
    @Field private boolean testStation;
    @Field private Date lastCommunicationTime;
    @Field private String landMark;

}
