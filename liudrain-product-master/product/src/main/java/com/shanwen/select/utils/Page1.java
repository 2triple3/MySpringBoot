package com.shanwen.select.utils;

import java.util.List;

/**   
* @ClassName: Page.java 
* @Package com.pms.tool 
* @Description: TODO 
* @author 杨小琪  
* @date 2018年5月9日 上午10:14:24 
*   
*/

/**
 * 定义一个分页对象
 * 
 * @author acer
 *
 */
public class Page1 {
	private Integer current;// 当前页
	private Integer totalPage;// 总共有多少页码数
	private Integer pageSize;// 每页的数量
	private Integer start;// 每页的起始页码数
	private Integer end;// 每页的终止页码数
	private Integer totalCount;// 数据总数

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getTotalPage() {
		if (totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = totalCount / pageSize + 1;
		}

		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		start = (current - 1) * pageSize;
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		end = start + pageSize;
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "current=" + current + " totalPage=" + totalPage + " pageSize=" + pageSize + " start=" + start + " end="
				+ end + " totalCount=" + totalCount;
	}

}
