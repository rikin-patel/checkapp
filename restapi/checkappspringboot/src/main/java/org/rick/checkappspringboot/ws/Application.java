package org.rick.checkappspringboot.ws;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrClientFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories("org.rick.checkappspringboot.ws.repository")
@EnableSolrRepositories("org.rick.checkappspringboot.ws.search.repository")
public class Application 
{
    public static void main( String[] args )throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public SolrClient solrServer() {
    	HttpSolrClientFactory factory = new HttpSolrClientFactory(new HttpSolrClient("http://localhost:8983/solr"), "checkapp"); 
    	return factory.getSolrClient();
    }
    @Bean
    public SolrTemplate solrTemplate(SolrClient server) throws Exception {
      return new SolrTemplate(server);
    }

}
