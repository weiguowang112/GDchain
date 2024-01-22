package com.example.rtdataassetcoord.common;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServlet工具类，获取当前request和response
 * 
 * @author zhq
 *
 */
public class HttpServletUtil {

	/**
	 * 获取当前请求的request对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (ObjectUtil.isNull(requestAttributes)) {
			throw new BusException(HttpStatus.INTERNAL_SERVER_ERROR.name());
		} else {
			return requestAttributes.getRequest();
		}
	}

	/**
	 * 获取当前请求的response对象
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (ObjectUtil.isNull(requestAttributes)) {
			throw new BusException(HttpStatus.INTERNAL_SERVER_ERROR.name());
		} else {
			return requestAttributes.getResponse();
		}

	}

}
