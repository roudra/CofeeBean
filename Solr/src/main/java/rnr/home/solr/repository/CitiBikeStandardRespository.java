package rnr.home.solr.repository;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import rnr.home.solr.bean.StationBean;

import java.util.List;

public interface CitiBikeStandardRespository extends SolrCrudRepository<StationBean, Long> {

    @Query(value ="stationName:*FDR* AND latitude: [40.7331 TO 40.740 ]")
    List<StationBean> findAllStationsWithNameFDR();

    @Query(value ="availableDocks:0")
    List<StationBean> findAllStationsWithMissingBikes();

    @Query(value ="availableDocks:[1 TO *]")
    List<StationBean> findAllStationsWithOutMissingBikes();

}
