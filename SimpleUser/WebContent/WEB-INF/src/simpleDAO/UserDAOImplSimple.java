package simpleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import object.User;
import simpleJDBC.DBConnection;

public class UserDAOImplSimple implements UserDAO {

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(String username) throws SQLException {
		Connection conn = null;
		try {
			User user = new User();
			
			conn = DBConnection.getConnection();
			PreparedStatement getUserStmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
			getUserStmt.setString(1, username);
			ResultSet result = getUserStmt.executeQuery();
			
			if(!result.next())
				return null;
			
			user.setFirstName(result.getString("firstname"));
			user.setLastName(result.getString("lastname"));
			user.setUserName(result.getString("username"));
			return user;
		}
		catch (Exception e) {
			System.out.println("oops: " + e);
		}
		finally {
			conn.close();
		}
		return null;
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
  public List<User> listAllUsers() {
	  List<User> t = new ArrayList<User>();
	  return t;
  }
}
