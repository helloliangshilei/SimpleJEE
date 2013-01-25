package simpleJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDAO.UserDAOImplSimple;

public class DBConnection {
	
	private static Logger log = LoggerFactory.getLogger(DBConnection.class);
	
	public static Connection getConnection() throws NamingException, SQLException {
		//TODO Abstract to resource file
		String dsContext = "java:comp/env/jdbc/simpleapp";
		Connection conn = null;
		Context initialContext = new InitialContext();  

		try {
			DataSource datasource = (DataSource) initialContext.lookup(dsContext);
			conn = datasource.getConnection();
		}
		catch (Exception e) {
			log.error("Exception happened in DBConnection: " + e);
		}
		return conn;
	}
}
