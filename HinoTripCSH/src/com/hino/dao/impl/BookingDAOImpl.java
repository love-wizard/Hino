package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.BookingDAO;
import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.util.Info;

public class BookingDAOImpl implements BookingDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	@Override
	public Booking findBookingById(long id) {
		return ht.get(Booking.class, id);
	}

	/*
	@SuppressWarnings("unchecked")
	@Override
	public Booking findBookingByRef(final String ref) {
		return DataAccessUtils.uniqueResult(ht.find("from Booking where bookingRef='"+ref+"'"));
	}
	*/
	@SuppressWarnings("unchecked")
	@Override
	public Booking findBookingByRef(final String ref) {
		return DataAccessUtils.uniqueResult(ht.find("from Booking where bookingRef='"+ref+"'"));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> findEffectiveBookingByVoucher(final String vcode) {
		return ht.find("from Booking where voucher = '" + vcode + "' and status <> " + Info.BKS_CANCELED);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> findBookingListByGid(int gid) {
		return ht.find("from Booking where group.id ='" +gid +"' order by status");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> findBookingListByGidAndSid(int gid, long sid) {
		return ht.find("from Booking where group.id ='" +gid +"' and rep.id='"+sid+"'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> findBookingListBySid(int sid) {
		return ht.find("from Booking where rep.id ='" +sid +"'");
	}

	@Override
	public void save(Booking b) {
		ht.save(b);
	}

	@Override
	public void update(Booking b) {
		ht.update(b);
	}

	@Override
	public List<Booking> findBookingListByGidAndStatus(int gid, int status) {
		return ht.find("from Booking where group.id ='" +gid +"' and status='" + status +"'");
	}

	@SuppressWarnings("unchecked")
	public List<Booking> complexSearching3(final int attrType, final String keyword,
			final int sid, final Integer[] status, final int size, final int page, final Calendar start, final Calendar end) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Criteria crit = session.createCriteria(Booking.class);
        		crit.setFetchMode("group", FetchMode.SELECT);
        		crit.addOrder(Order.desc("booking_time"));
        		//crit.setProjection(Projections.groupProperty("id"));
        		if(status!=null&&status.length>0)
        		{
        			crit.add(Restrictions.in( "status", status ));
        		} else
        		{
        			return new ArrayList<Booking>();
        		}
        		
        		if(sid!=-1)
        		{
        			crit.add(Restrictions.eq( "rep.id", sid ));
        		}
        		
        		if(size!=-1&&page!=-1)
        		{
        			crit.setFirstResult(page*size);
        			crit.setMaxResults(size);
        		}
        		
        		switch (attrType)
        		{
	        		case 0: crit.add(Restrictions.like("bookingRef", "%"+keyword+"%"));
	        		break;
	        		case 1:	crit.createAlias("group", "gp"); 
	        				//crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
	        				crit.add(Restrictions.eq("gp.id", Integer.parseInt(keyword)));
	        		break;
	        		case 2: crit.createAlias("group", "gp" , CriteriaSpecification.LEFT_JOIN); 
	        				//crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
	        				crit.add(Restrictions.like("gp.name", "%"+keyword+"%"));
	        		break;
	        		case 3: crit.add(Restrictions.eq("email", keyword));
	        		break;
	        		case 4: crit.add(Restrictions.like("chinesename", "%"+keyword+"%"));
	        		break;
	        		case 5: crit.add(Restrictions.or(Restrictions.like("firstname", "%"+keyword+"%"), Restrictions.like("lastname", "%"+keyword+"%")));
	        		break;
	        		case 6: crit.add(Restrictions.like("phone", "%"+keyword+"%"));
	        		break;
	        		case 7: crit.createAlias("group", "gp"); 
	        				//crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
	        				crit.add(Restrictions.between("gp.depart_date", start, end));
	        		break;
	        		default: 
	        			break;
        		}
        		
                return crit.list();
            }
			
        });
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> complexSearching(final int attrType, final String keyword,
			final int sid, final Integer[] status, final int size, final int page, final Calendar start, final Calendar end) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		StringBuffer hql = new StringBuffer("select distinct b from Booking b where ");
        		switch (attrType)
        		{
	        		case 0: hql.append(" b.bookingRef like :bookingref and ");
	        		break;
	        		case 1: hql.append(" b.group.id=:gid and ");
	        		break;
	        		case 2: hql.append(" b.group.name like :gname and ");
	        		break;
	        		case 3: hql.append(" b.email=:email and "); 
	        		break;
	        		case 4: hql.append(" b.chinesename like :chinesename and "); 
	        		break;
	        		case 5: 
	        				String[] nm = keyword.trim().split(" ");
	        				if(nm.length==2)
	        				{
	        					hql.append(" b.lastname like :lastname and b.firstname like :firstname and "); 
	        				} else
	        				{
	        					hql.append(" ( b.lastname like :lastname or b.firstname like :firstname ) and ");
	        				}
	        		break;
	        		case 6: hql.append(" b.phone like :phone and "); 
	        		break;

	        		default: 
	        			break;
        		}
        		
        		if(sid!=-1)
        		{
        			hql.append(" b.rep.id=:sid and ");
        		}
        		
        		if(status!=null&&status.length>0)
        		{
        			hql.append(" status in (:status) order by b.booking_time desc");
        		} else
        		{
        			return new ArrayList<Booking>();
        		}
        		
        		Query query = session.createQuery(hql.toString());
        		
        		if(size!=-1&&page!=-1)
        		{
        			query.setFirstResult(page*size);
        			query.setMaxResults(size);
        		}
        		
        		switch (attrType)
        		{
	        		case 0: query.setString("bookingref", "%"+keyword+"%");
	        		break;
	        		case 1: 
	        			Integer gid = 0;
	        			try {
	        				gid = Integer.parseInt(keyword);
	        			} catch (NumberFormatException nfe)
	        			{
	        				new ArrayList<Booking>();
	        			}
	        			
	        			query.setInteger("gid", gid);
	        		break;
	        		case 2: query.setString("gname", "%"+keyword+"%");
	        		break;
	        		case 3: query.setString("email", keyword);
	        		break;
	        		case 4: query.setString("chinesename", "%"+keyword+"%");
	        		break;
	        		case 5: 
	        				String[] nm = keyword.trim().split(" ");
	        				if(nm.length==2)
	        				{
	        					query.setString("firstname", "%"+nm[0]+"%");
	        					query.setString("lastname", "%"+nm[1]+"%");
	        				} else
	        				{
	        					query.setString("firstname", "%"+nm[0]+"%");
	        					query.setString("lastname", "%"+nm[0]+"%");
	        				}
	        		break;
	        		case 6: query.setString("phone", "%"+keyword+"%");
	        		break;

	        		default: 
	        			break;
        		}
        		
        		if(sid!=-1)
        		{
        			query.setInteger("sid", sid);
        		}
        		
        		if(status!=null&&status.length>0)
        		{
        			query.setParameterList("status", status);
        		}
        		
                return query.list();
            }
			
        });
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public long complexSearchingCount(final int attrType, final String keyword,
			final int sid, final Integer[] status) {
		return ht.execute(new HibernateCallback() {
        	@Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
        		Criteria crit = session.createCriteria(Booking.class);
        		crit.setFetchMode("group", FetchMode.SELECT);
        		
        		if(status!=null&&status.length>0)
        		{
        			crit.add(Restrictions.in( "status", status ));
        		} else
        		{
        			return new Long(0);
        		}
        		
        		if(sid!=-1)
        		{
        			crit.add(Restrictions.eq( "rep.id", sid ));
        		}
        		switch (attrType)
        		{
        		case 0: crit.add(Restrictions.like("bookingRef", "%"+keyword+"%"));
        		break;
        		case 1: crit.createAlias("group", "gp");
        				//crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
        				crit.add(Restrictions.eq("gp.id", Integer.parseInt(keyword)));
        		break;
        		case 2: crit.createAlias("group", "gp");
        				//crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
        				crit.add(Restrictions.like("gp.name", "%"+keyword+"%"));
        		break;
        		case 3: crit.add(Restrictions.eq("email", keyword));
        		break;
        		case 4: crit.add(Restrictions.like("chinesename", "%"+keyword+"%"));
        		break;
        		case 5: crit.add(Restrictions.or(Restrictions.like("firstname", "%"+keyword+"%"), Restrictions.like("lastname", "%"+keyword+"%")));
        		break;
        		case 6: crit.add(Restrictions.like("phone", "%"+keyword+"%"));
        		break;
        		default: 
        			break;
        		}
        		
                return (Long)crit.setProjection(Projections.rowCount()).uniqueResult();
            }
			
        });
		
	}

	@Override
	public Booking findBookingByAuth(String auth) {
		return DataAccessUtils.uniqueResult(ht.find("from Booking where ticket_auth='"+auth+"'"));
	}

	@Override
	public int countGroupBooking(int gid, int status) {
		return DataAccessUtils.intResult(ht.find("select count(*) from Booking where group.id="+gid+" and status =" +status));
	}

	@Override
	public void changeBookingStatusForGroup(int gid, int statusfrom, int statusto) {
		ht.bulkUpdate("update Booking bki set bki.status ="+statusto+" where bki.group.id = "+gid+" and bki.status = "+statusfrom);
		
	}

	@Override
	public List<Booking> findBookingListByTid(long tid) {
		return ht.find("from Booking where transfer.id ='" +tid +"'");
	}

	@Override
	public List<Booking> findBookingListByCid(long cid) {
		return ht.find("from Booking where customer.id ='" +cid +"'");
	}

	@Override
	public List<Booking> findBookingListForReward(final int status,final int sid,final Calendar start,final Calendar end) {
		return ht.executeFind(
				new HibernateCallback< List<Booking>>() {
					public  List<Booking> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Booking where status=:status and rep.id = :sid and group.depart_date between :start and :end");
						query.setParameter("status", status);
						query.setParameter("sid", sid);
						query.setParameter("start", start);
						query.setParameter("end", end);
					return query.list();
					}
				});
	}
	
	@Override
	public List<Booking> findBookingListForReward(final int status,final int sid, final int gid) {
		return ht.executeFind(
				new HibernateCallback< List<Booking>>() {
					public  List<Booking> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Booking where status=:status and rep.id = :sid and group.id= :gid");
						query.setParameter("status", status);
						query.setParameter("sid", sid);
						query.setParameter("gid", gid);
					return query.list();
					}
				});
	}

	@Override
	public void delete(Booking b) {
		ht.delete(b);
		
	}

	@Override
	public Booking getBookingById(long id) {
		return ht.get(Booking.class, id, LockMode.PESSIMISTIC_WRITE);
	}

	@Override
	public List<Booking> findGroupFeedbackBooking(final int gid) {
		return ht.find("from Booking where group.id="+gid+" and status =2 and feedback is not null");
	}

	@Override
	public List<Booking> findBookingForGroupTable(final int gid) {
		return ht.executeFind(
				new HibernateCallback< List<Booking>>() {
					public  List<Booking> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Booking where status=2 and group.id= :gid order by pickup");
						query.setParameter("gid", gid);
					return query.list();
					}
				});
	}

	@Override
	public int countGroupFeedbackBooking(int gid) {
		return DataAccessUtils.intResult(ht.find("select count(*) from Booking where group.id="+gid+" and status =2 and feedback is not null" ));
	}

	@Override
	public List<Booking> findBookingByEmail(final String email) {
		return ht.executeFind(
				new HibernateCallback< List<Booking>>() {
					public  List<Booking> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Booking where email= :email");
						query.setParameter("email", email);
					return query.list();
					}
				});
	}

	@Override
	public int count_unconfirmed_external_booking() {
		return DataAccessUtils.intResult(ht.find("select count(*) from Booking where status=0 and ( booking_method=1 or booking_method=2 or booking_method=3 ) "));
	}

	@Override
	public List<Booking> list_unconfirmed_external_booking() {
		return ht.executeFind(
				new HibernateCallback< List<Booking>>() {
					public  List<Booking> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Booking where status=0 and ( booking_method=1 or booking_method=2 or booking_method=3 ) ");
					return query.list();
					}
				});
	}

	@Override
	public boolean checkBooked(int gid, long cid) {
		int count = DataAccessUtils.intResult(ht.find("select count(*) from Booking where group.id="+gid+" and customer.id="+cid));
		return count>0;
	}

	@Override
	public List<Booking> list_sk_win(final int limit) {
		return ht.executeFind(
				new HibernateCallback< List<Booking>>() {
					public  List<Booking> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Booking where status=2 and book_type=2 order by booking_time desc");
						query.setMaxResults(limit);
					return query.list();
					}
				});
	}

	@Override
	public boolean checkBooked(int gid, String email) {
		int count = DataAccessUtils.intResult(ht.find("select count(*) from Booking where group.id="+gid+" and email = '"+email+"'"));
		return count>0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> findActiveBookingByGidAndType(int gid, int type) {
		return ht.find("from Booking where group.id= "+ gid + " and book_type = " + type + " and status in (0, 1, 2)");
	}
}