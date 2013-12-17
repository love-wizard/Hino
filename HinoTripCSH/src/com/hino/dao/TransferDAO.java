package com.hino.dao;

import java.util.Calendar;
import java.util.List;

import com.hino.model.Transfer;

public interface TransferDAO {
	public Transfer getTransferById(long id);
	public Transfer findTransferById(long id);
	public void delete(Transfer t);
	public void update(Transfer t);
	public void save(Transfer t);
	public List<Transfer> complexSearch(Calendar date, int sid, long cid, Integer[] status, int page, int size, long tid, long pid);
	public long complexSearchCount(Calendar date, int sid, long cid, Integer[] status, long tid, long pid);
}
