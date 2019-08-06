package net.atos.wl.blog.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.atos.wl.blog.common.enums.BlogState;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogDto implements Serializable, Comparable<BlogDto> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("idUser")
	private String idUser;
	
	@JsonProperty("state")
	private BlogState state;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("title")
	private String title;
	
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
	 * @return the state
	 */
	public BlogState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(BlogState state) {
		this.state = state;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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

	@Override
	public int compareTo(BlogDto b) {
		return getId().compareTo(b.getId());
	}
	
	
}
