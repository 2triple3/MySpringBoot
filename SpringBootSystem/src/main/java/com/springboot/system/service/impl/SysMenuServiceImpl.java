package com.springboot.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.common.entity.SysMenu;
import com.springboot.system.dao.SysMenuDAO;
import com.springboot.system.dao.SysUserDAO;
import com.springboot.system.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDAO menudao;

	@Override
	public int save(SysMenu record) {
		if(record.getId() == null || record.getId() == 0) {
			return menudao.insertSelective(record);
		}
		if(record.getParentId() == null) {
			record.setParentId(0L);
		}
		return menudao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysMenu record) {
		return menudao.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysMenu> records) {
		for(SysMenu record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysMenu findById(Long id) {
		return menudao.selectByPrimaryKey(id);
	}

//	@Override
//	public PageResult findPage(PageRequest pageRequest) {
//		return MybatisPageHelper.findPage(pageRequest, menudao);
//	}
	
	@Override
	public List<SysMenu> findTree(String userName, int menuType) {
		List<SysMenu> MenuEntitys = new ArrayList<>();
		List<SysMenu> menus = findByUser(userName);
		for (SysMenu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(MenuEntitys, menu)) {
					MenuEntitys.add(menu);
				}
			}
		}
		MenuEntitys.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(MenuEntitys, menus, menuType);
		return MenuEntitys;
	}

	@Override
	public List<SysMenu> findByUser(String userName) {
		if(userName == null || "".equals(userName) || "admin".equalsIgnoreCase(userName)) {
			return menudao.findAll();
		}
		return menudao.findByUserName(userName);
	}

	private void findChildren(List<SysMenu> MenuEntitys, List<SysMenu> menus, int menuType) {
		for (SysMenu SysMenu : MenuEntitys) {
			List<SysMenu> children = new ArrayList<>();
			for (SysMenu menu : menus) {
				if(menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
				}
				if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
					menu.setParentName(SysMenu.getName());
					menu.setLevel(SysMenu.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			SysMenu.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType);
		}
	}

	private boolean exists(List<SysMenu> MenuEntitys, SysMenu SysMenu) {
		boolean exist = false;
		for(SysMenu menu:MenuEntitys) {
			if(menu.getId().equals(SysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	}
	
}
