package net.atos.wl.blog.data.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import net.atos.wl.blog.data.entity.User;

/**
 * User DAO Implementation.
 */
@Repository
public class UserDAOImpl extends AbstractJpaDAO<User> implements UserDAO {

    /**
     * Default constructor.
     */
    public UserDAOImpl() {
        this.setClazz(User.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.atos.wl.blog.data.dao.UserDAO#findUserByUserId(java.lang.
     * String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public User findUserByUserId(final String userId) {
        final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.User.fetchUserByUserId");
        query.setParameter("userId", userId);
        final List<User> users = (List<User>) query.getResultList();
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public User findUserByUserToken(final String token) {
        final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.User.fetchUserByUserToken");
        query.setParameter("token", token);
        final List<User> users = (List<User>) query.getResultList();
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }
    

	@Override
	public boolean deleteUserByUserId(String userId) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.User.deleteUserByUserId");
        query.setParameter("userId", userId);
        if(query.executeUpdate() >= 1) {
        	return true;
        }
        return false;
	}
}
