package net.atos.wl.blog.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.atos.wl.blog.common.enums.UserType;

/**
 * Data transfer object for user details.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    /**
     * Generated serial version Id.
     */
    private static final long serialVersionUID = -604018936275238886L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("userType")
    private UserType userType;
    
    @JsonProperty("token")
    private String token;

    /**
     * Getter for id.
     *
     * @return the id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * Setter for id.
     *
     * @param id
     *            the id to set
     */
    public final void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for userId.
     *
     * @return the userId
     */
    public final String getUserId() {
        return userId;
    }

    /**
     * Setter for userId.
     *
     * @param userId
     *            the userId to set
     */
    public final void setUserId(String userId) {
        this.userId = userId;
    }

    public final String getPassword(){
        return password;
    }

    public final void setPassword(String password){
        this.password = password;
    }

    /**
     * Getter for firstName.
     *
     * @return the firstName
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName.
     *
     * @param firstName
     *            the firstName to set
     */
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName.
     *
     * @return the lastName
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName.
     *
     * @param lastName
     *            the lastName to set
     */
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for email.
     *
     * @return the email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     *
     * @param email
     *            the email to set
     */
    public final void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for mobile.
     *
     * @return the mobile
     */
    public final String getMobile() {
        return mobile;
    }

    /**
     * Setter for mobile.
     *
     * @param mobile
     *            the mobile to set
     */
    public final void setMobile(String mobile) {
        this.mobile = mobile;
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
