package object;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="role", schema="simpleapp")
public class Role implements Serializable {
	
	@Id 
	@Column(name="role", nullable=false, length=100)
	private String role = null;
	
	@Column(name="description", nullable=false, length=100)
	private String description = null;
	
	@ManyToMany
  @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "role_role") }, inverseJoinColumns = { @JoinColumn(name = "user_username") })
	private Set<User> users = new HashSet<User>(0);
	
	public Role() {
	}

	public Role(String role, String description) {
	  this.role = role;
	  this.description = description;
  }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
