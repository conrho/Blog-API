package net.atos.wl.blog.data.dao;

import net.atos.wl.blog.data.entity.User;

/**
 * User DAO.
 */
public interface UserDAO extends GenericDAO<User> {

    /**
     * Method to fetch the user details by given user Id.
     * 
     * @param userId
     *            String.
     * @return <code>net.atos.wl.odc.techhub.data.entity.User</code>.
     */
    User findUserByUserId(final String userId);
    
    boolean deleteUserByUserId(final String userId);

	User findUserByUserToken(final String token);
}
