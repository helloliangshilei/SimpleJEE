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
	public int saveUser(User user) throws SQLException {
		Connection conn = null;
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement insertUserStmt = conn.prepareStatement("INSERT INTO user(username, firstname, lastname, password) " +
																														"values (?,?,?,?);");
			insertUserStmt.setString(1, user.getUserName());
			insertUserStmt.setString(2, user.getFirstName());
			insertUserStmt.setString(3, user.getLastName());
			insertUserStmt.setString(4, user.getPassword());
			status = insertUserStmt.executeUpdate();
		}
		catch (SQLException sqle) {
			throw new SQLException(sqle);
		}
		catch(Exception e) {
			System.out.println("oops: " + e);
		}
		finally {
			conn.close();
		}
		return status;
	}

	@Override
	public int changeUser(User user) throws SQLException {
		Connection conn = null;
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement insertUserStmt = conn.prepareStatement("UPDATE user SET firstname = ?, lastname = ?, " +
																																" password = ? WHERE username = ?"); 
			
			insertUserStmt.setString(1, user.getFirstName());
			insertUserStmt.setString(2, user.getLastName());
			insertUserStmt.setString(3, user.getPassword());
			insertUserStmt.setString(4, user.getUserName());
			status = insertUserStmt.executeUpdate();
		}
		catch (SQLException sqle) {
			throw new SQLException(sqle);
		}
		catch(Exception e) {
			System.out.println("oops: " + e);
		}
		finally {
			conn.close();
		}
		return status;
	}

	@Override
	public User getUser(String username) throws SQLException {
		Connection conn = null;
		User user = new User();
		try {
			conn = DBConnection.getConnection();
			PreparedStatement getUserStmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
			getUserStmt.setString(1, username);
			ResultSet result = getUserStmt.executeQuery();
			
			if(!result.next())
				return null;
			
			user.setFirstName(result.getString("firstname"));
			user.setLastName(result.getString("lastname"));
			user.setUserName(result.getString("username"));
			user.setPassword(result.getString("password"));
		}
		catch (SQLException sqle) {
			throw new SQLException(sqle);
		}
		catch (Exception e) {
			System.out.println("oops: " + e);
		}
		finally {
			conn.close();
		}
		return user;
	}

	@Override
	public int removeUser(User user) throws SQLException {
		Connection conn = null;
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement deleteUserStmt = conn.prepareStatement("DELETE FROM user WHERE username = ?");
			deleteUserStmt.setString(1, user.getUserName());
			status = deleteUserStmt.executeUpdate();
		}
		catch (SQLException sqle) {
			throw new SQLException(sqle);
		}
		catch (Exception e) {
			System.out.println("oops: " + e);
		}
		finally {
			conn.close();
		}
		return status;
	}

	@Override
  public List<User> listAllUsers() throws SQLException {
		Connection conn = null;
		List<User> t = new ArrayList<User>();
		try {
			conn = DBConnection.getConnection();
			PreparedStatement getUserStmt = conn.prepareStatement("SELECT * FROM user");
			ResultSet result = getUserStmt.executeQuery();
			
			if(!result.next())
				return null;
			else while (result.next()) {
				User user = new User();
				user.setUserName(result.getString("username"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setPassword(result.getString("password"));
				t.add(user);
			}
		}
		catch (SQLException sqle) {
			throw new SQLException(sqle);
		}
		catch (Exception e) {
			System.out.println("oops: " + e);
		}
		finally {
			conn.close();
		}
	  return t;
  }
}
