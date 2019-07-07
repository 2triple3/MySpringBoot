package com.springboot.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.common.entity.MenuEntity;
import com.springboot.system.dao.MenuDAO;
import com.springboot.system.dao.UserDAO;
import com.springboot.system.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menudao;

	@Override
	public int save(MenuEntity record) {
		if(record.getId() == null || record.getId() == 0) {
			return menudao.insertSelective(record);
		}
		if(record.getParentId() == null) {
			record.setParentId(0L);
		}
		return menudao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(MenuEntity record) {
		return menudao.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<MenuEntity> records) {
		for(MenuEntity record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public MenuEntity findById(Long id) {
		return menudao.selectByPrimaryKey(id);
	}

//	@Override
//	public PageResult findPage(PageRequest pageRequest) {
//		return MybatisPageHelper.findPage(pageRequest, menudao);
//	}
	
	@Override
	public List<MenuEntity> findTree(String userName, int menuType) {
		List<MenuEntity> MenuEntitys = new ArrayList<>();
		List<MenuEntity> menus = findByUser(userName);
		for (MenuEntity menu : menus) {
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
	public List<MenuEntity> findByUser(String userName) {
		if(userName == null || "".equals(userName) || "admin".equalsIgnoreCase(userName)) {
			return menudao.findAll();
		}
		return menudao.findByUserName(userName);
	}

	private void findChildren(List<MenuEntity> MenuEntitys, List<MenuEntity> menus, int menuType) {
		for (MenuEntity MenuEntity : MenuEntitys) {
			List<MenuEntity> children = new ArrayList<>();
			for (MenuEntity menu : menus) {
				if(menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
				}
				if (MenuEntity.getId() != null && MenuEntity.getId().equals(menu.getParentId())) {
					menu.setParentName(MenuEntity.getName());
					menu.setLevel(MenuEntity.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			MenuEntity.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType);
		}
	}

	private boolean exists(List<MenuEntity> MenuEntitys, MenuEntity MenuEntity) {
		boolean exist = false;
		for(MenuEntity menu:MenuEntitys) {
			if(menu.getId().equals(MenuEntity.getId())) {
				exist = true;
			}
		}
		return exist;
	}
	
}
