package net.atos.wl.blog.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.wl.blog.business.mapper.ObjectMapper;
import net.atos.wl.blog.common.dto.BlogDto;
import net.atos.wl.blog.data.dao.BlogDAO;
import net.atos.wl.blog.data.entity.Blog;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public BlogDto create(final BlogDto blogDto) {
		final Blog blog = this.getObjectMapper().map(blogDto, Blog.class);
		this.getBlogDAO().create(blog);
		return this.getObjectMapper().map(blog, BlogDto.class);
	}
	
	@Override
	public BlogDto read(final Integer id) {
		final Blog blog = this.getBlogDAO().read(id);
		if(blog != null) {
			return this.getObjectMapper().map(blog, BlogDto.class);
		}
		return null;
	}

	@Override
	public void update(BlogDto blogDto) {
		final Blog blogFromDb = this.getBlogDAO().read(blogDto.getId());
		this.getObjectMapper().map(blogDto, blogFromDb);
		this.getBlogDAO().update(blogFromDb);
	}

	@Override
	public boolean delete(Integer id) {
		return this.getBlogDAO().deleteBlogByBlogId(id);
	}
	
	

	@Override
	public BlogDto findBlogByBlogId(Integer id) {
		final Blog blog = this.getBlogDAO().findBlogByBlogId(id);
		if(blog != null) {
			return this.getObjectMapper().map(blog,  BlogDto.class);
		}
		return null;
	}

	@Override
	public List<BlogDto> findAllBlogs() {
		final List<Blog> blogs = this.getBlogDAO().findAll();
		if(blogs != null && !blogs.isEmpty()) {
			final List<BlogDto> blogDtos = new ArrayList<>();
			for (final Blog blog : blogs) {
				blogDtos.add(this.getObjectMapper().map(blog, BlogDto.class));
			}
			
			return blogDtos;
		}
		
		return new ArrayList<BlogDto>();
	}
	
	@Override
	public int deleteByUserId(String id) {
		return this.getBlogDAO().deleteBlogByUserId(id);
	}
	/**
	 * @return the blogDAO
	 */
	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	/**
	 * @param blogDAO the blogDAO to set
	 */
	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
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
