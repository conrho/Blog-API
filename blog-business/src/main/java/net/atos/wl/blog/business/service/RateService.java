package net.atos.wl.blog.business.service;

import java.util.List;

import net.atos.wl.blog.common.dto.RateDto;
import net.atos.wl.blog.data.entity.Rate;

public interface RateService {
	
	RateDto create(final RateDto rateDto);
	
	RateDto read(final Integer id);
	
	void update(final RateDto rateDto);
	
	void delete(final Integer id);
	
	RateDto findRateByRateId(final Integer id);
	
	List<RateDto> findRateByBlogId(final Integer blogId);
	
	RateDto findRateByBlogIdAndUserId(final Integer blogId, final String idUser);
	
	int deleteRatesByUserId(String idUser);
	
	List<RateDto> findRateByUserId(String idUser); 
}
