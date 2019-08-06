package net.atos.wl.blog.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import net.atos.wl.blog.common.enums.UserType;

/**
 * JPA Entity representing the user information.
 */
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "net.atos.wl.blog.data.entity.User.fetchUserByUserId", query = "SELECT u FROM User u where u.userId = :userId"),
        @NamedQuery(name = "net.atos.wl.blog.data.entity.User.fetchUserByUserToken", query = "SELECT u FROM User u where u.token = :token"),
        @NamedQuery(name = "net.atos.wl.blog.data.entity.User.deleteUserByUserId", query = "DELETE FROM User u where u.userId = :userId")})
public class User extends AuditableEntity {

    /**
     * Generated serial version Id.
     */
    private static final long serialVersionUID = 6490416745249648193L;

    @Column(name = "user_id", nullable = false)
    private String userId;
	
	@Column(name = "password", nullable = false)
	private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;
    
    @Column(name = "token", nullable = true)
    private String token;

    /**
     * Getter for userId.
     *
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter for userId.
     *
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Getter for firstName.
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName.
     *
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName.
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName.
     *
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for mobile.
     *
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Setter for mobile.
     *
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Getter for email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     *
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for userType.
     *
     * @return the userType
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Setter for userType.
     *
     * @param userType
     *            the userType to set
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
    public final String getToken() {
        return token;
    }

    /**
     * Getter for token.
     *
     * @param token
     *            the token to get
     */
    public final void setToken(String token) {
        this.token = token;
    }
    
    /**
     * Setter for token.
     *
     * @param token
     *            the token to set
     */
}
