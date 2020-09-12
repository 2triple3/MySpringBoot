package com.springboot.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.common.entity.SysMenu;
import com.springboot.common.utils.HttpResult;
import com.springboot.system.service.SysMenuService;

/**
 * 菜单控制器
 */
@CrossOrigin
@RestController
@RequestMapping("menu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuServiceImpl;

	@PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysMenu record) {
		return HttpResult.ok(sysMenuServiceImpl.save(record));
	}

	@PreAuthorize("hasAuthority('sys:menu:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysMenu> records) {
		return HttpResult.ok(sysMenuServiceImpl.delete(records));
	}

	//	@PreAuthorize("hasAuthority('sys:menu:view')")
	@CrossOrigin
	@GetMapping(value = "/findNavTree")
	public HttpResult findNavTree(@RequestParam String userName) {
		return HttpResult.ok(sysMenuServiceImpl.findTree(userName, 1));
	}

	@PreAuthorize("hasAuthority('sys:menu:view')")
	@CrossOrigin
	@GetMapping(value = "/findMenuTree")
	public HttpResult findMenuTree() {
		 return HttpResult.ok(sysMenuServiceImpl.findTree(null, 0));
	}
}
