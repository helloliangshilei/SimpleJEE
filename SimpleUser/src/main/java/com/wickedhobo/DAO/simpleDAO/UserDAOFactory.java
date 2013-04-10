package com.wickedhobo.DAO.simpleDAO;


public class UserDAOFactory {
	// A simple factory just for the hell of it.
	public static UserDAOSimple getUserDAO(String dao) {
		if (dao.equalsIgnoreCase("jdbc")) {
			return new UserDAOImplSimple();
		} else
			return new UserDAOImplSimple();
	}
}
