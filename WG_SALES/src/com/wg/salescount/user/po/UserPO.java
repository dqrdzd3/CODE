package com.wg.salescount.user.po;

public class UserPO {

	public UserPO() {
		super();
	}

	private String userId;
	private String userName;
	private String name;
	private String password;
	private int status;
	private int competence;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCompetence() {
		return competence;
	}
	public void setCompetence(int competence) {
		this.competence = competence;
	}
	
	@Override
	public String toString() {
		return "UserPO [userId=" + userId + ", userName=" + userName + ", name=" + name + ", password=" + password
				+ ", status=" + status + ", competence=" + competence + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + competence;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + status;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPO other = (UserPO) obj;
		if (competence != other.competence)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (status != other.status)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	public UserPO(String userId, String userName, String name, String password, int status, int competence) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.status = status;
		this.competence = competence;
	}
	
	
}
