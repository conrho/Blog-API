package net.atos.wl.blog.business.service;

import java.util.List;

import net.atos.wl.blog.common.dto.CommentDto;
import net.atos.wl.blog.data.entity.Comment;

public interface CommentService {
	
	CommentDto create(final CommentDto comment);
	
	CommentDto read(final Integer id);
	
	void update(final CommentDto commentDto);
	
	void delete(final Integer id);
	
	CommentDto findCommentByCommentId(final Integer id);
	
	List<CommentDto> findCommentsByBlogId(final Integer blogId);
	
	int deleteCommentsByUserId(final String id);
	
	List<CommentDto> findCommentsByUserId(String id); 
	
}