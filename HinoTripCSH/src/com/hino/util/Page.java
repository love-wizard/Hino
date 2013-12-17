package com.hino.util;

/**
 * 
 * @author Devon King
 *
 */
public class Page {

	private int curPage;

	private int size;

	private long totalCount;
		 

	public int getCurrentPage() {
		return curPage;
	}

	public void setCurrentPage(int curPage) {
		this.curPage = curPage;
	}
	
	public long getTotalPage(){
		if (totalCount % size > 0) {
			return totalCount / size + 1;
		} else {
			return totalCount / size;
		}
	}
	
	public long getNextPage(){
		
		if (curPage < 1) {
			return 1;
		}else if(curPage >= getTotalPage()){
			return getTotalPage();
		}	
		
		return curPage + 1;
	}
	
	public long getPreviousPage(){
		if (curPage <= 1) {
			return 1;
		}else if(curPage > getTotalPage()){
			return getTotalPage();
		}	
		
		return curPage - 1;
	}
	
	public boolean isLastPage(int pageNo){
		if (pageNo < 1 || pageNo > getTotalPage()) {
			return false;
		}		
		
		if (pageNo * size <= totalCount && (pageNo - 1) * size < totalCount){
			return true;
		}else{
			return false;
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

}
