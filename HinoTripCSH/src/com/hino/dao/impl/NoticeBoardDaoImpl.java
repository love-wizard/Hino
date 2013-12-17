package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.NoticeBoardDao;
import com.hino.model.Booking;
import com.hino.model.Message;
import com.hino.model.NoticeBoard;
import com.hino.model.Route;
import com.hino.model.WebMenuRoute;

public class NoticeBoardDaoImpl implements NoticeBoardDao{
private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	

	@Override
	public void save(NoticeBoard content) {
		ht.save(content);
	}

	@Override
	public List<NoticeBoard> getLastNotice() {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select * from NoticeBoard order by startDate desc;";
        		Query query = session.createSQLQuery(hql).addEntity("nb", NoticeBoard.class);       		
                return query.list();
            }
			
        });
	}

	@Override
	public int getCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from NoticeBoard"));
	}

	@Override
	public List<NoticeBoard> getNoticeForToday(final Calendar c) {
		return ht.executeFind(
        	new HibernateCallback< List<NoticeBoard>>() {
				public  List<NoticeBoard> doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery("from NoticeBoard where status=:status and :date between startDate and endDate");
					query.setParameter("status", "I");
					query.setParameter("date", c);
				return query.list();
				}
			
        });
	}

	@Override
	public List<NoticeBoard> getAllNotice() {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select * from NoticeBoard";
        		Query query = session.createSQLQuery(hql).addEntity("nb", NoticeBoard.class);       		
                return query.list();
            }
			
        });
	}

	@Override
	public void delete(NoticeBoard b) {
		ht.delete(b);
	}

	@Override
	public void update(NoticeBoard b) {
		ht.update(b);
	}

	@Override
	public NoticeBoard getNoticeById(long id) {
		return ht.get(NoticeBoard.class, id);
	}

	@Override
	public void cancel(NoticeBoard b) {
		ht.update(b);
	}

	@Override
	public List<NoticeBoard> getNoticesByStartEndDate(final Calendar startDate, final Calendar endDate) {
		return ht.executeFind(
	        	new HibernateCallback< List<NoticeBoard>>() {
					public  List<NoticeBoard> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from NoticeBoard where status=:status and (startDate between :start and :end) or (endDate between :start and :end) and startDate!= :start and startDate != :end and endDate != :start and endDate != :end");
						query.setParameter("status", "I");
						query.setParameter("start", startDate);
						query.setParameter("end", endDate);
					return query.list();
					}
				
	        });
	}

}
