package com.hino.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hino.dao.NoticeBoardDao;
import com.hino.dao.TourSurveyDAO;
import com.hino.dto.NoticeBoardDto;
import com.hino.dto.SearchResultDto;
import com.hino.model.Group;
import com.hino.model.NoticeBoard;
import com.hino.service.NoticeService;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class NoticeServiceImpl implements NoticeService{
	private NoticeBoardDao noticeboarddao;
	
	public NoticeBoardDao getNoticeBoardDao() {
		return noticeboarddao;
	}

	public void setNoticeBoardDao(NoticeBoardDao noticeboarddao) {
		this.noticeboarddao = noticeboarddao;
	}
	@Override
	public NoticeBoard GetLastNoticeBoard() {
		return (NoticeBoard) noticeboarddao.getLastNotice().get(0);
	}
	@Override
	public int GetCount(){
		return noticeboarddao.getCount();
	}
	@Override
	public void SaveNoticeBoard(String content, Calendar startDate, Calendar endDate, String link) {
		NoticeBoard newNotice = new NoticeBoard();
		newNotice.setContent(content);
		newNotice.setStartDate(startDate);
		newNotice.setEndDate(endDate);
		newNotice.setStatus("I");
		newNotice.setLink(link);
		noticeboarddao.save(newNotice);
	}

	@Override
	public NoticeBoard GetNoticeBoardForToday() {
		Calendar realCalendar = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.set(realCalendar.get(Calendar.YEAR), realCalendar.get(Calendar.MONTH),
				realCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		if (noticeboarddao.getNoticeForToday(c).isEmpty()){
			NoticeBoard newNotice = new NoticeBoard();
			newNotice.setContent(Info.indexText);
			return newNotice;
		}else{
		return (NoticeBoard) noticeboarddao.getNoticeForToday(c).get(0);
		}
	}

	@Override
	public List<NoticeBoard> GetAllNoticeBoards() {
		return (List<NoticeBoard>) noticeboarddao.getAllNotice();
	}
	@Override
	public void DeleteNotice(NoticeBoard b) {
		noticeboarddao.delete(b);
	}

	@Override
	public ServiceResponse DeleteNotice(long id) {
		ServiceResponse sr = new ServiceResponse();
		NoticeBoard nb = noticeboarddao.getNoticeById(id);
		if(nb==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target notice was no longer avaliable!");
			return sr;
		} else 
		{
			noticeboarddao.delete(nb);
			sr.setSucc(true);
			return sr;
		} 
	
	}

	@Override
	public ServiceResponse CancelNotice(long id) {
		ServiceResponse sr = new ServiceResponse();
		NoticeBoard nb = noticeboarddao.getNoticeById(id);
		nb.setStatus("Cancelled");
		if(nb==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target notice was no longer avaliable!");
			return sr;
		} else 
		{
			noticeboarddao.cancel(nb);
			sr.setSucc(true);
			return sr;
		} 
	}

	@Override
	public boolean CheckNoticeBoard(Calendar a, Calendar b) {
		return noticeboarddao.getNoticesByStartEndDate(a, b).isEmpty();
	}
}
