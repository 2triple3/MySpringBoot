package com.springboot.system.service.impl;

/**
 * 
 */

import com.springboot.common.entity.SysMenu;
import com.springboot.common.entity.SysUser;
import com.springboot.common.entity.SysUserRole;
import com.springboot.common.page.PageRequest;
import com.springboot.common.page.PageResult;
import com.springboot.system.dao.SysUserDAO;
import com.springboot.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@org.springframework.stereotype.Service
public class SysUserServiceImpl implements com.springboot.system.service.SysUserService {
	
	@Autowired
	private SysUserDAO userdao;

	@Autowired
	private SysMenuService sysMenuService;


	@Override
	public List<SysUser> findUserInfoByUsername(String username) {
		return userdao.findUserInfoByUsername(username);
		// TODO Auto-generated method stub
	}

	@Override
	public int addUser(SysUser user) {
		return userdao.addUser(user);
	}

	@Override
	public void deleteUserByUsername(String username) {
		// TODO Auto-generated method stub
		userdao.deleteUserByUsername(username);
	}

	@Override
	public List<SysUser> findUser(SysUser userInfo) {
		// TODO Auto-generated method stub
		return userdao.findUser(userInfo);
	}


	@Override
	public int updateUser(SysUser user) {
		// TODO Auto-generated method stub
		return userdao.updateByPrimaryKeySelective(user);
	}




	@Override
	public int save(SysUser record) {
		return 0;
	}

	@Override
	public int delete(SysUser record) {
		return 0;
	}

	@Override
	public int delete(List<SysUser> records) {
		return 0;
	}

	@Override
	public SysUser findById(Long id) {
		return null;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return null;
	}


	@Override
	public SysUser findByName(String username) {
		return userdao.findByName(username);
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		return null;
	}

	@Override
	public Set<String> findPermissions(String userName) {
		Set<String> perms = new HashSet<>();
		List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
		for(SysMenu sysMenu:sysMenus) {
			if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
				perms.add(sysMenu.getPerms());
			}
		}
		return perms;
	}

}
