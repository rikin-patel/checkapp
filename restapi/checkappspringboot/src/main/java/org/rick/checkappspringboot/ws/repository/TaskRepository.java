/**
 * 
 */
package org.rick.checkappspringboot.ws.repository;

import java.util.List;

import org.rick.checkappspringboot.ws.model.Group;
import org.rick.checkappspringboot.ws.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author pateriki
 *
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("select t from tasks t where t.groupId = :groupId")
	List<Group> findByGroupId(@Param("groupId")long groupId);
	
	@Query("select t from tasks t where t.taskCreator = :taskCreatorId")
	List<Group> findByOwnerId(@Param("taskCreatorId")long taskCreatorId);
}
