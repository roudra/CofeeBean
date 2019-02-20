package rnr.home.solr.repository;

import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.PartialUpdate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;
import rnr.home.solr.bean.SearchBean;
import rnr.home.solr.bean.StationBean;

import javax.annotation.Resource;
import java.util.Collection;

@Repository
public class CitiBikeCustomRepository {

    @Resource
    private SolrTemplate st;

    public Collection<StationBean> retrieve(SearchBean searchTerm) {
        Criteria conditions = createCriteria(searchTerm);
        SimpleQuery search = new SimpleQuery(conditions);
        Page<StationBean> results = st.queryForPage("CitiBike", search, StationBean.class);
        return results.getContent();
    }

    private Criteria createCriteria(SearchBean searchTerm) {
        Criteria cf = new Criteria("availableBikes").greaterThan(searchTerm.getAvailableBikes());
        return cf;
    }

    public void partialUpdate(Collection<StationBean> res) {
        res.forEach(r -> {
            partialUpdate(r.getRecordId(), "availableBikes", r.getTotalDocks());
        });
    }

    public UpdateResponse partialUpdate(long id, String key, Object updatedValue) {
        PartialUpdate update = new PartialUpdate("recordId", id);
        update.add(key, updatedValue);
        return st.saveBean("CitiBike", update);
    }
}
