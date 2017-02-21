package com.druid.config.filter;

import org.apache.shiro.web.filter.authc.AnonymousFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 匿名访问过滤器
 * 
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-24
 * @version 1.0.0
 */
public class ShiroAnonymousFilter extends AnonymousFilter {

	@Override
	protected boolean preHandle(ServletRequest req, ServletResponse resp) throws Exception {
		HttpServletResponse hresp = (HttpServletResponse) resp;
		hresp.setHeader("Access-State", "unlogin");
		return true;
	}

}
