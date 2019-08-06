package net.atos.wl.blog.web.controller;

import java.text.DecimalFormat;
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
import net.atos.wl.blog.business.service.RateService;
import net.atos.wl.blog.common.dto.RateDto;
import net.atos.wl.blog.web.controller.Processes.Processes;

@RestController
@Api(value = "/api/rates", tags = "Rates API")
public class RateController {
	
	private static Logger log = LoggerFactory.getLogger(RateController.class);
	
	@Autowired
	private RateService rateService;
	
	// REST service to create rating and storing it in db
	@RequestMapping(value = "/api/rates", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create new rate.")
	public ResponseEntity<RateDto> createRate(@RequestBody final RateDto rateDto){
		Preconditions.checkNotNull(rateDto);
		log.info("Adding a new rate with rate id "+rateDto.getId()+" and user id "+rateDto.getIdUser());
		final RateDto currentRateDto = this.rateService.findRateByBlogIdAndUserId(rateDto.getBlogId(), rateDto.getIdUser());
		if(currentRateDto != null) {
			rateDto.setId(currentRateDto.getId());
			log.info("rate already exists, updating rating fr record with id "+rateDto.getId());
			this.rateService.update(rateDto);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		final RateDto persistedRateDto = this.rateService.create(rateDto);
		log.info("Rate details were added successfully.");
		return new ResponseEntity<>(persistedRateDto, HttpStatus.CREATED);
	}
	
	// REST service to get rating from db
	@RequestMapping(value = "/api/rates/{rateId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get rate by id.")
	public ResponseEntity<RateDto> getRate(@PathVariable("rateId") final Integer id){
		log.info("Getting rate with rate id "+id);
		final RateDto rateDto = this.rateService.findRateByRateId(id);
		if (rateDto != null) {
			log.info("Rate exists, returning details");
			return new ResponseEntity<>(rateDto, HttpStatus.OK);
		}
		log.info("Rate not found with rate id"+id);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// REST service to update rate
	@RequestMapping(value = "/api/rates", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Update rate")
	public ResponseEntity<RateDto> updateRate(@RequestBody final RateDto rateDto){
		Preconditions.checkNotNull(rateDto);
		log.info("Updating rate details with rate id"+rateDto.getId());
		this.rateService.update(rateDto);
		log.info("Rate has been updated successfully");
		return new ResponseEntity<>(rateDto, HttpStatus.OK);
	}
	
	// REST service to get a list of ratings using blog id
	@RequestMapping(value = "/api/rates/blog/{blogId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get rates by blog id")
	public ResponseEntity<List<RateDto>> getRateByBlogId(@PathVariable("blogId") final Integer blogId){
		log.info("Getting ratings from blog with id "+blogId);
		final List<RateDto> rateDtos = this.rateService.findRateByBlogId(blogId);
		if(rateDtos != null) {
			log.info("Ratings found for blog with id "+blogId);
			return new ResponseEntity<>(rateDtos, HttpStatus.OK);
		}
		log.info("No ratings found for blog with id "+blogId);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// REST service to delete rating by rating id
	@RequestMapping(value = "/api/rates/{rateId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete rate by rate id")
	public ResponseEntity<String> deleteRating(@PathVariable("rateId") final Integer id){
		
		log.info("finding rates with id "+ id);
		final RateDto rateDto = this.rateService.findRateByRateId(id);
		
		if(rateDto != null) {
			log.info("Rating found, deleting rating");
			this.rateService.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		log.info("Rating not found with id "+id);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// REST service to get average rating of a blog
	@RequestMapping(value = "/api/rates/avg/{blogId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get average rate by blog id")
	public ResponseEntity<Double> getAvgRateByBlogId(@PathVariable("blogId") final Integer blogId){
		log.info("Getting ratings from blog with id "+blogId);
		final List<RateDto> rateDtos = this.rateService.findRateByBlogId(blogId);
		if(rateDtos != null) {
			log.info(rateDtos.size() + " ratings found for blog with id "+blogId);
			
			double total = 0;
			for(RateDto rate: rateDtos) {
				total += rate.getRate();
			}
			Double average = (double) (total / rateDtos.size());
			average = Processes.round(average, 1);
			return new ResponseEntity<>(average, HttpStatus.OK);
		}
		log.info("No ratings found for blog with id "+blogId);
		return new ResponseEntity<>(0.0, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/api/rates/user/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "delete rates by userId")
	public ResponseEntity<String> deleteRatesByUserId(@PathVariable("userId") final String idUser){
		log.info("deleting rates from user "+idUser);
		
		final int numDeleted = this.rateService.deleteRatesByUserId(idUser);
		log.info(numDeleted+" ratings have been deleted");
		if(numDeleted == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/api/rates/userCount/{idUser}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get number of ratings by id user")
	public ResponseEntity<Integer> getNumberOfRatingsByUserId(@PathVariable("idUser") final String idUser){
		log.info("getting rate count for "+idUser);
		final List<RateDto> rates = this.rateService.findRateByUserId(idUser);
		if(rates == null) {
			log.info("no rates found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info(rates.size()+" rates found");
		return new ResponseEntity<>(rates.size(), HttpStatus.OK);
	}
	
}
