package net.atos.wl.blog.business.service;

import java.util.List;

import net.atos.wl.blog.common.dto.UserDto;

/**
 * User Service.
 */
public interface UserService {

    /**
     * Method create a new user record in DB.
     * 
     * @param user
     *            <code>net.atos.wl.odc.techhub.common.dto.UserDto</code> .
     * @return <code>net.atos.wl.odc.techhub.common.dto.UserDto</code>.
     */
    UserDto create(final UserDto user);

    /**
     * Method to fetch user details for the given user Id.
     * 
     * @param id
     *            Integer.
     * @return <code>net.atos.wl.odc.techhub.common.dto.UserDto</code>.
     */
    UserDto read(final Integer id);

    /**
     * Method to update user details.
     * 
     * @param user
     *            <code>net.atos.wl.odc.techhub.common.dto.UserDto</code> .
     */
    void update(final UserDto user);

    /**
     * Method to delete the given user record from DB.
     * 
     * @param id
     *            Integer.
     */
    boolean delete(final String userId);

    /**
     * Method to find a particular user for the given userId.
     * 
     * @param userId
     *            String.
     * @return <code>net.atos.wl.odc.techhub.common.dto.UserDto</code>.
     */
    UserDto findUserByUserId(final String userId);

    /**
     * Method to find all users.
     * 
     * @return List of <code>net.atos.wl.odc.techhub.common.dto.UserDto</code>.
     */
    List<UserDto> findAllUsers();
    
    UserDto findUserByUserToken(final String token);
}
