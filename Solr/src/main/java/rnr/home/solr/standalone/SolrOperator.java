package rnr.home.solr.standalone;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import rnr.home.solr.bean.CitiBikeStationInfo;
import rnr.home.solr.bean.StationBean;

import java.util.Collection;
import java.util.logging.Logger;

public class SolrOperator {
    Logger logger = Logger.getLogger(SolrOperator.class.getName());

    String urlString = "http://localhost:8983/solr/CitiBike";

    public void insert(CitiBikeStationInfo info) throws Exception{
        SolrClient solr = new HttpSolrClient.Builder(urlString).build();

        solr.addBeans(info.getStationBeanList());
        solr.commit();
    }

    public void update(CitiBikeStationInfo info) throws Exception{
        SolrClient solr = new HttpSolrClient.Builder(urlString).build();
//        solr.(info.getStationBeanList());
        solr.commit();
    }

    public void query() throws Exception{
        SolrClient solr = new HttpSolrClient.Builder(urlString).build();
        SolrQuery query = new SolrQuery("stationName:*FDR* AND latitude: [40.7331 TO 40.740 ]");

        QueryResponse rsp  = solr.query(query);
        Collection<StationBean> result = rsp.getBeans(StationBean.class);
        logger.info(""+result.size());
        logger.info(result.toString());
    }

}
