package net.atos.wl.blog.data.dao;

import java.util.List;

import net.atos.wl.blog.data.entity.Comment;

public interface CommentDAO extends GenericDAO<Comment>{
	
	Comment findCommentByCommentId(final Integer id);
	boolean deleteCommentByCommentId(final Integer id);
	List<Comment> findCommentByBlogId(final Integer id);
	int deleteCommentsByUserId(final String id);
	List<Comment> findCommentsByUserId(final String id);
}
