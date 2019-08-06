package net.atos.wl.blog.data.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import net.atos.wl.blog.data.entity.Blog;

@Repository
public class BlogDAOImpl extends AbstractJpaDAO<Blog> implements BlogDAO {

	public BlogDAOImpl() {
		this.setClazz(Blog.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Blog findBlogByBlogId(final Integer id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Blog.fetchBlogByBlogId");
		query.setParameter("id", id);
		final List<Blog> blogs = (List<Blog>) query.getResultList();
		if (!CollectionUtils.isEmpty(blogs)) {
			return blogs.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteBlogByBlogId(Integer id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Blog.deleteBlogByBlogId");
		query.setParameter("id", id);
		if (query.executeUpdate() >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteBlogByUserId(String id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Blog.deleteBlogsByUserId");
		query.setParameter("idUser", id);
		return query.executeUpdate();
	}

}
