package net.atos.wl.blog.data.dao;

import java.util.List;

import net.atos.wl.blog.data.entity.Message;

public interface MessageDAO extends GenericDAO<Message> {
	
	Message findMessageById(final Integer id);
	boolean deleteMessageById(final Integer id);
	int deleteMessageByIdUserTo(final String id);
	List<Message> findMessagesByUserTo(final String id);
	List<Message> findMessagesByUserFrom(final String id);
}
