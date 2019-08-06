package net.atos.wl.blog.business.service;

import java.util.List;

import net.atos.wl.blog.common.dto.BlogDto;

public interface BlogService {
	
	BlogDto create(final BlogDto blog);
	
	BlogDto read(final Integer id);
	
	void update(final BlogDto blog);
	
	boolean delete(final Integer id);
	
	BlogDto findBlogByBlogId(final Integer id);
	
	List<BlogDto> findAllBlogs();
	
	int deleteByUserId(final String id);
	
}
