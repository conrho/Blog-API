package net.atos.wl.blog.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.atos.wl.blog.business.service.UserService;
import net.atos.wl.blog.common.dto.UserDto;
import net.atos.wl.blog.common.enums.UserType;
import net.atos.wl.blog.web.controller.Processes.Processes;

/**
 * Spring REST Controller for exposing Users APIs.
 */
@RestController
@Api(value = "/api/users", tags = "Users API")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * REST service to create user.
	 * 
	 * @param userDto <code>net.atos.wl.odc.techhub.common.dto.UserDto</code>.
	 * @return ResponseEntity with headers and HTTP status.
	 */
	@RequestMapping(value = "/api/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create new user.")
	public ResponseEntity<UserDto> createUser(@RequestBody final UserDto userDto) {
		Preconditions.checkNotNull(userDto);
		log.info("Adding a new user details with user id " + userDto.getUserId());
		final UserDto persistedUserDto = this.userService.create(userDto);
		log.info("User detail was added successfully.");
		return new ResponseEntity<>(persistedUserDto, HttpStatus.CREATED);
	}

	/**
	 * REST service to get user by ID and password.
	 * 
	 * @param userId   String of the user to be searched.
	 * @param password String of the password related to the user.
	 * @return all user details with HTTP status.
	 */
	@RequestMapping(value = "/api/users/{userId}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get user token by user id and password.")
	public ResponseEntity<String> getUserWithPassword(@PathVariable("userId") final String userId,
			@PathVariable("password") final String password) {
		log.info("Getting user details with user id " + userId);
		final UserDto userDto = this.userService.findUserByUserId(userId);
		if (userDto == null) {
			log.info("User not found with user id " + userId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("User exists.");
		if (!userDto.getPassword().equals(password)) {
			log.info("password incorrect");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("Password correct, getting user details");
		userDto.setToken(Processes.makeid());
		this.userService.update(userDto);
		return new ResponseEntity<>(userDto.getToken(), HttpStatus.OK);
	}

	/**
	 * REST service to update user by given details.
	 * 
	 * @param userDto <code>net.atos.wl.odc.techhub.common.dto.UserDto</code> .
	 * @return ResponseEntity with user and HTTP status.
	 */
	@RequestMapping(value = "/api/users", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update user.")
	public ResponseEntity<UserDto> updateUser(@RequestBody final UserDto userDto) {
		Preconditions.checkNotNull(userDto);
		log.info("Updating user details with user id " + userDto.getUserId());
		this.userService.update(userDto);
		log.info("User detail was updated successfully.");
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	/**
	 * REST service to get all users.
	 * 
	 * @return ResponseEntity with user and HTTP status.
	 */
	@RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all users by token.")
	public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader("token") final String token) {
		log.info("Getting all users with token: " + token);
		final UserDto user = userService.findUserByUserToken(token);
		if (user == null) {
			log.info("User is not authorised to get all user infomation");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		if (user.getUserType().equals(UserType.NORMAL)) {
			log.info("User is not authorised to get all user infomation");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		final List<UserDto> users = this.userService.findAllUsers();
		log.info("Access authorised, total number of users found " + users.size());
		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete user by user id.")
	public ResponseEntity<String> deleteUserByUserId(@PathVariable("userId") final String userId,
			@RequestHeader("token") final String token) {
		UserDto user = this.userService.findUserByUserToken(token);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		if (user.getUserType().equals(UserType.NORMAL) && !user.getUserId().equals(userId)) {
			log.info(user.getUserId() + " does not have the authority to delete user with id " + userId);
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		log.info("Deleting user with id " + userId);
		if (this.userService.delete(userId)) {
			log.info("User has been successfully deleted");
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		log.info("User has not been deleted.");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// REST service to get user record using token.
	@RequestMapping(value = "/api/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get user by token.")
	public ResponseEntity<UserDto> getUserByToken(@RequestHeader("token") final String token) {
		log.info("Getting user by token " + token);
		final UserDto user = this.userService.findUserByUserToken(token);
		if (user != null) {
			log.info("User found with id: " + user.getId());
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		log.info("User not found");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
