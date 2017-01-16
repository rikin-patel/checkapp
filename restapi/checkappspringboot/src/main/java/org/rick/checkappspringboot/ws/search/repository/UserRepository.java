/**
 * 
 */
package org.rick.checkappspringboot.ws.search.repository;

import org.rick.checkappspringboot.ws.model.User;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pateriki
 *
 */
@Repository("searchUserRepository")
public interface UserRepository extends SolrCrudRepository<User, Long> {

	
}
