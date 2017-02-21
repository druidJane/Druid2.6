package com.druid.config.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权验证访问过滤器
 * 
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-24
 * @version 1.0.0
 */
public class ShiroAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hresp = (HttpServletResponse) resp;
		Subject subject = SecurityUtils.getSubject();

		boolean isPermitted = false;
		boolean isAuthenticated = subject.isAuthenticated();
		/*if (isAuthenticated) {
			String url = hreq.getPathInfo();
			if (StringUtils.isBlank(url)) {
				url = hreq.getServletPath();
			}
			if (StringUtils.isBlank(url)) {
				System.out.println("url is blank!");
				return false;
			}

			if (SessionUtil.isSessionTimeout()) {// session过期，超时登录
				SecurityUtils.getSubject().logout();
				return false;
			}

			if (!url.equals("/common/unReadNoticeCount")) {// 读取未读消息的请求不计算session时间
				SessionUtil.newRequestTimer();
			}

			isPermitted = subject.isPermitted(url);
			if (!isPermitted) {
				hresp.setHeader(Constants.HEADER_ACCESS_STATE, "unauthorized");
			}
		} else {
			hresp.setHeader(Constants.HEADER_ACCESS_STATE, "unlogin");
		}*/
		hresp.setHeader("Access-State", "unlogin");
		return isPermitted;
	}

}
