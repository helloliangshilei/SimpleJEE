package object;

import java.io.Serializable;

public class Role implements Serializable {

	private String role = null;
	private String description = null;
	
	public Role() {
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
}
