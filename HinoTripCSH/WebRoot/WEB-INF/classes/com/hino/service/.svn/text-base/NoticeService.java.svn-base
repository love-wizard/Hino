package com.hino.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hino.dao.NoticeBoardDao;
import com.hino.dto.NoticeBoardDto;
import com.hino.dto.SearchResultDto;
import com.hino.model.Booking;
import com.hino.model.NoticeBoard;
import com.hino.util.ServiceResponse;

public interface NoticeService {

	public void DeleteNotice(NoticeBoard b);
	public ServiceResponse DeleteNotice(long id);
	public ServiceResponse CancelNotice(long id);
	public NoticeBoard GetLastNoticeBoard();
	public NoticeBoard GetNoticeBoardForToday();
	public List<NoticeBoard> GetAllNoticeBoards();
	public boolean CheckNoticeBoard(Calendar a, Calendar b);
	public void SaveNoticeBoard(String Content, Calendar startDate, Calendar endDate, String link);
	public int GetCount();
}
