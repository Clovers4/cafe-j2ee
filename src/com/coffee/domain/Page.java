package com.coffee.domain;

import java.util.List;

/**
 * 一个VO：用于包装BeanList来传到前端进行分页显示
 * 
 * @author K
 *
 * @param <T>
 */
public class Page<T> {
	// 当前页
	private int currentPage;
	// 每页记录数
	private int pageSize;
	// 总记录数
	private int totalCount;
	// 数据集合
	private List<T> list;
	// url表示条件查询的条件(GET方式)
	private String url;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	// 设置总页数(计算得出)
	public int getTotalPage() {
		Double totalPage = Math.ceil((double) totalCount / pageSize);
		return totalPage.intValue();
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ",pageSize=" + pageSize + ",totalCount=" + totalCount
				+ ",totalPage=" + getTotalPage() + ",url=" + url + ",list=" + list +"]";
	}
	
	public String toStringDetail() {
		return "Page [currentPage=" + currentPage + ",pageSize=" + pageSize + ",totalCount=" + totalCount
				+ ",totalPage=" + getTotalPage() + ",url=" + url + ",list=" + list + "]";
	}
	
}