package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.MessageDAO;
import com.hino.model.Message;

public class MessageDAOImpl implements MessageDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Message getMessageById(long id) {
		return ht.get(Message.class, id);
	}

	@Override
	public List<Message> getMessageByRecieverId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Message msg) {
		ht.save(msg);
	}

	@Override
	public void update(Message msg) {
		ht.update(msg);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getPagedMsgByReceiverId(final long receiverId, final int page, final int size) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Message where reciver_id = '"+receiverId+"'");
                query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
        });
	}

	@Override
	public int getMsgCountByReceiverId(final long receiverId) {
		return DataAccessUtils.intResult(ht.find("select count(*) from Message where reciver_id = '"+receiverId+"'"));
	}

}
