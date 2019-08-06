package net.atos.wl.blog.web.controller;

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
import net.atos.wl.blog.business.service.CommentService;
import net.atos.wl.blog.common.dto.CommentDto;

@RestController
@Api(value = "/api/comments", tags = "Comments API")
public class CommentController {
	
	private static Logger log = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	// REST service to create comment and store it in db.
	@RequestMapping(value = "/api/comments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create new comment.")
	public ResponseEntity<CommentDto> createComment(@RequestBody final CommentDto commentDto){
		Preconditions.checkNotNull(commentDto);
		log.info("Adding a new comment with comment id"+commentDto.getId() + " and user id " + commentDto.getIdUser() );
		final CommentDto persistedCommentDto = this.commentService.create(commentDto);
		log.info("Comment details were added successfully.");
		return new ResponseEntity<>(persistedCommentDto, HttpStatus.CREATED);
	}
	
	// REST service to get comment by comment ID.
	@RequestMapping(value = "/api/comments/{commentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get comment by comment id.")
	public ResponseEntity<CommentDto> getComment(@PathVariable("commentId") final Integer id){
		log.info("Getting comment with comment id "+id);
		final CommentDto commentDto = this.commentService.findCommentByCommentId(id);
		if (commentDto != null) {
			log.info("Comment exists, returning detials");
			return new ResponseEntity<>(commentDto, HttpStatus.OK);
		}
		log.info("Comment not found with comment id "+id);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// REST service to update comment
	@RequestMapping(value = "/api/comments", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Comment")
	public ResponseEntity<CommentDto> updateComment(@RequestBody final CommentDto commentDto){
		Preconditions.checkNotNull(commentDto);
		log.info("Updating comment details with comment id "+commentDto.getId());
		this.commentService.update(commentDto);
		log.info("comment details were updated successfully");
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
	}
	
	// REST service to get a list of comments using blog id
	@RequestMapping(value = "/api/comments/blog/{blogId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get comments by blog id")
	public ResponseEntity<List<CommentDto>> getCommentByBlogId(@PathVariable("blogId") final Integer blogId){
		log.info("Getting comments from blog with id "+ blogId);
		final List<CommentDto> commentDtos = this.commentService.findCommentsByBlogId(blogId);
		if(commentDtos != null) {
			log.info("Comments found for blog with id " + blogId);
			return new ResponseEntity<>(commentDtos, HttpStatus.OK);
		}
		log.info("No comments found for blog with id "+blogId);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// REST service to delete comment by comment id
	@RequestMapping(value = "/api/comments/{commentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete comment by comment id")
	public ResponseEntity<String> deleteComment(@PathVariable("commentId") final Integer id){
		
		log.info("finding comment with id "+ id);
		final CommentDto commentDto = this.commentService.findCommentByCommentId(id);
		
		if(commentDto != null) {
			log.info("Comment found, deleting comment.");
			this.commentService.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		log.info("Comment not found with id "+id);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// REST service to delete comments by user id
	@RequestMapping(value = "/api/comments/userId/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete comments by user id")
	public ResponseEntity<String> deleteCommentByUserId(@PathVariable("userId") final String id){
		log.info("deleting comments by user "+id);
		final int commentsDeleted = this.commentService.deleteCommentsByUserId(id);
		if(commentsDeleted == 0) {
			log.info("no comments deleted");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info(commentsDeleted+" have been deleted");
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	// REST service to get the number of comments by a user
	@RequestMapping(value = "/api/comments/userCount/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get number of comments by user")
	public ResponseEntity<Integer> getNumberOfCommentsByUser(@PathVariable("userId") final String id){
		log.info("getting number of comments by "+id);
		final List<CommentDto> comments = this.commentService.findCommentsByUserId(id);
		if(comments == null) {
			log.info(" no comments found by "+id);
			return new ResponseEntity<>(0, HttpStatus.OK);
		}
		log.info(comments.size() + " comments found by "+id);
		return new ResponseEntity<>(comments.size(), HttpStatus.OK);
	}
	
}
