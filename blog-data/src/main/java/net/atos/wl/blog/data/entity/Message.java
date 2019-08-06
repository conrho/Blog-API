package net.atos.wl.blog.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "message")
@NamedQueries({
	@NamedQuery(name = "net.atos.wl.blog.data.entity.Message.fetchMessageById", query = "SELECT m FROM Message m where m.id = :id"),
	@NamedQuery(name = "net.atos.wl.blog.data.entity.Message.fetchMessagesByIdUserTo", query = "SELECT m FROM Message m where m.idUserTo = :idUserTo"),
	@NamedQuery(name = "net.atos.wl.blog.data.entity.Message.fetchMessagesByIdUserFrom", query = "SELECT m FROM Message m where m.idUserFrom = :idUserFrom"),
	@NamedQuery(name = "net.atos.wl.blog.data.entity.Message.fetchMessagesByMessageType", query = "SELECT m FROM Message m where m.messageType = :messageType"),
	@NamedQuery(name = "net.atos.wl.blog.data.entity.Message.deleteMessageById", query = "DELETE FROM Message m where m.id = :id"),
	@NamedQuery(name = "net.atos.wl.blog.data.entity.Message.deleteMessageByIdUserTo", query = "DELETE FROM Message m where m.idUserTo = :idUserTo"),
})
public class Message extends AuditableEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "idUserTo", nullable = false)
	private String idUserTo;
	
	@Column(name = "idUserFrom", nullable = false)
	private String idUserFrom;
	
	@Column(name = "messageType", nullable = true)
	private String messageType;

	@Column(name = "hasRead", nullable = false)
	private boolean hasRead;
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getcontent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setcontent(String content) {
		this.content = content;
	}

	/**
	 * @return the idUserTo
	 */
	public String getIdUserTo() {
		return idUserTo;
	}

	/**
	 * @param idUserTo the idUserTo to set
	 */
	public void setIdUserTo(String idUserTo) {
		this.idUserTo = idUserTo;
	}

	/**
	 * @return the idUserFrom
	 */
	public String getIdUserFrom() {
		return idUserFrom;
	}

	/**
	 * @param idUserFrom the idUserFrom to set
	 */
	public void setIdUserFrom(String idUserFrom) {
		this.idUserFrom = idUserFrom;
	}

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the read
	 */
	public boolean isRead() {
		return hasRead;
	}

	/**
	 * @param read the read to set
	 */
	public void setRead(boolean hasRead) {
		this.hasRead = hasRead;
	}
	
}
