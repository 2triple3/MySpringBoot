package com.springboot.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.common.entity.MenuEntity;
import com.springboot.common.utils.HttpResult;
import com.springboot.system.service.MenuService;

/**
 * 菜单控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private MenuService menuServiceImpl;
	
	//@PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody MenuEntity record) {
		return HttpResult.ok(menuServiceImpl.save(record));
	}

	//@PreAuthorize("hasAuthority('sys:menu:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<MenuEntity> records) {
		return HttpResult.ok(menuServiceImpl.delete(records));
	}

	//@PreAuthorize("hasAuthority('sys:menu:view')")
	@CrossOrigin
	@GetMapping(value="/findNavTree")
	public HttpResult findNavTree(@RequestParam String userName) {
		return HttpResult.ok(menuServiceImpl.findTree(userName, 1));
	}
	
	//@PreAuthorize("hasAuthority('sys:menu:view')")
	@GetMapping(value="/findMenuTree")
	public HttpResult findMenuTree() {
		return HttpResult.ok(menuServiceImpl.findTree(null, 0));
	}
}
