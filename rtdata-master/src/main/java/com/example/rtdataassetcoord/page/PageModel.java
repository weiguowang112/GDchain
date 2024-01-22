package com.example.rtdataassetcoord.page;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> list;
	private long pageTotal;
	private long pageSize;
	private long pageIndex;
	private long total;

	public static <E> PageModel<E> transform(IPage<E> fromPage) {
		PageModel<E> pageModel = new PageModel<>();
		pageModel.setList(fromPage.getRecords());
		pageModel.setPageIndex(fromPage.getCurrent());
		pageModel.setPageSize(fromPage.getSize());
		pageModel.setPageTotal(fromPage.getPages());
		pageModel.setTotal(fromPage.getTotal());

		return pageModel;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(long pageTotal) {
		this.pageTotal = pageTotal;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
