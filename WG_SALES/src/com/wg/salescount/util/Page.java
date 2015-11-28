package com.wg.salescount.util;

public class Page {
	
	private int currentPage;
	private int totalPage;
	private int total;
	private int size;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		setTotalPage();
		return totalPage;
	}
	public void setTotalPage() {
		if(this.size == 0){
			this.size = 1;
		}
		if(this.total%this.size == 0){
			this.totalPage = this.total/this.size;
		}else{
			this.totalPage = this.total/this.size+1;
		}
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
