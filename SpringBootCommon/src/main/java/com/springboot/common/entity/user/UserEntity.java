package com.springboot.common.entity.user;

import java.io.Serializable;

public class UserEntity implements Serializable {
	private String userid;
	private String username;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserEntity [userid=" + userid + ", username=" + username + "]";
	}

}
