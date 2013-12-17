package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.TourSurveyDAO;
import com.hino.model.Site;
import com.hino.model.Staff;
import com.hino.model.TourSurvey;

public class TourSurveyDAOImpl implements TourSurveyDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	@Override
	
	public void delete(long id) {
		TourSurvey ts = new TourSurvey();
		ts.setId(id);
		ht.delete(ts);

	}

	@Override
	public TourSurvey getTourSurveyById(long id) {
		return ht.get(TourSurvey.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourSurvey> list_survey_by_status(final int status) {
		
		return ht.executeFind(new HibernateCallback() {
			
					public  List<TourSurvey> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query;
						if(status==-1)
						{
							query = session.createQuery("from TourSurvey");
						} else
						{
							query = session.createQuery("from TourSurvey where status ="+status);
						}
						//Query query = session.createQuery("from TourSurvey where status ="+status); //temp no time todo
						
					return query.list();
					}
				});
	}

	@Override
	public void save(TourSurvey ts) {
		ht.save(ts);

	}

	@Override
	public void update(TourSurvey ts) {
		ht.update(ts);

	}

}
