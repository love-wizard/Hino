package com.hino.dao;

import java.util.List;

import com.hino.model.Message;

public interface MessageDAO {
	public void save(Message msg);
	public void delete(long id);
	public void update(Message msg);
	public Message getMessageById(long id);
	public List<Message> getMessageByRecieverId(long id);
	public List<Message> getPagedMsgByReceiverId(long id, final int page, final int size);
	public int getMsgCountByReceiverId(long receiverId);
}
