package net.atos.wl.blog.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.wl.blog.business.mapper.ObjectMapper;
import net.atos.wl.blog.common.dto.MessageDto;
import net.atos.wl.blog.data.dao.MessageDAO;
import net.atos.wl.blog.data.entity.Message;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * @return the messageDAO
	 */
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	/**
	 * @param messageDAO the messageDAO to set
	 */
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	/**
	 * @return the objectMapper
	 */
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * @param objectMapper the objectMapper to set
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public MessageDto create(MessageDto messageDto) {
		final Message message = this.getObjectMapper().map(messageDto, Message.class);
		this.getMessageDAO().create(message);
		return this.getObjectMapper().map(message, MessageDto.class);
	}

	@Override
	public MessageDto read(Integer id) {
		final Message message = this.getMessageDAO().read(id);
		if(message != null) {
			return this.getObjectMapper().map(message, MessageDto.class);
		}
		return null;
	}

	@Override
	public void update(MessageDto messageDto) {
		final Message messageFromDb = this.getMessageDAO().read(messageDto.getId());
		this.getObjectMapper().map(messageDto, messageFromDb);
		this.getMessageDAO().update(messageFromDb);
	}

	@Override
	public boolean delete(Integer id) {
		return this.getMessageDAO().deleteMessageById(id);
	}

	@Override
	public MessageDto findMessageById(Integer id) {
		final Message message = this.getMessageDAO().findMessageById(id);
		if(message != null) {
			return this.getObjectMapper().map(message, MessageDto.class);
		}
		return null;
	}

	@Override
	public int deleteMessageByIdUserTo(String id) {
		return this.getMessageDAO().deleteMessageByIdUserTo(id);
	}

	@Override
	public List<MessageDto> findMessagesByUserTo(String id) {
		final List<Message> messages = this.getMessageDAO().findMessagesByUserTo(id);
		if(messages != null && !messages.isEmpty()) {
			final List<MessageDto> messageDtos = new ArrayList<>();
			for(final Message message: messages) {
				messageDtos.add(this.getObjectMapper().map(message, MessageDto.class));
			}
			return messageDtos;
		}
		return null;
	}

	@Override
	public List<MessageDto> findMessagesByUserFrom(String id) {
		final List<Message> messages = this.getMessageDAO().findMessagesByUserFrom(id);
		if(messages != null && !messages.isEmpty()) {
			final List<MessageDto> messageDtos = new ArrayList<>();
			for(final Message message: messages) {
				messageDtos.add(this.getObjectMapper().map(message, MessageDto.class));
			}
			return messageDtos;
		}
		return null;
	}
	
	
}
