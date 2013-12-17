package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.AirlineBookingDAO;
import com.hino.model.AirlineBooking;
import com.hino.model.AirlineCatalog;
import com.hino.util.Info;

public class AirlineBookingDAOImpl implements AirlineBookingDAO {

	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void update(AirlineBooking ab) {
		ht.update(ab);
	}

	@Override
	public void save(AirlineBooking ab) {
		ht.save(ab);

	}

	@Override
	public void delete(long id) {
		AirlineBooking ab = new AirlineBooking();
		ab.setId(id);
		ht.delete(ab);
	}

	@Override
	public AirlineBooking getAirlineBookingById(long id) {
		return ht.get(AirlineBooking.class, id);
	}

	@Override
	public List<AirlineBooking> getAllAirlineBooking() {
		return ht.find("from AirlineBooking");
	}

	@Override
	public int getAllAirlineBookingCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from AirlineBooking"));
	}

	@Override
	public List<AirlineBooking> getAirlineBookingList(long status) {
		return ht.find("from AirlineBooking where status = " + status);
	}

	@Override
	public int getAirlineBookingListCount(long status) {
		return DataAccessUtils.intResult(ht.find("select count(*) from AirlineBooking where status = " + status));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineBooking> complexSearching(final int attrType, final String keyword,
			final int sid, final int payer_type_id, final int payment_method_id, final int[] status, final int size, final int page, final Calendar start, final Calendar end) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		StringBuffer hql = new StringBuffer("select distinct b from AirlineBooking b where 1=1 ");

        		switch (attrType)
        		{
	        		case 0: hql.append(" and b.bookingRef like :bookingref ");
	        		break;
	        		case 1: hql.append(" and b.group.id=:gid ");
	        		break;
	        		case 2: hql.append(" and b.group.name like :gname ");
	        		break;
	        		case 3: hql.append(" and b.email=:email "); 
	        		break;
	        		case 4: hql.append(" and b.chinese_name like :chinese_name "); 
	        		break;
	        		case 5: 
	        				String[] nm = keyword.trim().split(" ");
	        				if(nm.length==2)
	        				{
	        					hql.append(" and b.last_name like :last_name and b.first_name like :first_name "); 
	        				} else
	        				{
	        					hql.append(" and ( b.last_name like :last_name or b.first_name like :first_name ) ");
	        				}
	        		break;
	        		case 6: hql.append(" and b.phone like :phone "); 
	        		break;

	        		default: 
	        			break;
        		}
        		
        		if(sid!=-1)
        		{
        			hql.append(" and b.rep.id=:sid ");
        		}
        		if(payer_type_id!=-1)
        		{
        			hql.append(" and b.payer_type_id=:payer_type_id ");
        		}
        		if(payment_method_id!=-1)
        		{
        			hql.append(" and b.payment_method_id=:payment_method_id ");
        		}
        		
        		if(status!=null&&status.length>0)
        		{
        			hql.append(" and status in (:status) order by b.transfer_apply_date desc");
        		} 
        		
        		Query query = session.createQuery(hql.toString());
        		
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
	        				new ArrayList<AirlineBooking>();
	        			}
	        			
	        			query.setInteger("gid", gid);
	        		break;
	        		case 2: query.setString("gname", "%"+keyword+"%");
	        		break;
	        		case 3: query.setString("email", keyword);
	        		break;
	        		case 4: query.setString("chinese_name", "%"+keyword+"%");
	        		break;
	        		case 5: 
	        				String[] nm = keyword.trim().split(" ");
	        				if(nm.length==2)
	        				{
	        					query.setString("first_name", "%"+nm[0]+"%");
	        					query.setString("last_name", "%"+nm[1]+"%");
	        				} else
	        				{
	        					query.setString("first_name", "%"+nm[0]+"%");
	        					query.setString("last_name", "%"+nm[0]+"%");
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
        		
        		if(payer_type_id!=-1)
        		{
        			query.setInteger("payer_type_id", payer_type_id);
        		}
        		
        		if(payment_method_id!=-1)
        		{
        			query.setInteger("payment_method_id", payment_method_id);
        		}
        		
        		if(status!=null&&status.length>0)
        		{
        			String s = Arrays.toString(status).replace("[", "").replace("]", "") ;
        			query.setString("status", s);
        		}
        		
                return query.list();
            }
			
        });
		
	}

		
	@SuppressWarnings("unchecked")
	@Override
	public long complexSearchingCount(final int attrType, final String keyword,
			final int sid, final int payer_type_id, final int payment_method_id, final int[] status) {
		
        		StringBuffer hql = new StringBuffer("select count(*) from AirlineBooking b where 1=1 ");

        		switch (attrType)
        		{
	        		case 0: hql.append(" and b.bookingRef like " + "%"+keyword+"%");
	        		break;
//	        		case 1: hql.append(" and b.group.id=:gid ");
//	        		break;
//	        		case 2: hql.append(" and b.group.name like :gname ");
//	        		break;
	        		case 3: hql.append(" and b.email like " + "%"+keyword+"%");
	        		break;
	        		case 4: hql.append(" and b.chinese_name like " + "%"+keyword+"%");
	        		break;
	        		case 5: 
	        				String[] nm = keyword.trim().split(" ");
//	        				if(nm.length==2)
//	        				{
//	        					hql.append(" and b.last_name like “ + "%" + nm[0] + "%" + ” and b.first_name like” + "%" + nm[1] + "%"""""));
//	        				} else
//	        				{
	        					hql.append(" and ( b.last_name like " + "%"+nm[0]+"%" + " or b.first_name like " + "%"+nm[0]+"%" + ")");
//	        				}
	        		break;
	        		case 6: hql.append(" and b.phone  " + "%"+keyword+"%"); 
	        		break;

	        		default: 
	        			break;
        		}
        		
        		if(sid!=-1)
        		{
        			hql.append(" and b.rep.id = " + sid);
        		}
        		if(payer_type_id!=-1)
        		{
        			hql.append(" and b.payer_type_id = " + payer_type_id);
        		}
        		if(payment_method_id!=-1)
        		{
        			hql.append(" and b.payment_method_id = " + payment_method_id);
        		}
        		
        		if(status!=null&&status.length>0)
        		{
        			String s = Arrays.toString(status);
        			
        			hql.append(" and status in (" + s.replace("[", "").replace("]", "") + ") order by b.transfer_apply_date desc");
        		} 
        		
        		return DataAccessUtils.intResult(ht.find(hql.toString()));
		
	}


}
