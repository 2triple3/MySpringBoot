package com.springboot.system.service;

import com.springboot.common.entity.SysUser;
import com.springboot.common.entity.SysUserRole;

import java.util.List;
import java.util.Set;


public interface SysUserService extends CurdService<SysUser>{
	public List<SysUser> findUserInfoByUsername(String userid);

	public int addUser(SysUser user);

	public void deleteUserByUsername(String username);

	public int updateUser(SysUser user);

	public List<SysUser> findUser(SysUser userInfo);


	SysUser findByName(String username);

	Set<String> findPermissions(String userName);

	List<SysUserRole> findUserRoles(Long userId);

}
