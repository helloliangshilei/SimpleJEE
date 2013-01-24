package commonDAO;

import simpleDAO.UserDAO;
import simpleDAO.UserDAOImplSimple;

public class UserDAOFactory {
	
	public static UserDAO getUserDAO(String type) {
		if (type.equalsIgnoreCase("jdbc")) {
			return new UserDAOImplSimple();
		} else {
			return new UserDAOImplSimple();
		}
	}
}
