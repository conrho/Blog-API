package net.atos.wl.blog.data.dao;

import java.util.List;

import net.atos.wl.blog.data.entity.Rate;

public interface RateDAO extends GenericDAO<Rate>{

	Rate findRateByRateId(final Integer id);
	boolean deleteRateByRateId(final Integer id);
	List<Rate> findRateByBlogId(final Integer blogId);
	Rate findRateByBlogIdAndUserId(final Integer blogId, final String idUser);
	int deleteRatesByUserId(final String idUser);
	List<Rate> findRateByUserId(final String idUser);
}
