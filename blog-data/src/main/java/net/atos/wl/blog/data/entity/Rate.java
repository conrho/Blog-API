package net.atos.wl.blog.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "rate")
@NamedQueries({
	@NamedQuery(name = "net.atos.wl.blog.data.entity.Rate.fetchRateByRateId", query = "SELECT r FROM Rate r where r.id = :id"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Rate.deleteRateByRateId", query = "DELETE FROM Rate r where r.id = :id"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Rate.fetchRateByBlogId", query = "SELECT r FROM Rate r where r.blogId = :blogId"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Rate.fetchRateByBlogIdAndUserId", query = "SELECT r FROM Rate r where r.blogId = :blogId AND r.idUser = :idUser"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Rate.deleteRateByUserId", query = "DELETE FROM Rate r where r.idUser = :idUser"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Rate.fetchRateByUserId", query = "SELECT r FROM Rate r where r.idUser = :idUser"),
})
public class Rate extends AuditableEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "rate", nullable = false)
	private Integer rate;
	
	@Column(name = "blogId", nullable = false)
	private Integer blogId;
	
	@Column(name = "idUser", nullable = false)
	private String idUser;

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
