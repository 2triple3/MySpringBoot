package com.springboot.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.common.entity.SysDept;
import com.springboot.common.page.MybatisPageHelper;
import com.springboot.common.page.PageRequest;
import com.springboot.common.page.PageResult;
import com.springboot.system.dao.SysDeptDAO;
import com.springboot.system.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService {

	@Autowired
	private SysDeptDAO sysDeptDAO;

	@Override
	public int save(SysDept record) {
		if (record.getId() == null || record.getId() == 0) {
			return sysDeptDAO.insertSelective(record);
		}
		return sysDeptDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysDept record) {
		return sysDeptDAO.deleteByPrimaryKey(record.getId());
	}


	@Override
	public int delete(List<SysDept> records) {
		for (SysDept record : records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysDept findById(Long id) {
		return sysDeptDAO.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysDeptDAO);
	}

	@Override
	public List<SysDept> findTree() {
		List<SysDept> sysDepts = new ArrayList<>();
		List<SysDept> depts = sysDeptDAO.findAll();
		for (SysDept dept : depts) {
			if (dept.getParentId() == null || dept.getParentId() == 0) {
				dept.setLevel(0);
				sysDepts.add(dept);
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}

	private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
		for (SysDept sysDept : sysDepts) {
			List<SysDept> children = new ArrayList<>();
			for (SysDept dept : depts) {
				if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
					dept.setParentName(dept.getName());
					dept.setLevel(sysDept.getLevel() + 1);
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}

}
