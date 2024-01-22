package com.example.rtdataassetcoord.page;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rtdataassetcoord.common.HttpServletUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 默认分页参数构建
 * 
 * @author zhq
 */
public class PageFactory {

	/**
	 * 第几页（从1开始）
	 */
	private static final String PAGE_NO = "pageIndex";

	/**
	 * 每页大小（默认10）
	 */
	private static final String PAGE_SIZE = "pageSize";

	public static <T> Page<T> defaultPage() {
		int pageNo = 1;
		int pageSize = 10;

		HttpServletRequest request = HttpServletUtil.getRequest();

		// 第几页
		String pageNoParam = request.getParameter(PAGE_NO);
		if (StrUtil.isNotEmpty(pageNoParam)) {
			pageNo = Integer.parseInt(pageNoParam);
		}
		// 每页条数
		String pageSizeParam = request.getParameter(PAGE_SIZE);
		if (StrUtil.isNotEmpty(pageSizeParam)) {
			pageSize = Integer.parseInt(pageSizeParam);
		}
		return new Page<T>(pageNo, pageSize);
	}

}
