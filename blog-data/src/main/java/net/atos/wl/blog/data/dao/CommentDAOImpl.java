package net.atos.wl.blog.data.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import net.atos.wl.blog.data.entity.Comment;

@Repository
public class CommentDAOImpl extends AbstractJpaDAO<Comment> implements CommentDAO {
	
	public CommentDAOImpl() {
		this.setClazz(Comment.class);
	}

	@Override
	public Comment findCommentByCommentId(Integer id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Comment.fetchCommentByCommentId");
		query.setParameter("id", id);
		final List<Comment> comments = (List<Comment>) query.getResultList();
		if (!CollectionUtils.isEmpty(comments)) {
			return comments.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteCommentByCommentId(Integer id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Comment.deleteCommentByCommentId");
		query.setParameter("id", id);
		if (query.executeUpdate() >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Comment> findCommentByBlogId(Integer blogId) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Comment.fetchCommentByBlogId");
		query.setParameter("blogId", blogId);
		final List<Comment> comments = (List<Comment>) query.getResultList();
		if(!CollectionUtils.isEmpty(comments)) {
			return comments;
		}
		return null;
	}

	@Override
	public int deleteCommentsByUserId(String id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Comment.deleteCommentsByUserId");
		query.setParameter("userId",id);
		return query.executeUpdate();
	}

	@Override
	public List<Comment> findCommentsByUserId(String id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Comment.fetchCommentByUserId");
		query.setParameter("idUser", id);
		final List<Comment> comments = (List<Comment>) query.getResultList();
		if(!CollectionUtils.isEmpty(comments)) {
			return comments;
		}
		return null;
	}
	
	
	
	
}
