package simpleJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	
	public static Connection getConnection() throws NamingException, SQLException {
		//Abstract to resource file
		String dsContext = "java:comp/env/jdbc/simpleapp";
		Connection conn = null;
		Context initialContext = new InitialContext();  

		try {
			DataSource datasource = (DataSource) initialContext.lookup(dsContext);
			conn = datasource.getConnection();
		}
		catch (Exception e) {
			System.out.println(e);
			//TODO: add throws
		}
		return conn;
	}
	
	//TODO get rid of this test
	public void testOut() throws SQLException {
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			
			String query = "SELECT * FROM role;";
			
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				System.out.println("description: " + rs.getString("description"));
			}
		}
		catch (Exception e) {
			System.out.println("oops: " + e);
		}
		finally {
			conn.close();
		}
	}
}
