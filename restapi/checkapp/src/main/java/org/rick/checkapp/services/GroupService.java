/**
 * 
 */
package org.rick.checkapp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import org.rick.checkapp.db.DatabaseConnection;
import org.rick.checkapp.model.Group;

/**
 * @author Rikin Patel
 *
 */
public class GroupService {

	private String GROUP_INSERT_QUERY = "INSERT INTO GROUPS (GROUPNAME, DESCRIPTION, OWNERID, CREATE_DATE) " +
											"VALUES(?,?,?,?)";
	public List<Group> getAllGroups(){
		Group g1 = new Group(1L, "Grocery", "Daily Grocery Shopping",1L, new Date());
		Group g2 = new Group(2L, "Medicine", "Medicine Shopping", 2L, new Date());
		List<Group> groups = new ArrayList<Group>();
		groups.add(g1);
		groups.add(g2);
		return groups;
	}

	public void addGroup(Group group) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(GROUP_INSERT_QUERY);
			statement.setString(1, group.getGroupName());
			statement.setString(2, group.getDescription());
			statement.setLong(3, group.getOwnerId());
			statement.setDate(4, new java.sql.Date(group.getCreateDate().getTime()));
			
			if(statement.execute()){
				System.out.println("Data Inserted Successfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
