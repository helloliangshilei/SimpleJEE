package DAO.simpleDAO;

import java.util.List;

import object.User;

public interface UserDAOSimple {
	
	public int save(User user) throws Exception;
	public int change(User user) throws Exception;
	public User find(String username) throws Exception;
	public int remove(User user) throws Exception;
	public List<User> listAll() throws Exception;

}
