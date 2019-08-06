package net.atos.wl.blog.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.wl.blog.business.mapper.ObjectMapper;
import net.atos.wl.blog.common.dto.CommentDto;
import net.atos.wl.blog.data.dao.CommentDAO;
import net.atos.wl.blog.data.entity.Comment;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public CommentDto create(CommentDto commentDto) {
		final Comment comment = this.getObjectMapper().map(commentDto, Comment.class);
		this.getCommentDAO().create(comment);
		return this.getObjectMapper().map(comment, CommentDto.class);
	}

	@Override
	public CommentDto read(Integer id) {
		final Comment comment = this.getCommentDAO().read(id);
		if(comment != null) {
			return this.getObjectMapper().map(comment, CommentDto.class);
		}
		return null;
	}

	@Override
	public void update(CommentDto commentDto) {
		final Comment commentFromDb = this.getCommentDAO().read(commentDto.getId());
		this.getObjectMapper().map(commentDto, commentFromDb);
		this.getCommentDAO().update(commentFromDb);
	}

	@Override
	public void delete(Integer id) {
		this.getCommentDAO().deleteCommentByCommentId(id);
	}

	@Override
	public CommentDto findCommentByCommentId(Integer id) {
		final Comment comment = this.getCommentDAO().findCommentByCommentId(id);
		if(comment != null) {
			return this.getObjectMapper().map(comment, CommentDto.class);
		}
		return null;
	}

	@Override
	public List<CommentDto> findCommentsByBlogId(Integer blogId) {
		final List<Comment> comments = this.getCommentDAO().findCommentByBlogId(blogId);
		if(comments != null && !comments.isEmpty()) {
			final List<CommentDto> commentDtos = new ArrayList<>(); 
			for(final Comment comment : comments) {
				commentDtos.add(this.getObjectMapper().map(comment, CommentDto.class));
			}
			return commentDtos;
		}
		return null;
	}
	
	@Override
	public int deleteCommentsByUserId(String id) {
		return this.getCommentDAO().deleteCommentsByUserId(id);
	}
	
	@Override
	public List<CommentDto> findCommentsByUserId(String id) {
		final List<Comment> comments = this.getCommentDAO().findCommentsByUserId(id);
		if(comments != null && !comments.isEmpty()) {
			final List<CommentDto> commentDtos = new ArrayList<>(); 
			for(final Comment comment : comments) {
				commentDtos.add(this.getObjectMapper().map(comment, CommentDto.class));
			}
			return commentDtos;
		}
		return null;
	}

	/**
	 * @return the commentDAO
	 */
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	/**
	 * @param commentDAO the commentDAO to set
	 */
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	/**
	 * @return the objectMapper
	 */
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * @param objectMapper the objectMapper to set
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


	
	
}
