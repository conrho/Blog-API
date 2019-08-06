package net.atos.wl.blog.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import net.atos.wl.blog.common.enums.BlogState;


@Entity
@Table(name = "blog")
@NamedQueries({
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Blog.fetchBlogByBlogId", query = "SELECT b FROM Blog b where b.id = :id ORDER BY b.id"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Blog.deleteBlogByBlogId", query = "DELETE FROM Blog b where b.id = :id"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Blog.deleteBlogsByUserId", query = "DELETE FROM Blog b where b.idUser = :idUser"),
	 @NamedQuery(name = "net.atos.wl.blog.data.entity.Blog.getBlogsByUserId", query = "SELECT b FROM Blog b where b.idUser = :idUser")
})
public class Blog extends AuditableEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "idUser", nullable = false)
	private String idUser;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private BlogState state;
	
	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "title", nullable = false)
	private String title;

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
	
	
}