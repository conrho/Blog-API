package net.atos.wl.blog.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.wl.blog.business.mapper.ObjectMapper;
import net.atos.wl.blog.common.dto.RateDto;
import net.atos.wl.blog.data.dao.RateDAO;
import net.atos.wl.blog.data.entity.Rate;

@Service
@Transactional
public class RateServiceImpl implements RateService {

	@Autowired
	private RateDAO rateDAO;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public RateDto create(RateDto rateDto) {
		final Rate rate = this.getObjectMapper().map(rateDto, Rate.class);
		this.getRateDAO().create(rate);
		return this.getObjectMapper().map(rate, RateDto.class);
	}

	@Override
	public RateDto read(Integer id) {
		final Rate rate = this.getRateDAO().read(id);
		if(rate != null) {
			return this.getObjectMapper().map(rate, RateDto.class);
		}
		return null;
	}

	@Override
	public void update(RateDto rateDto) {
		final Rate rateFromDb = this.getRateDAO().read(rateDto.getId());
		this.getObjectMapper().map(rateDto, rateFromDb);
		this.getRateDAO().update(rateFromDb);
	}

	@Override
	public void delete(Integer id) {
		this.getRateDAO().deleteRateByRateId(id);
	}

	@Override
	public RateDto findRateByRateId(Integer id) {
		final Rate rate = this.getRateDAO().findRateByRateId(id);
		if(rate != null) {
			return this.getObjectMapper().map(rate, RateDto.class);
		}
		return null;
	}

	@Override
	public List<RateDto> findRateByBlogId(Integer blogId) {
		final List<Rate> rates = this.getRateDAO().findRateByBlogId(blogId);
		if(rates != null && !rates.isEmpty()) {
			final List<RateDto> rateDtos = new ArrayList<>();
			for(final Rate rate : rates) {
				rateDtos.add(this.getObjectMapper().map(rate, RateDto.class));
			}
			return rateDtos;
		}
		return null;
	}
	
	@Override
	public RateDto findRateByBlogIdAndUserId(Integer blogId, String idUser) {
		final Rate rate = this.getRateDAO().findRateByBlogIdAndUserId(blogId, idUser);
		if(rate != null) {
			return this.getObjectMapper().map(rate, RateDto.class);
		}
		return null;
	}	
	
	@Override
	public int deleteRatesByUserId(String idUser) {
		return this.rateDAO.deleteRatesByUserId(idUser);
	}
	
	@Override
	public List<RateDto> findRateByUserId(String idUser) {
		final List<Rate> rates = this.getRateDAO().findRateByUserId(idUser);
		if(rates != null && !rates.isEmpty()) {
			final List<RateDto> rateDtos = new ArrayList<>();
			for(final Rate rate : rates) {
				rateDtos.add(this.getObjectMapper().map(rate, RateDto.class));
			}
			return rateDtos;
		}
		return null;
	}

	/**
	 * @return the rateDAO
	 */
	public RateDAO getRateDAO() {
		return rateDAO;
	}

	/**
	 * @param rateDAO the rateDAO to set
	 */
	public void setRateDAO(RateDAO rateDAO) {
		this.rateDAO = rateDAO;
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
