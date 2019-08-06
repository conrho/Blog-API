package net.atos.wl.blog.data.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import net.atos.wl.blog.data.entity.Rate;

@Repository
public class RateDAOImpl extends AbstractJpaDAO<Rate> implements RateDAO{

	public RateDAOImpl() {
		this.setClazz(Rate.class);
	}

	@Override
	public Rate findRateByRateId(Integer id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Rate.fetchRateByRateId");
		query.setParameter("id", id);
		final List<Rate> rates = (List<Rate>) query.getResultList();
		if(!CollectionUtils.isEmpty(rates)) {
			return rates.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteRateByRateId(Integer id) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Rate.deleteRateByRateId");
		query.setParameter("id", id);
		if(query.executeUpdate() >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Rate> findRateByBlogId(Integer blogId) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Rate.fetchRateByBlogId");
		query.setParameter("blogId", blogId);
		final List<Rate> rates = (List<Rate>) query.getResultList();
		if(!CollectionUtils.isEmpty(rates)) {
			return rates;
		}
		return null;
	}

	@Override
	public Rate findRateByBlogIdAndUserId(Integer blogId, String idUser) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Rate.fetchRateByBlogIdAndUserId");
		query.setParameter("blogId", blogId);
		query.setParameter("idUser", idUser);
		final List<Rate> rates = (List<Rate>) query.getResultList();
		if(!CollectionUtils.isEmpty(rates)) {
			return rates.get(0);
		}
		return null;
	}

	@Override
	public int deleteRatesByUserId(String idUser) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Rate.deleteRateByUserId");
		query.setParameter("userId", idUser);
		return query.executeUpdate();
	}

	@Override
	public List<Rate> findRateByUserId(String idUser) {
		final Query query = this.createNamedQuery("net.atos.wl.blog.data.entity.Rate.fetchRateByUserId");
		query.setParameter("idUser", idUser);
		final List<Rate> rates = (List<Rate>) query.getResultList();
		if(!CollectionUtils.isEmpty(rates)) {
			return rates;
		}
		return null;
	}
}
