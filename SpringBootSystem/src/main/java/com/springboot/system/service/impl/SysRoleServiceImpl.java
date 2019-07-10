package com.springboot.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.common.entity.SysMenu;
import com.springboot.common.entity.SysRole;
import com.springboot.common.entity.SysRoleMenu;
import com.springboot.common.page.ColumnFilter;
import com.springboot.common.page.MybatisPageHelper;
import com.springboot.common.page.PageRequest;
import com.springboot.common.page.PageResult;
import com.springboot.system.constants.SysConstants;
import com.springboot.system.dao.SysMenuDAO;
import com.springboot.system.dao.SysRoleDAO;
import com.springboot.system.dao.SysRoleMenuDAO;
import com.springboot.system.service.SysRoleService;



@Service
public class SysRoleServiceImpl  implements SysRoleService {

	@Autowired
	private SysRoleDAO sysRoleDAO;
	@Autowired
	private SysRoleMenuDAO sysRoleMenuDAO;
	@Autowired
	private SysMenuDAO sysMenuDAO;

	@Override
	public int save(SysRole record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysRoleDAO.insertSelective(record);
		}
		return sysRoleDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysRole record) {
		return sysRoleDAO.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysRole> records) {
		for(SysRole record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysRole findById(Long id) {
		return sysRoleDAO.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		if(columnFilter != null && columnFilter.getValue() != null) {
			return MybatisPageHelper.findPage(pageRequest, sysRoleDAO, "findPageByName", columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, sysRoleDAO);
	}

	@Override
	public List<SysRole> findAll() {
		return sysRoleDAO.findAll();
	}

	public SysRoleDAO getSysRoleMapper() {
		return sysRoleDAO;
	}

	public void setSysRoleMapper(SysRoleDAO sysRoleDAO) {
		this.sysRoleDAO = sysRoleDAO;
	}

	@Override
	public List<SysMenu> findRoleMenus(Long roleId) {
		SysRole sysRole = sysRoleDAO.selectByPrimaryKey(roleId);
		if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
			// 如果是超级管理员，返回全部
			return sysMenuDAO.findAll();
		}
		return sysMenuDAO.findRoleMenus(roleId);
	}

	@Transactional
	@Override
	public int saveRoleMenus(List<SysRoleMenu> records) {
		if(records == null || records.isEmpty()) {
			return 1;
		}
		Long roleId = records.get(0).getRoleId(); 
		sysRoleMenuDAO.deleteByRoleId(roleId);
		for(SysRoleMenu record:records) {
			sysRoleMenuDAO.insertSelective(record);
		}
		return 1;
	}

	@Override
	public List<SysRole> findByName(String name) {
		return sysRoleDAO.findByName(name);
	}
	
}
