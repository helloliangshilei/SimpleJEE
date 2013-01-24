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
		}
		return conn;
	}
}
