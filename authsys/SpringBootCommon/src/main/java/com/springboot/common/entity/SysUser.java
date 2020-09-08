package com.springboot.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class SysUser extends BaseModel implements Serializable  {
	private String userid;
	
    private String username;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private Byte status;

    private Long deptId;
    
    private String deptName;
    
    
    private Byte delFlag;
    
    private String roleNames;
    
    private List<SysUserRole> userRoles = new ArrayList<>();

	
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt(String salt) {
		this.salt = salt;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Byte getStatus() {
		return status;
	}


	public void setStatus(Byte status) {
		this.status = status;
	}


	public Long getDeptId() {
		return deptId;
	}


	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public Byte getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}


	public String getRoleNames() {
		return roleNames;
	}


	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}


	public List<SysUserRole> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(List<SysUserRole> userRoles) {
		this.userRoles = userRoles;
	}


	@Override
	public String toString() {
		return "UserEntity [userid=" + userid + ", username=" + username + ", password=" + password + "]";
	}


}
