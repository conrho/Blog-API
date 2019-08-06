package net.atos.wl.blog.common.dto;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("idUserTo")
	private String idUserTo;
	
	@JsonProperty("idUserFrom")
	private String idUserFrom;
	
	@JsonProperty("dateSent")
	private Date createdDate;
	
	@JsonProperty("messageType")
	private String messageType;

	@JsonProperty("hasRead")
	private boolean hasRead;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	public boolean isHasRead() {
		return hasRead;
	}

	/**
	 * @param read the read to set
	 */
	public void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
