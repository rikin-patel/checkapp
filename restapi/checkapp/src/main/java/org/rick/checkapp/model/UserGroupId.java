package org.rick.checkapp.model;
import java.io.Serializable;
 
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
 
@Embeddable
public class UserGroupId implements Serializable { 
    private Users user;
    private Group group;
 
    @ManyToOne(cascade = CascadeType.ALL)
    public Users getUser() {
        return user;
    }
 
    public void setUser(Users user) {
        this.user = user;
    }
 
    @ManyToOne(cascade = CascadeType.ALL)
    public Group getGroup() {
        return group;
    }
 
    public void setGroup(Group group) {
        this.group = group;
    }
}