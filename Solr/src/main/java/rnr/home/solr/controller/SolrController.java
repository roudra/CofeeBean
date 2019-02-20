package rnr.home.solr.controller;

import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rnr.home.solr.bean.CitiBikeStationInfo;
import rnr.home.solr.bean.SearchBean;
import rnr.home.solr.bean.StationBean;
import rnr.home.solr.repository.CitiBikeCustomRepository;
import rnr.home.solr.repository.CitiBikeStandardRespository;
import rnr.home.solr.standalone.FeedLoader;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
public class SolrController {

    @Autowired
    CitiBikeStandardRespository cbsr;
    @Autowired
    CitiBikeCustomRepository cbcr;
    @Autowired
    private FeedLoader fl;

    @RequestMapping(value = "/feed/view", method = RequestMethod.GET)
    public CitiBikeStationInfo viewFeed() {
        try {
            return fl.loadFeed("/feed/citibike_nyc.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/feed/insert", method = RequestMethod.GET)
    public int loadFeed() {
        try {
            CitiBikeStationInfo info = fl.loadFeed("/feed/citibike_nyc.json");
            Iterable<StationBean> result = cbsr.saveAll(info.getStationBeanList());
            return Iterables.size(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequestMapping(value = "/feed/update", method = RequestMethod.GET)
    public int update() {
        Collection<StationBean> result =  cbsr.findAllStationsWithMissingBikes();
        cbcr.partialUpdate(result);
        return Iterables.size(result);
    }

    @RequestMapping(value = "/feed/reset", method = RequestMethod.GET)
    public int reset() {
        List<StationBean> result =  cbsr.findAllStationsWithOutMissingBikes();
        return Iterables.size(result);
    }


    @RequestMapping(value = "/solr/purge", method = RequestMethod.GET)
    public boolean delete() {
        cbsr.deleteAll();
        return true;
    }

}
