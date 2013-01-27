package object;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {

	private String role = null;
	private String description = null;
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
