package rnr.home.solr.repository;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = "rnr.home.solr.repository")
@Profile("prod")
@ComponentScan
public class SolrConfig {
    @Value("spring.data.solr.host") String solrURL;

    @Bean
    public SolrClient solrClient() {
        System.out.println(solrURL);
        return new HttpSolrClient.Builder(solrURL).build();
    }

    @Bean
    public SolrTemplate solrTemplate() {
        return new SolrTemplate(solrClient());
    }
}
