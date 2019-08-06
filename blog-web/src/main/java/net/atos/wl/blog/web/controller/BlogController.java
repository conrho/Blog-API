package net.atos.wl.blog.web.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.atos.wl.blog.business.service.BlogService;
import net.atos.wl.blog.common.dto.BlogDto;

@RestController
@Api(value = "/api/blogs", tags = "Blogs API")
public class BlogController {
	
    private static Logger log = LoggerFactory.getLogger(BlogController.class);
    
    @Autowired
    private BlogService blogService;
    
    // REST service to create new blog and add it to DB.
    @RequestMapping(value = "/api/blogs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new blog.")
    public ResponseEntity<BlogDto> createBlog(@RequestBody final BlogDto blogDto){
    	Preconditions.checkNotNull(blogDto);
    	log.info("Adding a new blog details with blog id " + blogDto.getId() + " and user id " + blogDto.getIdUser());
    	final BlogDto persistedBlogDto = this.blogService.create(blogDto);
    	log.info("Blog details were added successfully.");
    	return new ResponseEntity<>(persistedBlogDto,HttpStatus.CREATED);
    }
    
    // REST service to get blog using its blog ID.
    @RequestMapping(value = "/api/blogs/{blogId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get blog by blog id.")
    public ResponseEntity<BlogDto> getBlog(@PathVariable("blogId") final Integer id) {
    	log.info("Getting blog with blog id" + id );
    	final BlogDto blogDto = this.blogService.findBlogByBlogId(id);
    	if (blogDto == null) {
    		log.info("Blog not found with blog id " + id);
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	log.info("Blog exists. Returning the details.");
    	return new ResponseEntity<>(blogDto, HttpStatus.OK);
    }
    
    // REST service to update blog details
    @RequestMapping(value = "/api/blogs", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update blog.")
    public ResponseEntity<BlogDto> updateBlog(@RequestBody final BlogDto blogDto){
    	Preconditions.checkNotNull(blogDto);
    	log.info("Updating blog details with blog id " + blogDto.getId());
    	this.blogService.update(blogDto);
    	log.info("Blog details were update successfully.");
    	return new ResponseEntity<>(blogDto, HttpStatus.OK);
    }
    
    // REST service to get all blogs
    @RequestMapping(value = "/api/blogs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all blogs.")
    public ResponseEntity<List<BlogDto>> geAllBlogs(){
    	log.info("Getting all blogs.");
    	final List<BlogDto> blogs = this.blogService.findAllBlogs();
    	Collections.sort(blogs);
    	Collections.reverse(blogs);
    	log.info("Total number of blogs found " + blogs.size());
    	return new ResponseEntity<>(blogs,HttpStatus.OK);
    }
    
    // REST service to delete blog with the specified id
    @RequestMapping(value = "/api/blogs/{blogId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete blog by blog id.")
    public ResponseEntity<String> deleteBlog(@PathVariable("blogId") final Integer id){
    	
    	log.info("finding blog details with blog id "+id);
    	if(this.blogService.delete(id)) {
        	log.info("Blog exists. Deleting blog");
    		return new ResponseEntity<>(HttpStatus.ACCEPTED);
    	}
    	log.info("Could not find blog");
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
    @RequestMapping(value = "api/blogs/userId/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete blogs by user id.")
    public ResponseEntity<String> deleteBlogsByUserId(@PathVariable("userId") final String id){
    	log.info("finding blogs from user with id: " + id);
    	int blogsDeleted = this.blogService.deleteByUserId(id);
    	if(blogsDeleted > 0) {
    		log.info(blogsDeleted + " have been deleted.");
    		return new ResponseEntity<>(HttpStatus.ACCEPTED);
    	}
    	log.info("no blogs were found");
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
}
