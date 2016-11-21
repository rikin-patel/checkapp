/**
 * 
 */
package org.rick.checkappspringboot.ws.repository;

import org.rick.checkappspringboot.ws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pateriki
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
