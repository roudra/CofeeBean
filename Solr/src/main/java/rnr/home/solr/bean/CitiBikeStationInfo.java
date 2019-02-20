package rnr.home.solr.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@JsonAutoDetect
public @Data class CitiBikeStationInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date executionTime;
    private Collection<StationBean> stationBeanList;
}

