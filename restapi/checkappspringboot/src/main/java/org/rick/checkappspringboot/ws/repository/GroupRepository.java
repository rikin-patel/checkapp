/**
 * 
 */
package org.rick.checkappspringboot.ws.repository;

import java.util.List;

import org.rick.checkappspringboot.ws.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author pateriki
 *
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	@Query("select g from groups g where g.ownerId = :ownerId")
	List<Group> findByOwnerId(@Param("ownerId")long ownerId);
}
