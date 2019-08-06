package net.atos.wl.blog.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RateDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("rate")
	private Integer rate;
	
	@JsonProperty("blogId")
	private Integer blogId;
	
	@JsonProperty("idUser")
	private String idUser;

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
	 * @return the rate
	 */
	public Integer getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
	}

	/**
	 * @return the blogId
	 */
	public Integer getBlogId() {
		return blogId;
	}

	/**
	 * @param blogId the blogId to set
	 */
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
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
	
}
