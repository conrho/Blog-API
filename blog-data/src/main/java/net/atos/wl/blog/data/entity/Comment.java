package net.atos.wl.blog.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
@NamedQueries({
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Comment.fetchCommentByCommentId", query = "SELECT c FROM Comment c where c.id = :id"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Comment.deleteCommentByCommentId", query = "DELETE FROM Comment c where c.id = :id"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Comment.fetchCommentByBlogId", query = "SELECT c FROM Comment c where c.blogId = :blogId ORDER BY c.id"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Comment.deleteCommentsByUserId", query = "DELETE FROM Comment c where c.idUser = :idUser"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Comment.fetchCommentByUserId", query = "SELECT c FROM Comment c where c.idUser = :idUser"),
})
public class Comment extends AuditableEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "idUser", nullable = false)
	private String idUser;
	
	@Column(name = "blogId", nullable = false)
	private Integer blogId;

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
