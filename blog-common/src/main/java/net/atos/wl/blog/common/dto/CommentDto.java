package net.atos.wl.blog.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("idUser")
	private String idUser;
	
	@JsonProperty("blogId")
	private Integer blogId;
	
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
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the blogId
	 */
	public Integer getblogId() {
		return blogId;
	}

	/**
	 * @param blogId the blogId to set
	 */
	public void setblogId(Integer blogId) {
		this.blogId = blogId;
	}

}
