package net.atos.wl.blog.business.service;

import java.util.List;

import net.atos.wl.blog.common.dto.MessageDto;
import net.atos.wl.blog.data.entity.Message;

public interface MessageService {
	
	MessageDto create(final MessageDto message);
	
	MessageDto read(final Integer id);
	
	void update(final MessageDto messageDto);
	
	boolean delete(final Integer id);
	
	MessageDto findMessageById(final Integer id);
	
	int deleteMessageByIdUserTo(final String id);
	
	List<MessageDto> findMessagesByUserTo(final String id);
	
	List<MessageDto> findMessagesByUserFrom(final String id);
	
}
