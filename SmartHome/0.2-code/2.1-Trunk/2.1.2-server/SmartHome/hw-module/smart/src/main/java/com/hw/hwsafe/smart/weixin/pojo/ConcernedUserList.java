package com.hw.hwsafe.smart.weixin.pojo;

import java.util.List;
//关注者列表
public class ConcernedUserList {
	private String total; //用户总数
	private String count;//拉去的个数
	
	private List<String> users; //所有用户
	private String next_userid; //下一个用户id
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public String getNext_userid() {
		return next_userid;
	}
	public void setNext_userid(String next_userid) {
		this.next_userid = next_userid;
	}
	
}
