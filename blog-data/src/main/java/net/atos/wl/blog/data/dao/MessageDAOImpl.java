package net.atos.wl.blog.data.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import net.atos.wl.blog.data.entity.Comment;
import net.atos.wl.blog.data.entity.Message;

@Repository
public class MessageDAOImpl extends AbstractJpaDAO<Message> implements MessageDAO {
	
	public MessageDAOImpl() {
		this.setClazz(Message.class);
	}
	
	@Override
	public Message findMessageById(Integer id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Message.fetchMessageById");
		query.setParameter("id", id);
		final List<Message> messages = (List<Message>) query.getResultList();
		if(!CollectionUtils.isEmpty(messages)) {
			return messages.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteMessageById(Integer id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Message.deleteMessageById");
		query.setParameter("id", id);
		if(query.executeUpdate() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public int deleteMessageByIdUserTo(String id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Message.deleteMessageByIdUserTo");
		query.setParameter("idUserTo", id);
		return query.executeUpdate();
	}

	@Override
	public List<Message> findMessagesByUserTo(String id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Message.fetchMessagesByIdUserTo");
		query.setParameter("idUserTo", id);
		final List<Message> messages = (List<Message>) query.getResultList();
		if(!CollectionUtils.isEmpty(messages)) {
			return messages;
		}
		return null;
	}

	@Override
	public List<Message> findMessagesByUserFrom(String id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Message.fetchMessagesByIdUserFrom");
		query.setParameter("idUserFrom", id);
		final List<Message> messages = (List<Message>) query.getResultList();
		if(!CollectionUtils.isEmpty(messages)) {
			return messages;
		}
		return null;
	}

}
