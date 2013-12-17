package com.hino.dao;

import java.util.Calendar;
import java.util.List;

import com.hino.model.Booking;
import com.hino.model.NoticeBoard;

public interface NoticeBoardDao {
	public void delete(NoticeBoard b);
	public void update(NoticeBoard b);
	public void save(NoticeBoard content);
	public void cancel(NoticeBoard b);
	public List<NoticeBoard> getLastNotice();
	public List<NoticeBoard> getNoticeForToday(final Calendar c);
	public NoticeBoard getNoticeById(long id);
	public List<NoticeBoard> getAllNotice();
	public List<NoticeBoard> getNoticesByStartEndDate(final Calendar a,final Calendar b);
	public int getCount();
	
}
