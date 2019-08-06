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
import net.atos.wl.blog.business.service.MessageService;
import net.atos.wl.blog.business.service.UserService;
import net.atos.wl.blog.common.dto.MessageDto;
import net.atos.wl.blog.common.dto.UserDto;
import net.atos.wl.blog.common.enums.UserType;

@RestController
@Api(value = "/api/messages", tags = "Messages API")
public class MessageController {

	private static Logger log = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/messages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create new message.")
	public ResponseEntity<MessageDto> createMessage(@RequestBody final MessageDto messageDto) {
		Preconditions.checkNotNull(messageDto);
		log.info("Adding a new message with id " + messageDto.getId());
		final MessageDto persistedMessageDto = this.messageService.create(messageDto);
		log.info("Message has been added");
		return new ResponseEntity<>(persistedMessageDto, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/messages/{messageId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Message")
	public ResponseEntity<MessageDto> getMessage(@PathVariable("messageId") final Integer id,
			@RequestHeader("token") final String token) {
		UserDto user = this.userService.findUserByUserToken(token);
		log.info("Getting message with id " + id);
		final MessageDto messageDto = this.messageService.findMessageById(id);

		if (messageDto == null) {
			log.info("Message not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (user == null) {
			log.info("user is not authorised to get this message");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		if (!(user.getUserId().equals(messageDto.getIdUserFrom()) || user.getUserId().equals(messageDto.getIdUserTo()))) {
			log.info(user.getUserId()+" is not authorised to get this message");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		log.info("Message exists, returning details");
		return new ResponseEntity<>(messageDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/messages/inbox/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Messages of a users inbox")
	public ResponseEntity<List<MessageDto>> getInbox(@PathVariable("userId") final String idUserTo,
			@RequestHeader("token") final String token) {
		UserDto user = this.userService.findUserByUserToken(token);
		log.info("Getting all messages for user with id " + idUserTo);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		if (!user.getUserId().equals(idUserTo)) {
			log.info(user.getUserId() + " is not authorised to get " + idUserTo + "'s messages.");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		final List<MessageDto> messages = this.messageService.findMessagesByUserTo(idUserTo);

		if (messages != null) {
			log.info("Access authorised, total messages found: " + messages.size());
			return new ResponseEntity<>(messages, HttpStatus.OK);
		}
		log.info("Messages were not found");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/api/messages/sent/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Messages of a users sent folder")
	public ResponseEntity<List<MessageDto>> getSentMessages(@PathVariable("userId") final String idUserFrom,
			@RequestHeader("token") final String token) {
		UserDto user = this.userService.findUserByUserToken(token);
		log.info("Getting all sent messages by " + idUserFrom);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		if (!user.getUserId().equals(idUserFrom)) {
			log.info(user.getUserId() + " is not authorised to get " + idUserFrom + "'s sent messages.");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		final List<MessageDto> messages = this.messageService.findMessagesByUserFrom(idUserFrom);
		
		if(messages != null) {
			log.info("Access authorised, total messages found: " + messages.size());
			return new ResponseEntity<>(messages, HttpStatus.OK);			
		}
		log.info("No sent messages found");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/api/messages/{messageId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete message by id")
	public ResponseEntity<String> deleteMessageByMessageId(@PathVariable("messageId") final Integer id,
			@RequestHeader("token") final String token) {
		UserDto user = this.userService.findUserByUserToken(token);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		MessageDto message = this.messageService.findMessageById(id);

		if (message == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (!message.getIdUserTo().equals(user.getUserId())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		this.messageService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/messages/inbox/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete messages by userId")
	public ResponseEntity<String> deleteMessagesByUserId(@PathVariable("userId") final String id,
			@RequestHeader("token") final String token) {
		UserDto user = this.userService.findUserByUserToken(token);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (user.getUserId().equals(id) || !user.getUserType().equals(UserType.NORMAL)) {
			int numDeleted = this.messageService.deleteMessageByIdUserTo(id);
			log.info(numDeleted + " messages have been deleted");
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}

		log.info(id + "'s messages have not been deleted");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
