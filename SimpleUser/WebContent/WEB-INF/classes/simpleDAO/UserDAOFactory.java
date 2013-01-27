package simpleDAO;

import simpleDAO.UserDAOSimple;
import simpleDAO.UserDAOImplSimple;

public class UserDAOFactory {
	// TODO clean up.
	public static UserDAOSimple getUserDAO(String dao) {
		if (dao.equalsIgnoreCase("jdbc")) {
			return new UserDAOImplSimple();
		} else
			return new UserDAOImplSimple();
	}
}
