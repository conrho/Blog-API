package net.atos.wl.blog.data.dao;

import net.atos.wl.blog.data.entity.Blog;
import net.atos.wl.blog.data.entity.User;

public interface BlogDAO extends GenericDAO<Blog> {
	
    Blog findBlogByBlogId(final Integer id);
    boolean deleteBlogByBlogId(final Integer id);
    int deleteBlogByUserId(final String id);
}
